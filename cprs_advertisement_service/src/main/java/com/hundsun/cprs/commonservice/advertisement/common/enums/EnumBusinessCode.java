package com.hundsun.cprs.commonservice.advertisement.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务码枚举
 * 
 * @author linbo
 *
 */
public enum EnumBusinessCode {
	//1000-1099 通用的业务提示
	BUSINESS_1000(1000, "参数错误"),
	BUSINESS_1001(1001, "参数为空"),
	/**短信发送失败*/
	BUSINESS_1007(1006, "短信发送失败"),
	/**请稍后再试*/
	BUSINESS_1011(1011, "请勿重复操作"),
	;
	/** 业务错误码 */
	private int errorNo;
	/** 业务错误说明 */
	private String errorInfo;

	private EnumBusinessCode(int errorNo, String errorInfo) {
		this.errorNo = errorNo;
		this.errorInfo = errorInfo;
	}

	/**
	 * 全局索引池
	 */
	private static Map<String, EnumBusinessCode> pool = new HashMap<String, EnumBusinessCode>();
	static {
		for (EnumBusinessCode et : EnumBusinessCode.values()) {
			pool.put(et.errorNo + "", et);
		}
	}

	/**
	 * 根据内容索引
	 * 
	 * @param errorNo
	 * @return
	 */
	public static EnumBusinessCode indexByValue(String errorNo) {
		return pool.get(errorNo);
	}

	public int getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

}
