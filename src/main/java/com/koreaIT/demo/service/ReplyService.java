package com.koreaIT.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreaIT.demo.repository.ReplyRepository;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.Reply;
import com.koreaIT.demo.vo.ResultData;

@Service
public class ReplyService {
	
	private ReplyRepository replyRepository;
	
	@Autowired
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}
	
	public ResultData<Integer> writeReply(int loginedMemberId, String relTypeCode, int relId, String body) {
		replyRepository.writeReply(loginedMemberId, relTypeCode, relId, body);
		
		int id = replyRepository.getLastInsertId();
		
		return ResultData.from("S-1", Util.f("%d번 댓글이 등록되었습니다", id), "id", id);
	}

	public List<Reply> getReplies(int loginedMemberId, String relTypeCode, int relId) {
		
		List<Reply> replies = replyRepository.getReplies(relTypeCode, relId);
		
		for (Reply reply : replies) {
			actorCanChangeData(loginedMemberId, reply);
		}
		
		return replies;
	}

	public void actorCanChangeData(int loginedMemberId, Reply reply) {
		ResultData actorCanChangeDataRd = actorCanMD(loginedMemberId, reply);
		reply.setActorCanChangeData(actorCanChangeDataRd.isSuccess());
	}

	private ResultData actorCanMD(int loginedMemberId, Reply reply) {
		
		if (reply == null) {
			return ResultData.from("F-1", "해당 댓글은 존재하지 않습니다");
		}
		
		if (loginedMemberId != reply.getMemberId()){
			return ResultData.from("F-2", "해당 댓글에 대한 권한이 없습니다");
		}
		
		return ResultData.from("S-1", "가능");
	}
	
}