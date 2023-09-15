package com.geekyants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekyants.entity.TODO;

public interface TODORepository extends JpaRepository<TODO, Integer> {

	TODO findById(int id);

}