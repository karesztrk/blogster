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
	 * Displays the home of the application and returns with the view to be
	 * displayed.
	 *
	 * @param locale
	 *            used locale
	 * @param model
	 *            holder of model attributes
	 * @return view
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(final Locale locale, final Model model) {
		return "redirect:/blog";
	}

	/**
	 * Displays the index of the application and returns with the view to be
	 * displayed.
	 *
	 * @return view
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage() {
		return "redirect:/blog";
	}

}
