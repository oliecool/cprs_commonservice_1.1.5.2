package com.hundsun.cprs.article.response;

import com.hundsun.cprs.article.vo.ArticleVo;

public class ArticleResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;
	private ArticleVo articleVo;
	public ArticleVo getArticleVo() {
		return articleVo;
	}
	public void setArticleVo(ArticleVo articleVo) {
		this.articleVo = articleVo;
	}
	
	
}
