package com.kh.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity //mysql과 매핑
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BCUser {

//	@Id //기본키
//	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 올라가는 시퀀스
	private int id;
	private String username;
	private String email;
	private String password;
}
