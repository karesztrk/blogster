package hu.sample.blogster.controller.entry;

import hu.sample.blogster.controller.HomeController;
import hu.sample.blogster.entity.entry.Post;
import hu.sample.blogster.service.entry.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/post")
public class PostController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private PostService service;

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
}
