package com.hundsun.cprs.commonservice.article.remote;

import com.hundsun.cprs.article.request.ArticleTypeRequest;
import com.hundsun.cprs.article.response.ArticleTypeListResponse;
import com.hundsun.cprs.article.response.ArticleTypeResponse;
import com.hundsun.cprs.article.service.RemoteArticleTypeService;
import com.hundsun.cprs.article.vo.ArticleTypeVo;
import com.hundsun.cprs.commonservice.advertisement.common.enums.EnumBusinessCode;
import com.hundsun.cprs.commonservice.advertisement.common.enums.EnumCommonCode;
import com.hundsun.cprs.commonservice.advertisement.common.util.BeanUtils;
import com.hundsun.cprs.commonservice.article.domain.ArticleType;
import com.hundsun.cprs.commonservice.article.enums.EnumArticleTypeStatus;
import com.hundsun.cprs.commonservice.article.service.ArticleTypeService;
import com.hundsun.cprs.commonservice.article.service.BaseService;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("remoteArticleTypeService")
public class RemoteArticleTypeServiceImpl extends BaseService implements RemoteArticleTypeService {
	@Autowired
	private ArticleTypeService articleTypeService;

	@Override
	public ArticleTypeResponse getArticleTypeByTypeCode(ArticleTypeRequest request){

		ArticleTypeResponse response = new ArticleTypeResponse();
		response.setSuccess(true);
		try{
			Validate.notNull(request.getArticleTypeCode(),"参数非法");

			ArticleType articleType = articleTypeService.selectArticleTypeBycode(request.getArticleTypeCode());

			response.setArticleTypeVo(BeanUtils.transfrom(ArticleTypeVo.class, articleType));

		}catch (IllegalArgumentException e) {
			response.setSuccess(false);
			response.setErrorNo(EnumBusinessCode.BUSINESS_1000.getErrorNo());
			response.setErrorInfo(e.getMessage());			
		}catch (Exception e) {
			logger.error("处理异常",e);
			response.setSuccess(false);
			response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
			response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
		}
		return response;
	}

	@Override
	public ArticleTypeListResponse getArticleTypeList() {
		ArticleTypeListResponse response = new ArticleTypeListResponse();
		response.setSuccess(true);
		try{

			//启用的文章分类code
			List<ArticleType>  list = new ArrayList<ArticleType>();
			List<ArticleType> articleTypeList = articleTypeService.selectArticleTypeList();

			for(ArticleType type : articleTypeList){
				if(type != null && type.getStatus().equals(EnumArticleTypeStatus.ENABLE.getCode())){
					list.add(type);
				}
			}
			response.setArticleVoList(BeanUtils.batchTransform(ArticleTypeVo.class, list));

		} catch (Exception e) {
			logger.error("处理异常",e);
			response.setSuccess(false);
			response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
			response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
		}
		return response;
	}

}
