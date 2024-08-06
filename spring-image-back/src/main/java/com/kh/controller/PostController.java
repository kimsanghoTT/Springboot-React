package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Post;
import com.kh.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	//ResponseEntity = 데이터가 무사히 전달되는지 체크
	
	//db에 저장된 게시글 내용, 이미지 가져오기
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAll(){
		List<Post> posts = postService.findAll();
		return ResponseEntity.ok(posts);
	}
	
	//이미지 저장
	@PostMapping("/gallery/upload")
	public ResponseEntity<String> uploadImages( 
			@RequestParam("files") MultipartFile[] files, 
			@RequestParam("title") String title, 
			@RequestParam("content") String content) {
		postService.uploadImages(files, title, content);
		return ResponseEntity.ok("이미지 db 업로드 성공");
	}
}
