package com.kh.service;

import java.util.List;

import com.kh.dto.NaverUser;

public interface NaverUserService {

	void insertNaverUser(NaverUser naverUser);
	List<NaverUser> findAll();
}
