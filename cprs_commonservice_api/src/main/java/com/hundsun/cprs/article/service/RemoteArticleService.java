package com.hundsun.cprs.article.service;

import com.hundsun.cprs.article.request.ArticleRequest;
import com.hundsun.cprs.article.request.ArticleTypeRequest;
import com.hundsun.cprs.article.response.ArticleListResponse;
import com.hundsun.cprs.article.response.ArticleResponse;
import com.hundsun.jresplus.remoting.impl.annotation.Service;
import com.hundsun.jresplus.remoting.impl.annotation.ServiceModule;

@ServiceModule
public interface RemoteArticleService {
	
	@Service(functionId = "311164", desc = "查询某一个文章分类下所有已发布的文章")
	public ArticleListResponse getArticleListByTypeCode(ArticleTypeRequest request);
	
	@Service(functionId = "311165", desc = "根据code值或者文章id查询某篇文章的详细内容")
	public ArticleResponse getArticleByCodeOrId(ArticleRequest request);
}
