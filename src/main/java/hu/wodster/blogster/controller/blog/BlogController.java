package hu.wodster.blogster.controller.blog;

import hu.wodster.blogster.common.core.PageWrapper;
import hu.wodster.blogster.common.core.UserAccount;
import hu.wodster.blogster.controller.blog.support.TagEditor;
import hu.wodster.blogster.model.blog.Post;
import hu.wodster.blogster.service.blog.PostService;
import hu.wodster.blogster.service.blog.TagService;

import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles all requests for the application blog.
 *
 * @author Károly
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

	/**
	 * The logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

	/**
	 * Post manager service.
	 */
	@Autowired
	private PostService postService;

	/**
	 * Tag manager service.
	 */
	@Autowired
	private TagService tagService;

	/**
	 * Tagging editor service.
	 */
	@Autowired
	private TagEditor tagEditor;

	/**
	 * Initializes the {@link WebDataBinder} which will be used for populating
	 * command and form object arguments of annotated handler methods.
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "tags", tagEditor);
	}

	/**
	 * Adds a new blog post.
	 *
	 * @param post
	 *            injected post instance which will be used in the model
	 *            attributes
	 * @return view
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Post post) {
		logger.debug("Post add requested");
		return "blog/add";
	}

	/**
	 * Prepares the view for a single blog post display.
	 *
	 * @param publicId
	 *            blog post public id
	 * @param model
	 *            injected model attributes
	 * @return
	 */
	@RequestMapping(value = "{publicId}", method = RequestMethod.GET)
	public String get(@PathVariable("publicId") final String publicId, final Model model) {
		logger.debug("Querying post with publicId " + publicId);
		final Post post = postService.find(publicId);
		final Post previous = postService.findPrevious(post);
		final Post next = postService.findNext(post);

		addPostPageToModel(post, model);
		model.addAttribute("previousPost", previous);
		model.addAttribute("nextPost", next);

		return "blog/view";
	}

	/**
	 * Prepares the view for the blog post edition.
	 *
	 * @param publicId
	 *            blog post public id
	 * @param model
	 *            injected model attributes
	 * @return view
	 */
	@RequestMapping(value = "{publicId}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("publicId") final String publicId, final Model model) {
		logger.debug("Querying post with publicId " + publicId);
		final Post post = postService.find(publicId);

		model.addAttribute("post", post);

		return "blog/edit";
	}

	/**
	 * Prepares the view for the blog post listing.
	 *
	 * @param page
	 *            the current page number to be displayed, by default it is the
	 *            first
	 * @param model
	 *            injected model attributes
	 * @return view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") final Integer page, final Model model) {
		logger.debug("Listing posts paginated");

		final PageWrapper<Post> postsPage = new PageWrapper<Post>(postService.list(page));
		addPostPageToModel(postsPage, model);

		return "blog";
	}

	/**
	 * Saves or update a blog post entry into the application.
	 *
	 * @param post
	 *            post instance to be saved
	 * @param user
	 *            the authenticated user
	 * @return view
	 */
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String savePost(@ModelAttribute("post") final Post post, @AuthenticationPrincipal final UserAccount user,
			final Model model) {
		logger.debug("Saving post");
		final Post savedPost = postService.save(user, post);

		model.addAttribute("post", savedPost);

		return "redirect:/blog/" + savedPost.getPublicId();
	}

	@RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
	public String listByTag(@PathVariable(value = "tagName") final String tagName,
			@RequestParam(value = "page", defaultValue = "1") final Integer page, final Model model) {
		logger.debug("Loading posts for tag " + tagName);

		final PageWrapper<Post> posts = new PageWrapper<Post>(postService.listByTag(tagName, page));
		addPostPageToModel(posts, model);

		return "blog";
	}

	/**
	 * Prepares the view for the blog archive content listing.
	 *
	 * @param page
	 *            the current page number to be displayed, by default it is the
	 *            first
	 * @param model
	 *            injected model attributes
	 * @return view
	 */
	@RequestMapping(value = "/archive/{archiveName}", method = RequestMethod.GET)
	public String showArchive(@PathVariable(value = "archiveName") @DateTimeFormat(pattern = "yyyyMM") final Date date,
			@RequestParam(value = "page", defaultValue = "1") final Integer page, final Model model) {
		logger.debug("Loading posts for archive " + date);

		final PageWrapper<Post> posts = new PageWrapper<Post>(postService.findInDateArchive(date, page));
		addPostPageToModel(posts, model);

		return "blog";
	}

	/**
	 * Prepares the view for the blog post listing according to the search
	 * criteria.
	 *
	 * @param criteria
	 *            search criteria
	 * @param page
	 *            the current page number to be displayed, by default it is the
	 *            first
	 * @param model
	 *            injected model attributes
	 * @return view
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("criteria") final String criteria,
			@RequestParam(value = "page", defaultValue = "1") final Integer page, final Model model) {
		logger.debug("Loading posts for seach criteria " + criteria);

		final PageWrapper<Post> posts = new PageWrapper<Post>(postService.findByContent(criteria, page));
		addPostPageToModel(posts, model);
		model.addAttribute("criteria", criteria);

		return "blog";
	}

	/**
	 * Adds a single post to the model with all dependencies.
	 *
	 * @param post
	 * @param model
	 */
	private void addPostPageToModel(final Post post, final Model model) {
		model.addAttribute("post", post);
		addAsideDataToModel(model);
	}

	/**
	 * Adds multiple post to the model as a single page.
	 *
	 * @param posts
	 * @param model
	 */
	private void addPostPageToModel(final PageWrapper<Post> posts, final Model model) {
		model.addAttribute("posts", posts);
		addAsideDataToModel(model);
	}

	/**
	 * Adds all dependencies which should be displayed to the model.
	 *
	 * @param model
	 */
	private void addAsideDataToModel(final Model model) {
		model.addAttribute("archives", postService.getArchives());
		model.addAttribute("popularTags", tagService.findMostPopularTags());
	}
}
