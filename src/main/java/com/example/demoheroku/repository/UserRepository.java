package com.example.demoheroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoheroku.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// select * from user where username = {name}
	User findByUsername(String user);

}