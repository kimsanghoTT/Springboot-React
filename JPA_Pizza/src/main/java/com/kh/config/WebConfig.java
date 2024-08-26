package com.kh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")// 포트로 가져오는 뒤 api url 주소 모두 허용
				.allowedOrigins("http://localhost:3000")//주소와 포트 허용
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")//db와의 상호작용 모두 허용
				.allowCredentials(true)//쿠기나 세션같은 자격 허용
				.allowedHeaders("*");
	}
}
