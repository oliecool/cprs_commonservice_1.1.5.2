package com.hundsun.cprs.commonservice.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.sms.dao.SendSmsDAO;
import com.hundsun.cprs.commonservice.sms.domain.SendSms;
import com.hundsun.cprs.commonservice.sms.domain.query.SendQuery;
import com.hundsun.cprs.commonservice.sms.service.SendService;
@Service
public class SendServiceImpl implements SendService {
	@Autowired
	private SendSmsDAO sendSmsDAO;
	
	@Override
	public void querySendSmsByPage(SendQuery query) {
		List<SendSms> sendSmsList=sendSmsDAO.querySendSmsByPage(query);
		query.compatible(sendSmsList);
	}

	@Override
	public SendSms selectSmsById(Long id) {
		return sendSmsDAO.selectByPrimaryKey(id);
	}

}
