package com.hundsun.cprs.commonservice.sms.domain;

import java.util.Date;

import com.hundsun.cprs.commonservice.sms.common.DomainBase;

/**
 * 
 * @author zhangbao
 */
public class SendSms extends DomainBase {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 短信模板ID
     */
    private Long smsId;

    /**
     * 手机号
     */
    private String moblieNo;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 短信发送状态：success=发送成功，fail=发送失败，no_send=未发送等
     */
    private String status;

    /**
     * 是否立即发送：yes=是，no=否。
     */
    private String isTransmit;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 发送失败原因
     */
    private String failReason;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModify;
    
    /**
     * 短信内容
     */
    private String smsContent;

    public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

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