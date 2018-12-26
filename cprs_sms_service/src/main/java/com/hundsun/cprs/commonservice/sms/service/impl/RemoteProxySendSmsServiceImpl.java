/*
 * Hundsun Inc.
 * Copyright (c) 2006-2012 All Rights Reserved.
 *
 * Author     :wuly18574
 * Version    :1.0
 * Create Date:2017年1月18日
 */
package com.hundsun.cprs.commonservice.sms.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.sms.service.RemoteSendSmsServiceType;
import com.hundsun.cprs.sms.service.RemoteSendSmsService;

/**
 * @author wuly18574
 */
@Service("remoteSendSmsService")
public class RemoteProxySendSmsServiceImpl implements RemoteSendSmsService {

	@Value("${serviceName}")
	private String serviceName;
	
    private static final Logger log = LoggerFactory.getLogger(RemoteProxySendSmsServiceImpl.class);
    
    @Autowired
    private BeanFactory beanFactory;

    /**
     * @param phone
     * @param smsCode
     * @param params
     * @param userId
     * @param date
     * @return
     */
    @Override
    public Map<String, String> toUserSendSms(String phone, String smsCode,
                                             Map<String, String> params, Long userId, String date) {

        try {
        	
            RemoteSendSmsServiceType remoteSendSmsService = beanFactory.getBean(serviceName,
                RemoteSendSmsServiceType.class);
            return remoteSendSmsService.toUserSendSms(phone, smsCode, params, userId, date);
        } catch (Exception e) {
            log.error("调用remoteSendSmsService.toUserSendSms()方法出错", e);
        }
        return null;
    }

}
