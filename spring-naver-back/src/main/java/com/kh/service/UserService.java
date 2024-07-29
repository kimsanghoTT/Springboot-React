package com.kh.service;

import java.util.List;

import com.kh.dto.User;

//서비스 목록 리스트. impl에서 오버라이드해서 환경에 맞게 재사용
//상세하게 작성할 기능 목록을 구성
public interface UserService {

	List<User> findAll();
	void insertUser(User user);
	void deleteUser(int id);
	void updateUser(User user);
}
