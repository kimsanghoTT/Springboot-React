package com.kh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.entity.Pizza;
// Repository : mapper와 mybatis를 합쳐놓은 기능. select, insert, 특정 단어 찾기는 기본으로 제공
// 검색같은 특수작업만 작성
// mapper와 같은 interface. 다만 mapper와 다르게 mybatis의 기능을 포함
public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

	List<Pizza> findByNameContainingIgnoreCase(String query);
	//검색처럼 여러개를 동시에 볼 때는 List
	//findBy = where
	//ContainingIgnoreCase = Like % % 특정 단어 앞 뒤로 상관없이 검색, 대소문자 구분 x
	//findByNameContainingIgnoreCase = where name like % {검색한 이름} %
	
	//Like %name : 문자열 끝이 name으로 끝나는 값 찾기
	//Like %name% : 문자열 중간에 name이 들어가는 값 찾기
	//Like name% : 문자열 시작에 name이 들어가는 값 찾기
}
