package com.hundsun.cprs.commonservice.advertisement.remote;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.advertisement.request.AdPositionRequest;
import com.hundsun.cprs.advertisement.response.AdPositionResponse;
import com.hundsun.cprs.advertisement.response.AdvertisementResponse;
import com.hundsun.cprs.advertisement.service.RemoteAdvertisementService;
import com.hundsun.cprs.advertisement.vo.AdPositionInfoVo;
import com.hundsun.cprs.advertisement.vo.AdvertisementInfoVo;
import com.hundsun.cprs.commonservice.advertisement.common.enums.EnumBusinessCode;
import com.hundsun.cprs.commonservice.advertisement.common.enums.EnumCommonCode;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.advertisement.common.util.BeanUtils;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.domain.Advertisement;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionService;
import com.hundsun.cprs.commonservice.advertisement.service.AdvertisementService;
import com.hundsun.cprs.commonservice.advertisement.service.BaseService;
@Service("remoteAdvertisementService")
public class RemoteAdvertisementServiceImpl extends BaseService implements RemoteAdvertisementService {
	@Autowired
	private AdPositionService adPositionService;
	@Autowired
	private AdvertisementService advertisementService;
	
	@Override
	public AdvertisementResponse getAdListByAdPositionCode(String adPositionCode) {
		AdvertisementResponse response = new AdvertisementResponse();
		try{
			Validate.notNull(adPositionCode,"参数非法");
			List<Advertisement> advertisementList=advertisementService.selectListByTypeAndCode(adPositionCode);
			response.setAdvertisementInfoVoList(BeanUtils.batchTransform(AdvertisementInfoVo.class, advertisementList));
		}catch (IllegalArgumentException e) {
			response.setErrorNo(EnumBusinessCode.BUSINESS_1000.getErrorNo());
			response.setErrorInfo(e.getMessage());			
		} catch (ServiceCodeException e) {
			response.setErrorNo(e.getErrorCode());
			response.setErrorInfo(e.getMessage());
			response.setSuccess(false);
		}catch (Exception e) {
			logger.error("处理异常",e);
			response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
			response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
		}
		return response;
	}

	@Override
	public AdPositionResponse getAdPositionList(AdPositionRequest request) {
		AdPositionResponse response = new AdPositionResponse();
		try{
			Validate.notNull(request,"参数非法");
			List<AdPosition> adPositionList=adPositionService.selectAdPositionBycodeOrType(request);
			response.setAdPositionList(BeanUtils.batchTransform(AdPositionInfoVo.class, adPositionList));
		}catch (IllegalArgumentException e) {
			response.setErrorNo(EnumBusinessCode.BUSINESS_1000.getErrorNo());
			response.setErrorInfo(e.getMessage());			
		} catch (ServiceCodeException e) {
			response.setErrorNo(e.getErrorCode());
			response.setErrorInfo(e.getMessage());
			response.setSuccess(false);
		}catch (Exception e) {
			logger.error("处理异常",e);
			response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
			response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
		}
		return response;
	}

	@Override
	public AdvertisementResponse getAdvertisement(Long advertisement_id) {
		AdvertisementResponse response = new AdvertisementResponse();
		try{
			Validate.notNull(advertisement_id,"参数非法");
			Advertisement advertisement = advertisementService.selectAdvertisementById(advertisement_id);
			response.setAdvertisementInfoVo(BeanUtils.transfrom(AdvertisementInfoVo.class, advertisement));
		}catch (IllegalArgumentException e) {
			response.setErrorNo(EnumBusinessCode.BUSINESS_1000.getErrorNo());
			response.setErrorInfo(e.getMessage());			
		} catch (ServiceCodeException e) {
			response.setErrorNo(e.getErrorCode());
			response.setErrorInfo(e.getMessage());
			response.setSuccess(false);
		}catch (Exception e) {
			logger.error("处理异常",e);
			response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
			response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
		}
		return response;
	}

	@Override
	public AdPositionResponse getAdPosition(String code) {
		AdPositionResponse response = new AdPositionResponse();
		try{
			Validate.notNull(code,"参数非法");
			List<AdPosition> adPositionList= adPositionService.selectAdPositionBycode(code);
			if(adPositionList.isEmpty()){
				response.setAdPositionInfoVo(BeanUtils.transfrom(AdPositionInfoVo.class, null));
				return response;				
			}else{
				AdPosition adPosition = adPositionList.get(0);
				response.setAdPositionInfoVo(BeanUtils.transfrom(AdPositionInfoVo.class, adPosition));
			}
		}catch (IllegalArgumentException e) {
			response.setErrorNo(EnumBusinessCode.BUSINESS_1000.getErrorNo());
			response.setErrorInfo(e.getMessage());			
		} catch (ServiceCodeException e) {
			response.setErrorNo(e.getErrorCode());
			response.setErrorInfo(e.getMessage());
			response.setSuccess(false);
		}catch (Exception e) {
			logger.error("处理异常",e);
			response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
			response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
		}
		return response;
	}

}
