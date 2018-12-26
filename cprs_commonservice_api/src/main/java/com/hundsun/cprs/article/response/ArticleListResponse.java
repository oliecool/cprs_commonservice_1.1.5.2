package com.hundsun.cprs.article.response;

import java.util.List;

import com.hundsun.cprs.article.vo.ArticleVo;

public class ArticleListResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	private List<ArticleVo> articleVoList;
	public List<ArticleVo> getArticleVoList() {
		return articleVoList;
	}
	public void setArticleVoList(List<ArticleVo> articleVoList) {
		this.articleVoList = articleVoList;
	}
	
}
