package com.kh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class OAuthController {
	
	//@Value : application.properties나 config.properties에 작성한 키 이름과 키에 담긴 값을 가져옴
	
	@Value("${naver.client-id}")
	private String clientId; // = YMEPgkqT9p7r23tbgj3h
	
	@Value("${naver.client-secret}")
	private String clientSecret; // = VaKtGlGPXp
	
	@Value("${naver.redirect-uri}")
	private String redirectUri; // = http://localhost:9010/naverLogin
	
	@Value("${naver.state}")
	private String state; // = RANDOM_STATE
	
	/*
	app.get('/naverLogin', function (req, res) {
		api_url = 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=' + client_id + '&redirect_uri=' + redirectURI + '&state=' + state;
		res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'});
		res.end("<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>");
	});
	와 맞추는 getmapping
	*/
	@GetMapping("/naverLogin") //  api/naverLogin
	public String naverLogin() {
		String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&state=" + state;
		return "<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>";
	}
	//url에 {}=변수명 표시가 없으면 @RequestParam이나 @RequestBody
	//url에 {}=변수명 표시가 있으면 @PathVariable. {}안에 있는 변수명에 값을 집어넣음
	@GetMapping("/callback")
	public String callback(@RequestParam String code, @RequestParam String state) {
		//네이버에서 로그인을 성공했을 때 받는 값
		// 1. clientId = 어디 회사에 들어왔는가
		// 2. clientSecret = 회사에 들어오기 위한 비밀번호
		// 3. redirectUri = 들어오기 위한 심사를 완료했으면 나갈 위치로 전달
		// 4. code = 네이버로부터 무사히 들어왔다는 인증코드를 받음
		// 5. state = CSRF 공격을 방지하기 위해 사용
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
			     + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUri + "&code=" + code + "&state=" + state;

		//RestTemplate = http 메서드(get,post,put,delete)를 통해 데이터를 json 형식으로 데이터를 처리
		RestTemplate rt = new RestTemplate();
		
		//api_url 주소로 응답받은 결과를 String(문자열)으로 가져와서 사용
		String responseResult = rt.getForObject(api_url, String.class);
		return responseResult;
	}
	
}
