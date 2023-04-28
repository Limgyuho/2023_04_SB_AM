package com.koreaIT.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.demo.vo.Board;

@Mapper
//서비스의 부분을 레파지토리로 일시킨다
public interface BoardRepository {

	@Select("""
			SELECT *
				FROM board
				WHERE id = #{boardId}
				AND delStatus = 0
			""")
	public Board getBoardById(int boardId);
		
	
}