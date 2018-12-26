package com.hundsun.cprs.commonservice.article.domain;

import com.hundsun.cprs.commonservice.advertisement.common.DomainBase;
import com.hundsun.cprs.commonservice.common.enums.EnumDefinyType;

import java.util.Date;
/*
 * 文章实体类
 * 
 * */
public class Article extends DomainBase{

	private static final long serialVersionUID = 1L;
	private Long id;//主键id
	private Long articalTypeId;//文章分类id
	private String articleCode;//文章编码
	private String articleName;//文章标题
	private String articleRemark;//文章摘要（简介）
	private String articleText;//文章内容
	private String logoImageUrl;//文章logo图片
	private String articleAuthor;//文章作者
	private String isPublish;//是否发布
	private String isTop;//是否置顶
	private int orders;//排序号
	private String definyType = EnumDefinyType.CLIENT_TYEP.getType();//客户定义类型
	private Date publishDate;//发布时间
	//private Date beginDate;//开始时间
	//private Date endDate;//结束时间
	private Date gmtCreate;	//创建时间
	private Date gmtModify;	//修改时间
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
	public String getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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

	public String getDefinyType() {
		return definyType;
	}

	public void setDefinyType(String definyType) {
		this.definyType = definyType;
	}
}
