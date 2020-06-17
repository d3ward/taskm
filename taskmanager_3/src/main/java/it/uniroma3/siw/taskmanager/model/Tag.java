package it.uniroma3.siw.taskmanager.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
	private Project project;

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

	public Project getProject() {
		return project;
	}
	public void addProject(Project project) {
		this.project=project;
	}
	public void setProject(Project project) {
		this.project = project;
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
