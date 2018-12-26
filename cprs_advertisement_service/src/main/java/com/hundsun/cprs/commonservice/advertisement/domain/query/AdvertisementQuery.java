package com.hundsun.cprs.commonservice.advertisement.domain.query;

import java.util.Date;

import com.hundsun.cprs.commonservice.advertisement.common.page.Pagination;
import com.hundsun.cprs.commonservice.advertisement.domain.Advertisement;

public class AdvertisementQuery extends Pagination<Advertisement>{

	private static final long serialVersionUID = 1L;
	private String title;	//标题
	private String type;	//广告类型
	private Long adPositionId;	//所属广告位id
	private Date beginDate;	//开始时间
	private Date endDate;	//结束时间
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
}
