package com.sun.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sub")
public class SubController {
	
	// GET localhost:8080/sub
	// main과 sub 컨트롤러의 주소가 충돌해서 에러가 뜸
	@GetMapping("/")
	public String getMethod() {
		return "sub get method";
	}

}
