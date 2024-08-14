package com.kh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.dto.BCUser;

// mybatis와 mapper를 생략해서 작성하는 방법
// JpaRepository : sql문을 알아서 작성해줌
public interface BCUserRepository /*extends JpaRepository<BCUser, Integer>*/{
	//save, select로 무언가를 특정하여 검색하는 행동을 하지 않는 한 기본적인 sql 작성 할 필요 없음
	//BCUser saveUser();
	
	//이메일 찾기 기능
	//sql문이 알아서 완성됨 (select * from BCUser ~~)
	BCUser findByEmail(String email);
}
