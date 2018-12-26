package com.hundsun.cprs.commonservice.plate.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 是否删除状态枚举类
 * @author jinghn20983
 *
 */
public enum EnumIsDeleteStatus {
	ISDELETE_N("N", "没删除"),
	ISDELETE_Y("Y", "删除"),;
	
	private String status;
	private String desc;
	
	private EnumIsDeleteStatus(String status, String desc){
		this.status = status;
		this.desc = desc;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDesc() {
		return desc;
	}
	/**
     * 全局索引池
     */
    private static Map<String, EnumIsDeleteStatus> pool = new HashMap<String, EnumIsDeleteStatus>();
    static {
        for (EnumIsDeleteStatus et : EnumIsDeleteStatus.values()) {
            pool.put(et.status+"", et);
        }
    }
    
   
    /**
     * 根据内容索引
     * @param level
     * @return
     */
    public static EnumIsDeleteStatus indexByValue(String level){
        return pool.get(level);
    }
    public static boolean containLevel(String level){
    	return pool.containsKey(level);
    }
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
