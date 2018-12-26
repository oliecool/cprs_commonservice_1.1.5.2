package com.hundsun.cprs.commonservice.advertisement.common;

import java.io.Serializable;

/**
 * 远程服务返回对象基类 子类必须有无参构造函数
 * 
 * @author fish
 * 
 */
public class ServiceResult implements Serializable {

	private static final long serialVersionUID = -3006110260323581844L;

	private Integer errorNO;// 错误代码,缺省为null,无错误

	private String errorInfo;// 错误原因

	private String serviceIp;// 执行service的服务器地址

	/**
	 * 是否有错误
	 * 
	 * @return
	 */
	public boolean error() {
		return !correct();
	}

	/**
	 * 是否正确
	 * 
	 * @return
	 */
	public boolean correct() {
		return this.errorNO == null || this.errorNO == 0;
	}

	public Integer getErrorNO() {
		return errorNO;
	}

	public void setErrorNO(Integer errorNO) {
		this.errorNO = errorNO;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getServiceIp() {
		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}

}
