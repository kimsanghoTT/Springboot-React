package com.kh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIService {

	@Value("${api.base-url}")
	private String baseUrl;
	
	@Value("${api.key}")
	private String apiKey;
	
	@Value("${api.content-type}")
	private String contentType;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//공공 데이터를 가져오기 위한 환경설정 서비스
	public String getApiData(String endPoint) {
		//시작주소(baseUrl)와 api 안의 상세 주소(endPoint)
		String url = baseUrl + endPoint; 
		
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", contentType);
		header.set("Authorization", "Bearer " + apiKey);
		
		HttpEntity<String> entity = new HttpEntity<>(header);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
				
		return response.getBody();
	}
}
