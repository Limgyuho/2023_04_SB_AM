package com.koreaIT.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.demo.vo.Board;

@Mapper
public interface BoardRepository {

	@Select("""
			SELECT *
				FROM board
				WHERE id = #{boardId}
				AND delStatus = 0
			""")
	public Board getBoardById(int boardId);
}