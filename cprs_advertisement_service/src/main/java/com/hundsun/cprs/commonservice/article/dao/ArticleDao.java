package com.hundsun.cprs.commonservice.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hundsun.cprs.commonservice.article.domain.Article;
import com.hundsun.cprs.commonservice.article.domain.query.ArticleQuery;
import com.hundsun.cprs.commonservice.article.dto.ArticleInfo;

public interface ArticleDao {
	/*
	 * 创建文章
	 * @param Article
	 * */
	public void createArticle(Article article);
	
	/*
	 * 删除文章
	 * @param Long
	 * */
	public void deleteArticle(Long articleId);
	
	/*
	 * 修改文章
	 * @param Article
	 * */
	public void editArticle(Article article);
	
	/*
	 * 分页查询文章
	 * @param ArticleQuery
	 * */
	public List<ArticleInfo> selectArticleByPage(ArticleQuery query);
	
	/*
	 * 根据文章id查询文章
	 * @param Long
	 * */
	public Article selectArticleById(Long articleId);
	
	/*
	 * 根据文章code查询文章
	 * @param String
	 * */
	public Article selectArticleBycode(Article article);
	
	/*
	 * 根据文章类型code查询该分类下的所有文章
	 * @param String
	 * */
	public List<Article> selectArticleListByArticleTypecode(String code);
	
	/*
	 * 根据排序号和分类id查询文章列表
	 * @param int，Long
	 * */
	public List<Article> selectArticleByOrderAndTypeId(@Param("orders")int orders,@Param("articleTypeId")Long articleTypeId);
	
	/*
	 * 根据文章类型code查询该分类下的所有已发布的文章
	 * @param String
	 * */
	public List<Article> getArticleListByTypeCode(String code);
}
