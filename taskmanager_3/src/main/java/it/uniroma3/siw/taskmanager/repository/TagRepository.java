package it.uniroma3.siw.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.taskmanager.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Long>{
	
	public Optional<Tag> findByName(String name);
	

}
