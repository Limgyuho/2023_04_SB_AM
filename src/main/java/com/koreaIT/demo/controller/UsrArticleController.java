package com.koreaIT.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.vo.Article;

@Controller
public class UsrArticleController {
	
	private int lastArticleId;
	private List<Article> articles;
	
	public UsrArticleController(){
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		
		int id = this.lastArticleId + 1;
		this.lastArticleId = id;
		
		Article article = new Article(id, title, body);
		
		articles.add(article);
		
		return article;
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		return this.articles;
	}
	
	
}