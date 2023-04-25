package com.koreaIT.demo.controller;

import java.util.List;

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
	public ResultData<Article> doAdd(HttpSession httpSession, String title, String body) {
		
		if (httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		articleService.writeArticle((int) httpSession.getAttribute("loginedMemberId"), title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다", id), "article", articleService.getArticleById(id));
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, HttpSession httpSession, int id) {
		
		//로그인이 되었을때 디테일부분에서 권한을 확인 하기 위해 서비스 부분으로 넘겨주기 위해 변수를 만들고
		int loginedMemberId = 0;
		
		//세션으로 권한 체크를 한후
		if (httpSession.getAttribute("loginedMemberId") != null) {
			//서비스로 넘겨주기 위해 만들어주 변수에 결과를 형변환을 한다 
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		//서비스 디테일을 바라보는 부분에 변수를 넘겨주어 야 하기 때문에 파라미터를 추가한다
		Article article = articleService.getForPrintArticle(loginedMemberId, id);
		
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
	public ResultData<Article> doModify(HttpSession httpSession, int id, String title, String body) {
		
		if (httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify((int) httpSession.getAttribute("loginedMemberId"), article.getMemberId());
		
		if (actorCanModifyRd.isFail()) {
			return ResultData.from(actorCanModifyRd.getResultCode(), actorCanModifyRd.getMsg());
		}
		
		return articleService.modifyArticle(id, title, body);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession httpSession, int id) {
		
		if (httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if ((int) httpSession.getAttribute("loginedMemberId") != article.getMemberId()) {
			return ResultData.from("F-B", "해당 게시물에 대한 권한이 없습니다");
		}
		
		articleService.deleteArticle(id);
		
		return ResultData.from("S-1", Util.f("%d번 게시물을 삭제했습니다", id));
	}
}