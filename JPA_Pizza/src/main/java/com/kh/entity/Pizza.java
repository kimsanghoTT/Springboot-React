package com.kh.entity;

import jakarta.persistence.Entity; //lombok에도 entity가 있으므로 jakarta로 확실하게 import할 것
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
	db에 존재하는 기존의 테이블에 연결 = dto
	db에 테이블이 없어서 생성해줘야할 때 = entity
	db랑 상관 없음 = vo
	
	model = dto, entity, vo

*/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//만약 db의 테이블명을 pizzas로 지정하고 싶다면 @Table에 이름을 명시해주면 됨
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	private double price;
}
