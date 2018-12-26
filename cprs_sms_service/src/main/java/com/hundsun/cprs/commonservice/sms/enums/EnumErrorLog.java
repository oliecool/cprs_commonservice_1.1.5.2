package com.hundsun.cprs.commonservice.sms.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumErrorLog {
	PHONE_ERROR("1111", "根据id没有查到手机号码id="), 
	UC_PHONE_ERROR("2222", "根据传值userid查询错误id="),
	SMS_MODEL_ERROR("SMS_MODEL_ERROR", "没有查询到有效的短信模板"),
	PARAMETER_ERROR("PARAMETER_ERROR", "参数错误"),
	;
    private String value;
    
    private String name;
    
    
    private static Map<String, EnumErrorLog> pool = new HashMap<String, EnumErrorLog>();
    static {
        for (EnumErrorLog et : EnumErrorLog.values()) {
            pool.put(et.value, et);
        }
    }

    public static EnumErrorLog indexByValue(String value){
        return pool.get(value);
    }
    
    private EnumErrorLog(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName(){
      return name;
    }
}