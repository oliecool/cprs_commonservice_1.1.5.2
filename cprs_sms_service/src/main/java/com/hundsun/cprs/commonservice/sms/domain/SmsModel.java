package com.hundsun.cprs.commonservice.sms.domain;

import java.util.Date;

import com.hundsun.cprs.commonservice.sms.common.DomainBase;



/**
 * 
 * @author zhangbao
 */
public class SmsModel extends DomainBase {
    /**
     * 模板ID
     */
    private Long id;

    /**
     * 短信代码
     */
    private String smsCode;

    /**
     * 短信类型
     */
    private String smsType;

    /**
     * 短信模板内容
     */
    private String smsContent;

    /**
     * 使用状态：using=使用，disable=禁用
     */
    private String status;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModify;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}