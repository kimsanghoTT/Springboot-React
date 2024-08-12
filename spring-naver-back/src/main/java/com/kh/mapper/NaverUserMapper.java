package com.kh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.dto.NaverUser;

@Mapper
public interface NaverUserMapper {

	//네이버 SNS 연동해서 회원가입하는 insert
	void insertNaverUser(NaverUser naverUser);
	
	List<NaverUser> findAll();
}
