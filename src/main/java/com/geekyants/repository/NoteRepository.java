package com.geekyants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekyants.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	
}