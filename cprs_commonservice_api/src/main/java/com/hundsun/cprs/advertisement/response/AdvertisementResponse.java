package com.hundsun.cprs.advertisement.response;

import java.util.List;

import com.hundsun.cprs.advertisement.vo.AdvertisementInfoVo;

public class AdvertisementResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;
	private AdvertisementInfoVo advertisementInfoVo;
	private List<AdvertisementInfoVo> advertisementInfoVoList;
	public AdvertisementInfoVo getAdvertisementInfoVo() {
		return advertisementInfoVo;
	}
	public void setAdvertisementInfoVo(AdvertisementInfoVo advertisementInfoVo) {
		this.advertisementInfoVo = advertisementInfoVo;
	}
	public List<AdvertisementInfoVo> getAdvertisementInfoVoList() {
		return advertisementInfoVoList;
	}
	public void setAdvertisementInfoVoList(List<AdvertisementInfoVo> advertisementInfoVoList) {
		this.advertisementInfoVoList = advertisementInfoVoList;
	}
	
}
