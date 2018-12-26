package com.hundsun.cprs.commonservice.plate.domain;

import java.io.Serializable;

public class PlateJson implements Serializable{
    
    private static final long serialVersionUID = -4568695663528114653L;
    
    private int id;
    /**
     * 地域类型
     */
    private String plateType;
    /**
     * 父节点code串
     */
    private int parentId;
    
    /**
     * 名称
     */
    private String name;
    
    
    public PlateJson(Plate type){
        if (type != null){
            this.setId(type.getId());
            this.setPlateType(type.getPlateType());
            this.setName(type.getPlate());
            this.setParentId(type.getParentId());
        } 
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPlateType() {
        return plateType;
    }


    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }
    
}
