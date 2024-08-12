package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.NaverUser;
import com.kh.mapper.NaverUserMapper;

@Service
public class NaverUserServiceImpl implements NaverUserService{

	@Autowired
	private NaverUserMapper mapper;
	
	@Override
	public void insertNaverUser(NaverUser naverUser) {
		mapper.insertNaverUser(naverUser);
	}
	
	@Override
	public List<NaverUser> findAll() {
		return mapper.findAll();
	}
}
