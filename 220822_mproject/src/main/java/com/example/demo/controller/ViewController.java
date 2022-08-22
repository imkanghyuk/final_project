package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.annotations.ApiIgnore;

// 화면을 띄우는 컨트롤러
@ApiIgnore // swager에서 읽어오지 않도록 설정
@Controller
public class ViewController {

	@GetMapping({"/", "/board/list"})
	public String list() { // 매핑 주소가 2개 이상이라 void 처리 불가능 
		return "/board/list"; // 리턴값으로 주소 지정
	}
	
	@GetMapping("/board/write")
	public void write(){
		
	}
	
	@GetMapping("/board/read")
	public void read() {
		
	}
	
}
