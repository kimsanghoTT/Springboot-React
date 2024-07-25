package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.User;
import com.kh.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	
	@Override
	public List<User> findAll(){
		return mapper.findAll();
	}
	
	@Override
	public void insertUser(User user) {
		mapper.insertUser(user);
	}
}
