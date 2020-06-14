package it.uniroma3.siw.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The MainController is a Spring Boot Controller to handle the generic
 * interactions with the home pages (e.g. index page), that do not refer to
 * specific entities
 */
@Controller
public class MainController {

	public MainController() {
	}

	/**
	 * This method is called when a GET request is sent by the user to URL "/" or
	 * "/index". This method prepares and dispatches the index view.
	 *
	 * @param model the Request model
	 * @return the name of the target view, that in this case is "index"
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
	
	
}