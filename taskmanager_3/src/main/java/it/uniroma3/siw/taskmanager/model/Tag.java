package it.uniroma3.siw.taskmanager.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * A Tag is a unitary tag managed by the TaskManager.
 */
@Entity
public class Tag {

	/**
	 * Unique identifier for this Tag
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Name for this Tag
	 */
	@Column(nullable = false, length = 100)
	private String name;
	/**
	 * Description for this Tag
	 */
	@Column
	public String description;
	/**
	 * Color for this Tag
	 */
	@Column()
	public String color;

	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "tags")
	private List<Project> projects;

	@ManyToMany
	private List<Task> tasks;

	public Tag() {
	}

	public Tag(String name, String description, String color) {
		this.name = name;
		this.color = color;
		this.description = description;
	}

	// GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Project> getProject() {
		return projects;
	}
	public void addProject(Project project) {
		projects.add(project);
	}
	public void setProject(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Tag t = (Tag) o;
		return Objects.equals(name, t.name) && Objects.equals(description, t.description)
				&& Objects.equals(color, t.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, color, id);
	}
}
