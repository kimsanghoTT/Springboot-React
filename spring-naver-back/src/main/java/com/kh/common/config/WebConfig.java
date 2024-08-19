package com.kh.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
외부 도메인에서 요청을 주고 받을 수 있도록 허용하는 것
설정을 통해 특정 도메인에서 오는 요청으로 허용할 수 있고,
허용할 HTTP 메서드(Get, Post, Put, Delete) 지정할 수 있음
 */

@Configuration // 개발 설정
public class WebConfig implements WebMvcConfigurer{
	// WebMvcConfigurer mapping 을 재설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**")
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		.allowedHeaders("*");

		
	}
}