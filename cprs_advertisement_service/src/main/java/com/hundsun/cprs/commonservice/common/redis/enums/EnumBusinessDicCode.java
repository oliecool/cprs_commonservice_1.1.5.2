package com.hundsun.cprs.commonservice.common.redis.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EnumBusinessDicCode {
	    TOKEN_DAY_GET("access_token","null"),// 发送短信令牌
	    ;
	    private String                                  key;
	    private String                                  defaultValue;

	    /**
	     * 
	     */
	    private static Map<String, EnumBusinessDicCode> pool = new HashMap<String, EnumBusinessDicCode>();
	    static {
	        for (EnumBusinessDicCode dic : EnumBusinessDicCode.values()) {
	            pool.put(dic.key, dic);
	        }
	    }

	    private EnumBusinessDicCode(String key, String defaultValue) {
	        this.key = key;
	        this.defaultValue = defaultValue;
	    }

	    public String getKey() {
	        return key;
	    }

	    public String getDefaultValue() {
	        return defaultValue;
	    }

	    public static Map<String, String> toMap() {
	        Map<String, String> enumDataMap = new HashMap<String, String>();
	        for (EnumBusinessDicCode dic : EnumBusinessDicCode.values()) {
	            enumDataMap.put(dic.getKey(), dic.getDefaultValue());
	        }
	        return enumDataMap;
	    }

	    /**
	     * 
	     * @param key
	     * @return
	     */
	    public static EnumBusinessDicCode getDefaultValueByKey(String key) {
	        return pool.get(key);
	    }

	    /**
	     * 
	     * @param key
	     * @return
	     */
	    public static List<EnumBusinessDicCode> indexByGroup(String key) {
	        List<EnumBusinessDicCode> list = new ArrayList<EnumBusinessDicCode>();
	        for (EnumBusinessDicCode dic : EnumBusinessDicCode.values()) {
	            if (dic.getKey().equals(key))
	                list.add(dic);
	        }
	        return list;
	    }
}
