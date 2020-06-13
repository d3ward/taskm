package it.uniroma3.siw.taskmanager.controller;

import it.uniroma3.siw.taskmanager.controller.session.SessionData;
import it.uniroma3.siw.taskmanager.controller.validation.CredentialsValidator;
import it.uniroma3.siw.taskmanager.controller.validation.UserValidator;
import it.uniroma3.siw.taskmanager.model.Credentials;
import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.repository.UserRepository;
import it.uniroma3.siw.taskmanager.service.CredentialsService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The UserController handles all interactions involving User data.
 */
@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserValidator userValidator;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
    CredentialsValidator credentialsValidator;
	
	@Autowired
    CredentialsService credentialsService;

	@Autowired
	SessionData sessionData;

	/**
	 * This method is called when a GET request is sent by the user to URL
	 * "/users/user_id". This method prepares and dispatches the User registration
	 * view.
	 *
	 * @param model the Request model
	 * @return the name of the target view, that in this case is "register"
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		model.addAttribute("user", loggedUser);
		return "home";
	}

	/**
	 * This method is called when a GET request is sent by the user to URL
	 * "/users/user_id". This method prepares and dispatches the User registration
	 * view.
	 *
	 * @param model the Request model
	 * @return the name of the target view, that in this case is "register"
	 */
	@RequestMapping(value = { "/users/me" }, method = RequestMethod.GET)
	public String me(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		Credentials credentials = sessionData.getLoggedCredentials();
		System.out.println(credentials.getPassword());
		model.addAttribute("user", loggedUser);
		model.addAttribute("credentials", credentials);

		return "userProfile";
	}

	@RequestMapping(value = { "/users/me/update" }, method = RequestMethod.POST)
	public String meUpdate(@Valid @ModelAttribute("userForm") User user, BindingResult userBindingResult,
			@Valid @ModelAttribute("credentialsForm") Credentials credentials, BindingResult credentialsBindingResult,
			Model model) {

		String userName=credentials.getUserName();
		//String currentPassword = (sessionData.getLoggedCredentials()).getPassword();
		credentials.setUserName("update="+userName);
		this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);
		if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {

			//Nome e cognome 
			User currentUser = sessionData.getLoggedUser();
			currentUser.setFirstName(user.getFirstName());
			currentUser.setLastName(user.getLastName());
			
			//Password e username
			Credentials currentCredential = sessionData.getLoggedCredentials();
			currentCredential.setPassword(credentials.getPassword());
			currentCredential.setUserName(userName);
			
			credentialsService.saveCredentials(currentCredential);
			User loggedUser = sessionData.getLoggedUser();
			model.addAttribute("user", loggedUser);
			return "home";
		}

		return "updateProfile";
	}

	/** **/

	@RequestMapping(value = { "/users/me/update" }, method = RequestMethod.GET)
	public String updateProfile(Model model) {
		model.addAttribute("userForm", sessionData.getLoggedUser());
		model.addAttribute("credentialsForm", sessionData.getLoggedCredentials());
		return "updateProfile";
	}

	/**
	 * This method is called when a GET request is sent by the user to URL
	 * "/users/user_id". This method prepares and dispatches the User registration
	 * view.
	 *
	 * @param model the Request model
	 * @return the name of the target view, that in this case is "register"
	 */
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String admin(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		model.addAttribute("user", loggedUser);
		return "admin";
	}

}
