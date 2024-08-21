package com.kh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.dto.Chicken;

@Repository //mybatis, mapper 두 가지를 설정, Repository랑 mapper는 interface
public interface ChickenRepository extends JpaRepository<Chicken, Integer>{
	//CRUD같은 기본 기능은 JpaRepository 안에 모두 들어있음 -> 기본적인 건 생략 가능
	
	//특정 값을 찾을 때 쓰는 기능
	//findById(Integer id); -> where 대신 find를 사용
	//만약에 where로 이메일, 비밀번호로 로그인한다고 하면 findByEmailPassword
}
