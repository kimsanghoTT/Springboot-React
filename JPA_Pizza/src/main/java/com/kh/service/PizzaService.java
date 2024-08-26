package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.entity.Pizza;
import com.kh.repository.PizzaRepository;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	//피자 메뉴 추가하기
	public Pizza insertPizza(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}
	
	//피자 메뉴 모두 보기
	public List<Pizza> getAllPizza(){
		return pizzaRepository.findAll();
	}
	
	//피자 검색하기
	public List<Pizza> searchPizza(String query){
		return pizzaRepository.findByNameContainingIgnoreCase(query);
	}
}
