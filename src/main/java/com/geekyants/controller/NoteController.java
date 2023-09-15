package com.geekyants.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.dto.NoteDTO;
import com.geekyants.entity.Note;
import com.geekyants.service.LogService;
import com.geekyants.service.NoteService;
import com.geekyants.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/note")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private LogService logService;

	private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

	/**
	 * This method is responsible for retrieving all notes for currently logged in
	 * user
	 */
	@RequestMapping(value = "/myNotes", method = RequestMethod.POST)
	public ResponseEntity<Object> myNotes() {

		logger.info(logService.formatLogRequest("/myNotes", null, null).toString());

		List<Note> notes = noteService.findAllNotes(userDetailsService
				.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId());

		logger.info(logService.formatLogResponse("/myNotes", notes).toString());

		return new ResponseEntity<Object>(notes, HttpStatus.OK);

	}

	/**
	 * This method is responsible for creating a new note by taking RequestBody as
	 * NoteDTO
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createNote(@RequestBody NoteDTO noteDTO) {

		logger.info(logService.formatLogRequest("/create", noteDTO, null).toString());

		Note noteRes = noteService.addNote(SecurityContextHolder.getContext().getAuthentication().getName(), noteDTO);

		logger.info(logService.formatLogResponse("/create", noteRes).toString());

		return new ResponseEntity<Object>(noteRes, HttpStatus.ACCEPTED);
	}

	/**
	 * This method is responsible for updating an existing note by taking noteId as
	 * path variable and RequestBody as Note
	 */
	@RequestMapping(value = "/update/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateNote(@PathVariable("noteId") int noteId, @RequestBody Note note) {

		logger.info(logService.formatLogRequest("/update", note, null).toString());

		Note noteRes = noteService.updateNote(noteId, note);

		logger.info(logService.formatLogResponse("/update", noteRes).toString());

		return new ResponseEntity<Object>(noteRes, HttpStatus.ACCEPTED);
	}

	/**
	 * This method is responsible for deleting a note permanently
	 */
	@RequestMapping(value = "/delete/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteNote(@PathVariable("noteId") int noteId) {

		String msg = null;

		logger.info(logService.formatLogRequest("/delete", null, new HashMap<String, Object>().put("noteId", noteId))
				.toString());

		boolean res = noteService.deleteNote(noteId);
		if (res) {
			msg = "Note - " + noteId + " has been successfully deleted";
		}

		logger.info(logService.formatLogResponse("/delete", msg).toString());

		return new ResponseEntity<Object>(msg, HttpStatus.OK);
	}

	/**
	 * This method is responsible for finding a note by taking noteId as path
	 * variable
	 */
	@RequestMapping(value = "/find/{noteId}", method = RequestMethod.GET)
	public ResponseEntity<Object> findNote(@PathVariable("noteId") int noteId) {

		logger.info(logService.formatLogRequest("/find", null, new HashMap<String, Object>().put("noteId", noteId))
				.toString());

		Note noteRes = noteService.findNote(noteId);

		logger.info(logService.formatLogResponse("/find", noteRes).toString());

		return new ResponseEntity<Object>(noteRes, HttpStatus.OK);
	}

}
