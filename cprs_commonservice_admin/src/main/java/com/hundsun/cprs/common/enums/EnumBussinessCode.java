package com.hundsun.cprs.common.enums;

import java.util.HashMap;
import java.util.Map;


public enum EnumBussinessCode {
	/*时间1000-1099*/
	BEGIN_GREATER_THAN_END("1000", "开始时间不能大于结束时间"),
	END_LESS_THAN_NOW("1001", "结束时间不能小于当前时间"),
	
	/*文章1100-1199*/
	ARTICLE_SAVE_SUCCESS("1100", "文章保存成功"),
	ARTICLE_SAVE_FAILED("1101", "文章保存失败"),
	ARTICLE_PUBLISH_SUCCESS("1102", "文章发布成功"),	
	ARTICLE_PUBLISH_FAILED("1103", "文章发布失败"),
	ARTICLE_DELETE_SUCCESS("1104", "文章删除成功"),
	ARTICLE_DELETE_FAILED("1105", "文章删除失败"),
	ARTICLE_EDIT_SUCCESS("1106", "文章编辑成功"),
	ARTICLE_EDIT_FAILED("1107", "文章编辑失败"),
	ARTICLE_CANCELPUBLISH_SUCCESS("1108", "文章取消发布成功"),
	ARTICLE_CANCELPUBLISH_FAILED("1109", "文章取消发布失败"),
	
	/*文章分类1200-1299*/
	ARTICLETYPE_SAVE_SUCCESS("1200","文章分类添加成功"),
	ARTICLETYPE_EDIT_SUCCESS("1201","文章分类编辑成功"),
	ARTICLETYPE_DELETE_SUCCESS("1202","文章分类删除成功"),
	ARTICLETYPE_DELETE_FAILED("1203","该分类下存在内容，不允许删除"),
	SYSTEMTYPE_DELETE_FAILED("1204","该分类为系统创建，不允许删除"),
	;
	/** 状态值 */
	private String code;
	/** 状态值说明 */
	private String value;

	private EnumBussinessCode(String code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * 全局索引池
	 */
	private static Map<String, EnumBussinessCode> pool = new HashMap<String, EnumBussinessCode>();
	static {
		for (EnumBussinessCode et : EnumBussinessCode.values()) {
			pool.put(et.code + "", et);
		}
	}

	/**
	 * 根据内容索引
	 * 
	 * @param code
	 * @return
	 */
	public static EnumBussinessCode indexByValue(String code) {
		return pool.get(code);
	}
	public static Map<String, EnumBussinessCode> toMap() {
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

	public static Map<String, EnumBussinessCode> getPool() {
		return pool;
	}

	public static void setPool(Map<String, EnumBussinessCode> pool) {
		EnumBussinessCode.pool = pool;
	}
}
