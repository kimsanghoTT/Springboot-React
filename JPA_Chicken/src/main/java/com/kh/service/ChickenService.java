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
	
	//findById를 쓸 때는 id를 찾지 못하는 예외 사항을 필수로 작성해줘야함
	//상세보기
	public Chicken findById(Integer id) {
		return chickenRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("일치하는 정보를 찾을 수 없습니다."));
	}

	//수정하기
	public Chicken updateChicken(Integer id, Chicken updateChicken) {
		Chicken chicken = chickenRepository.findById(id)
							.orElseThrow(() -> new RuntimeException("일치하는 정보를 찾을 수 없습니다."));
		
		chicken.setChickenName(updateChicken.getChickenName()); //수정된 이름을 가져와서 넣기
		chicken.setDescription(updateChicken.getDescription());
		chicken.setPrice(updateChicken.getPrice());
		return chickenRepository.save(chicken);
	}
	
	//삭제하기
	public void deleteChicken(Integer id) {
		Chicken chicken = chickenRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("일치하는 정보를 찾을 수 없습니다."));
		System.out.println(chicken);
		chickenRepository.delete(chicken);
	}
	
	//검색하기
	public List<Chicken> searchChicken(String query){
		return chickenRepository.findByChickenNameContainingIgnoreCase(query);
	}
}
