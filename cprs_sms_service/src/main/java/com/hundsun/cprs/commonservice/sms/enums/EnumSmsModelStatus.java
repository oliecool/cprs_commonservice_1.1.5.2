package com.hundsun.cprs.commonservice.sms.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumSmsModelStatus {
	
	USING("using","启用"),
	DISABLE("disable","禁用")
	;
	private String                           value;

    private String                           name;

    /**
     * 全局索引池
     */
    private static Map<String, EnumSmsModelStatus> pool = new HashMap<String, EnumSmsModelStatus>();
    static {
        for (EnumSmsModelStatus et : EnumSmsModelStatus.values()) {
            pool.put(et.value, et);
        }
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumSmsModelStatus indexByValue(String value) {
        return pool.get(value);
    }

    private EnumSmsModelStatus(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public static Map<String, EnumSmsModelStatus> toMap() {
		return pool;
	}
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
