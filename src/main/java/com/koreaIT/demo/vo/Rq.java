package com.koreaIT.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreaIT.demo.util.Util;

import lombok.Getter;

public class Rq {
	
	@Getter//롬복으로 게터세터를 가져오는 것이 가능하지만 게터만 필요하므로 게터만 사용한다
//컨트롤로에서 세션을 통해 판별하던 것들 이곳에서 한다
	private int loginedMemberId;
//	private HttpServletRequest req;
	private HttpServletResponse resp;

	//실행되자마자 생성자가 실행이 된다
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		
//		this.req = req;
		this.resp = resp;
		
		//리퀘스트에서 세션을 반번에 꺼네어야 한다
		HttpSession httpSession = req.getSession();
		
		int loginedMemberId = 0;
		
		//꺼낸세션을 로그인 판별을 하여 확인 한다 로그인을 한상태
		if (httpSession.getAttribute("loginedMemberId") != null) {
//			로그인을 한상태라면 로그인드 멤버에 값을 넣어준다
			//로그인드 멤버의 타입이 인트라서 형변환을 해주어야한다
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
	}

	public void jsPrintHistoryBack(String msg) {
		resp.setContentType("text/html; charset=UTF-8;");
		
		//순수한 문자로 들어간다
		print(Util.jsHistoryBack(msg));
		
		
	}

	private void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}