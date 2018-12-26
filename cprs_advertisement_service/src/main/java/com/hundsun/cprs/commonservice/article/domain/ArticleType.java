package com.hundsun.cprs.commonservice.article.domain;

import com.hundsun.cprs.commonservice.advertisement.common.DomainBase;
import com.hundsun.cprs.commonservice.common.enums.EnumDefinyType;

import java.util.Date;

/*
 * 文章分类实体类
 * 
 * */
public class ArticleType extends DomainBase{

	private static final long serialVersionUID = 1L;
	private Long id;//主键id
	private String articleTypeName;//文章类型名称
	private String articleTypeCode;//文章类型编码
	private String status="Y";//文章分类状态
	private String description;//文章分类描述
	private String definyType = EnumDefinyType.CLIENT_TYEP.getType();//客户定义类型
	private Long parentId;//父分类id
	private Date gmtCreate;	//创建时间
	private Date gmtModify;	//修改时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModify() {
		return gmtModify;
	}
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getDefinyType() {
		return definyType;
	}

	public void setDefinyType(String definyType) {
		this.definyType = definyType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
