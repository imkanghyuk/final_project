package com.example.demo.controller.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BoardControllerAdvice { // BoardController의 bindingresult 처리를 위한 핸들러
	
	@ExceptionHandler(ConstraintViolationException.class) // 핸들러 어노테이션 ConstraintViolationException = 에러코드 500
	public ResponseEntity<String> constraintViolationException(){
		return ResponseEntity.status(HttpStatus.CONFLICT).body("데이터가 누락됨"); // 409에러 상태로 바꾸어 body의 내용 출력
		// 500 에러를 내가 지정한 409 에러로 표현하기 위한 예외처리
	}
}
