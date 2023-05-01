package com.koreaIT.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreaIT.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	public void writeArticle(int memberId, int boardId, String title, String body);
	
	public int getLastInsertId();

	public Article getArticleById(int id);
	
	public List<Article> getArticles(int boardId, int limitStart, int itemsInAPage);

	public void modifyArticle(int id, String title, String body);

	public void deleteArticle(int id);

	public Article getForPrintArticle(int id);

	public int getArticlesCnt(int boardId);

}