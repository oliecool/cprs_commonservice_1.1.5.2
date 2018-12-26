package com.hundsun.cprs.advertisement.service;

import com.hundsun.cprs.advertisement.request.AdPositionRequest;
import com.hundsun.cprs.advertisement.response.AdPositionResponse;
import com.hundsun.cprs.advertisement.response.AdvertisementResponse;
import com.hundsun.jresplus.remoting.impl.annotation.Service;
import com.hundsun.jresplus.remoting.impl.annotation.ServiceModule;
import com.hundsun.jresplus.remoting.impl.annotation.ServiceParam;

@ServiceModule
public interface RemoteAdvertisementService {
	
	@Service(functionId = "311160", desc = "查询某个广告位下的所有广告")
	AdvertisementResponse getAdListByAdPositionCode(@ServiceParam("adPositionCode") String adPositionCode);
	
	@Service(functionId = "311161", desc = "查询广告位列表")
	AdPositionResponse getAdPositionList(AdPositionRequest request);
	
	@Service(functionId = "311162", desc = "根据广告位code查询广告位")
	AdPositionResponse getAdPosition(@ServiceParam("code") String code);
	
	@Service(functionId = "311163", desc = "根据广告id查询广告的详细信息")
	AdvertisementResponse getAdvertisement(@ServiceParam("advertisement_id") Long advertisement_id);
}
