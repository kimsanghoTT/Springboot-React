package com.kh.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
/*
리액트 포트와 백엔드 포트가 제대로 연결될 수 있도록 설정
WebSocket 프론트와 백엔드가 서로 상호작용을 주기적으로 진행할 때 좀 더 안전하게 연결을 계속함을 설정
*/
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**")
		.allowedOrigins("http://localhost:3000")
		//.allowedOrigins("*")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		.allowedHeaders("*");
		/*
		.allowedOrigins("http://localhost:3000") -> 해당 주소의
		
		.addMapping("/**") -> 뒤에 오는 url api 주소를 모두 허용
		
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			-> http://localhost:3000/**로 들어오는 모든 요청 허용
			
		.allowedHeaders("*") <head>정보에 들어갈 모든 요청 허용
		*/
		
	}
}
