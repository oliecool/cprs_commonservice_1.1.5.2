package com.hundsun.cprs.sms.service;

import java.util.Map;

import com.hundsun.jresplus.remoting.impl.annotation.Service;
import com.hundsun.jresplus.remoting.impl.annotation.ServiceModule;
import com.hundsun.jresplus.remoting.impl.annotation.ServiceParam;

@ServiceModule
public interface RemoteSendSmsService {

	/**
	 * 传入手机号码，交易商id，模板code，发送时间。返回map
	 * 发送时间为空则立即发送，填写发送时间格式为yyyy-mm-dd hh:mm:ss，例如：2013-08-02 10:02:15
	 * @param sms
	 * @param userId
	 * @param date
	 * @return
	 */
	@Service(functionId = "8801", desc = "初始化加载数据")
	public Map<String, String> toUserSendSms(@ServiceParam("phone")String phone, @ServiceParam("smsCode")String smsCode, 
			@ServiceParam("params")Map<String,String> params, @ServiceParam("userId")Long userId, @ServiceParam("date")String date);
}
