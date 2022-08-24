package com.example.demo.controller.advice;

import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.BoardNotFoundException;
import com.example.demo.exception.JobFailException;

@RestControllerAdvice
public class BoardControllerAdvice { // BoardController의 bindingresult 처리를 위한 핸들러
	
	@ExceptionHandler(ConstraintViolationException.class) // 핸들러 어노테이션 ConstraintViolationException = 에러코드 500
	public ResponseEntity<String> constraintViolationException(){
		return ResponseEntity.status(HttpStatus.CONFLICT).body("데이터가 누락됨"); // 409에러 상태로 바꾸어 body의 내용 출력
		// 500 에러를 내가 지정한 409 에러로 표현하기 위한 예외처리
	}
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<String> sQLException(SQLException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		// sql service 에러 처리
	}
	
	@ExceptionHandler(BoardNotFoundException.class) // BNFE시 내용 처리 (글이 일정하기 때문에 body 내용을 출력) 
	public ResponseEntity<String> BNFException(){
		return ResponseEntity.status(HttpStatus.CONFLICT).body("게시물을 찾을 수 없습니다");
	}
	
	@ExceptionHandler(JobFailException.class) // JFE시 내용 처리 방법 (작업별 메세지를 받아와야하기 때문에 변수 입력 및 입력받은 내용 출력)
	public ResponseEntity<String> JFException(JobFailException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
}
