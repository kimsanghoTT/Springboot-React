package com.kh.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //mysql에 테이블이 존재하지 않으면 테이블 생성
@Getter
@Setter
@ToString
public class Chicken {

	@Id//기본키 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//시퀀스 지정
	private int id;
	
	private String chickenName;
	private String description;
	private double price; //소수점 고려
}
