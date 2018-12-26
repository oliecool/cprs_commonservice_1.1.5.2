package com.hundsun.cprs.commonservice.sms.common.exception;

/**
 * 简单运行时异常封装
 * @author linrl
 * @date 2017年9月16日 下午3:12:25
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -8248917601755821571L;
	
	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(String msg, Throwable e) {
		super(msg, e);
	}
}
