package com.hundsun.cprs.commonservice.advertisement.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumAdvertisementType {
	
	IMAGE_TYPE("1", "图片"),
	TEXT_TYPE("2", "文本"),
	;
	/** 状态值 */
	private String code;
	/** 状态值说明 */
	private String value;

	private EnumAdvertisementType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * 全局索引池
	 */
	private static Map<String, EnumAdvertisementType> pool = new HashMap<String, EnumAdvertisementType>();
	static {
		for (EnumAdvertisementType et : EnumAdvertisementType.values()) {
			pool.put(et.code + "", et);
		}
	}

	/**
	 * 根据内容索引
	 * 
	 * @param code
	 * @return
	 */
	public static EnumAdvertisementType indexByValue(String code) {
		return pool.get(code);
	}
	public static Map<String, EnumAdvertisementType> toMap() {
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

	public static Map<String, EnumAdvertisementType> getPool() {
		return pool;
	}

	public static void setPool(Map<String, EnumAdvertisementType> pool) {
		EnumAdvertisementType.pool = pool;
	}
}
