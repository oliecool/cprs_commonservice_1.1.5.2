package com.hundsun.cprs.plate.request;

public class PlateRequest {
    
    private Integer     id;
    
    private String      plate;//地方名
    
    private Integer     parentId;//上级id
    
    private String      parentName;//父类名称
    
    private String      plateType;//地方类型（P：省份  C：城市   A：区）
    
    private String      isDelete;//是否删除状态N：没删除  Y：删除

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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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
    
    
}
