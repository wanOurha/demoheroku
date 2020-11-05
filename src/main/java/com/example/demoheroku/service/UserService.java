package com.example.demoheroku.service;

import com.example.demoheroku.controller.request.UserRequest;
import com.example.demoheroku.model.User;

public interface UserService {

	User register(UserRequest userRequest);

	User findUserByUsername(String userName);

}
