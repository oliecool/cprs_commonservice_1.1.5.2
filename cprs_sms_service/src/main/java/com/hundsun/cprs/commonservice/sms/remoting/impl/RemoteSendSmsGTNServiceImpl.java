/*
 * Hundsun Inc.
 * Copyright (c) 2006-2012 All Rights Reserved.
 *
 * Author     :wuly18574
 * Version    :1.0
 * Create Date:2017年11月23日
 */
package com.hundsun.cprs.commonservice.sms.remoting.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.sms.common.BaseResponse;
import com.hundsun.cprs.commonservice.sms.remoting.RemoteSendSmsGTNService;
import com.hundsun.cprs.commonservice.sms.service.SendSmsService;
import com.hundsun.network.common.service.BaseService;



@Service(value = "remoteSendSmsGTNService")
public class RemoteSendSmsGTNServiceImpl implements RemoteSendSmsGTNService {

	protected static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	
    @Autowired
    private SendSmsService remoteSendSmsServiceGTN;

    @Override
    public BaseResponse getAccesstoken() {
        BaseResponse response = new BaseResponse();
        try {
            remoteSendSmsServiceGTN.getAccessToken();
            return response;
        } catch (Exception e) {
            logger.error("get access_token from redis error", e);
            response.setErrorNOInfo(-999, "获取令牌失败");
            return response;
        }
    }
}
