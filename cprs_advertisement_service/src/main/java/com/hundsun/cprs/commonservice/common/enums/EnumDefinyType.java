package com.hundsun.cprs.commonservice.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumDefinyType {
	CLIENT_TYEP("client", "客户定义"),
	SYSTEM_TYPE("system", "系统定义"),
	;
	/** 状态值 */
	private String type;
	/** 状态值说明 */
	private String value;

	private EnumDefinyType(String type, String value) {
		this.type = type;
		this.value = value;
	}

	/**
	 * 全局索引池
	 */
	private static Map<String, EnumDefinyType> pool = new HashMap<String, EnumDefinyType>();
	static {
		for (EnumDefinyType et : EnumDefinyType.values()) {
			pool.put(et.type + "", et);
		}
	}

	/**
	 * 根据内容索引
	 * 
	 * @param code
	 * @return
	 */
	public static EnumDefinyType indexByValue(String code) {
		return pool.get(code);
	}
	public static Map<String, EnumDefinyType> toMap() {
		return pool;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Map<String, EnumDefinyType> getPool() {
		return pool;
	}

	public static void setPool(Map<String, EnumDefinyType> pool) {
		EnumDefinyType.pool = pool;
	}
}
