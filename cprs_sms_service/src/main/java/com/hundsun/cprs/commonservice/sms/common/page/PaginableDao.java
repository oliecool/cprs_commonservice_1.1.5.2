/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-3-25
 */
package com.hundsun.cprs.commonservice.sms.common.page;



/**
 * 分页DAO。
 * 
 */
public interface PaginableDao {

    /**
     * 根据分页条件获取分页信息，其中page参数目前默认采用SimplePage或其子类。
     * @param <T>
     * @param page 分页信息
     * @param qTotalCount 查询总页数的SQL
     * @param qPagination 查询分页的SQL
     */
    public <T> void paginate(Paginable<T> page, String qTotalCount, String qPagination);
    /**
     * 根据分页条件获取分页信息, 其中page参数目前默认采用SimplePage或其子类(chengduadao划分而来)
     * 
     * @param <T>
     * @param page 分页信息
     * @param qTotalCount
     * @param qPagination
     * @return Paginable<T>
     */
    public <T> Paginable<T> getPagination(Paginable<T> page, String qTotalCount, String qPagination);

}
