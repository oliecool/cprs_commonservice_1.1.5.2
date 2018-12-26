package com.hundsun.cprs.article.request;

public class ArticleRequest {
	private Long articleId;//文章主键id
	private String articleCode;//文章编号

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}
	
}
