package com.hundsun.cprs.commonservice.plate.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 省市区枚举类
 * @author jinghn20983
 *
 */
public enum EnumPlateType {
	PLATE_ONE("P", "省份"),
	PLATE_TWO("C", "城市"),
	PLATE_THREE("A", "区"),;
	
	private String type;
	private String desc;
	
	private EnumPlateType(String type, String desc){
		this.type = type;
		this.desc = desc;
	}
	
	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDesc() {
		return desc;
	}
	/**
     * 全局索引池
     */
    private static Map<String, EnumPlateType> pool = new HashMap<String, EnumPlateType>();
    static {
        for (EnumPlateType et : EnumPlateType.values()) {
            pool.put(et.type+"", et);
        }
    }
    
    public static Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (EnumPlateType record : values()) {
            map.put(record.getType(), record.getDesc());
        }
        return map;
    }
    
    /**
     * 根据内容索引
     * @param level
     * @return
     */
    public static EnumPlateType indexByValue(String level){
        return pool.get(level);
    }
    public static boolean containLevel(String level){
    	return pool.containsKey(level);
    }
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
