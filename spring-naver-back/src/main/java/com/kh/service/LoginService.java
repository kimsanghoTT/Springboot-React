package com.kh.service;

import com.kh.dto.NaverUser;

public interface LoginService {

	NaverUser Login(String id, String password);
}
