package com.hundsun.cprs.commonservice.sms.common;

public class BaseResponse extends DomainBase {

	private static final long serialVersionUID = 2L;
	/**
	 * 错误编号</br>
	 * 操作正确请设置为null
	 */
	private Integer errorNO = null;
	/** 错误信息 */
	private String errorInfo;
	/** ip */
	private String serviceIp;

	private String successInfo;

	public boolean error() {
		return !correct();
	}

	public boolean correct() {
		return this.errorNO == null || this.errorNO ==0;
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

	public void setErrorNOInfo(Integer errorNO, String errorInfo) {
		this.errorNO = errorNO;
		this.errorInfo = errorInfo;
	}

	public String getSuccessInfo() {
		return successInfo;
	}

	public void setSuccessInfo(String successInfo) {
		this.successInfo = successInfo;
	}
}
