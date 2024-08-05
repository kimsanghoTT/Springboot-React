package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.Post;
import com.kh.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostMapper mapper;
	
	@Override
	public List<Post> findAll(){
		return mapper.findAll();
	}
	
	@Override
	public void insertPost(Post post) {
		mapper.insertPost(post);
	}
	
	@Override
	public void insertImage() {
		mapper.insertImage();
	}
	
	
}
