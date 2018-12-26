package com.hundsun.cprs.plate.response;

import java.util.List;

import com.hundsun.cprs.advertisement.response.BaseResponse;
import com.hundsun.cprs.plate.vo.PlateVo;

public class PlateResponse extends BaseResponse{

    private static final long serialVersionUID = 492027260749097543L;

    private PlateVo plateVo;
	
	private List<PlateVo> plateVoList;

    public PlateVo getPlateVo() {
        return plateVo;
    }

    public void setPlateVo(PlateVo plateVo) {
        this.plateVo = plateVo;
    }

    public List<PlateVo> getPlateVoList() {
        return plateVoList;
    }

    public void setPlateVoList(List<PlateVo> plateVoList) {
        this.plateVoList = plateVoList;
    }

	
}
