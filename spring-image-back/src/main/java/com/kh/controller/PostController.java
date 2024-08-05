package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.Post;
import com.kh.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping
	public List<Post> findAll(){
		return postService.findAll();
	}
	
	@PostMapping
	public void insertPost(Post post) {
		postService.insertPost(post);
	}
	
	@PostMapping
	public void insertImage() {
		postService.insertImage();
	}
}
