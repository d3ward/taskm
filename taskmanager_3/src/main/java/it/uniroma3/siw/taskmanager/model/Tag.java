package it.uniroma3.siw.taskmanager.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A Task is a unitary activity managed by the TaskManager.
 * It is generated and owned by a specific User within the context of a specific Project.
 * The task is contained in the Project and is visible to whoever has visibility over its Project.
 * The Task can be marked as "completed".
 */
@Entity
public class Tag {

    /**
     * Unique identifier for this Task
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Name for this task
     */
    @Column(nullable = false, length = 100)
    private String name;
    /**
     * Project for this task
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;
    
    @ManyToOne
    private Task task;
    /**
     * Description for this task
     */
    @Column(nullable=false)
    public String color;
    /**
     * Boolean flag specifying whether this Task is completed or not
     */

    @Column
    public String description;
    
    

    /**
     * Timestamp for the instant this Task was created/loaded into the DB
     */
    @Column(updatable = false, nullable = false)
    private LocalDateTime creationTimestamp;

    /**
     * Timestamp for the last update of this Task into the DB
     */
    @Column(nullable = false)
    private LocalDateTime lastUpdateTimestamp;

    public Tag() {}

    
    public Tag(String name, String description, String color) {
        this.name = name;
        this.color = color;
        this.description = description;
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


	/**
     * This method initializes the creationTimestamp and lastUpdateTimestamp of this User to the current instant.
     * This method is called automatically just before the User is persisted thanks to the @PrePersist annotation.
     */
    @PrePersist
    protected void onPersist() {
        this.creationTimestamp = LocalDateTime.now();
        this.lastUpdateTimestamp = LocalDateTime.now();
    }

    /**
     * This method updates the lastUpdateTimestamp of this User to the current instant.
     * This method is called automatically just before the User is updated thanks to the @PreUpdate annotation.
     */
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateTimestamp = LocalDateTime.now();
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
    

   

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public LocalDateTime getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(LocalDateTime lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(color,tag.color ) &&
               Objects.equals(name, tag.name) &&
                Objects.equals(creationTimestamp, tag.creationTimestamp) &&
                Objects.equals(lastUpdateTimestamp, tag.lastUpdateTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creationTimestamp, lastUpdateTimestamp);
    }

    public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
