package it.uniroma3.siw.taskmanager.controller.validation;

import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * or Project
 */
@Component
public class ProjectValidator implements Validator {

	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 2;

	@Autowired
	ProjectService userService;

	@Override
	public void validate(Object o, Errors errors) {
		Project p = (Project) o;
		String name = p.getName().trim();
		String description = p.getDescription().trim();
		//User owner = p.getOwner();

		if (name.trim().isEmpty())
			errors.rejectValue("firstName", "required");
		else if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
			errors.rejectValue("firstName", "size");

		if (description.trim().isEmpty())
			errors.rejectValue("lastName", "required");
		else if (description.length() < MIN_NAME_LENGTH || description.length() > MAX_NAME_LENGTH)
			errors.rejectValue("lastName", "size");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Project.class.equals(clazz);
	}

}
