package com.hundsun.cprs.commonservice.sms.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.sms.common.BaseResponse;
import com.hundsun.cprs.commonservice.sms.remoting.RemoteSendSmsGTNService;
import com.hundsun.network.common.service.BaseService;

@Service("autoGetAccessTokenService")
public class AutoGetAccessTokenService {
	protected static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	@Autowired
	private RemoteSendSmsGTNService remoteSendSmsGTNService;
	
	@PostConstruct
	public void init(){
		 BaseResponse response = new BaseResponse();
		 response=remoteSendSmsGTNService.getAccesstoken();
		 if(response.getErrorNO()!=null){
			 logger.error("应用启动时自动获取令牌失败，服务异常");
		 }else{
			 logger.info("应用启动自动获取令牌成功");
		 }
	}
}
