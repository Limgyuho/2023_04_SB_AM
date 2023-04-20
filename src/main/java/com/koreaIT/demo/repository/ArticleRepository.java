package com.koreaIT.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreaIT.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	public void writeArticle(int memberId, String title, String body);
	
	public int getLastInsertId();

	public Article getArticleById(int id);
	
	public List<Article> getArticles();

	public void modifyArticle(int id, String title, String body);

	public void deleteArticle(int id);

}