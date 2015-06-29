package hu.wodster.blogster.controller.blog;

import hu.wodster.blogster.common.core.UserAccount;
import hu.wodster.blogster.controller.blog.support.TagEditor;
import hu.wodster.blogster.controller.home.HomeController;
import hu.wodster.blogster.model.blog.Post;
import hu.wodster.blogster.service.blog.PostService;
import hu.wodster.blogster.service.blog.TagService;

import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

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
	public String get(@PathVariable("publicId") final String publicId,
			final Model model) {
		logger.debug("Querying post with publicId " + publicId);
		final Post post = postService.find(publicId);

		model.addAttribute("post", post);

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
	public String edit(@PathVariable("publicId") final String publicId,
			final Model model) {
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
	public String list(
			@RequestParam(value = "page", defaultValue = "1") final Integer page,
			final Model model) {
		logger.debug("Listing posts paginated");

		final Page<Post> postsPage = postService.list(page);

		final int current = postsPage.getNumber() + 1;
		final int begin = Math.max(1, current - 5);
		final int end = Math.min(begin + 10, postsPage.getTotalPages());

		model.addAttribute("posts", postsPage);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("archives", postService.getArchives());

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
	public String savePost(@ModelAttribute("post") final Post post,
			@AuthenticationPrincipal final UserAccount user) {
		logger.debug("Saving post");
		postService.save(user, post);
		return "redirect:/blog";
	}

	@RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
	public String listByTag(
			@PathVariable(value = "tagName") final String tagName,
			@RequestParam(value = "page", defaultValue = "1") final Integer page,
			final Model model) {
		logger.debug("Loading posts for tag " + tagName);

		final Page<Post> postsPage = postService.listByTag(tagName, page);

		final int current = postsPage.getNumber() + 1;
		final int begin = Math.max(1, current - 5);
		final int end = Math.min(begin + 10, postsPage.getTotalPages());

		model.addAttribute("posts", postsPage);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("archives", postService.getArchives());

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
	public String showArchive(
			@PathVariable(value = "archiveName") @DateTimeFormat(pattern = "yyyyMM") final Date date,
			@RequestParam(value = "page", defaultValue = "1") final Integer page,
			final Model model) {
		logger.debug("Loading posts for seach criteria");

		final Page<Post> posts = postService.findInDateArchive(date, page);

		final int current = posts.getNumber() + 1;
		final int begin = Math.max(1, current - 5);
		final int end = Math.min(begin + 10, posts.getTotalPages());

		model.addAttribute("posts", posts);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("archives", postService.getArchives());

		return "blog";
	}

}
