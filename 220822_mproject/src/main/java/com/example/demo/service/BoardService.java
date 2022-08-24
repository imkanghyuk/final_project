package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.dao.CommentDao;
import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.exception.BoardNotFoundException;

@Service
public class BoardService {

	@Autowired
	BoardDao boarddao;
	
	// 글쓰기 : 실패하면 409
	public Board write(BoardDto.Write dto, String loginId) {
		Board board = dto.toEntity().addWriter(loginId);
		boarddao.save(board);
		return board;
	}
	
	// 서비스는 업무로직 (비즈니스 로직)이 있는 곳	
	// 글읽기 : 글이 없으면 409(BNFE), 글이 있고 글쓴이이라면 조회수 증가
	public BoardDto.Read read(Integer bno, String loginId){
		BoardDto.Read dto = boarddao.findById(bno).orElseThrow(() -> new BoardNotFoundException());
		
		if(loginId != null && dto.getWriter().equals(loginId)==false) {
			boarddao.update(Board.builder().bno(bno).readCnt(1).build()); // 업데이트시 readCnt 값이 null이 아니면 증가되기 때문에 값을 아무거나 줌
			dto.setReadCnt(dto.getReadCnt()+1); // 다음에 값을 불러올때 1이 증가되기 때문에 인위적으로 상승시키기 위함 
		}
		
		
		
		return dto;
	}
	
	// 글목록 : 글이 없으면 빈 목록
	
	// 글변경 : 실패 - 글이 없다(BoardNotFoundException), 글쓴이가 아니다(JonFailException).
	
	// 글삭제 : 실패 - 글이 없다(BNFE), 글쓴이가 아니다(JFE).
}
