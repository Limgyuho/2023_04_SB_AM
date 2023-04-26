package com.koreaIT.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreaIT.demo.repository.ArticleRepository;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.Article;
import com.koreaIT.demo.vo.ResultData;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public void writeArticle(int memberId, String title, String body) {
		articleRepository.writeArticle(memberId, title, body);
	}
	
	public int getLastInsertId() {
		return articleRepository.getLastInsertId();
	}
	
	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}
	
	public List<Article> getArticles(){
		return articleRepository.getArticles();
	}
	
	public ResultData<Article> modifyArticle(int id, String title, String body) {
		
		articleRepository.modifyArticle(id, title, body);
		
		return ResultData.from("S-1", Util.f("%d번 게시물을 수정했습니다", id), "article", getArticleById(id));
		
	}
	
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}
	
	//실직적인 수정 삭제 권한 체크는 이곳에서 한다
	//수정 삭제 기능을 한번에 하기 위함
	public ResultData actorCanMD(int loginedMemberId, Article article) {
		//한번에 가능하게 만들어 컨트롤로에서 널검증을 하여 보여주던 것을 지우고
		//여기서 한번에 보여주는 것이 가능하다
		if(article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다");
		}
		
		if (loginedMemberId != article.getMemberId()) {
			return ResultData.from("F-B", "해당 게시물에 대한 권한이 없습니다");	
		}
		
		return ResultData.from("S-1", "가능");
	}

	//디테일 부분에서 버튼이 보이냐 안보이냐 부분이 이곳에서
	public Article getForPrintArticle(int loginedMemberId, int id) {
		
		Article article = articleRepository.getForPrintArticle(id);
		
		//실제 실행은 아래 실행문 에서 하며
		actorCanChangeData(loginedMemberId, article);
		
		return article;
	}
	//실제로actorCanChangeData(loginedMemberId, article);은 이곳에서 동작한다
	private void actorCanChangeData(int loginedMemberId, Article article) {
		
		ResultData actorCanChangeDataRd = actorCanMD(loginedMemberId, article);
		
		article.setActorCanChangeData(actorCanChangeDataRd.isSuccess());
	}


	
}