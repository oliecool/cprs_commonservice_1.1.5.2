package com.hundsun.cprs.commonservice.article.domain.query;

import com.hundsun.cprs.commonservice.advertisement.common.page.Pagination;
import com.hundsun.cprs.commonservice.article.dto.ArticleInfo;

public class ArticleQuery extends Pagination<ArticleInfo>{
	
	private static final long serialVersionUID = 1L;
	private Long articalTypeId;//文章分类id
	private String articleRemark;//文章摘要（简介）
	private String articleCode;//文章编码
	private String articleName;//文章标题
	private String isPublish;//是否发布
	
	public Long getArticalTypeId() {
		return articalTypeId;
	}
	public void setArticalTypeId(Long articalTypeId) {
		this.articalTypeId = articalTypeId;
	}
	public String getArticleCode() {
		return articleCode;
	}
	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}

	public String getArticleRemark() {
		return articleRemark;
	}

	public void setArticleRemark(String articleRemark) {
		this.articleRemark = articleRemark;
	}
}
