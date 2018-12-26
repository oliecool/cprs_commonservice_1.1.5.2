package com.hundsun.cprs.commonservice.sms.service;

import com.hundsun.cprs.commonservice.sms.common.ServiceResult;
import com.hundsun.cprs.commonservice.sms.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.sms.domain.SmsModel;
import com.hundsun.cprs.commonservice.sms.domain.query.ModelQuery;

public interface SmsModelService {
	/*
	 * 分页查询短信模板列表
	 * @param query
	 * 
	 * */
	public void querySmsModelByPage(ModelQuery query);
	
	/*
	 * 新增短信模板
	 * @param smsModel
	 * 
	 * */
	public ServiceResult createSmsModel(SmsModel smsModel)throws ServiceCodeException;
	
	/*
	 * 修改短信模板
	 * @param smsModel
	 * 
	 * */
	public ServiceResult editSmsModel(SmsModel smsModel)throws ServiceCodeException;
	
	/*
	 * 删除短信模板
	 * @param id
	 * 
	 * */
	public void deleteSmsModel(Long id)throws ServiceCodeException;
	
	/*
	 * 根据主键id查找短信模板
	 * @param id
	 * @return
	 * 
	 * */
	public SmsModel selectSmsModelByPrimaryKey(Long id);
	
	/*
	 * 根据code值查找短信模板
	 * @param code
	 * @return
	 * 
	 * */
	public SmsModel selectSmsModelByCode(String code);
	
}
