package com.example.demoheroku.controller.api;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoheroku.controller.request.UserRequest;
import com.example.demoheroku.exception.ValidationException;
import com.example.demoheroku.model.User;
import com.example.demoheroku.service.UserServiceImplement;

@RestController
@RequestMapping("/auth")
public class UserController {

	// สำคัญ ใช้ ตรวจ error
//	if (bindingResult.hasErrors()) {
//		bindingResult.getFieldErrors().stream().forEach(fieldError -> {
//			throw new ValidationException(fieldError.getField());
//		});
//	}

	private final UserServiceImplement userServiceImplement;

	// injection because @Service on productServiceImplement
	UserController(UserServiceImplement userServiceImplement) {
		this.userServiceImplement = userServiceImplement;
	}

	// @RequestBody get JSON
	@PostMapping(path = "/register")
	public User register(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField());
			});
		}
		return userServiceImplement.register(userRequest);
	}

	@PostMapping(path = "/auth/login")
	public User Login(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField());
			});
			System.out.println(userServiceImplement.findUserByUsername(userRequest.getUsername()));
		}
		return userServiceImplement.findUserByUsername(userRequest.getUsername());
	}

}
