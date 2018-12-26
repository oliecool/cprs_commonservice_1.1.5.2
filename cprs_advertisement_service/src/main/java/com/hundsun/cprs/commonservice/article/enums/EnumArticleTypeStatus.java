package com.hundsun.cprs.commonservice.article.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumArticleTypeStatus {
	ENABLE("Y", "启用"),
	DISABLE("N", "禁用"),
	;
	/** 状态值 */
	private String code;
	/** 状态值说明 */
	private String value;

	private EnumArticleTypeStatus(String code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * 全局索引池
	 */
	private static Map<String, EnumArticleTypeStatus> pool = new HashMap<String, EnumArticleTypeStatus>();
	static {
		for (EnumArticleTypeStatus et : EnumArticleTypeStatus.values()) {
			pool.put(et.code + "", et);
		}
	}

	/**
	 * 根据内容索引
	 * 
	 * @param code
	 * @return
	 */
	public static EnumArticleTypeStatus indexByValue(String code) {
		return pool.get(code);
	}
	public static Map<String, EnumArticleTypeStatus> toMap() {
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

	public static Map<String, EnumArticleTypeStatus> getPool() {
		return pool;
	}

	public static void setPool(Map<String, EnumArticleTypeStatus> pool) {
		EnumArticleTypeStatus.pool = pool;
	}
}
