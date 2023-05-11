package com.koreaIT.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.ReplyService;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.Reply;
import com.koreaIT.demo.vo.ResultData;
import com.koreaIT.demo.vo.Rq;

@Controller
public class UsrReplyController {

	private ReplyService replyService;
	private Rq rq;

	@Autowired
	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body) {

		ResultData<Integer> replyWriteRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		return Util.jsReplace(replyWriteRd.getMsg(), Util.f("../article/detail?id=%d", relId));
	}
	
	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Reply reply = replyService.getReply(id);
		
		ResultData actorCanMDRd = replyService.actorCanMD(rq.getLoginedMemberId(), reply);
		
		if (actorCanMDRd.isFail()) {
			return Util.jsHistoryBack(actorCanMDRd.getMsg());
		}
		
		replyService.deleteReply(id);
		
		return Util.jsReplace(Util.f("%d번 댓글을 삭제했습니다", id), Util.f("../article/detail?id=%d", reply.getRelId()));
	}

	
}