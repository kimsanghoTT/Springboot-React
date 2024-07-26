package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.User;
import com.kh.mapper.UserMapper;

//implements로 상속받는 인터페이스 서비스는 기능목록일 뿐이므로 상속한 impl에서 기능 목록들을 모두 상세하게 구현할 의무가 있음
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
	
	@Override
	public void deleteUser(int id) {
		mapper.deleteUser(id);
	}
	
	@Override
	public void updateUser(User user) {
		mapper.updateUser(user);
	}
}
