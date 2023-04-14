
package com.koreaIT.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreaIT.demo.repository.ArticleRepository;
import com.koreaIT.demo.vo.Article;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		
		articleRepository.makeTestData();
	}
	
	public Article writeArticle(String title, String body) {
		return articleRepository.writeArticle(title, body);
	}
	
	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}
	
	public List<Article> getArticles(){
		return articleRepository.getArticles();
	}
	
	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}
	
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}
	
}
