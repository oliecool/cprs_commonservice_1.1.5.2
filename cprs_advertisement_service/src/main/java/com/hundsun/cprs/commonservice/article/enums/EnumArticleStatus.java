package com.hundsun.cprs.commonservice.article.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumArticleStatus {
	FINISH_PUBLISH("Y", "已发布"),
	WAIT_PUBLISH("N", "待发布"),
	CANCEL_PUBLISH("C","取消发布")
	;
	/** 状态值 */
	private String code;
	/** 状态值说明 */
	private String value;

	private EnumArticleStatus(String code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * 全局索引池
	 */
	private static Map<String, EnumArticleStatus> pool = new HashMap<String, EnumArticleStatus>();
	static {
		for (EnumArticleStatus et : EnumArticleStatus.values()) {
			pool.put(et.code + "", et);
		}
	}

	/**
	 * 根据内容索引
	 * 
	 * @param code
	 * @return
	 */
	public static EnumArticleStatus indexByValue(String code) {
		return pool.get(code);
	}
	public static Map<String, EnumArticleStatus> toMap() {
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

	public static Map<String, EnumArticleStatus> getPool() {
		return pool;
	}

	public static void setPool(Map<String, EnumArticleStatus> pool) {
		EnumArticleStatus.pool = pool;
	}
}
