/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-3-25
 */
package com.hundsun.cprs.commonservice.advertisement.common.page;

import java.util.List;

/**
 * 来自JEECMS中的分页Pagination类，实现了Paginable接口的getDate()方法。
 *
 */
public class Pagination<T> extends SimplePage<T> {

    private static final long serialVersionUID = 6398443093873035070L;

    public Pagination() {
    }

    public Pagination(int pageNo, int pageSize, int totalCount) {
        super(pageNo, pageSize, totalCount);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Pagination(int pageNo, int pageSize, int totalCount, List list) {
        super(pageNo, pageSize, totalCount);
        this.list = list;
    }

    public int getFirstResult() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 当前页的数据
     */
    private List<T> list;

    /**
     * 取得分页数据
     *
     * @return List<T> 分页数据
     */
    public List<T> getData() {
        return list;
    }

    /**
     * 设置分页数据
     *
     * @param data 分页数据
     */
    public void setData(List<T> data) {
        this.list = data;
    }
    
    /**
     * 兼容mybatis的Page结果集
     * @param result
     */
    public void compatible(List<T> result){
		MyBatisPageHelper.setPageInfo(this, result);
	}
    
    /**
     * 查询条件仅仅用于返回所有符合条件的结果集
     * 复用分页SQL查询语句
     * 查询前，请调用
     */
    public void onlyReturnList(){
		setPageSize(0);//查询所有
		setCountSql(false);//只查结果集，不统计(默认分页是统计的)
    }
}

