package com.hundsun.cprs.commonservice.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.article.dao.ArticleTypeDao;
import com.hundsun.cprs.commonservice.article.domain.ArticleType;
import com.hundsun.cprs.commonservice.article.domain.query.ArticleTypeQuery;
import com.hundsun.cprs.commonservice.article.service.ArticleTypeService;
import com.hundsun.cprs.commonservice.article.service.BaseService;
@Service
public class ArticleTypeServiceImpl extends BaseService implements ArticleTypeService {
	@Autowired
	private ArticleTypeDao articleTypeDao;
	
	@Override
	public ServiceResult createArticleType(ArticleType articleType) {
		ServiceResult result= new ServiceResult();
		try{
			articleTypeDao.createArticleType(articleType);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public ServiceResult deleteArticleType(Long articleTypeId) {
		ServiceResult result= new ServiceResult();
		try{
			articleTypeDao.deleteArticleType(articleTypeId);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public ServiceResult editArticleType(ArticleType articleType) {
		ServiceResult result= new ServiceResult();
		try{
			articleTypeDao.editArticleType(articleType);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public void selectArticleTypeByPage(ArticleTypeQuery query) {
		List<ArticleType> articleTypeList = articleTypeDao.selectArticleTypeByPage(query);
		query.compatible(articleTypeList);

	}

	@Override
	public ArticleType selectArticleTypeById(Long articleTypeId) {
		return articleTypeDao.selectArticleTypeById(articleTypeId);
	}

	@Override
	public ArticleType selectArticleTypeBycode(String code) {
		return articleTypeDao.selectArticleTypeBycode(code);
	}

	@Override
	public List<ArticleType> selectArticleTypeList() {
		return articleTypeDao.selectArticleTypeList();
	}

	@Override
	public List<ArticleType> selectFirstArticleTypeList() {
		return articleTypeDao.selectFirstArticleTypeList();
	}

	@Override
	public List<ArticleType> selectSecondArticleTypeListById(Long articleTypeId) {
		return articleTypeDao.selectSecondArticleTypeListById(articleTypeId);
	}

	@Override
	public List<ArticleType> selectSecondArticleTypeList() {
		return articleTypeDao.selectSecondArticleTypeList();
	}
}
