package com.hundsun.cprs.commonservice.article.remote;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.article.request.ArticleRequest;
import com.hundsun.cprs.article.request.ArticleTypeRequest;
import com.hundsun.cprs.article.response.ArticleListResponse;
import com.hundsun.cprs.article.response.ArticleResponse;
import com.hundsun.cprs.article.service.RemoteArticleService;
import com.hundsun.cprs.article.vo.ArticleVo;
import com.hundsun.cprs.commonservice.advertisement.common.enums.EnumBusinessCode;
import com.hundsun.cprs.commonservice.advertisement.common.enums.EnumCommonCode;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.advertisement.common.util.BeanUtils;
import com.hundsun.cprs.commonservice.article.domain.Article;
import com.hundsun.cprs.commonservice.article.enums.EnumArticleStatus;
import com.hundsun.cprs.commonservice.article.service.ArticleService;
import com.hundsun.cprs.commonservice.article.service.BaseService;
@Service("remoteArticleService")
public class RemoteArticleServiceImpl extends BaseService implements RemoteArticleService {
	@Autowired
	private ArticleService articleService;
	
	@Override
	public ArticleListResponse getArticleListByTypeCode(ArticleTypeRequest request) {
		ArticleListResponse response = new ArticleListResponse();
		try{
			Validate.notNull(request.getArticleTypeCode(),"参数非法");
			List<Article> articleList=articleService.getArticleListByTypeCode(request.getArticleTypeCode());
			response.setArticleVoList(BeanUtils.batchTransform(ArticleVo.class, articleList));
		}catch (IllegalArgumentException e) {
			response.setErrorNo(EnumBusinessCode.BUSINESS_1000.getErrorNo());
			response.setErrorInfo(e.getMessage());			
		} catch (ServiceCodeException e) {
			response.setErrorNo(e.getErrorCode());
			response.setErrorInfo(e.getMessage());
			response.setSuccess(false);
		}catch (Exception e) {
			logger.error("处理异常",e);
			response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
			response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
		}
		return response;
	}

	@Override
	public ArticleResponse getArticleByCodeOrId(ArticleRequest request) {
		ArticleResponse response = new ArticleResponse();
		try{
			Validate.notNull(request,"参数非法");
			if(request.getArticleCode()==null && request.getArticleId()==null){
				response.setErrorNo(EnumBusinessCode.BUSINESS_1001.getErrorNo());
				response.setErrorInfo(EnumBusinessCode.BUSINESS_1001.getErrorInfo());
			}else if("".equals(request.getArticleCode()) && request.getArticleId()==null){
				response.setErrorNo(EnumBusinessCode.BUSINESS_1001.getErrorNo());
				response.setErrorInfo("paramter null error!");
			}else{
				Article articleParam = new Article();
				articleParam.setArticleCode(request.getArticleCode());
				articleParam.setId(request.getArticleId());
				articleParam.setIsPublish(EnumArticleStatus.FINISH_PUBLISH.getCode());
				Article article = articleService.selectArticleBycodeOrId(articleParam);
				response.setArticleVo(BeanUtils.transfrom(ArticleVo.class, article));
			}
		}catch (IllegalArgumentException e) {
			response.setErrorNo(EnumBusinessCode.BUSINESS_1000.getErrorNo());
			response.setErrorInfo(e.getMessage());			
		} catch (ServiceCodeException e) {
			response.setErrorNo(e.getErrorCode());
			response.setErrorInfo(e.getMessage());
			response.setSuccess(false);
		}catch (Exception e) {
			logger.error("处理异常",e);
			response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
			response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
		}
		return response;
	}

}
