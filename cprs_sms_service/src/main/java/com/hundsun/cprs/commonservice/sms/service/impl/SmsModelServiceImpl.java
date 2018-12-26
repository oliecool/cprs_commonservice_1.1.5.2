package com.hundsun.cprs.commonservice.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.sms.common.ServiceResult;
import com.hundsun.cprs.commonservice.sms.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.sms.dao.SmsModelDAO;
import com.hundsun.cprs.commonservice.sms.domain.SmsModel;
import com.hundsun.cprs.commonservice.sms.domain.query.ModelQuery;
import com.hundsun.cprs.commonservice.sms.service.SmsModelService;
@Service
public class SmsModelServiceImpl implements SmsModelService {
	@Autowired
	private SmsModelDAO smsModelDAO;
	
	@Override
	public void querySmsModelByPage(ModelQuery query) {
		List<SmsModel> smsModelList=smsModelDAO.querySmsModelByPage(query);
		query.compatible(smsModelList);
	}

	@Override
	public ServiceResult createSmsModel(SmsModel smsModel) throws ServiceCodeException {
		ServiceResult result= new ServiceResult();
		result=validateParam(smsModel);
		if(result.getErrorNO()!=null){
			return result;
		}
		smsModelDAO.insert(smsModel);
		return result;
	}

	@Override
	public ServiceResult editSmsModel(SmsModel smsModel) throws ServiceCodeException {
		ServiceResult result= new ServiceResult();
		result=validateParam(smsModel);
		if(result.getErrorNO()!=null){
			return result;
		}
		smsModelDAO.updateByPrimaryKey(smsModel);
		return result;
	}

	@Override
	public void deleteSmsModel(Long id) throws ServiceCodeException {
		smsModelDAO.deleteByPrimaryKey(id);
	}

	@Override
	public SmsModel selectSmsModelByPrimaryKey(Long id) {
		return smsModelDAO.selectByPrimaryKey(id);
	}

	@Override
	public SmsModel selectSmsModelByCode(String code) {
		return smsModelDAO.selectModelBySmsCode(code);
	}
	
	public ServiceResult validateParam(SmsModel smsModel){
		ServiceResult result= new ServiceResult();
		if(smsModel.getSmsCode()==null || smsModel.getSmsCode()==""){
			result.setErrorNO(1000);
			result.setErrorInfo("参数不全，短信模板编码缺失");
			return result;
		}
		if(smsModel.getSmsType()==null || smsModel.getSmsType()==""){
			result.setErrorNO(1001);
			result.setErrorInfo("参数不全，短信模板类型缺失");
			return result;
		}
		if(smsModel.getStatus()==null || smsModel.getStatus()==""){
			result.setErrorNO(1002);
			result.setErrorInfo("参数不全，短信模板状态缺失");
			return result;
		}
		if(smsModel.getSmsContent()==null || smsModel.getSmsContent()==""){
			result.setErrorNO(1003);
			result.setErrorInfo("参数不全，短信模板内容缺失");
			return result;
		}
		return result;
	}
}
