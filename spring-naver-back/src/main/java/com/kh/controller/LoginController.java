package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.NaverUser;
import com.kh.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService service;
	/*
	parameters total 1 : 일치하는 컬럼이 1개 있음
	parameters total 0 : 일치하는 컬럼이 0개 있음
	*/
	@PostMapping("/login")
	//login @RequestBody Map<String, String>
	public ResponseEntity<String> Login(@RequestParam("id")String id, @RequestParam("password")String password) {
		NaverUser user = service.Login(id, password);
		System.out.println("아이디 : " + id + " 비밀번호 : " + password);
		if(user != null) {
			return ResponseEntity.ok("로그인 성공");
		}
		else {
			//ResponseEntity.status : DB나 어떤 값에 대한 결과 상태
			//HttpStatus : get, post 같은 메서드 기능이 동작했는지 확인
			//unauthorized : 인증 실패. 주로 로그인 실패
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
		}
	}
}
