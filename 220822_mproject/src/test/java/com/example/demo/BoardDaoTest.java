package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BoardDao;
import com.example.demo.entity.Board;

// Junit은 단위 테스트 도구 -> 메소드의 동작 확인

@SpringBootTest
public class BoardDaoTest {

	@Autowired
	BoardDao dao;
	
	//@Test
	public void initTest() {
		assertNotNull(dao);
	}
	// TEST 케이스 1 : dao가 생성 되는지 확인 > dao 생성 > not null  dao에 오타나 어노테이션이 있는지 확인용
	
	//@Transactional // 이렬ㄴ의 sql을 모아서 하나의 작업으로 지정
	//@Test
	public void saveTest() {
		Board board = Board.builder().title("test").writer("spring").content("test").build();
		assertEquals(1, dao.save(board));
	}
	// insert, delete, update의 실행 결과는 변경된 행의 개수
	// TEST 케이스 2 : save : Board -> 의 결과값 : 1

	//@Test
	public void countTest() {
		assertEquals(1, dao.count(null));
	}	
	// TEST 케이스 3 : count : count -> 개수를 수동으로 확인해서 assert한다.
	
	//@Test
	public void findAllTest() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", 1);
		map.put("end", 11);
		map.put("writer", null);
		
		assertEquals(1, dao.findAll(map).size());
	}
	// TEST 케이스 4 : findall :글이 없다 11 ~ 14까지 4개를 읽어오자
	
	@Transactional
	//@Test
	public void updateTest() {
		assertNotEquals(0, dao.update(Board.builder().bno(1).readCnt(1).build()));
		assertNotEquals(0, dao.update(Board.builder().bno(1).goodCnt(1).build()));
		assertNotEquals(0, dao.update(Board.builder().bno(1).commentCnt(15).build()));
		assertNotEquals(0, dao.update(Board.builder().bno(1).title("변경").content("변경").build()));		
	}
	// TEST 케이스 5 : 제목 내용 조회수 좋아요 싫어요 변경 가능, 해당 
	
	//@Test
	public void findByidTest() {
		assertEquals(true, dao.findById(1).isPresent());
		assertEquals(true, dao.findById(2).isEmpty());
	}	
	// TEST 케이스 6 : 글이 비어있다, 글이 존재한다 유무 확인
	
	//@Test
	public void findWriterTest() {
		assertEquals("spring", dao.findWriterById(1));
	}	
	// TEST 케이스 7 : 글의 작성자 확인
	
	//@Test
	public void deleteByIdTest() {
		assertEquals(true, dao.findById(1).isEmpty());
		assertEquals(true, dao.findById(2).isEmpty());
	}	
	// TEST 케이스 8 : 글의 삭제 결과 성공시 1 실패시 0 
	
}