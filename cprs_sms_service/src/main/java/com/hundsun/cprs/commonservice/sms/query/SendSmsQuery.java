package com.hundsun.cprs.commonservice.sms.query;

import java.util.Date;

import com.hundsun.cprs.commonservice.sms.common.DomainBase;


/**
 * 
 * @author zhangbao
 */
public class SendSmsQuery extends DomainBase {
    /**
     */
    private Long id;

    /**
     */
    private Long smsId;

    /**
     */
    private String moblieNo;

    /**
     */
    private String businessType;

    /**
     */
    private String status;

    /**
     */
    private String isTransmit;

    /**
     */
    private String operator;

    /**
     */
    private String failReason;

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

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public String getMoblieNo() {
        return moblieNo;
    }

    public void setMoblieNo(String moblieNo) {
        this.moblieNo = moblieNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsTransmit() {
        return isTransmit;
    }

    public void setIsTransmit(String isTransmit) {
        this.isTransmit = isTransmit;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
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