package com.hundsun.cprs.commonservice.plate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hundsun.cprs.commonservice.plate.domain.Plate;

public interface PlateDao {
    
    /**
     * 插入省份信息
     * @param plate
     */
    public int insert(Plate plate);
    /**
     * 更新地域信息
     * @param plate
     */
    public void update(Plate plate);
    /**
     * 删除地域信息
     * @param plate
     */
    public void delete(Plate plate);
    /**
     * 查看地域信息
     * @param plate
     */
    public Plate getPlate(Plate plate);
    
    /**
     * 获取全部信息
     * @return
     */
    public List<Plate> getPlates(@Param("isDelete")String isDelete,@Param("plateType")String plateType);
    
    /**
     * 根据省份信息查找市信息
     * 根据市信息查找区信息
     */
    public  List<Plate> getPlatesByPorC(@Param("isDelete")String  isDelete,@Param("parentId")Integer parentId);
    
    /**
     * 获取现在全部省份信息
     */
    public  List<Plate> getProvinces(@Param("isDelete")String  isDelete);
}
