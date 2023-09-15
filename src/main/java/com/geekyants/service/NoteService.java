package com.geekyants.service;

import java.util.List;

import com.geekyants.dto.NoteDTO;
import com.geekyants.entity.Note;

public interface NoteService {

	public List<Note> findAllNotes(int userId);

	public Note addNote(String username, NoteDTO noteDTO);

	public Note updateNote(int noteId, Note note);

	public boolean deleteNote(int noteId);

	public Note findNote(int noteId);

}
