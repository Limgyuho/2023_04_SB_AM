package com.koreaIT.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.ArticleService;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.Article;
import com.koreaIT.demo.vo.ResultData;
import com.koreaIT.demo.vo.Rq;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	@Autowired
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	// 액션 메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpServletRequest req, String title, String body) {
		
		Rq rq = new Rq(req);
		
		if (rq.getLoginedMemberId() == 0) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다", id), "article", articleService.getArticleById(id));
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, HttpServletRequest req, int id) {
		
		Rq rq = new Rq(req);
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		model.addAttribute("article", article);
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpServletRequest req, int id, String title, String body) {
		
		Rq rq = new Rq(req);
		
		if (rq.getLoginedMemberId() == 0) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticleById(id);
		
		ResultData actorCanModifyRd = articleService.actorCanMD(rq.getLoginedMemberId(), article);
		
		if (actorCanModifyRd.isFail()) {
			return ResultData.from(actorCanModifyRd.getResultCode(), actorCanModifyRd.getMsg());
		}
		
		return articleService.modifyArticle(id, title, body);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		
		Rq rq = new Rq(req);
		
		if (rq.getLoginedMemberId() == 0) {
			return Util.jsHistoryBack("로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticleById(id);
		
		ResultData actorCanModifyRd = articleService.actorCanMD(rq.getLoginedMemberId(), article);
		
		if (actorCanModifyRd.isFail()) {
			return Util.jsHistoryBack(actorCanModifyRd.getMsg());
		}
		
		articleService.deleteArticle(id);
		
		return Util.jsReplace(Util.f("%d번 게시물을 삭제했습니다", id), "list");
	}
}