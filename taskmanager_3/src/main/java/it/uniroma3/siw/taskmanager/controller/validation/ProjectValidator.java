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
	final Integer MAX_DESCRIPTION_LENGTH = 1000;

	@Autowired
	ProjectService userService;

	@Override
	public void validate(Object o, Errors errors) {
		Project p = (Project) o;
		String name = p.getName().trim();
		String description = p.getDescription().trim();
		//User owner = p.getOwner();

		if (name.trim().isEmpty())
			errors.rejectValue("name", "required");
		else if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
			errors.rejectValue("name", "size");

		if (description.length()>MAX_DESCRIPTION_LENGTH)
			errors.rejectValue("description", "required");
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Project.class.equals(clazz);
	}

}
