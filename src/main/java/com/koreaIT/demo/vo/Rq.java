package com.koreaIT.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreaIT.demo.util.Util;

import lombok.Getter;

public class Rq {
	
	@Getter
	private int loginedMemberId;
//	private HttpServletRequest req;
	private HttpServletResponse resp;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		
//		this.req = req;
		this.resp = resp;
		
		HttpSession httpSession = req.getSession();
		
		int loginedMemberId = 0;
		
		if (httpSession.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
	}

	public void jsPrintHistoryBack(String msg) {
		resp.setContentType("text/html; charset=UTF-8;");
		
		println("<script>");
		
		if (!Util.empty(msg)) {
			println("alert('" + msg + "');");
		}
		
		println("history.back();");
		println("</script>");
	}

	private void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void println(String str) {
		print(str + "\n");
	}
	
	
}