package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.Chicken;
import com.kh.repository.ChickenRepository;

@Service
public class ChickenService {

	@Autowired
	private ChickenRepository chickenRepository;
	
	//치킨 테이블 모두 보기 -> List
	public List<Chicken> getAllChickens() {
		return chickenRepository.findAll();
	}
	
	//치킨 메뉴 추가
	public Chicken createChicken(Chicken chicken) {
		return chickenRepository.save(chicken); //DTO에 작성된 컬럼들에 모두 삽입
	}
}
