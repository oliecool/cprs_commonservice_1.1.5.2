package com.hundsun.cprs.article.vo;

public class ArticleTypeVo {

	/**
	 * 文章类型名称
	 */
	private String articleTypeName;
	/**
	 * 文章类型编码
	 */
	private String articleTypeCode;

	/**
	 * 客户定义client
	 * 系统定义system
	 */
	private String definyType;
	/**
	 * 文章分类描述
	 */
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefinyType() {
		return definyType;
	}

	public void setDefinyType(String definyType) {
		this.definyType = definyType;
	}
}
