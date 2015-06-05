package hu.sample.blogster.controller.blog;

import hu.sample.blogster.controller.home.HomeController;
import hu.sample.blogster.model.blog.Post;
import hu.sample.blogster.service.blog.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		return "blog/add";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			Model model) {
		logger.debug("Listing posts paginated");

		Page<Post> postsPage = service.list(page);

		int current = postsPage.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, postsPage.getTotalPages());

		model.addAttribute("posts", postsPage);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return "blog";
	}

	// @AuthenticationPrincipal User user*/
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String savePost(@ModelAttribute("post") Post post) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		User user = (User) authentication.getPrincipal();
		service.save(user, post);
		return "redirect:/blog";
	}
}
