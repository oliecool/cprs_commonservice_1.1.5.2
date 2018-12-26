package com.hundsun.cprs.commonservice.sms.domain.query;

import com.hundsun.cprs.commonservice.sms.common.page.Pagination;
import com.hundsun.cprs.commonservice.sms.domain.SmsModel;

public class ModelQuery extends Pagination<SmsModel>{

	private static final long serialVersionUID = 1L;
	private String smsType;
	private String smsCode;
	private String status;
	
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
