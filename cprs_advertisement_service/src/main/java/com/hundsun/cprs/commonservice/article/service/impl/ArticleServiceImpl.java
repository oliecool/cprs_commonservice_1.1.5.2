package com.hundsun.cprs.commonservice.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.article.dao.ArticleDao;
import com.hundsun.cprs.commonservice.article.domain.Article;
import com.hundsun.cprs.commonservice.article.domain.query.ArticleQuery;
import com.hundsun.cprs.commonservice.article.dto.ArticleInfo;
import com.hundsun.cprs.commonservice.article.service.ArticleService;
import com.hundsun.cprs.commonservice.article.service.BaseService;
@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public ServiceResult createArticle(Article article) {
		ServiceResult result= new ServiceResult();
		try{
			articleDao.createArticle(article);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public ServiceResult deleteArticle(Long articleId) {
		ServiceResult result= new ServiceResult();
		try{
			articleDao.deleteArticle(articleId);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public ServiceResult editArticle(Article article) {
		ServiceResult result= new ServiceResult();
		try{
			articleDao.editArticle(article);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public void selectArticleByPage(ArticleQuery query) {
		List<ArticleInfo> articleList=articleDao.selectArticleByPage(query);
		query.compatible(articleList);
	}

	@Override
	public Article selectArticleById(Long articleId) {
		return articleDao.selectArticleById(articleId);
	}

	@Override
	public Article selectArticleBycodeOrId(Article article) {		
		return articleDao.selectArticleBycode(article);
	}

	@Override
	public List<Article> selectArticleListByArticleTypecode(String code) {
		return articleDao.selectArticleListByArticleTypecode(code);
	}

	@Override
	public List<Article> selectArticleByOrderAndTypeId(int orders, Long articleTypeId) {
		return articleDao.selectArticleByOrderAndTypeId(orders, articleTypeId);
	}

	@Override
	public List<Article> getArticleListByTypeCode(String code) {
		return articleDao.getArticleListByTypeCode(code);
	}

}
