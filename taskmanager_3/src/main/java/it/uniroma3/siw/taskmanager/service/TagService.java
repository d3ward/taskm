package it.uniroma3.siw.taskmanager.service;


import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.taskmanager.model.Tag;
import it.uniroma3.siw.taskmanager.repository.TagRepository;

public class TagService {
	
	
	@Autowired
	public TagRepository tagRepository;
	
	@Transactional
	public Tag getTag(long id) {
		Optional<Tag> result = this.tagRepository.findById(id);
		return result.orElse(null);	
	}
	
	@Transactional
	public Tag saveTag(Tag tag) {
		return this.tagRepository.save(tag);
	}
	
	@Transactional
	public void deleteTag(Tag tag) {
		this.tagRepository.delete(tag);
	}

	@Transactional
	public Tag retrieveTagByName(String name) {
		return this.tagRepository.findbyName(name);
	}
	
	
	
	

}
