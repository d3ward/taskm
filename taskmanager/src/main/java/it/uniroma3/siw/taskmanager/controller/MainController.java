package it.uniroma3.siw.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.taskmanager.controller.session.SessionData;
import it.uniroma3.siw.taskmanager.model.Credentials;
import it.uniroma3.siw.taskmanager.model.User;

/**
 * The MainController is a Spring Boot Controller to handle the generic
 * interactions with the home pages (e.g. index page), that do not refer to
 * specific entities
 */
@Controller
public class MainController {
	
	public MainController() {
	}
	
	@Autowired
	SessionData sessionData;
	/**
	 * This method is called when a GET request is sent by the user to URL "/" or
	 * "/index". This method prepares and dispatches the index view.
	 *
	 * @param model the Request model
	 * @return the name of the target view, that in this case is "index"
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		try {
		User loggedUser = sessionData.getLoggedUser();
		if(loggedUser != null)model.addAttribute("isLogged", true);
		}catch(Exception e) {
			  //  Block of code to handle errors
		}
	
		return "index";
	}
	@RequestMapping(value= {"/login"}, method= RequestMethod.GET)
	public String showLoginForm(Model model) {
		model.addAttribute("credentialsForm", new Credentials());
		return "login";
	}
	
	
}