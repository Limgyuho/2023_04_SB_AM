package com.koreaIT.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.demo.vo.ReactionPoint;

@Mapper
public interface ReactionPointRepository {

	@Select("""
			SELECT IFNULL(SUM(`point`), 0) AS sumReactionPoint
				FROM reactionPoint
				WHERE memberId = #{loginedMemberId}
				AND relId = #{relId}
				AND relTypeCode = #{relTypeCode}
			""")
	ReactionPoint getReactionPoint(int loginedMemberId, int relId, String relTypeCode);

	@Insert("""
			INSERT INTO reactionPoint
				SET regDate = NOW(),
					updateDate = NOW(),
					memberId = #{loginedMemberId},
					relTypeCode = #{relTypeCode},
					relId = #{relId},
					`point` = #{point}
			""")
	void doInsertReactionPoint(int loginedMemberId, int relId, String relTypeCode, int point);

	@Delete("""
			DELETE FROM reactionPoint
				WHERE memberId = #{loginedMemberId}
				AND relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	void doDeleteReactionPoint(int loginedMemberId, int relId, String relTypeCode);
	
}