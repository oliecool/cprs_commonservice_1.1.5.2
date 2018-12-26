package com.hundsun.cprs.advertisement.response;

import java.util.List;

import com.hundsun.cprs.advertisement.vo.AdPositionInfoVo;

public class AdPositionResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;
	private AdPositionInfoVo adPositionInfoVo;
	private List<AdPositionInfoVo> adPositionList;
	public AdPositionInfoVo getAdPositionInfoVo() {
		return adPositionInfoVo;
	}
	public void setAdPositionInfoVo(AdPositionInfoVo adPositionInfoVo) {
		this.adPositionInfoVo = adPositionInfoVo;
	}
	public List<AdPositionInfoVo> getAdPositionList() {
		return adPositionList;
	}
	public void setAdPositionList(List<AdPositionInfoVo> adPositionList) {
		this.adPositionList = adPositionList;
	}
	
}
