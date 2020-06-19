package it.uniroma3.siw.taskmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.taskmanager.model.Comment;
import it.uniroma3.siw.taskmanager.model.Task;
import it.uniroma3.siw.taskmanager.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Transactional
	public Comment getComment(long id) {
		Optional<Comment> result = this.commentRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Comment saveComment(Comment comment) {
		return this.commentRepository.save(comment);
	}

	@Transactional
	public void deleteComment(Comment comment) {
		this.commentRepository.delete( comment);
	}

	@Transactional
	public List<Comment>retrieveCommentByTask(Task task) {
		Iterable<Comment> i = this.commentRepository.findByTask(task);
		ArrayList<Comment> lista = new ArrayList<>();
		for (Comment u : i)
			lista.add(u);
		return lista;
		
	}
}
