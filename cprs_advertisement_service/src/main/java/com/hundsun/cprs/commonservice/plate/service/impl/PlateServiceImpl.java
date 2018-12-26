package com.hundsun.cprs.commonservice.plate.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.advertisement.service.BaseService;
import com.hundsun.cprs.commonservice.common.util.DownloadUtil;
import com.hundsun.cprs.commonservice.common.util.FileUploadUtil;
import com.hundsun.cprs.commonservice.plate.dao.PlateDao;
import com.hundsun.cprs.commonservice.plate.domain.Plate;
import com.hundsun.cprs.commonservice.plate.domain.PlateJson;
import com.hundsun.cprs.commonservice.plate.enums.EnumIsDeleteStatus;
import com.hundsun.cprs.commonservice.plate.enums.EnumPlateType;
import com.hundsun.cprs.commonservice.plate.service.FileOfPlateJson;
import com.hundsun.cprs.commonservice.plate.service.PlateService;

@Service
public class PlateServiceImpl extends  BaseService implements PlateService{
    
    @Autowired
    private PlateDao plateDao;
    
    
    @Value("${report.template.dir}")
    public String importTempleteDir;
    
    
    @Value("${file.upload.dir}")
    public String fileUploadDir;
    
    /**
     * 插入地方信息
     * @param plate
     */
    public int  insert(Plate plate){
        return plateDao.insert(plate);
    }
    
    /**
     * 更新地方信息
     * @param plate
     */
    public void update(Plate plate){
        plateDao.update(plate);
    }
    
    /**
     * 删除地方信息
     * @param plate
     */
    public void delete(Plate plate){
        plateDao.delete(plate);
    }

    /**
     * 获取地域信息,根据地域编码
     */
    public Plate getPlateByCode(String code){
        Plate plate = new  Plate();
        plate.setPlateCode(code);
        return plateDao.getPlate(plate);
    }
    /**
     * 获取地域信息
     */
    public  Plate getPlateById(Integer id){
        Plate plate = new  Plate();
        plate.setId(id);
        return plateDao.getPlate(plate);
    }
    
    
    /**
     * 获取全部地域信息
     * @param isDelete
     */
    public  List<PlateJson> getPlates(String  isDelete){
        
        List<Plate>  plateList = plateDao.getPlates(isDelete,null);
        
        List<PlateJson> jsonList = new ArrayList<PlateJson>();
        if ( plateList != null && !plateList.isEmpty()) {
              for (Plate plateTree : plateList) {
                  PlateJson json = new PlateJson(plateTree);
                  jsonList.add(json);
              }
          }
        return jsonList;
    }

    /**
     * 根据省份信息查找市信息
     * 根据市信息查找区信息
     */
    public  List<Plate> getPlatesByPorC(String  isDelete,Integer parentId){
        return plateDao.getPlatesByPorC(isDelete, parentId);
    }
    
    /**
     * 获取现在全部省份信息
     */
    public  List<Plate> getProvinces(String  isDelete){
        return plateDao.getProvinces(isDelete);
    }
    
    /**
     * 导入地域文件
     * @param file
     */
    public void importPlateJson(MultipartFile file){
        try {
            
            if (file != null && file.getSize() > 0) {
                
                String path = FileUploadUtil.uploadCommonFile(file, "importPlateTXT");
                String filePath = fileUploadDir + "/" + path;
                String strJson = FileOfPlateJson.readFile(filePath);
                this.readContent(strJson);
            }
         } catch (Exception e) {
             logger.error("导入地域文件异常!",e);
             throw new ServiceCodeException(-1, "导入地域文件异常!");
        }
    }
    
    
    @Override
    public void downloadTXT(HttpServletResponse response) throws IOException {
        DownloadUtil.outPutFile(importTempleteDir + "plate.txt", "省市区.txt",
            response);
    }
    
    
    public  List<Plate> readContent(String strJson){
        if(strJson.isEmpty()){
            throw new ServiceCodeException(-1, "导入文件的内容为空！");
        }
        
        List<Plate> cities = new ArrayList<Plate>();
        JSONArray jsonArray =  JSONArray.parseArray(strJson);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            String provinceCode = json.getString("code");
            //省份信息
            String cityName = json.getString("name");
            
            Plate provincePlate = new Plate(provinceCode,cityName,0,EnumPlateType.PLATE_ONE.getType(),EnumIsDeleteStatus.ISDELETE_N.getStatus());
            plateDao.insert(provincePlate);
            cities.add(provincePlate);
            
            parseJsonToCity(json.getJSONArray("city"),provincePlate.getId());
            
        }
        return cities;
    }
    
    
    /**
    * 解析城市
    * @param jsonArray
    * @return
    */
   public  List<Plate> parseJsonToCity(JSONArray jsonArray,int parentId){
       List<Plate> nextCities = new ArrayList<Plate>();
       for (int i = 0; i < jsonArray.size(); i++) {
           JSONObject json = jsonArray.getJSONObject(i);
           String cityCode = json.getString("code");
           if(cityCode == null ){
               continue;
           }
           String cityName = json.getString("name");
           Plate cityPlate = new Plate(cityCode,cityName, parentId, EnumPlateType.PLATE_TWO.getType(),EnumIsDeleteStatus.ISDELETE_N.getStatus());
           plateDao.insert(cityPlate);
           ParseJsonToArea(json.getJSONArray("area"), cityPlate.getId());
           nextCities.add(cityPlate);
       }
       return nextCities;
   }
   /**
    * 解析地区
    * @param jsonArray
    * @return
    */
   public  List<Plate> ParseJsonToArea(JSONArray jsonArray,int parentId){
       List<Plate> areas = new ArrayList<Plate>();
       for (int i = 0; i < jsonArray.size(); i++) {
           String areaStr = jsonArray.getString(i);
           if(areaStr == null || areaStr == " "){
               continue;
           }
           String [] strArr = areaStr.split(": ");
           String code = strArr[0];
           String name = strArr[1];

           Plate plate = new Plate(code,name, parentId, EnumPlateType.PLATE_THREE.getType(),EnumIsDeleteStatus.ISDELETE_N.getStatus());
           plateDao.insert(plate);
           areas.add(plate);
       }
       return areas;
   }
}
