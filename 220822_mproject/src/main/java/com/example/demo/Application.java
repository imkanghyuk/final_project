package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 입력길이에 상관없이 일정한 길이의 결과가 나오는 함수 : hash함수
		// 예) 1234%10->4, 174%10->4
		// 비밀번호는 해시함수로 암호화(해독 불가능)
		// 암호화 작업에는 항상 랜덤이 필요 -> 비밀번호의 경우 salt 필요(자동처리)
		return new BCryptPasswordEncoder();
	}

}
