package hu.sample.blogster.controller.blog;

import hu.sample.blogster.common.core.UserAccount;
import hu.sample.blogster.controller.home.HomeController;
import hu.sample.blogster.model.blog.Post;
import hu.sample.blogster.service.blog.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class BlogController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private PostService service;

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() {
		logger.debug("Post add requested");
		return "blog/add";
	}

	@RequestMapping(value = "{publicId}", method = RequestMethod.GET)
	public String get(@PathVariable("publicId") final String publicId,
			final Model model) {
		logger.debug("Querying post with publicId " + publicId);
		final Post post = service.find(publicId);

		model.addAttribute("post", post);

		return "blog/view";
	}

	@RequestMapping(value = "{publicId}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("publicId") final String publicId,
			final Model model) {
		logger.debug("Querying post with publicId " + publicId);
		final Post post = service.find(publicId);

		model.addAttribute("post", post);

		return "blog/edit";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") final Integer page,
			final Model model) {
		logger.debug("Listing posts paginated");

		final Page<Post> postsPage = service.list(page);

		final int current = postsPage.getNumber() + 1;
		final int begin = Math.max(1, current - 5);
		final int end = Math.min(begin + 10, postsPage.getTotalPages());

		model.addAttribute("posts", postsPage);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return "blog";
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String savePost(@ModelAttribute("post") final Post post,
			@AuthenticationPrincipal final UserAccount user) {
		logger.debug("Saving post");
		service.save(user, post);
		return "redirect:/blog";
	}
}
