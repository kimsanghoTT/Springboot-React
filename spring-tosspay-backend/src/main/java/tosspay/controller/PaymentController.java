package tosspay.controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController // html 파일 url 주소값으로 연동
@RequestMapping("/confirm")
public class PaymentController {

	//application.properties에 적은 키 이름을 가져오기 위한 value
	@Value("${apiSecretKey}") //특정 키 이름을 외부나 다른 곳에서 가져와 사용할 때는 ${키이름}
	private String widgetSecretKey;
	
	@Value("${apiSecretKey}")
	private String apiSecretKey;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private String encodeSecretKey(String secretKey) {
		return "Basic" + new String(Base64.getEncoder().encode((secretKey + ":").getBytes()));
	}
	
	//widget이라는 주소로 결제 정보가 들어오면 결제 확인 창구로 넘겨줌. 결제정보와 사용자의 비밀번호가 담겨있음
	// 위젯 -> 페이먼트, 브랜드페이 결제랑 방식이 약간 다름
	@PostMapping("/widget")
	public ResponseEntity<?> confirmWidget(@RequestBody Map<String, String> requestBody){
		return confirmPayment(requestBody, encodeSecretKey(widgetSecretKey));
	}
	
	//payment라는 주소로 결제 정보가 들어오면 결제 확인 창구로 넘겨주는 것. 결제정보와 사용자의 비밀번호가 담겨있음
	@PostMapping("/payment")
	public ResponseEntity<?> confirmPayment(@RequestBody Map<String, String> requestBody){
		return confirmPayment(requestBody, encodeSecretKey(apiSecretKey));
	}
	
	@PostMapping("/brandpay")
	public ResponseEntity<?> confirmBrandpay(@RequestBody Map<String, String> requestBody){
		return confirmBrandPayment(requestBody, encodeSecretKey(apiSecretKey));
	}
	
	private ResponseEntity<?> confirmPayment(Map<String, String> requestBody, String encodedKey){
		
		//fetch("https://api.tosspayments.com/v1/payments/confirm"
		String url = "https://api.tosspayments.com/v1/payments/confirm";
		HttpHeaders headers = new HttpHeaders();
		
		//Authorization: encryptedApiSecretKey,
		headers.set("Authorization", encodedKey);
		//"Content-Type": "application/json"
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		return new ResponseEntity<>(response.getBody(), response.getStatusCode());
	}
	
	private ResponseEntity<?> confirmBrandPayment(Map<String, String> requestBody, String encodedKey){
		
		//fetch("https://api.tosspayments.com/v1/brandpay/payments/confirm"
		String url = "https://api.tosspayments.com/v1/brandpay/payments/confirm";
		HttpHeaders headers = new HttpHeaders();
		
		//Authorization: encryptedApiSecretKey,
		headers.set("Authorization", encodedKey);
		//"Content-Type": "application/json"
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		
		//성공 했을 때, 실패 했을 때		
		try {
			ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
			return new ResponseEntity<>(response.getBody(), response.getStatusCode());
			
		}
		catch (Exception e) {
			//getMessage : 사용자에게 보내는 응답. 실패 메시지
			//HttpStatus.BAD_REQUEST : 잘못된 요청이라는 상태 코드 보낸 것
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/*
	Entity
	
	ResponseEntity : Http를 상속받아 Http 기능의 응답에 대한 기능을 추가로 설정한 Entity
		- 사용자가 요청한 응답을 개발자가 다시 사용자에게 전달할 때 사용
		- HttpEntity를 상속해 Http 응답에 대한 추가적인 정보를 제공.
		- 상태코드를 포함하고 있어 사용자에게 응답을 보낼 때 사용
		- ResponseEntity<문자열 or 숫자 or 여러값이면 ? or 모르면 비워두기> abc = new ResponseEntity<>("응답 본문", headers);
		
	RequestEntity : Http를 상속받아 Http 기능의 요청에 대한 기능을 추가로 설정한 Entity
		- HttpEntity를 상속해 Http 요청에 대한 추가적인 정보를 제공
		- url과 http메서드(get, post, put, delete)를 포함하고 있어 서버로 요청을 보낼 때 사용
		- RequestEntity<문자열 or 숫자 or 여러값이면 ? or 모르면 비워두기> abc = new RequestEntity<>("요청 본문", headers);
		- RequestEntity<String> abc = new RequestEntity<>("요청 본문", headers, HttpMethod.POST, url);
	
	HttpEntity : http 요청 또는 응답의 본문(body)와 헤더(headers)를 포함하는 객체
		- http 요청을 보낼 때 본문과 헤더를 설정하고자 할 때 사용
		- 본문(body) : 실제 전송될 데이터. ex) 아이디, 비밀번호 등
		- 헤더(headers) : http 헤더 정보를 포함. ex) 어떤 파일이 들어오는가. 누가 보내는 것인가
		- HttpEntity<문자열 or 숫자 or 여러값이면 ? or 모르면 비워두기> abc = new HttpEntity<>("요청 본문", headers);
		
	** 차이점
	클래스명					상속 관계					주요 사용 목적						추가 정보
	HttpEntity				기본 클래스				Http요청/응답의 본문, 헤더 포함		상태코드(성공여부) 없음
	ResponseEntity			HttpEntity 상속			Http 응답 반환					상태코드(성공여부)포함
	RequestEntity			HttpEntity 상속			Http 요청 전송					URI와 Http메서드 포함
		
	
	** HTTP : 웹에서 데이터를 전송하기 위한 전송 수단
		
	** URI / URL
		* URI : 주소값, 식별값이 들어 있음. URL을 포함
		* URL : URI의 한 종류. 주소이름(도메인명)
		
		※ URN : 고유 식별 번호
	*/
}
