package com.koreaIT.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.koreaIT.demo.vo.Rq;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {

	@Override
	//웹이 시작 하자 마자 요청을 받는데 그 순간 이곳으로 들어 온다
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//파라미터로 받은 리퀘스트를 rq에 담아 
		//키로 보낸다
		Rq rq = new Rq(request, response);
		request.setAttribute("rq", rq);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}