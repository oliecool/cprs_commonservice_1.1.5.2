package com.hundsun.cprs.commonservice.article.domain.query;

import com.hundsun.cprs.commonservice.advertisement.common.page.Pagination;
import com.hundsun.cprs.commonservice.article.domain.ArticleType;

public class ArticleTypeQuery extends Pagination<ArticleType>{

	private static final long serialVersionUID = 1L;
	private String articleTypeName;//文章类型名称
	private String articleTypeCode;//文章类型编码
	private String status;//文章分类状态
	
	public String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}
	public String getArticleTypeCode() {
		return articleTypeCode;
	}
	public void setArticleTypeCode(String articleTypeCode) {
		this.articleTypeCode = articleTypeCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
