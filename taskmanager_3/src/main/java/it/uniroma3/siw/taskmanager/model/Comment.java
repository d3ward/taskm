package it.uniroma3.siw.taskmanager.model;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@ManyToOne(fetch = FetchType.LAZY, optional= false )
	@JoinColumn(name="task_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
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
