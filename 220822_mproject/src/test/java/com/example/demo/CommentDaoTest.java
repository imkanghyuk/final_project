package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CommentDao;
import com.example.demo.entity.Comment;

@SpringBootTest
public class CommentDaoTest {
	
	@Autowired
	private CommentDao dao;
	
	// save test
	@Transactional
	//@Test
	public void save() {
		Comment co = Comment.builder().writer("spring").content("test").bno(1).build();
		assertEquals(1, dao.save(co));
	}
	
	// findByBno test	
	//@Test
	public void findBybnoTest() {
		assertEquals(1, dao.findByBno(1).size());
		assertEquals(1, dao.findByBno(10).size());
	}	
	
	// findwritebyCno test	
	@Test
	public void findWriterByCno() {
		assertEquals("spring", dao.findWriterById(2).get());
		assertEquals(true, dao.findWriterById(1).isEmpty());
	}
	
	// deleteBycno test	
	@Transactional
	//@Test
	public void deleteByCno() {
		assertEquals(1, dao.deleteByCno(2));
		assertEquals(0, dao.deleteByCno(1)); 
	}
	
	// deleteByBno test	
	@Transactional
	//@Test
	public void deleteByBno() {
		assertEquals(1, dao.deleteByBno(1));
		assertEquals(0, dao.deleteByBno(2));
	}

}
