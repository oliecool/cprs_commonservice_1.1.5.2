package com.hundsun.cprs.commonservice.sms.dao;

import java.util.List;

import com.hundsun.cprs.commonservice.sms.domain.SmsModel;
import com.hundsun.cprs.commonservice.sms.domain.query.ModelQuery;
import com.hundsun.cprs.commonservice.sms.query.SmsModelQuery;

public interface SmsModelDAO {
	
	/**
	 * 根据主键ID删除短信模板
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加短信模板
     * @param smsModel
     * @return
     */
    Long insert(SmsModel smsModel);
    
    /**
     * 根据主键id查询短信模板对象
     * @param id
     * @return
     */
    SmsModel selectByPrimaryKey(Long id);


    /**
     * 更新短信模板内容
     * @param smsModel
     * @return
     */
    int updateByPrimaryKey(SmsModel smsModel);
    
    /**
     * 分页查询短信模板
     * @param smsModelQuery
     */
    void selectByPage(SmsModelQuery smsModelQuery);
    
    /**
     * 修改短信模板状态
     * @param smsModel
     */
    void changeSmsModelStatus(SmsModel smsModel);

    /**
     * 根据模板code查询未被禁用的模板信息
     * @param smsCode
     * @return
     */
	SmsModel selectBySmsCode(String smsCode);
	
	/*
	 * 分页查询模板列表
	 * @param query
	 * @return
	 * */
	List<SmsModel> querySmsModelByPage(ModelQuery query);
	/**
     * 根据模板code查询模板信息
     * @param smsCode
     * @return
     */
	SmsModel selectModelBySmsCode(String code);
}