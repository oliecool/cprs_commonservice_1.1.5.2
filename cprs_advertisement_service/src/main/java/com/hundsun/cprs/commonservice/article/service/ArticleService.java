package com.hundsun.cprs.commonservice.article.service;

import java.util.List;

import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.article.domain.Article;
import com.hundsun.cprs.commonservice.article.domain.query.ArticleQuery;

public interface ArticleService {
	/*
	 * 创建文章
	 * @param Article
	 * */
	public ServiceResult createArticle(Article article);
	
	/*
	 * 删除文章
	 * @param Long
	 * */
	public ServiceResult deleteArticle(Long articleId);
	
	/*
	 * 修改文章
	 * @param Article
	 * */
	public ServiceResult editArticle(Article article);
	
	/*
	 * 分页查询文章
	 * @param ArticleQuery
	 * */
	public void selectArticleByPage(ArticleQuery query);
	
	/*
	 * 根据文章id查询文章
	 * @param Long
	 * */
	public Article selectArticleById(Long articleId);
	
	/*
	 * 根据文章code查询文章
	 * @param String
	 * */
	public Article selectArticleBycodeOrId(Article article);
	
	/*
	 * 根据文章类型code查询该分类下的所有文章
	 * @param String
	 * */
	public List<Article> selectArticleListByArticleTypecode(String code);
	
	/*
	 * 根据排序号和分类id查询文章列表
	 * @param int，Long
	 * */
	public List<Article> selectArticleByOrderAndTypeId(int orders,Long articleTypeId);
	
	/*
	 * 根据文章类型code查询该分类下的所有已发布的文章
	 * @param String
	 * */
	public List<Article> getArticleListByTypeCode(String code);
}
