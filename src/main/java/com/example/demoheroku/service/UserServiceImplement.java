package com.example.demoheroku.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demoheroku.controller.request.UserRequest;
import com.example.demoheroku.exception.UserDuplicateException;
import com.example.demoheroku.model.User;
import com.example.demoheroku.repository.UserRepository;

@Service
public class UserServiceImplement implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// constructor to injection Repository
	// BCryptPasswordEncoder มาจาก springboot security ใช้ encode รหัส ได้
	public UserServiceImplement(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public User register(UserRequest userRequest) {
		User user = userRepository.findByUsername(userRequest.getUsername());
		if (user == null) {
			user = new User().setUsername(userRequest.getUsername())
					.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()))
					.setRole(userRequest.getRole());
			return userRepository.save(user);
		} else {
			throw new UserDuplicateException(userRequest.getUsername());
		}

	}

	@Override
	public User findUserByUsername(String userName) {
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(userName));
		if (user.isPresent()) {
			return user.get();

		} else {
			return null;
		}
	}

}
