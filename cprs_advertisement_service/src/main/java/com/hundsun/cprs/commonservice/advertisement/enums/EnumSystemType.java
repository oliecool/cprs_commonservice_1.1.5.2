package com.hundsun.cprs.commonservice.advertisement.enums;

import java.util.HashMap;
import java.util.Map;


public enum EnumSystemType {
	BID_SYSYTEM("0", "竞购系统"), 
	TRADE_SYSTEM("1", "交易系统"), 
	UC_SYSTEM("2", "会员系统"), 
	FUND_SYSTEM("3", "资金系统"),
	BROKER_SYSTEM("4", "佣金宝系统"),
	;
	/** 状态值 */
	private String code;
	/** 状态值说明 */
	private String value;

	private EnumSystemType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * 全局索引池
	 */
	private static Map<String, EnumSystemType> pool = new HashMap<String, EnumSystemType>();
	static {
		for (EnumSystemType et : EnumSystemType.values()) {
			pool.put(et.code + "", et);
		}
	}

	/**
	 * 根据内容索引
	 * 
	 * @param code
	 * @return
	 */
	public static EnumSystemType indexByValue(String code) {
		return pool.get(code);
	}
	public static Map<String, EnumSystemType> toMap() {
		return pool;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Map<String, EnumSystemType> getPool() {
		return pool;
	}

	public static void setPool(Map<String, EnumSystemType> pool) {
		EnumSystemType.pool = pool;
	}
}
