
package com.koreaIT.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public Article writeArticle(String title, String body);
	
	@Select("SELECT * FROM article WHERE id =#{id}")
	public Article getArticleById(int id);

	@Select("SELECT * FROM article ORDER BY id DESC")
	public List<Article> getArticles();

	@Select("UPDATE article SET update = NOW(), title=#{title},`body`=#{body} WHERE id = #{id}")
	public void modifyArticle(int id, String title, String body);

	
	@Select("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(int id);

}