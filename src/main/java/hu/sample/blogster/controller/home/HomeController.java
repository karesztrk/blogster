package hu.sample.blogster.controller.home;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(final Locale locale, final Model model) {
		return "redirect:/blog";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage() {
		return "redirect:/blog";
	}

}