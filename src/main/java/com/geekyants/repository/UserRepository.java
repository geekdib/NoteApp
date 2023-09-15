package com.geekyants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekyants.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findById(int id);

}
