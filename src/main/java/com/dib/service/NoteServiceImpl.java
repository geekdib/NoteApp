package com.dib.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dib.dto.NoteDTO;
import com.dib.dto.TodoDTO;
import com.dib.entity.Note;
import com.dib.entity.TODO;
import com.dib.entity.User;
import com.dib.enums.Status;
import com.dib.exception.ResourceNotFoundException;
import com.dib.repository.NoteRepository;
import com.dib.repository.TODORepository;
import com.dib.repository.UserRepository;

/**
 * @author Dibyaprakash
 * 
 *         This component is responsible for handling all CRUD operations on
 *         Note
 */
@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private TODORepository todoRepository;

	@Override
	public List<Note> findAllNotes(int userId) {

		return userRepository.findById(userId).getNoteList();
	}

	@Override
	public Note addNote(String username, NoteDTO noteDTO) {
		Note note = new Note();
		note.setTitle(noteDTO.getTitle());

		List<TODO> todoList = new ArrayList<TODO>();

		for (TodoDTO dto : noteDTO.getDescription()) {
			TODO todo = new TODO();
			todo.setStatus(Status.PENDING);
			todo.setValue(dto.getValue());
			todoList.add(todoRepository.save(todo));
		}

		note.setDescription(todoList);

		User user = userRepository.findByUsername(username);
		List<Note> noteList = user.getNoteList();
		noteList.add(noteRepository.save(note));
		user.setNoteList(noteList);
		userRepository.save(user);

		return note;
	}

	@Override
	public Note updateNote(int noteId, Note note) {
		return noteRepository.save(note);
	}

	@Override
	public boolean deleteNote(int noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note not found with id :" + noteId));
		note.getDescription().removeAll(note.getDescription());
		for (TODO todo : note.getDescription()) {
			todoRepository.delete(todo);
		}
		User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.getNoteList().remove(note);
		userRepository.save(user);
		noteRepository.delete(note);
		return true;
	}

	@Override
	public Note findNote(int noteId) {

		User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note not found with id :" + noteId));

		if (user.getNoteList().contains(note)) {
			return note;
		}else {
			throw new ResourceNotFoundException("You don't have a note with such id :" + noteId);
		}

		
	}

}
