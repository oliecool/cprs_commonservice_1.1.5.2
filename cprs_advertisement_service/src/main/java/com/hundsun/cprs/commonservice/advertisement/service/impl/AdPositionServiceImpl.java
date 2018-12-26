package com.hundsun.cprs.commonservice.advertisement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.advertisement.request.AdPositionRequest;
import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.advertisement.dao.AdPositionDao;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdPositionQuery;
import com.hundsun.cprs.commonservice.advertisement.service.BaseService;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionService;
@Service
public class AdPositionServiceImpl extends BaseService implements AdPositionService{

	@Autowired
	private AdPositionDao adPositionDao;
	
	@Override
	public void createAdPosition(AdPosition news) {		
		try{
			adPositionDao.createAdPosition(news);
		}catch(ServiceCodeException e){
			logger.error(e);
			throw e;
		}
	}

	@Override
	public void queryAdPositionByPage(AdPositionQuery query) {
		int count=adPositionDao.selectAdPositionCount(query);
		query.setTotalCount(count);
		List<AdPosition> adPositionList=adPositionDao.selectAdPositionByPage(query, query.getBeginStart(),query.getPageSize());
		query.setData(adPositionList);
	}

	@Override
	public ServiceResult deleteAdPosition(Long id) {
		ServiceResult result= new ServiceResult();
		try{
			adPositionDao.deleteAdPosition(id);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public ServiceResult editAdPosition(AdPosition adPosition) {
		ServiceResult result= new ServiceResult();
		try{
			adPositionDao.editAdPosition(adPosition);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public AdPosition selectAdPositionById(Long id) {	
		return adPositionDao.selectAdPositionById(id);
	}

	@Override
	public List<AdPosition> selectAdPositionList() {
		return adPositionDao.selectAdPositionList();
	}

	@Override
	public List<AdPosition> selectAdPositionBycode(String code) {
		return adPositionDao.selectAdPositionBycode(code);
	}

	@Override
	public List<AdPosition> selectAdPositionBycodeOrType(AdPositionRequest request) {
		return adPositionDao.selectAdPositionBycodeOrType(request.getAdPositioncode(),request.getAdPositionTypeCode());
	}

	@Override
	public List<AdPosition> selectAdPositionByAdPositionTypeId(String code) {
		AdPositionRequest request = new AdPositionRequest();
		request.setAdPositionTypeCode(code);
		return adPositionDao.selectAdPositionBycodeOrType(request.getAdPositioncode(),request.getAdPositionTypeCode());
	}

}
