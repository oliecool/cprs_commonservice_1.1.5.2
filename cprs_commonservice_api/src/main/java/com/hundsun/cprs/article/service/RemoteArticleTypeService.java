package com.hundsun.cprs.article.service;

import com.hundsun.cprs.article.request.ArticleTypeRequest;
import com.hundsun.cprs.article.response.ArticleTypeListResponse;
import com.hundsun.cprs.article.response.ArticleTypeResponse;
import com.hundsun.jresplus.remoting.impl.annotation.Service;
import com.hundsun.jresplus.remoting.impl.annotation.ServiceModule;

@ServiceModule
public interface RemoteArticleTypeService {
	
	@Service(functionId = "311170", desc = "查询某一个文章分类根据文章分类code")
	public ArticleTypeResponse getArticleTypeByTypeCode(ArticleTypeRequest request);

	@Service(functionId = "311171", desc = "查询所有文章分类")
	public ArticleTypeListResponse getArticleTypeList();
}
