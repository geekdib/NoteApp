package com.dib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dib.entity.TODO;

@Repository("todoRepository")
public interface TODORepository extends JpaRepository<TODO, Integer> {

	TODO findById(int id);
	
}