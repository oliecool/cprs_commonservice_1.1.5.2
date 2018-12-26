package com.hundsun.cprs.commonservice.advertisement.domain.query;

import com.hundsun.cprs.commonservice.advertisement.common.page.Pagination;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionType;

public class AdPositionTypeQuery extends Pagination<AdPositionType>{

	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
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
	
}
