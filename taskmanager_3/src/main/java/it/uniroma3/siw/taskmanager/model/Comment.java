package it.uniroma3.siw.taskmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
	private Task task;
	

	public Comment() {
		
	};

	
	public Comment(String content, User owner) {
		this();
		this.content = content;
		this.owner = owner;
	}


	public Long getId() {
		return id;
	}

	public void setTask(Task task) {
		this.task=task;
	}
	public Task getTask() {
		return this.task;
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

	
}
