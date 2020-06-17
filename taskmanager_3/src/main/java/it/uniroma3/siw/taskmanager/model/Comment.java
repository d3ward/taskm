package it.uniroma3.siw.taskmanager.model;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Comment {

	/**
	 * Unique identifier for this Comment
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Content for this Comment
	 */
	@Column
	private String content;

	/**
	 * User owner for this Comment
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private User owner;

	

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

	/**
	 * Tags that this project contains
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany
	@JoinColumn(name = "project_id")
	private List<Tag> tags;

	public Comment() {
		this.tags = new ArrayList<>();
	}

	public Comment(String name, String description) {
		this();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setName(String name) {
		this.content = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}


	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
	}


	@Override
	public String toString() {

		return "Comment{" + "id=" + id + ",  content='" + content + '\'' + ", project="
				+ project + '}';
	}

	
}
