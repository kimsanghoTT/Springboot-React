package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.User;
import com.kh.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> findALl(){
		return userService.findAll();
	}
	
	@PostMapping
	public void insertUser(@RequestBody User user) {
		userService.insertUser(user);
	}
	/*
	//requestbody : 전체를 가져옴
	//requestparam : 하나만 가져옴
	///await axios.delete(`/users?id=${id}`);
	@DeleteMapping("/{id}") //api 삭제를 진행하기 위해 만나는 주소(api)가 users/유저번호
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
	*/
	
	//await axios.delete(`/users`, {params: {id}});
	@DeleteMapping //api 삭제를 진행하기 위해 만나는 주소(api)가 users
	public void deleteUser(@RequestParam("id") int id) {
		userService.deleteUser(id);
	}
	
	@PutMapping
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}
}
