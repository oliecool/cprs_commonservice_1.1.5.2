package com.hundsun.cprs.commonservice.advertisement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.advertisement.dao.AdPositionTypeDao;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionType;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdPositionTypeQuery;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionTypeService;
import com.hundsun.cprs.commonservice.advertisement.service.BaseService;
@Service
public class AdPositionTypeServiceImpl extends BaseService implements AdPositionTypeService {
	
	@Autowired
	private AdPositionTypeDao adPositionTypeDao;
	
	@Override
	public ServiceResult createAdPositionType(AdPositionType adPositionType) {
		ServiceResult result= new ServiceResult();
		try{
			adPositionTypeDao.createAdPositionType(adPositionType);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public void queryByPage(AdPositionTypeQuery query) {
		List<AdPositionType> result= adPositionTypeDao.queryByPage(query);
		query.compatible(result);
	}

	@Override
	public ServiceResult deleteAdPositionType(Long id) {
		ServiceResult result= new ServiceResult();
		try{
			adPositionTypeDao.deleteAdPositionType(id);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public ServiceResult editAdPositionType(AdPositionType adPositionType) {
		ServiceResult result= new ServiceResult();
		try{
			adPositionTypeDao.editAdPositionType(adPositionType);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public AdPositionType selectAdPositionTypeById(Long id) {		
		return adPositionTypeDao.selectAdPositionTypeById(id);
	}

	@Override
	public List<AdPositionType> selectAdPositionTypeList() {
		return adPositionTypeDao.selectAdPositionTypeList();
	}

	@Override
	public List<AdPositionType> selectAdPositionTypeByCode(String code) {
		return adPositionTypeDao.selectAdPositionTypeByCode(code);
	}

}
