package com.hundsun.cprs.commonservice.plate.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.hundsun.cprs.commonservice.advertisement.common.DomainBase;
import com.hundsun.cprs.commonservice.plate.enums.EnumIsDeleteStatus;

public class Plate extends  DomainBase{
    
    private Integer     id;
    
    private String      plate;//地方名

    private String      plateCode;//编码

    private Integer     parentId = 0;//上级id
    
    private String      parentName;//父类名称
    
    private String      plateType;//地方类型（P：省份  C：城市   A：区）
    
    private String      isDelete;//是否删除状态N：没删除  Y：删除
    
    private Integer     cityId;
    
    private String     cityName;
    
    private Integer     provinceId;
    
    private String     provinceName;
    
    private  Date       gmtCreate;
    
    private Date        gmtModify;

    
    public Plate() {
        super();
    }

    public Plate(String plateCode,String plate, Integer parentId, String plateType,String isDelete
                ) {
        super();
        this.plateCode = plateCode;
        this.plate = plate;
        this.parentId = parentId;
        this.plateType = plateType;
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }
}
