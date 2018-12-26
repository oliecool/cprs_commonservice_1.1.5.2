package com.hundsun.cprs.plate;

import com.hundsun.cprs.plate.request.PlateRequest;
import com.hundsun.cprs.plate.response.PlateResponse;
import com.hundsun.jresplus.remoting.impl.annotation.Service;
import com.hundsun.jresplus.remoting.impl.annotation.ServiceModule;
import com.hundsun.jresplus.remoting.impl.annotation.ServiceParam;

@ServiceModule
public interface RemotePlateService {

    /**
     * 查询全部省份信息
     * @return
     */
    @Service(functionId = "311166", desc = "查询全部省份信息")
    PlateResponse getProvinces();
    
    /**
     * 根据省份id查询城市信息
     * @param request
     * @return
     */
    @Service(functionId = "311167", desc = "根据省份查询城市信息")
    PlateResponse getCitysByProvince(@ServiceParam("provinceId")int provinceId);
    
    
    /**
     * 根据城市查询区信息
     * @param request
     * @return
     */
    @Service(functionId = "311168", desc = "根据城市查询区信息")
    PlateResponse getAreasByCity(@ServiceParam("cityId")int cityId);


    /**
     * 根据地域编码获取地域信息
     * @param request
     * @return
     */
    @Service(functionId = "311169", desc = "根据地域编码获取地域信息")
    PlateResponse getPlateMess(@ServiceParam("code")String code);
}
