package com.hundsun.cprs.commonservice.article.dto;

import com.hundsun.cprs.commonservice.article.domain.Article;

public class ArticleInfo extends Article {

	private static final long serialVersionUID = 1L;
	private String articleTypeName;
	public String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}
	

}
