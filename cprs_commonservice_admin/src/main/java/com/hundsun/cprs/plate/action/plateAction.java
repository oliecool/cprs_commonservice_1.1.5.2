package com.hundsun.cprs.plate.action;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.BaseAction;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.plate.domain.Plate;
import com.hundsun.cprs.commonservice.plate.domain.PlateJson;
import com.hundsun.cprs.commonservice.plate.enums.EnumIsDeleteStatus;
import com.hundsun.cprs.commonservice.plate.enums.EnumPlateType;
import com.hundsun.cprs.commonservice.plate.service.PlateService;

import javax.servlet.http.HttpServletResponse;


@Controller
public class plateAction extends BaseAction{
    
    @Autowired
    private  PlateService  plateService;
    
    /**
     * 通用数据初始化
     */
    public void addPLateData(ModelMap model){
        model.put("plateTypeMap", EnumPlateType.toMap());
        List<Plate> provinceList = plateService.getProvinces(EnumIsDeleteStatus.ISDELETE_N.getStatus());
        model.put("provinces", provinceList);
    }
    
    
    /**
     * 类目录树的展示
     * @return
     */
    @RequestMapping(value = "/plate/plateIndex.htm")
    public String plateListTree(ModelMap model) throws Exception {
        return "/plate/plateIndex";
    }
    
    /**
     * 地方树的展示(将所有的启用的地方目录，包括一级、二级、三级)
     * @return
     */
     @RequestMapping(value = "/ajax/getPlateTypeTree")
     public @ResponseBody List<PlateJson> getProvinceTypeTree() {
         return plateService.getPlates(EnumIsDeleteStatus.ISDELETE_N.getStatus());
     }
    
    /**
     * 左边是类目录树形展示
     * @return
     */
   @RequestMapping(value = "/plate/plateLeft")
   public String plateLeftTree() {
       return "/plate/plateLeft";
   }

    /**
     * 右边是可以进行操作的界面
     * @return
     */
   @RequestMapping(value = "/plate/plateRight")
   public String plateRightTree(){
       return "/plate/plateRight";
   }
    
   
    @RequestMapping(value = "plate/plateAdd.htm", method = RequestMethod.GET)
    public String insertProvinceGet(ModelMap model,@ModelAttribute("plate")Plate plate){
        addPLateData(model);
        return "plate/plateAdd";
    }
    
    /**
     * 根据省份查到市
     * @return
     */
     @RequestMapping(value = "/ajax/getPlateTypeOfCityTree.json")
     public @ResponseBody JSONObject getCityTree(@RequestParam("parentId")int parentId) {
         
         JSONObject json = new  JSONObject();
         String isDelete = EnumIsDeleteStatus.ISDELETE_N.getStatus();
         List<Plate > citylist = plateService.getPlatesByPorC(isDelete, parentId);
         json.put("cityList",citylist);
        return json;
     }

    /**
     * 查看地域编码是否唯一
     * @param plateCode
     * @return
     */
    @RequestMapping(value = "ajax/getPlateCodeUnique.json")
    public @ResponseBody JSONObject getPlateCodeUnique(@RequestParam("plateCode") String plateCode) {
        JSONObject obj = new JSONObject();
        try {
            //根据编码查询地域信息
            Plate plate =  plateService.getPlateByCode(plateCode);
            if(plate != null){
                obj.put("message","地域编码重复！");
            }
        } catch (Exception e) {
            log.error("校验地域编码异常！",e);
            obj.put("message","校验地域编码异常！");
        }
        return obj;
    }
    
    @RequestMapping(value = "plate/plateAdd.htm", method = RequestMethod.POST)
    public String  insertProvincePost(ModelMap model,@ModelAttribute("plate")Plate plate){
        try {
            if(plate.getPlate() == null || plate.getPlate() == ""){
                return error(model, "请正确填写地域信息!");
            }
            
            if(plate.getPlateType().equals(EnumPlateType.PLATE_TWO.getType())){
                plate.setParentId(plate.getProvinceId());
            }else if(plate.getPlateType().equals(EnumPlateType.PLATE_THREE.getType())){
                plate.setParentId(plate.getCityId());
            }
            plate.setIsDelete(EnumIsDeleteStatus.ISDELETE_N.getStatus());
            plateService.insert(plate);
        } catch (Exception e) {
            log.error("插入地域信息异常！",e);
            return error(model, "插入地域信息异常!");
        }
        
        return "/plate/plateHelp";
    }
    
    @RequestMapping(value= "plate/plateUpdate.htm", method = RequestMethod.GET)
    public String  updatePlateByGet(ModelMap model,@RequestParam("id")int id){
        Plate plate = null;
        try {
            plate = plateService.getPlateById(id);
            if(plate == null){
                log.warn("地域信息不存在!");
                return error(model, "地域信息不存在!");
            }
            
            if(plate.getPlateType().equals(EnumPlateType.PLATE_TWO.getType())){
                plate.setProvinceId(plate.getParentId());
                plate.setProvinceName(plate.getParentName());
                
            }
            
            if(plate.getPlateType().equals(EnumPlateType.PLATE_THREE.getType())){
                
                Plate parentPlate = plateService.getPlateById(plate.getParentId());
                if(parentPlate == null ){
                    log.warn("地域信息不存在,无法修改!");
                    return error(model, "地域信息不存在,无法修改!");
                }
                
                plate.setCityId(plate.getParentId());
                plate.setCityName(plate.getParentName());
                plate.setProvinceId(parentPlate.getParentId());
                plate.setProvinceName(parentPlate.getParentName());
                
                List<Plate> citys = plateService.getPlatesByPorC(EnumIsDeleteStatus.ISDELETE_N.getStatus(),parentPlate.getParentId());
                model.put("citys", citys);
            }
            
        } catch (Exception e) {
            log.error("根据id查询地域信息异常！", e);
            return error(model, "查询地域信息失败！");
        }
        addPLateData(model);
        model.put("plate", plate);
        return "/plate/plateUpdate";
    }
    
    
    @RequestMapping(value="plate/plateUpdate.htm", method = RequestMethod.POST)
    public String  updatePlateByPost(ModelMap model,@ModelAttribute("plate")Plate plate){
        try {
            
            if(plate.getPlate() == null || plate.getPlate() == ""){
                return error(model, "请正确填写地域信息!");
            }
            
            if(plate.getPlateType().equals(EnumPlateType.PLATE_TWO.getType())){
                plate.setParentId(plate.getProvinceId());
            }else if(plate.getPlateType().equals(EnumPlateType.PLATE_THREE.getType())){
                plate.setParentId(plate.getCityId());
            }
            plateService.update(plate);
        } catch (Exception e) {
            log.error("修改地域信息异常！",e);
            return error(model, "修改地域信息异常!");
        }
        
        return "/plate/plateHelp";
    }
    
    
    @RequestMapping("plate/plateDelete.htm")
    public String  deletePlate(ModelMap model,@RequestParam("id")int  id){
        
        Plate plate = null;
        try {
            
          
            plate = plateService.getPlateById(id);
            if(plate == null){
                log.warn("地域信息不存在!");
                return error(model, "地域信息不存在!");
            }
            
            plate.setIsDelete(EnumIsDeleteStatus.ISDELETE_Y.getStatus());
            plateService.delete(plate);
            
            if(plate.getPlateType().equals(EnumPlateType.PLATE_ONE.getType())){
                List<Plate> cityList = plateService.getPlatesByPorC(null, plate.getId());
                for(Plate cityPlate : cityList){
                    cityPlate.setIsDelete(EnumIsDeleteStatus.ISDELETE_Y.getStatus());
                    plateService.delete(cityPlate);
                }
            }
           
        } catch (Exception e) {
            log.error("删除地域信息异常！",e);
            return error(model, "删除地域信息异常!");
        }
        
        return "/plate/plateHelp";
    }
    
    
    @RequestMapping("plate/plateView.htm")
    public String  viewPlate(ModelMap model,@RequestParam("id")int  id){
        
        Plate plate = null;
        try {
            
            plate = plateService.getPlateById(id);
            if(plate == null){
                log.warn("地域信息不存在!");
                return error(model, "地域信息不存在!");
            }
            
            if(plate.getPlateType().equals(EnumPlateType.PLATE_TWO.getType())){
                plate.setProvinceId(plate.getParentId());
                plate.setProvinceName(plate.getParentName());
                
            }
            
            if(plate.getPlateType().equals(EnumPlateType.PLATE_THREE.getType())){
                
                Plate parentPlate = plateService.getPlateById(plate.getParentId());
                if(parentPlate == null ){
                    log.warn("地域信息不存在!");
                    return error(model, "地域信息不存在!");
                }
                
                plate.setCityId(plate.getParentId());
                plate.setCityName(plate.getParentName());
                plate.setProvinceId(parentPlate.getParentId());
                plate.setProvinceName(parentPlate.getParentName());
                
                List<Plate> citys = plateService.getPlatesByPorC(EnumIsDeleteStatus.ISDELETE_N.getStatus(),parentPlate.getParentId());
                model.put("citys", citys);
            }
           
          
           
        } catch (Exception e) {
            log.error("查询地域信息异常！",e);
            return error(model, "查询地域信息异常!");
        }
        addPLateData(model);
        model.put("plate", plate);
        return "plate/plateView";
    }
    
    
    @RequestMapping("plate/fileImport.htm")
    public String  getFile(ModelMap model,@RequestParam("file") MultipartFile file){
        
        return "plate/plateView";
    }
    
    /**
     * 导入地域文件
     * @param file
     * @return
     */
    @RequestMapping(value = "plate/insertData.json")
    public @ResponseBody JSONObject insertMatch(@RequestParam("attachmentFile") MultipartFile file) {
        JSONObject obj = new JSONObject();
        try {
            //导入省市区数据
            plateService.importPlateJson(file);
            obj.put("message","操作成功！");
        } catch (ServiceCodeException e) {
            log.error("导入地域文件失败！",e);
            obj.put("message",e.getErrorDesc());
        }catch (Exception e) {
            log.error("导入地域文件异常！",e);
            obj.put("message","导入地域文件异常！");
        }
        return obj;
    }
    
    @RequestMapping(value = "plate/downloadImportExcel.htm")
    public String importInit(HttpServletResponse httpServletResponse, ModelMap model) throws IOException {
        try {
            plateService.downloadTXT(httpServletResponse);
        } catch (Exception e) {
            model.put("message", "下载模板异常!");
            httpServletResponse.reset();
            httpServletResponse.setContentType("text/html");
            httpServletResponse.setHeader("Content-Disposition", "");
            return "error";
        }
        return null;
    }
    
}
