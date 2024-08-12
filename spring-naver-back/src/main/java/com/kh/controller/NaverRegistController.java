package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.NaverUser;
import com.kh.service.NaverUserService;

//네이버로 회원가입 후 DB에 회원가입 정보를 등록하는 컨트롤러
@RestController
public class NaverRegistController {

	@Autowired
	private NaverUserService service;
	
	//회원가입을 위한 postmapping
	@PostMapping("/naverAPI/register") //front와 데이터를 주고받기 위한 주소 (url, api)
	public String insertNaverUser(@RequestBody NaverUser naverUser) {
		
		
		//service.insertNaverUser(null);
		//null이 들어간 자리에는 리액트에서 받아온 값을 넣어주는 공간
		//처음에는 java가 어떤 값을 넣어야할지 모르기 때문에 null로 기본설정 되어 있음
		//null 자리에는 @RequestBody나 @RequestParam으로 가져온 값을 작성
		//@RequestBody = 전체(부분적인 수정이 필요하거나 커스텀이 필요하지 않을 경우)
		//@RequestParam = 부분 수정 추가, 부분적으로 추가할 때 사용
		
		service.insertNaverUser(naverUser);
		
		return "회원가입성공";
	}
}
