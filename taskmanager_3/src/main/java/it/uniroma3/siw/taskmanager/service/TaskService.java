package it.uniroma3.siw.taskmanager.service;

import it.uniroma3.siw.taskmanager.model.Task;
import it.uniroma3.siw.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The TaskRepository handles logic for Tasks.
 */
@Service
public class TaskService {

    @Autowired
    protected TaskRepository taskRepository;

    /**
     * This method retrieves a Task from the DB based on its ID.
     * @param id the id of the Task to retrieve from the DB
     * @return the retrieved Task, or null if no Task with the passed ID could be found in the DB
     */
    @Transactional
    public Task getTask(long id) {
        Optional<Task> result = this.taskRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a Task in the DB.
     * @param task the Task to save into the DB
     * @return the saved Task
     */
    @Transactional
    public Task saveTask(Task task) {
        return this.taskRepository.save(task);
    }

    /**
     * This method sets a Task in the DB as completed.
     * @param task the Task to set as completed
     * @return the task, after it has been set as completed and flushed in to the DB
     */
    @Transactional
    public Task setCompleted(Task task) {
        task.setCompleted(true);
        return this.taskRepository.save(task);
    }


    /**
     * This method deletes a Task from the DB.
     * @param task the Task to delete from the DB
     */
    @Transactional
    public void deleteTask(Task task) {
        this.taskRepository.delete(task);
    }
}
