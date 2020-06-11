package it.uniroma3.siw.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.taskmanager.controller.session.SessionData;
import it.uniroma3.siw.taskmanager.controller.validation.ProjectValidator;
import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.service.ProjectService;
import it.uniroma3.siw.taskmanager.service.UserService;


@Controller
public class ProjectController {

		@Autowired
		ProjectService projectService;
		
		@Autowired
		UserService userService;
		@Autowired
		ProjectValidator projectValidator;
		@Autowired
		SessionData sessionData;
		
		@RequestMapping(value= {"/projects"},method=RequestMethod.GET)
		public String myOwnedProjects(Model model) {
			User loggedUser =sessionData.getLoggedUser();
			List<Project> projectsList = projectService.retrieveProjectsOwnedBy(loggedUser);
			model.addAttribute("loggedUser",loggedUser);
			model.addAttribute("projectsList",projectsList);
			return "myOwnedProjects";
			
		}
		@RequestMapping(value= {"/projects/{projectId"},method=RequestMethod.GET)
		public String project(Model model,@PathVariable Long projectId) {
			User loggedUser =sessionData.getLoggedUser();
			Project project = projectService.getProject(projectId);
			if(project == null) 
				return "redirect:/projects";
			
			List<User> members = userService.getMembers(project);
			if(!project.getOwner().equals(loggedUser) && !members.contains(loggedUser))
				return "redirect:/projects";
			
			model.addAttribute("loggedUser",loggedUser);
			model.addAttribute("project",project);
			model.addAttribute("members",members);
			
			return "project";

		}
		
}
