package com.kh.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

// NaverLogin을 한 후에 로그인한 내용을 리액트에서 볼 수 있도록 NaverLoginController 파일을 수정
// NaverLoginController.java 주소 (api url) 충돌을 막기 위해 @RequestMapping("/api")를 제거

@RestController
@RequestMapping("/naver") //NaverRegist와 주소충돌 방지
public class OAuthController {
	
	@Value("${naver.client-id}")
	private String clientId; 
	
	@Value("${naver.client-secret}")
	private String clientSecret;
	
	@Value("${naver.redirect-uri}")
	private String redirectUri;
	
	@Value("${naver.state}")
	private String state; 
	
	@GetMapping("/naverLogin") 
	public String naverLogin() {
		String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&state=" + state;
		return "<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>";
	}
	@GetMapping("/callback")
	public String callback(@RequestParam String code, @RequestParam String state, HttpSession session) {
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
			     + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUri + "&code=" + code + "&state=" + state;

		RestTemplate restTemplate = new RestTemplate();
		
		// 앞의 값은 키 이름이기 때문에 String
		// 키 이름에 담긴 값은 String이라고 확정지을 수 없으므로 Object
		Map<String, Object> responseResult = restTemplate.getForObject(api_url, Map.class);
		System.out.println("Token response : " + responseResult);
		
		// token 인증받은 값을 가져오는 데 Bearer access_token 사용
		// 가져온 token 데이터를 문자열로 변환, 글자처럼 사용
		String accessToken = (String) responseResult.get("access_token"); 
		//네이버 개발자 문서에 access_token으로 로그인 허영된 값을 가져가라고 써있음
		
		String 유저정보가담긴Url = "https://openapi.naver.com/v1/nid/me";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);
		HttpEntity<String> entity = new HttpEntity<>("", headers);
		//HttpEntity = 응답, 요청 모두 있음. 상세한 기능은 없음. ** ResponseEntity, RequestEntity = 각자 상세 기능 보유
		
		ResponseEntity<Map> userInfoRes = restTemplate.exchange(유저정보가담긴Url, HttpMethod.GET, entity, Map.class);
		
		Map<String, Object> userInfo = userInfoRes.getBody();
		session.setAttribute("userInfo", userInfo); //session에 로그인 정보를 담음
		/*
		HttpHeaders에 인증에 대한 값을 Bearer로 가져오기
		Bearer : 인증을 위해 서버에 제공되는 보안 토큰. 사용자가 인증을 받고 나서 API 요청을 할 때 사용
		
		ex) 네이버에 로그인을 하고 나면 사용자에게 로그인 됐음을 인증하는 토큰을 발급
			추후 네이버에 로그인 된 기록을 가지고 어떠한 요청을 할 때 헤더에
			Authorization : Bearer{} 작성하고 요청을 해야함
			
			* Bearer = 소유자
			
			Authorization : Bearer{}
		=		권한 부여	:	권한을 가진 사람{"권한을 가지고 있는 번호"}
		*/
		return "redirect:";
	}
	
	//가져온 네이버 정보를 리액트로 전달할 GetMapping
	@GetMapping("/userInfo")
	//HttpSession - > import jakarta O - 신버전 / javax X - 구버전
	public Map<String, Object> userInfo(HttpSession session) {
		//httpSession을 Map으로 형변환
		Map<String, Object> userInfo = (Map<String, Object>)session.getAttribute("userInfo");
		return userInfo;
	}
	
	
}
