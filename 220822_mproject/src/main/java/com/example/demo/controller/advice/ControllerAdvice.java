package com.example.demo.controller.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(ConstraintViolationException.class) // 핸들러 어노테이션 ConstraintViolationException = 에러코드 500
	public ResponseEntity<String> constraintViolationException(){
		return ResponseEntity.status(HttpStatus.CONFLICT).body("데이터가 누락됨"); // 409에러 상태로 바꾸어 body의 내용 출력
	}
}
