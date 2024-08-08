package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Profile;
import com.kh.service.ProfileService;



@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService service;
	/*
	@Autowired 생략 시
	public ProfileController(ProfileService profileService){
		this.service = profileService;
	}
	
	를 작성해줘야함
	*/
	@GetMapping("/watching")
	public ResponseEntity<List<Profile>> getProfile() {
		return ResponseEntity.ok(service.getProfile());
	}
	
	/*
	parameterType error : 
	@RequestParam 안에 react에서 값을 가져올 때 값을 가져온 변수명을 작성하지 않으면 에러가 발생
	@RequestParam("리액트에서 가져올 변수명")
	*/
	@PostMapping("/upload")
	public ResponseEntity<String> insertProfile(	
			@RequestParam("files") MultipartFile[] files,
			@RequestParam("username") String username,
			@RequestParam("profileImageUrl") String profileImageUrl
			) {
		service.uploadProfile(files, username, profileImageUrl);
		
		return ResponseEntity.ok("등록 성공");
	}
	
}
