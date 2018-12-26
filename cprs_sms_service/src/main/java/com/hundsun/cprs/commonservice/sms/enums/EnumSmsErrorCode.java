package com.hundsun.cprs.commonservice.sms.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信错误枚举类
 * 
 * @author CHENYU
 * @version 
 */
public enum EnumSmsErrorCode {
    
	NO_HUMAN("5101000", "接收人为空"), 
	NO_CONTENT ("5101001", "短信内容为空"),     
	NO_COMPANY("5101002", "机构编码为空"),
	NO_MOBILE("5101003","手机号为空"),
	MOBILE_ERROR("5101004","手机号不合法"),
	TOO_MANEY_MOBILE("5101005", "手机号码超出200个"), 
	NO_SIGNATURE("5101006", "签名为空"),     
	NO_SUBSYSTEM("5101007", "子系统编码为空"),
	NO_OWN("5101008","归属机构编码为空"),
	NO_SMS_SERVICE("5101009","未开通短信发送服务"),	
	NO_EMAIL_SERVICE("5101010","未开通邮件发送服务"),
	NO_AUTHKEY("5101011","授权码为空"),	

	
	;
    
    private String value;
    private String description;
    
    private EnumSmsErrorCode(String value, String description) {
        this.value = value;
        this.description = description;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getDescription() {
        return description;
    }

    public static Map<String, String> toMap() {
        Map<String, String> enumDataMap = new HashMap<String, String>();
        for (EnumSmsErrorCode institution : EnumSmsErrorCode.values()) {
            enumDataMap.put(institution.getValue(), institution.getDescription());
        }
        return enumDataMap;
    }
    
    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static String indexByValue(String value){
        return toMap().get(value);
    }
}
