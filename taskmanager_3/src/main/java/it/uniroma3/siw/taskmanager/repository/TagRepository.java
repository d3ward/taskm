package it.uniroma3.siw.taskmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.taskmanager.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Long>{
	
	//public Tag findbyName(String name);
	

}
