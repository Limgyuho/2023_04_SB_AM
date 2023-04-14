
package com.koreaIT.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.koreaIT.demo.vo.Article;

@Component
public class ArticleRepository {

	private int lastArticleId;
	private List<Article> articles;

	public ArticleRepository() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
	}
	
	// 서비스 메서드
	public void makeTestData() {
		for (int i = 1; i <= 10; i++) {

			String title = "제목" + i;
			String body = "내용" + i;

			writeArticle(title, body);
		}
	}

	public Article writeArticle(String title, String body) {
		int id = this.lastArticleId + 1;
		this.lastArticleId = id;

		Article article = new Article(id, title, body);

		articles.add(article);

		return article;
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	public List<Article> getArticles(){
		return articles;
	}

	public void modifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);

		article.setTitle(title);
		article.setBody(body);
	}

	public void deleteArticle(int id) {
		Article article = getArticleById(id);

		articles.remove(article);
	}

}