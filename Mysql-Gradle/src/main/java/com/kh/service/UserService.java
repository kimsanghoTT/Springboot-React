package com.kh.service;

import java.util.List;

import com.kh.dto.User;

public interface UserService {

	List<User> findAll();
	void insertUser(User user);
}
