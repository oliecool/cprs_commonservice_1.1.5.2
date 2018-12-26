/*
 * Hundsun Inc.
 * Copyright (c) 2006-2017 All Rights Reserved.
 *
 * Author     :linbo
 * Version    :1.1.1.1-RC1
 * Create Date:2017-09-15
 */
package com.hundsun.cprs.commonservice.common.redis.dao;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

public class RedisGeneratorDAO {

	protected Log logger = LogFactory.getLog(this.getClass());

	@Autowired(required=false)
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	protected static final String redisCode = "utf-8";

	public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
		return redisTemplate;
	}

}
