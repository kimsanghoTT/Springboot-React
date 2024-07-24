package tosspay.controller;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/billing")
public class BillingController {

	//api시크릿키 가져오기
	@Value("${apiSecretKey}")
	private String widgetSecretKey;
	
	@Value("${apiSecretKey}")
	private String apiSecretKey;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private String encodeSecretKey(String secretKey) {
		return "Basic" + new String(Base64.getEncoder().encode((secretKey + ":").getBytes()));
	}
	
	private final Map<String, String> billingMap = new ConcurrentHashMap<>();
	
	@PostMapping("/confirm-billing")
	public ResponseEntity<?> confirmBilling(@RequestBody Map<String, String> requestBody){
		String billingKey = billingMap.get(requestBody.get("customerKey"));
		
		//https://api.tosspayments.com/v1/billing/${billingKeyMap.get(customerKey)}
		String url = "https://api.tosspayments.com/v1/billing/" + billingKey;
		
		//HttpHeaders + return new
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", encodeSecretKey(apiSecretKey));
		headers.set("Content-Type", "application/json");
		
		//key-value 타입을 Map을 이용해 모두 문자열로 가지고 옴(String, String)
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		
		//requestBody = 본문. 고객이 작성한 키 값
		//billingKey = 정기 결제에 관련된 키 값
		billingMap.put(requestBody.get("customerKey"), response.getBody().get("billingKey").toString());
		return new ResponseEntity<>(response.getBody(), response.getStatusCode());
	}
}
