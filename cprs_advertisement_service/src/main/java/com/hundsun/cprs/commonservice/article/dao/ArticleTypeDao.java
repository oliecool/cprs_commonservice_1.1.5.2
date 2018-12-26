package com.hundsun.cprs.commonservice.article.dao;

import java.util.List;

import com.hundsun.cprs.commonservice.article.domain.ArticleType;
import com.hundsun.cprs.commonservice.article.domain.query.ArticleTypeQuery;


public interface ArticleTypeDao {
	/*
	 * 创建文章类型
	 * @param ArticleType
	 * */
	public void createArticleType(ArticleType articleType);
	
	/*
	 * 删除文章类型
	 * @param Long
	 * */
	public void deleteArticleType(Long articleTypeId);
	
	/*
	 * 修改文章类型
	 * @param ArticleType
	 * */
	public void editArticleType(ArticleType articleType);
	
	/*
	 * 分页查询文章类型
	 * @param ArticleTypeQuery
	 * */
	public List<ArticleType> selectArticleTypeByPage(ArticleTypeQuery query);
	
	/*
	 * 根据文章类型id查询文章类型
	 * @param Long
	 * */
	public ArticleType selectArticleTypeById(Long articleTypeId);
	
	/*
	 * 根据文章类型code查询文章类型
	 * @param String
	 * */
	public ArticleType selectArticleTypeBycode(String code);
	
	/*
	 * 查询所有的文章分类
	 * 
	 * */
	public List<ArticleType> selectArticleTypeList();

	/*
	 * 查询所有的文章一级分类
	 *
	 * */
	public List<ArticleType> selectFirstArticleTypeList();
	
	/*
	 * 查询所有的文章二级分类
	 *
	 * */
	public List<ArticleType> selectSecondArticleTypeList();

	/*
	 * 根据一级分类id查询所有的二级分类
	 *
	 * */
	public List<ArticleType> selectSecondArticleTypeListById(Long articleTypeId);
}
