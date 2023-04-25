package com.koreaIT.demo.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;

public class Rq {
	
	@Getter
	private int loginedMemberId;

	public Rq(HttpServletRequest req) {
		
		HttpSession httpSession = req.getSession();
		
		int loginedMemberId = 0;
		
		if (httpSession.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
	}
	
	
}