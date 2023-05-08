package com.koreaIT.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.ReactionPointService;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.ReactionPoint;
import com.koreaIT.demo.vo.ResultData;
import com.koreaIT.demo.vo.Rq;

@Controller
public class UsrReactionPointController {

	private ReactionPointService reactionPointService;
	private Rq rq;

	@Autowired
	public UsrReactionPointController(ReactionPointService reactionPointService, Rq rq) {
		this.reactionPointService = reactionPointService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reactionPoint/getReactionPoint")
	@ResponseBody
	public ResultData<ReactionPoint> getReactionPoint(int relId, String relTypeCode) {
		
		ReactionPoint reactionPoint = reactionPointService.getReactionPoint(rq.getLoginedMemberId(), relId, relTypeCode);
		
		return ResultData.from("S-1", "리액션 정보 조회 성공", "reactionPoint", reactionPoint);
	}
	
	@RequestMapping("/usr/reactionPoint/doInsertReactionPoint")
	@ResponseBody
	public String doInsertReactionPoint(int relId, String relTypeCode, int point) {
		
		reactionPointService.doInsertReactionPoint(rq.getLoginedMemberId(), relId, relTypeCode, point);
		
		if (point == 1) {
			return Util.jsReplace(Util.f("%d번 글에 좋아요", relId), Util.f("../article/detail?id=%d", relId));
		} else {
			return Util.jsReplace(Util.f("%d번 글에 싫어요", relId), Util.f("../article/detail?id=%d", relId));
		}
	}
	
}