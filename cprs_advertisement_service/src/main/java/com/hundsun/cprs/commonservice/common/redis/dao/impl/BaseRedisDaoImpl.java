/*
 * Hundsun Inc.
 * Copyright (c) 2006-2017 All Rights Reserved.
 *
 * Author     :linbo
 * Version    :1.1.1.1-RC1
 * Create Date:2017-09-15
 */
package com.hundsun.cprs.commonservice.common.redis.dao.impl;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

import com.hundsun.cprs.commonservice.common.redis.dao.BaseRedisDao;
import com.hundsun.cprs.commonservice.common.redis.dao.RedisGeneratorDAO;
import com.hundsun.cprs.commonservice.common.util.SerializeUtil;

/**
 * Redis通用缓存存储
 * 
 * @author linbo
 * @version $Id: BaseRedisDaoImpl.java,v 1.1.1.1-RC1 2017年9月15日 Exp $
 */
@Repository
public class BaseRedisDaoImpl<T> extends RedisGeneratorDAO implements BaseRedisDao<T> {

	@Override
	public T getValueByKey(final String key) throws Exception {
		try {
			return (T) getRedisTemplate().execute(new RedisCallback<T>() {
				@Override
				public T doInRedis(RedisConnection connection) throws DataAccessException {
					T businessDic = null;
					byte[] keyStr = getRedisTemplate().getStringSerializer().serialize(key);
					if (connection.exists(keyStr)) {
						byte[] valuebytes = connection.get(keyStr);
						businessDic = (T) SerializeUtil.unserialize(valuebytes);

					}
					return businessDic;
				}
			});
		} catch (Exception e) {
			logger.error("BaseRedisDao.getValueByKey error", e);
		}
		return null;
	}

	@Override
	public boolean updateValueByKey(final String key, final T value) throws Exception {
		try {
			return getRedisTemplate().execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					if (key == null) {
						logger.error("update Redis[" + key + "]  fail, reason is key is null");
						return false;
					}
					byte[] traceKey = getRedisTemplate().getStringSerializer().serialize(key);
					byte[] valueObj = SerializeUtil.serialize(value);
					connection.set(traceKey, valueObj);
					return true;
				}
			});
		} catch (Exception e) {
			logger.error("BaseRedisDao.updateValueByKey error", e);
			throw new Exception("BaseRedisDao.updateValueByKey error", e);
		}
	}
	@Override
	public boolean updateValueByKey(final String key, final T value,final long expireTime) throws Exception {
		try {
			return getRedisTemplate().execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					if (key == null) {
						logger.error("update Redis[" + key + "]  fail, reason is key is null");
						return false;
					}
					byte[] traceKey = getRedisTemplate().getStringSerializer().serialize(key);
					byte[] valueObj = SerializeUtil.serialize(value);
					connection.setEx(traceKey, expireTime, valueObj);
					return true;
				}
			});
		} catch (Exception e) {
			logger.error("BaseRedisDao.updateValueByKey error", e);
			throw new Exception("BaseRedisDao.updateValueByKey error", e);
		}
	}

	@Override
	public boolean addMapByKey(final String key, final Map<?, ?> bankDataMap) throws Exception {
		try {
			return getRedisTemplate().execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					if (key == null) {
						logger.error("add addMapByKey[" + key + "]  fail, reason is key is null");
						return false;
					}
					byte[] traceKey = getRedisTemplate().getStringSerializer().serialize(key);
					byte[] valueObj = SerializeUtil.serialize(bankDataMap);
					connection.set(traceKey, valueObj);
					
					return true;
				}
			});
		} catch (Exception e) {
			logger.error("BusinessDicRedisDao.addMapByKey error", e);
			throw new Exception("BusinessDicRedisDao.addMapByKey error", e);
		}
	}

	@Override
	public Map<?, ?> getMapByKey(final String key) throws Exception {
		try {
			return (Map<?, ?>) getRedisTemplate().execute(new RedisCallback<Map<?, ?>>() {
				@Override
				public Map<?, ?> doInRedis(RedisConnection connection) throws DataAccessException {
					Map<?, ?> bankMap = null;
					byte[] keyStr = getRedisTemplate().getStringSerializer().serialize(key);
					if (connection.exists(keyStr)) {
						byte[] valuebytes = connection.get(keyStr);
						bankMap = (Map<?, ?>) SerializeUtil.unserialize(valuebytes);

					}
					return bankMap;
				}
			});
		} catch (Exception e) {
			logger.error("BaseRedisDao.getMapByKey error", e);
		}
		return null;
	}

	@Override
	public boolean deleteByKey(final String key) throws Exception {
		try {
			return getRedisTemplate().execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					if (key == null) {
						logger.error("delete deleteByKey[" + key + "]  fail, reason is key is null");
						return false;
					}
					byte[] traceKey = getRedisTemplate().getStringSerializer().serialize(key);
					return connection.del(traceKey).compareTo(0L) > 0;
				}
			});
		} catch (Exception e) {
			logger.error("BusinessDicRedisDao.deleteByKey error", e);
			throw new Exception("BusinessDicRedisDao.deleteByKey error", e);
		}
	}

	@Override
	public boolean lockByKey(final String key, final Long second) throws Exception {
		return getRedisTemplate().execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				if (key == null) {
					logger.error("lock lockByKey[" + key + "]  fail, reason is key is null");
					return false;
				}
				byte[] traceKey = getRedisTemplate().getStringSerializer().serialize(key);
				byte[] valueObj = SerializeUtil.serialize(System.currentTimeMillis());
				boolean ret = connection.setNX(traceKey, valueObj);
				if (ret) {
					connection.expire(traceKey, second);
				}
				return ret;
			}
		});
	}

}
