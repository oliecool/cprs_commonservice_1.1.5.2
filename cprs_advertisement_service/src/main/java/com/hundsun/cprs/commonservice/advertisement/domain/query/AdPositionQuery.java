package com.hundsun.cprs.commonservice.advertisement.domain.query;

import com.hundsun.cprs.commonservice.advertisement.common.page.Pagination;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;

public class AdPositionQuery extends Pagination<AdPosition>{

	private static final long serialVersionUID = 1L;
	private String name;//广告位名称
	private String code;//广告位编码
	private String systemtype;//所属系统类型
	private Long adPositionTypeId; //所属分类id
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSystemtype() {
		return systemtype;
	}
	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
	public Long getAdPositionTypeId() {
		return adPositionTypeId;
	}
	public void setAdPositionTypeId(Long adPositionTypeId) {
		this.adPositionTypeId = adPositionTypeId;
	}
	
}
