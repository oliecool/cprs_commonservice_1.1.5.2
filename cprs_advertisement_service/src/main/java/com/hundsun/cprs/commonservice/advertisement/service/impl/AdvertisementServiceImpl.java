package com.hundsun.cprs.commonservice.advertisement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.common.enums.EnumBusinessCode;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.advertisement.dao.AdPositionDao;
import com.hundsun.cprs.commonservice.advertisement.dao.AdvertisementDao;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.domain.Advertisement;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdvertisementQuery;
import com.hundsun.cprs.commonservice.advertisement.service.AdvertisementService;
import com.hundsun.cprs.commonservice.advertisement.service.BaseService;
@Service
public class AdvertisementServiceImpl extends BaseService implements AdvertisementService {
	
	@Autowired
	private AdvertisementDao advertisementDao;
	@Autowired
	private AdPositionDao adPositionDao;
	
	@Override
	public ServiceResult createAdvertisement(Advertisement advertisement) {
		ServiceResult result= new ServiceResult();
		try{
			advertisementDao.createAdvertisement(advertisement);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public void queryBypage(AdvertisementQuery query) {
		List<Advertisement> advertisementList = advertisementDao.queryBypage(query);		
		query.compatible(advertisementList);
	}

	@Override
	public ServiceResult editAdvertisement(Advertisement advertisement) {
		ServiceResult result= new ServiceResult();
		try{
			advertisementDao.editAdvertisement(advertisement);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public ServiceResult deleteAdvertisement(Long id) {
		ServiceResult result= new ServiceResult();
		try{
			advertisementDao.deleteAdvertisement(id);
		}catch(ServiceCodeException e){
			result.setErrorInfo(e.getErrorDesc());
			result.setErrorNO(e.getErrorCode());
			logger.error(e);			
		}
		return result;
	}

	@Override
	public Advertisement selectAdvertisementById(Long id) {
		return advertisementDao.selectAdvertisementById(id);
	}

	@Override
	public List<Advertisement> selectListByTypeAndCode(String adPositionCode) {
		List<AdPosition> adPositionList=adPositionDao.selectAdPositionBycode(adPositionCode);
		AdPosition adPosition = new AdPosition();
		if(adPositionList.isEmpty()){
			return null;
		}else{
			adPosition = adPositionList.get(0);
			return advertisementDao.selectListByTypeAndCode(adPosition.getId());
		}
	}

	@Override
	public List<Advertisement> selectAdvertisementByorders(int orders,Long adPositionId) {
		return advertisementDao.selectAdvertisementByorders(orders,adPositionId);
	}

}
