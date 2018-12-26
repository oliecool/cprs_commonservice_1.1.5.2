/*
 * Hundsun Inc.
 * Copyright (c) 2006-2012 All Rights Reserved.
 *
 * Author     :wuly18574
 * Version    :1.0
 * Create Date:2017年11月23日
 */
package com.hundsun.cprs.commonservice.sms.service;

/**
 * @author wuly18574
 * @version $Id: SendSmsService.java,v 0.1 2017年11月23日 下午1:53:21 Exp $
 */
public interface SendSmsService {
    /**
     * 从GTN获取短信令牌
     * @return
     */
    public void getAccessToken();
}
