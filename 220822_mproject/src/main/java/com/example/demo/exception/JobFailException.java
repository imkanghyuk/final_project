package com.example.demo.exception;

import lombok.AllArgsConstructor;

// 오류 메세지 : 특정 동작이 실패했을 경우 출력
// 해당 작업이 실패시 메세지 추가하여 해당 메세지 출력
@AllArgsConstructor
public class JobFailException extends RuntimeException {
	String message;
}
