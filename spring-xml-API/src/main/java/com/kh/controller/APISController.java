package com.kh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


//공공 데이터 api를 이용한 api url 주소값 한 번 더 확인

//http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty
//http://apis.data.go.kr/ : 공공데이터 주소 -> env로 포트번호 처리를 한 것
//http://localhost:8080 : 내 컴퓨터 주소 -> env로 포트번호 처리를 안한 것
@RequestMapping("/B552584/ArpltnInforInqireSvc") //대기오염 서비스 공공데이터 공통 주소
@RestController
public class APISController {

	// 1.측정소별 실시간 측정정보 조회
	@GetMapping("/getMsrstnAcctoRltmMesureDnsty")
	public String get측정소측정정보조회() {
		return "측정결과전달";
	}
	
	// 2. 통합대기환경지수 나쁨 이상 측정소 목록 조회
	@GetMapping("/getUnityAirEnvrnIdexSnstiveAboveMsrstnList")
	public String get목록조회() {
		return "측정결과전달";
	}
	
	// 3.시도별 실시간 측정정보 조회
	@GetMapping("/getCtprvnRltmMesureDnsty") 
	public String getRealTimeInfo() {
		return "측정결과전달";
	}
	
	// 4. 대기질 예보 통보 조회
	@GetMapping("/getMinuDustFrcstDspth")
	public void get예보조회() {
		System.out.println("측정 결과 전달");
	}
	
	// 5. 초미세먼지 주간 예보 조회
	@GetMapping("/getMinuDustWeekFrcstDspth")
	public void get주간예보조회() {
		System.out.println("측정 결과 전달");
	}
	
	/* \특수문자 : 특수문자를 글자 취급해버림 -> \" 하면 에러뜸 */
	
}
