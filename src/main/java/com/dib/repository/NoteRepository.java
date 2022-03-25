package com.dib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dib.entity.Note;

@Repository("noteRepository")
public interface NoteRepository extends JpaRepository<Note, Integer> {

	
}