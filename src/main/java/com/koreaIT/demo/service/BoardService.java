package com.koreaIT.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreaIT.demo.repository.BoardRepository;
import com.koreaIT.demo.vo.Board;

//보드서비스에 의존성 주입 해주고
@Service
public class BoardService {
	
	private BoardRepository boardRepository;
	
	@Autowired
	//의존성 주입을 위해 디스를 사용
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	//결과를 남기기 위함
	public Board getBoardById(int boardId) {
		return boardRepository.getBoardById(boardId);
	}

}