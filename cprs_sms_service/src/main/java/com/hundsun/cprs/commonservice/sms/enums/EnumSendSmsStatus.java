package com.hundsun.cprs.commonservice.sms.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送状态
 * @author qinjun
 *
 */
public enum EnumSendSmsStatus {


    SUCCESS("success", "发送成功"),
    NOSEND("no_send", "未发送"),
    INSEND("in_send", "发送中"),
    FAIL("fail", "发送失败") ;

    private String                           value;

    private String                           name;

    /**
     * 全局索引池
     */
    private static Map<String, EnumSendSmsStatus> pool = new HashMap<String, EnumSendSmsStatus>();
    static {
        for (EnumSendSmsStatus et : EnumSendSmsStatus.values()) {
            pool.put(et.value, et);
        }
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumSendSmsStatus indexByValue(String value) {
        return pool.get(value);
    }

    private EnumSendSmsStatus(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public static Map<String, EnumSendSmsStatus> toMap() {
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
