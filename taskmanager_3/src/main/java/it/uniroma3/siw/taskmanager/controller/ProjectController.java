package it.uniroma3.siw.taskmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.taskmanager.controller.session.SessionData;
import it.uniroma3.siw.taskmanager.controller.validation.ProjectValidator;
import it.uniroma3.siw.taskmanager.model.Credentials;
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

	@RequestMapping(value = { "/projects" }, method = RequestMethod.GET)
	public String myOwnedProjects(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		List<Project> projectsList = projectService.retrieveProjectsOwnedBy(loggedUser);
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("projectsList", projectsList);
		return "projects";

	}

	@RequestMapping(value = { "/project/{projectId}" }, method = RequestMethod.GET)
	public String project(Model model, @PathVariable Long projectId) {
		User loggedUser = sessionData.getLoggedUser();
		Project project = projectService.getProject(projectId);
		if (project == null)
			return "redirect:/projects";

		List<User> members = userService.getMembers(project);
		if (!project.getOwner().equals(loggedUser) && !members.contains(loggedUser))
			return "redirect:/projects";

		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("project", project);
		model.addAttribute("members", members);

		return "project";

	}

	@RequestMapping(value = { "/projects/add" }, method = RequestMethod.GET)
	public String createProjectForm(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("projectForm", new Project());
		return "addProject";

	}

	@RequestMapping(value = { "/projects/add" }, method = RequestMethod.POST)
	public String createProject(@Valid @ModelAttribute("projectForm") Project project,
			BindingResult projectBindingResult, Model model) {

		User loggedUser = sessionData.getLoggedUser();

		projectValidator.validate(project, projectBindingResult);
		if (!projectBindingResult.hasErrors()) {
			project.setOwner(loggedUser);
			this.projectService.saveProject(project);
			return "redirect:/projects/";
		}

		model.addAttribute("loggedUser", loggedUser);
		return "addProject";

	}
/*
	@RequestMapping(value = { "/project/{id}/edit" }, method = RequestMethod.POST)
	public String editProject(@Valid @ModelAttribute("projectForm") Project projectForm,
			BindingResult projectBindingResult, Model model, @PathVariable Long id) {
		// validate project fields
		this.projectValidator.validate(projectForm, projectBindingResult);

		Project project = projectService.getProject(id);
		Credentials visitor = sessionData.getLoggedCredentials();
		if (project != null) {
			if (!projectBindingResult.hasErrors()) {
				if (visitor.getRole().equals(ADMIN_ROLE)
						|| project.getOwner().getId().equals(visitor.getUser().getId())) {
					// Only admins/owners can edit project
					project.setName(projectForm.getName());
					project.setDescription(projectForm.getDescription());
					projectService.saveProject(project);
					sessionData.invalidate();
				}
				return "redirect:/project/" + id;
			}
			model.addAttribute("visitor", visitor);
			return "projectEdit";
		}
		return "redirect:/project/" + id;
	}*/

}
