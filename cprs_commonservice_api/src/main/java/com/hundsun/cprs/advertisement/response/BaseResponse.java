package com.hundsun.cprs.advertisement.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = -8386282767332441300L;

	private boolean success = true;
	
	private int errorNo = 0;
	
	private String errorInfo = "成功";
	
	public BaseResponse(){
		
	}
	
	public BaseResponse(int errorNo,String errorDesc){
		if(errorNo != 0)
			this.success = false;
		this.errorInfo = errorDesc;
		this.errorNo = errorNo;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
		if(this.errorNo != 0)
			this.success = false;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
}
