package com.hundsun.cprs.commonservice.sms.service;

import java.util.Map;

public interface RemoteSendSmsServiceType {

	/**
	 * 分成几种不同的发送短信的方式，gtn、hundsun、等
	 * @param sendPhone
	 * @param smsCode
	 * @param params
	 * @param userId
	 * @param date
	 * @return
	 */
	public Map<String, String> toUserSendSms(final String sendPhone, final String smsCode,
			final Map<String, String> params, final Long userId, final String date);
}
