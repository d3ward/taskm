package it.uniroma3.siw.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.taskmanager.model.Credentials;

@Controller
public class LoginController {
	
	
	@RequestMapping(value= {"/login"}, method= RequestMethod.GET)
	public String showLoginForm(Model model) {
		model.addAttribute("credentialsForm", new Credentials());
		return "login";
	}

}
