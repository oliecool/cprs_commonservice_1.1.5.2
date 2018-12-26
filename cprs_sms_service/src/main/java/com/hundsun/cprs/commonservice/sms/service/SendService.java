package com.hundsun.cprs.commonservice.sms.service;

import com.hundsun.cprs.commonservice.sms.domain.SendSms;
import com.hundsun.cprs.commonservice.sms.domain.query.SendQuery;

public interface SendService {
	/*
	 * 分页查询已发送短信列表
	 * @param query
	 * */
	public void querySendSmsByPage(SendQuery query);
	
	/*
	 * 根据id查询短信信息
	 * @param id
	 * */
	public SendSms selectSmsById(Long id);
}
