package com.dib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dib.dto.NoteDTO;
import com.dib.dto.TodoDTO;
import com.dib.entity.Note;
import com.dib.entity.TODO;
import com.dib.enums.Status;
import com.dib.service.NoteService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class NotesApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	NoteService noteService;
	
	@Test
	@Order(1)
	public void testCreateNote () {
		
		NoteDTO noteDTO = new NoteDTO();
		noteDTO.setTitle("NoteEx1");
		
		List<TodoDTO> todoList = new ArrayList<>();
		
		TodoDTO todoDTO1 = new TodoDTO();
		todoDTO1.setValue("todoEx1");
		todoList.add(todoDTO1);
		
		TodoDTO todoDTO2 = new TodoDTO();
		todoDTO2.setValue("todoEx2");
		todoList.add(todoDTO2);
		
		noteDTO.setDescription(todoList);
		Note note = noteService.addNote("dibya", noteDTO);
		
		assertNotNull(note);
	}
		
	@Test
	@Order(2)
	public void testUpdateNote () {
		Note note = new Note();
		note.setId(2);
		note.setTitle("mynote2");
		
		TODO todo1 = new TODO();
		todo1.setId(3);
		todo1.setStatus(Status.DONE);
		
		TODO todo2 = new TODO();
		todo2.setId(4);
		todo2.setStatus(Status.DONE);
		
		List<TODO> todos = new ArrayList<>();
		todos.add(todo1);
		todos.add(todo2);
		
		note.setDescription(todos);
		assertEquals("DONE", noteService.updateNote(2, note).getDescription().get(0).getStatus().toString());
	}
		
	@Test
	@Order(3)
	public void testFindNote () {
		Note note = noteService.findNote(2);
		assertEquals("mynote2", note.getTitle());
	}
		
	@Test
	@Order(4)
	public void testMyNotes () {
		List<Note> myNotes = new ArrayList<>();
		myNotes = noteService.findAllNotes(1);
		assertEquals(7, myNotes.size());
	}
		
	@Test
	@Order(5)
	public void testDelete () {
		noteService.deleteNote(2);
		assertThat(noteService.findNote(2)).isNull();
	}

}
