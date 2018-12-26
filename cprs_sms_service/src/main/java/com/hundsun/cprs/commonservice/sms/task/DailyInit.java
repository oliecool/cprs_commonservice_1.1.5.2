package com.hundsun.cprs.commonservice.sms.task;

import java.io.Serializable;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.sms.remoting.RemoteSendSmsGTNService;


@Service("dailyInit")
public  class DailyInit implements Serializable{
	
	private static final long serialVersionUID = 5047608210726104800L;

	private static final Logger log = LoggerFactory.getLogger(DailyInit.class);
	
	@Autowired
	private RemoteSendSmsGTNService remoteSendSmsGTNService;
	
	public void dailyInit(JobExecutionContext context) {
		try {
			 // 获取短信令牌
	        remoteSendSmsGTNService.getAccesstoken();
		} catch (Exception e) {
			log.error("获取短信令牌异常！");
		}
	}
}
