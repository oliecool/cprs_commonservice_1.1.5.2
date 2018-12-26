/*
 * Hundsun Inc.
 * Copyright (c) 2006-2012 All Rights Reserved.
 *
 * Author     :wuly18574
 * Version    :1.0
 * Create Date:2017年11月23日
 */
package com.hundsun.cprs.commonservice.sms.remoting;


import com.hundsun.cprs.commonservice.sms.common.BaseResponse;
import com.hundsun.jresplus.remoting.impl.annotation.ServiceModule;

/**
 * 调用alps发送短信服务
 * @author wuly18574
 * @version $Id: RemoteSendSmsService.java,v 0.1 2017年11月23日 上午9:38:15 Exp $
 */
@ServiceModule
public interface RemoteSendSmsGTNService {
    public BaseResponse getAccesstoken();
}
