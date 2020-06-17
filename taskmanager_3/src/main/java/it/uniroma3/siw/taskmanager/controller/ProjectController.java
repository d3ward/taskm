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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.taskmanager.controller.session.SessionData;
import it.uniroma3.siw.taskmanager.controller.validation.ProjectValidator;
import it.uniroma3.siw.taskmanager.controller.validation.TaskValidator;
import it.uniroma3.siw.taskmanager.model.Credentials;
import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.model.Tag;
import it.uniroma3.siw.taskmanager.model.Task;
import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.service.CredentialsService;
import it.uniroma3.siw.taskmanager.service.ProjectService;
import it.uniroma3.siw.taskmanager.service.TagService;
import it.uniroma3.siw.taskmanager.service.TaskService;
import it.uniroma3.siw.taskmanager.service.UserService;

@Controller
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	TagService tagService;

	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	@Autowired
	ProjectValidator projectValidator;
	@Autowired
	SessionData sessionData;

	@Autowired
	CredentialsService credentialsService;

	@Autowired
	TaskValidator taskValidator;

	@RequestMapping(value = { "/projects" }, method = RequestMethod.GET)
	public String myProjects(Model model) {
		User loggedUser = sessionData.getLoggedUser();

		List<Project> projectsList = projectService.retrieveProjectsOwnedBy(loggedUser);
		List<Project> memberOfProjects = projectService.retrieveProjectByMembers(loggedUser);

		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("projectsList", projectsList);
		model.addAttribute("memberOfProjects", memberOfProjects);

		return "projects";
	}

	@RequestMapping(value = { "/project/{projectId}" }, method = RequestMethod.GET)
	public String project(Model model, @PathVariable Long projectId) {
		User loggedUser = sessionData.getLoggedUser();
		Project project = projectService.getProject(projectId);

		if (project == null)
			return "redirect:/projects";
		User user = project.getOwner();
		List<User> members = project.getMembers();
		List<Tag> tags = project.getTags();
		if (!project.getOwner().equals(loggedUser) && !members.contains(loggedUser))
			return "redirect:/projects";

		model.addAttribute("tags",tags);
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("canEdit", user.getId() == loggedUser.getId());
		model.addAttribute("owner", user.getFirstName() + " " + user.getLastName());
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

	@RequestMapping(value = { "/project/{id}/share" }, method = RequestMethod.POST)
	public String shareProject(@RequestParam("username") String username, @PathVariable Long id) {
		Project project = projectService.getProject(id);
		Credentials credentials = this.credentialsService.getCredentials(username);

		if (credentials != null) {
			this.projectService.shareProjectWithUser(project, credentials.getUser());

			return "redirect:/project/" + id;
		}
		return "redirect:/home";
	}

	@RequestMapping(value = { "/project/{id}/delete" }, method = RequestMethod.GET)
	public String deleteProject(@PathVariable Long id) {

		Project project = projectService.getProject(id);

		this.projectService.deleteProject(project);
		return "redirect:/projects";
	}

	@RequestMapping(value = { "/project/{idP}/deleteTask/{id}" }, method = RequestMethod.GET)
	public String deleteTask(@PathVariable Long id, @PathVariable Long idP) {
		Task task = this.taskService.getTask(id);
		this.taskService.deleteTask(task);
		return "redirect:/project/" + idP;
	}

	@RequestMapping(value = { "/project/{idP}/completeTask/{id}" }, method = RequestMethod.GET)
	public String completeTask(@PathVariable Long id, @PathVariable Long idP) {
		Task task = this.taskService.getTask(id);
		task.setCompleted(!task.isCompleted());
		this.taskService.saveTask(task);
		return "redirect:/project/" + idP;
	}

	@RequestMapping(value = { "/project/{id}/edit" }, method = RequestMethod.GET)
	public String updateProject(Model model, @PathVariable Long id) {
		Project project = this.projectService.getProject(id);
		model.addAttribute("projectForm", project);
		return "updateProject";
	}

	@RequestMapping(value = { "/project/edit/{id}" }, method = RequestMethod.POST)
	public String editProject(@Valid @ModelAttribute("projectForm") Project projectForm,
			BindingResult projectBindingResult, Model model, @PathVariable Long id) {
		// validate project fields
		this.projectValidator.validate(projectForm, projectBindingResult);
		if (!projectBindingResult.hasErrors()) {
			Project project = projectService.getProject(id);
			project.setName(projectForm.getName());
			project.setDescription(projectForm.getDescription());
			this.projectService.saveProject(project);
			User loggedUser = sessionData.getLoggedUser();
			model.addAttribute("user", loggedUser);
			return "redirect:/project/ " + id;
		}

		return "updateProject";
	}

	@RequestMapping(value = { "/project/{id}/task" }, method = RequestMethod.POST)
	public String newTask(@Valid @ModelAttribute("taskForm") Task task, @PathVariable Long id,
			BindingResult taskBindingResult) {

		Project project = this.projectService.getProject(id);
		
		
		if (!taskBindingResult.hasErrors()) {
			task.setProject(project);
			this.taskService.saveTask(task);
			return "redirect:/project/" + id;

		}
		return "redirect:/projects";
	}
	
	
	@RequestMapping(value = { "/project/{idP}/editTask/{id}" }, method = RequestMethod.GET)
	public String updateTask(Model model, @PathVariable Long id, @PathVariable Long idP) {
		Task task = this.taskService.getTask(id);
		Project project = this.projectService.getProject(idP);
		List<User> members = project.getMembers();
		User loggedUser = sessionData.getLoggedUser();
		model.addAttribute("members",members);
		model.addAttribute("project",project);
		model.addAttribute("task",task);
		model.addAttribute("loggedUser",loggedUser);
		return "editTask";
	}
	
	@RequestMapping(value = { "/project/{idP}/taskEdit/{id}" }, method = RequestMethod.POST)
	public String editTask(@Valid @ModelAttribute("task") Task taskForm,
			BindingResult taskBindingResult, Model model, @PathVariable Long id,@PathVariable Long idP) {
		// validate project fields
		this.taskValidator.validate(taskForm, taskBindingResult);
		if (!taskBindingResult.hasErrors()) {
			Task task= taskService.getTask(id);
			task.setName(taskForm.getName());
			task.setDescription(taskForm.getDescription());
			task.setAssignedTo(taskForm.getAssignedTo());
			this.taskService.saveTask(task);
			User loggedUser = sessionData.getLoggedUser();
			model.addAttribute("user", loggedUser);
			return "redirect:/project/ " + idP;
		}

		return "updateProject";
	}
	
	
	@RequestMapping(value = { "/project/{id}/addTag" }, method = RequestMethod.POST)
	public String tagProject(@Valid @ModelAttribute("tagForm") Tag tagForm, @PathVariable Long id) {
		
		Project project = projectService.getProject(id);
		
		Tag tag= this.tagService.retrieveTagByName(tagForm.getName());
		if(tag!=null) tagForm=tag;
		
		tagForm.addProject(project);
		project.addTag(tagForm);
		this.projectService.saveProject(project);
		return "redirect:/project/" + id;
	}
	
	@RequestMapping(value = { "/project/{id}/deleteTag/{tagid}" }, method = RequestMethod.GET)
	public String deletaTag( @PathVariable Long id ,@PathVariable Long tagid) {
		
		Tag tag= this.tagService.getTag(tagid);
		
		
		this.tagService.deleteTag(tag);
		
		return "redirect:/project/" + id;
	}
	
	
	
	
	@RequestMapping(value = { "/project/{id}/taskTag" }, method = RequestMethod.POST)
	public String tagTask(@Valid @ModelAttribute("tagForm") Tag tagForm, @PathVariable Long id) {
		
		Project project = projectService.getProject(id);
		
		tagForm.addProject(project);
		project.addTag(tagForm);
		this.projectService.saveProject(project);
		return "redirect:/project/" + id;
	}

	

}
