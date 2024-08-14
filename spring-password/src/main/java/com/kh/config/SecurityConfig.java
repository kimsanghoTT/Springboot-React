package com.kh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
	BCryptPasswordEncoder -> 이 이름으로 객체 생성하면 안됨
	Blowfish 암호 알고리즘 기반
	Crypt : Encrypt의 준말
	Encoder : 데이터를 특정 방식으로 변환하는 역할

*/

//보안 설정
//보안 기능을 사용하기 위해선 build.gradle에 security를 상속해야함
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http // 모든 http 메서드 요청 허용
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll() // -> 비밀번호 없이 포트에 들어갈 수 있도록 연결 허용
            )
            // 3000 포트나 외부에서 오는 보호 비활성화
            .csrf(csrf -> csrf.disable());
        
        // http.build : 위의 작성한 상세내용 통합
        return http.build();
    }

    //패스워드 관련 객체 생성
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
