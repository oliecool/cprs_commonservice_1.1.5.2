package com.hundsun.cprs.commonservice.sms.query;

import java.util.Date;

import com.hundsun.cprs.commonservice.sms.common.DomainBase;



/**
 * 
 * @author zhangbao
 */
public class SmsModelQuery extends DomainBase {
    /**
     */
    private Long id;

    /**
     */
    private String smsCode;

    /**
     */
    private String smsType;

    /**
     */
    private String smsContent;

    /**
     */
    private String status;

    /**
     */
    private Date gmtCreate;

    /**
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