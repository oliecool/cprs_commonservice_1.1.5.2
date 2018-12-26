package com.hundsun.cprs.commonservice.advertisement.domain;

import com.hundsun.cprs.commonservice.advertisement.common.DomainBase;
import com.hundsun.cprs.commonservice.common.enums.EnumDefinyType;

import java.util.Date;

public class Advertisement extends DomainBase{

	private static final long serialVersionUID = 1L;
	private Long id;	//主键id
	private String title;	//标题
	private String content;	//广告内容
	private String type;	//广告类型
	private String path;	//图片路径
	private String url;	//链接
	private int orders;	//排序号
	private Long adPositionId;	//所属广告位id
	private String adPositionName;//所属广告位名称
	private String definyType = EnumDefinyType.CLIENT_TYEP.getType();//客户定义类型
	private Date beginDate;	//开始时间
	private Date endDate;	//结束时间
	private Date gmtCreate;	//创建时间
	private Date gmtModify;	//修改时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public Long getAdPositionId() {
		return adPositionId;
	}
	public void setAdPositionId(Long adPositionId) {
		this.adPositionId = adPositionId;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public String getAdPositionName() {
		return adPositionName;
	}
	public void setAdPositionName(String adPositionName) {
		this.adPositionName = adPositionName;
	}

	public String getDefinyType() {
		return definyType;
	}

	public void setDefinyType(String definyType) {
		this.definyType = definyType;
	}
}
