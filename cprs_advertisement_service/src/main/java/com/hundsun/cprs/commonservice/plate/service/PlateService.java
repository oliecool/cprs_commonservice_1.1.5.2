package com.hundsun.cprs.commonservice.plate.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.hundsun.cprs.commonservice.plate.domain.Plate;
import com.hundsun.cprs.commonservice.plate.domain.PlateJson;

public interface PlateService {
    /**
     * 插入地方信息
     * @param plate
     */
    public int insert(Plate plate);
    /**
     * 更新地方信息
     * @param plate
     */
    public void update(Plate plate);
    /**
     * 删除地方信息
     * @param plate
     */
    public void delete(Plate plate);
    /**
     * 获取地域信息,根据地域编码
     */
    public Plate getPlateByCode(String code);
    /**
     * 获取地域信息
     */
    public Plate getPlateById(Integer id);
    
    /**
     * 获取现在全部地域信息
     */
    public  List<PlateJson> getPlates(String  isDelete);

    /**
     * 根据省份信息查找市信息
     * 根据市信息查找区信息
     */
    public  List<Plate> getPlatesByPorC(String  isDelete,Integer parentId);
    
    /**
     * 获取现在全部省份信息
     */
    public  List<Plate> getProvinces(String  isDelete);
    
    /**
     * 导入地域文件
     * @param file
     */
    public void importPlateJson(MultipartFile file);
    
    
    public  void downloadTXT(HttpServletResponse response) throws IOException;

}
