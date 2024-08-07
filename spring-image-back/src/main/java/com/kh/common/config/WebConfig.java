package com.kh.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
/*
리액트 포트와 백엔드 포트가 제대로 연결될 수 있도록 설정
WebSocket 프론트와 백엔드가 서로 상호작용을 주기적으로 진행할 때 좀 더 안전하게 연결을 계속함을 설정
*/
	//react가 이미지폴더 경로를 가져갈 수 있도록 허용
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//http://localhost:9015/images/ 모든 경로 허용
		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:C:/Users/user1/Desktop/saveImage/"); //바탕화면에 지정한 이미지 경로
	}
	
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
