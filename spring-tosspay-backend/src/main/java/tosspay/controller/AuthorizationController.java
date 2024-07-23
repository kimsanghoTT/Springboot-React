package tosspay.controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//인증키 확인 후 전송하는 코드
@RestController
public class AuthorizationController {

	//스프링부트에서 application.properties에서 작성한 값을 가져오기 위해 @Value 어노테이션 사용
	//@Value("${application.properties에 작성한 변수명}")
	@Value("${apiSecretKey}")
	private String apiSecretKey; //가져온 값을 담을 변수 설정
	
	//http 요청을 보내기 위해 요청 보내는 것을 담을 공간 생성
	private final RestTemplate restTemplate = new RestTemplate();
	
	//const encryptedWidgetSecretKey = "Basic " + Buffer.from(widgetSecretKey + ":").toString("base64");
	//const encryptedApiSecretKey = "Basic " + Buffer.from(apiSecretKey + ":").toString("base64");
	//주어진 비밀키를 Base64로 인코딩해서 http 헤더에 비밀키를 가져갈 수 있도록 설정
	/*
	Base64 : 사람이 작성한 데이터를 컴퓨터가 읽을 수 있는 텍스트 형식으로 변환하는 객체
		ex) Hello라고 작성한 내용을 Base64를 이용해 인코딩하면
		사람언어 : Hello / 컴퓨터언어 : SGVsbG8=
	*/
	private String encodeSecretKey(String secretKey) {
		return "Basic " + new String(Base64.getEncoder().encode(  (secretKey + ":").getBytes()  ));
	}
	/*
	브랜드페이 Access Token 발급
app.get("/callback-auth", function (req, res) {
  const { customerKey, code } = req.query;

  요청으로 받은 customerKey 와 요청한 주체가 동일인인지 검증 후 Access Token 발급 API 를 호출하세요.
  @docs https://docs.tosspayments.com/reference/brandpay#access-token-발급
  fetch("https://api.tosspayments.com/v1/brandpay/authorizations/access-token", {
    method: "POST",
    headers: {
    
    function (req, res) {const { customerKey, code } = req.query;
    function은 기능명 생략가능
    자바는 기능명이 생략 불가
    따라서 function callbackAuth (req, res) {const { customerKey, code } = req.query; 와 같음
	*/
	
	@GetMapping("/callback-auth") //app.get("/callback-auth")
	public ResponseEntity<?> callbackAuth(
			@RequestParam String customerKey,
			@RequestParam String code){// = function (req, res) {const { customerKey, code } = req.query;
		//fetch("https://api.tosspayments.com/v1/brandpay/authorizations/access-token"
		String url = "https://api.tosspayments.com/v1/brandpay/authorizations/access-token";
		
		/*
		headers: {
      		Authorization: encryptedApiSecretKey,
      		"Content-Type": "application/json",
    	},
		*/
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", encodeSecretKey(apiSecretKey));
		headers.set("Content-Type", "application/json");
		
		
		/**
		body: JSON.stringify({
        	grantType: "AuthorizationCode",
        	customerKey,
        	code,
    	}),
		 */
		//body: JSON.stringfy({})
		Map<String, String> requestBody = Map.of(
	        	"grantType", "AuthorizationCode",
	        	"customerKey", customerKey,
	        	"code", code
		);
		//Map.of() : Map 객체 안에 있는 of라는 기능
		//of : 가져온 값을 추가하거나 제거할 수 없도록 설정. -> 가져온 값이 손상되지 않도록 보호
		/*
		Map<String, String> map = Map.of(
	        	"key 1", "val 1",
	        	"key 2", val 2,
	        	"key 3", val 3
				);
		*/
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		// = .then(async function (response) 
		//HttpEntity : http 요청의 본문과 요청사항이 담긴 headers를 가져와서 한 번에 전달
		
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		// = const result = await response.json();
		/*
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
			- url : 요청을 보낼 주소값 가져옴
			- HttpMethod.POST : 값을 어떻게 쓸지 http메서드로 전달(get, post, put, delete)
			- entity : 코드를 작성한 목적이 담긴 내용물과 제목 -> 요청사항이 담긴 내용
			- Map.class : 응답받을 데이터 타입을 지정 -> 응답을 key-value로 받아서 가짐
		*/
		
		//응답에 대한 실패 / 성공 결과가 담긴 내용을 전달
		return new ResponseEntity<>(response.getBody(), response.getStatusCode());
		// = res.status(response.status).json(result);
	}
}
