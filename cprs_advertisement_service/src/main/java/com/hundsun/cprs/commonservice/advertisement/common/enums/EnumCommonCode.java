package com.hundsun.cprs.commonservice.advertisement.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 程序异常编码
 * @author linbo
 * @version $Id: EnumCommonCode.java,v 1.1.1.1-RC1 2017年9月13日 Exp $
 */
public enum EnumCommonCode {
    
	COMMON_SUCCESS(0,"成功"),
	COMMON_SERVER_ERROR(-1,"服务器处理异常"),
	COMMON_BUSINESS_ERROR(500,"redis操作出错");
	
	/** 错误码 */
	private int errorNo;
	/** 错误说明 */
	private String errorInfo;

	private EnumCommonCode(int errorNo, String errorInfo) {
		this.errorNo = errorNo;
		this.errorInfo = errorInfo;
	}

	/**
	 * 全局索引池
	 */
	private static Map<String, EnumCommonCode> pool = new HashMap<String, EnumCommonCode>();
	static {
		for (EnumCommonCode et : EnumCommonCode.values()) {
			pool.put(et.errorNo + "", et);
		}
	}

	/**
	 * 根据内容索引
	 * 
	 * @param errorNo
	 * @return
	 */
	public static EnumCommonCode indexByValue(String errorNo) {
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
