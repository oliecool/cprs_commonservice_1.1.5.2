package com.hundsun.cprs.commonservice.article.domain;

import java.io.Serializable;

public class ArticleTypeJson implements Serializable {
    private Long id;
    private String name;
    private Long parentId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
