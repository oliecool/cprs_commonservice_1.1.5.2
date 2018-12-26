package com.hundsun.cprs.article.vo;

import java.util.Date;

public class ArticleVo {
	private Long id;//主键id
	private Long articalTypeId;//文章分类id
	private String articleCode;//文章编码
	private String articleRemark;//文章简介
	private String articleName;//文章标题
	private String logoImageUrl;//文章logo图片
	private String articleText;//文章内容
	private String articleAuthor;//文章作者
	private Date publishDate;//发布时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getArticleText() {
		return articleText;
	}
	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}
	public String getArticleAuthor() {
		return articleAuthor;
	}
	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getArticleRemark() {
		return articleRemark;
	}

	public void setArticleRemark(String articleRemark) {
		this.articleRemark = articleRemark;
	}

	public String getLogoImageUrl() {
		return logoImageUrl;
	}

	public void setLogoImageUrl(String logoImageUrl) {
		this.logoImageUrl = logoImageUrl;
	}
}
