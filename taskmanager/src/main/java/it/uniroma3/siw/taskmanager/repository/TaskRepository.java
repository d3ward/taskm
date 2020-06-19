package it.uniroma3.siw.taskmanager.repository;
import it.uniroma3.siw.taskmanager.model.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * This interface is a CrudRepository for repository operations on Tasks.
 *
 * @see Task
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

}

