package com.hundsun.cprs.commonservice.plate.remote;

import java.util.List;

import com.hundsun.jresplus.remoting.impl.annotation.ServiceParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.advertisement.common.enums.EnumCommonCode;
import com.hundsun.cprs.commonservice.advertisement.common.util.BeanUtils;
import com.hundsun.cprs.commonservice.advertisement.service.BaseService;
import com.hundsun.cprs.commonservice.plate.domain.Plate;
import com.hundsun.cprs.commonservice.plate.enums.EnumIsDeleteStatus;
import com.hundsun.cprs.commonservice.plate.service.PlateService;
import com.hundsun.cprs.plate.RemotePlateService;
import com.hundsun.cprs.plate.response.PlateResponse;
import com.hundsun.cprs.plate.vo.PlateVo;

@Service("remotePlateService")
public class RemotePlateServiceImpl extends  BaseService implements  RemotePlateService{

    @Autowired
    private  PlateService plateService;
    
    /**
     * 查询全部省份信息
     * @return
     */
    public PlateResponse getProvinces(){
        PlateResponse  response = new PlateResponse();
        response.setSuccess(true);
        try {
            String isDelete = EnumIsDeleteStatus.ISDELETE_N.getStatus();
            List<Plate> provinceList = plateService.getProvinces(isDelete);
            response.setPlateVoList(BeanUtils.batchTransform(PlateVo.class, provinceList));
            
        } catch (Exception e) {
            response.setSuccess(false);
            logger.error("获取省份信息异常！",e);
            response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
            response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
        }
        return response;
    }
    
    
    /**
     * 根据省份id查询城市信息
     * @param provinceId
     * @return
     */
    public PlateResponse getCitysByProvince(int provinceId){
        
        PlateResponse  response = new PlateResponse();
        response.setSuccess(true);
        try {
            String isDelete = EnumIsDeleteStatus.ISDELETE_N.getStatus();
            List<Plate> cityList = plateService.getPlatesByPorC(isDelete, provinceId);
            response.setPlateVoList(BeanUtils.batchTransform(PlateVo.class, cityList));
        } catch (Exception e) {
            response.setSuccess(false);
            logger.error("获取城市信息异常！",e);
            response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
            response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
        }
        return response;
    }
    
    
    /**
     * 根据城市查询区信息
     * @param cityId
     * @return
     */
   public  PlateResponse getAreasByCity(int cityId){
       PlateResponse  response = new PlateResponse();
       response.setSuccess(true);
       try {
           String isDelete = EnumIsDeleteStatus.ISDELETE_N.getStatus();
           List<Plate> cityList = plateService.getPlatesByPorC(isDelete, cityId);
           response.setPlateVoList(BeanUtils.batchTransform(PlateVo.class, cityList));
       } catch (Exception e) {
           response.setSuccess(false);
           logger.error("获取区县信息异常！",e);
           response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
           response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
       }
       return response;
    }

    /**
     * 根据地域编码获取地域信息
     * @param request
     * @return
     */
    public PlateResponse getPlateMess(@ServiceParam("code")String code){
        PlateResponse  response = new PlateResponse();
        response.setSuccess(true);
        try {
            Plate plate = plateService.getPlateByCode(code);

            PlateVo plateVo = new PlateVo();
            BeanUtils.copyEntityProperties(plate,plateVo);
            response.setPlateVo(plateVo);
        } catch (Exception e) {
            response.setSuccess(false);
            logger.error("获取地域信息异常！",e);
            response.setErrorNo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorNo());
            response.setErrorInfo(EnumCommonCode.COMMON_SERVER_ERROR.getErrorInfo());
        }
        return response;
    }
}
