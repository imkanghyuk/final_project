package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.exception.BoardNotFoundException;
import com.example.demo.exception.JobFailException;
import com.example.demo.service.BoardService;

@SpringBootTest
public class BoardServiceTest {
	
	@Autowired
	private BoardService service;
	
	
	//@Test
	public void saveTest() {
		BoardDto.Write dto = BoardDto.Write.builder().title("aaa").content("bbbb").build();
		Board board = service.write(dto, "spring");
		
		assertNotNull(board.getBno());
	}
	
	@Transactional
	@Test
	public void readTest() {
		
		Assertions.assertThrows(BoardNotFoundException.class, () -> service.read(1000,  "spring"));
		
		assertEquals(1, service.read(5, null).getReadCnt());		
		assertEquals(1, service.read(5, "spring").getReadCnt());
		assertEquals(2, service.read(5, "winter").getReadCnt());		
	}
	
	//@Test
	public void listTest() {
		assertNotNull(service.list(1, null));
		//BoardDto.Page a = service.list(1, "spring");
	}
	
	@Transactional
	//@Test
	public void updateTest() {
		BoardDto.Update dto = BoardDto.Update.builder().bno(4).content("bbb").build();		
		BoardDto.Update dto2 = BoardDto.Update.builder().bno(100).content("bbb").build();
		BoardDto.Update dto3 = BoardDto.Update.builder().bno(5).content("bbb").title("dddd").build();
		
		service.update(dto, "spring");
		service.update(dto3, "spring");
		
		Assertions.assertThrows(JobFailException.class, () -> service.update(dto, "spring1"));
		Assertions.assertThrows(BoardNotFoundException.class, () -> service.update(dto2, "spring"));
	}
	
	
	
	@Transactional
	//@Test
	public void deleteTest() {
		
		
		service.delete(4, "spring");
		
		Assertions.assertThrows(BoardNotFoundException.class, () -> service.delete(1000, "spring"));
		
		Assertions.assertThrows(JobFailException.class, () -> service.delete(5, "spring1"));
		
	}
}
