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

	public ResultData actorCanModify(int loginedMemberId, int memberId) {
		
		if (loginedMemberId != memberId) {
			return ResultData.from("F-B", "해당 게시물에 대한 권한이 없습니다");
		}
		
		return ResultData.from("S-1", "수정 가능");
	}


	
	
	
	
	//내용들을 보여주는 기능을 하니까 로그인 되었냐 아니냐 판단을 하기 위해 변수를 넣어준다
	//컨트롤로에서 추가한 파라미터를 넣어준다
	public Article getForPrintArticle(int loginedMemberId, int id) {
		
		
		Article article = articleRepository.getForPrintArticle(id);
		
		//함수 실행문에 로그인 정보가 들어있는 파라미터와 평소에 보여줄수있는 디테일 article파라미터를 같이 넣어준다
		//변경이 가능한지 여부를 판단실행문
		actorCanChangeData(loginedMemberId, article);
		
		//여기는 권한이 없으면 평소대로 보여준다
		return article;
		//결국에 마지막에 보여주는 것은 바로 위 리턴이 화면에 보여진다
	}

	//위의 디테이르 메서드에서 보여주는 함수실행문을 위해서 메서드를 생성하고
	//변경이 가능한지 를 판단하기 위한 메서드 생성
	private void actorCanChangeData(int loginedMemberId, Article article) {
		//디테일 내용들이 없으면 그냥 리턴으로 아래로 넘어간다
		if(article == null) {
			return;
		}
		
		//리절트 데이터를 보여주기 위한 변수를 만들어서 실행문에 파라미터를 넣어준다
		ResultData actorCanChangeDataRd = actorCanDelete(loginedMemberId, article);
		
		//최종적인 권한 체크는 바로 아래의 실행문으로 학인 한다
		article.setActorCanChangeData(actorCanChangeDataRd.isSuccess());
	}

	//리절트 데이터를 보여주기 위해서 메서드를 생성하고
	private ResultData actorCanDelete(int loginedMemberId, Article article) {
		
		//게시물이 없으면 
		if(article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다");
		}
		//권한이 없으면
		if (loginedMemberId != article.getMemberId()) {
			return ResultData.from("F-B", "해당 게시물에 대한 권한이 없습니다");	
		}
		//있으면 삭제 가능 메세지
		return ResultData.from("S-1", "삭제 가능");
	}
	
}