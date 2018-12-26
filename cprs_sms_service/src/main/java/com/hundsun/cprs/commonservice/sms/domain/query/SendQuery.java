package com.hundsun.cprs.commonservice.sms.domain.query;

import com.hundsun.cprs.commonservice.sms.common.page.Pagination;
import com.hundsun.cprs.commonservice.sms.domain.SendSms;

public class SendQuery extends Pagination<SendSms>{

	private static final long serialVersionUID = 1L;
	private String moblieNo;
	private String status;
	
	public String getMoblieNo() {
		return moblieNo;
	}
	public void setMoblieNo(String moblieNo) {
		this.moblieNo = moblieNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
