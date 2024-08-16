package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.service.APIService;

//공공데이터 API 활용 컨트롤러
	
@RestController
public class APIController {
	
	@Autowired
	private APIService service;
	
	@GetMapping("/api/dataService")
	public String dataAPI() {
		String endPoint = "/끝나는 주소";
		return service.getApiData(null);
	}
}