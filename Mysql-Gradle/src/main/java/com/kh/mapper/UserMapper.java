package com.kh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.dto.User;

//mybatis에 id 값으로 작성한 sql 기능 목록 작성 -> 인터페이스
@Mapper
public interface UserMapper {

	List<User> findAll();
	void insertUser(User user);
	void deleteUser(int id);
	void updateUser(User user);
}
