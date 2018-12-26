/*
 * Hundsun Inc.
 * Copyright (c) 2006-2017 All Rights Reserved.
 *
 * Author     :linbo
 * Version    :1.1.1.1-RC1
 * Create Date:2017-09-15
 */
package com.hundsun.cprs.commonservice.common.redis.dao;

import java.util.Map;

/**
 * 操作业务字典缓存信息：新增、修改、读取
 * 
 * @author linbo
 * @version $Id: BusinessDicRedisDao.java,v 0.1 2016年8月23日 上午10:09:55 linbo Exp
 *          $
 */
public interface BaseRedisDao<T> {
	/**
	 * 取得缓存信息
	 * 
	 * @param key
	 * @return
	 */
	public T getValueByKey(String key) throws Exception;

	/**
	 * 新增或更新缓存信息
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public boolean updateValueByKey(String key, T value) throws Exception;

	/**
	 * 新增或更新缓存信息，在expireTime后失败
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return
	 * @throws Exception
	 */
	public boolean updateValueByKey(String key, T value,long expireTime) throws Exception;

	/**
	 * 添加map数据
	 * 
	 * @param key
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean addMapByKey(String key, Map<?, ?> map) throws Exception;

	/**
	 * 通过key获取map
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Map<?, ?> getMapByKey(String key) throws Exception;

	/**
	 * 通过key删除值
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public boolean deleteByKey(String key) throws Exception;

	/**
	 * 锁住key
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public boolean lockByKey(String key, Long second) throws Exception;
}
