package com.hundsun.cprs.commonservice.advertisement.domain;

import java.io.Serializable;

public class AdPositionJson implements Serializable {
    private Long id;
    private String name;
    private Long adPositionTypeId=0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdPositionTypeId() {
        return adPositionTypeId;
    }

    public void setAdPositionTypeId(Long adPositionTypeId) {
        this.adPositionTypeId = adPositionTypeId;
    }
}
