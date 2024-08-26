package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.entity.Pizza;
import com.kh.service.PizzaService;


@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	//피자 넣기
	@PostMapping
	public Pizza insertPizza(@RequestBody Pizza pizza) {
		return pizzaService.insertPizza(pizza);
	}
	
	//피자 모두 보기
	@GetMapping
	public List<Pizza> getAllPizza() {
		return pizzaService.getAllPizza();
	}
	
	//피자 검색
	@GetMapping("/search")
	public List<Pizza> searchPizza(@RequestParam("query") String query) {
		return pizzaService.searchPizza(query);
	}
	
}
