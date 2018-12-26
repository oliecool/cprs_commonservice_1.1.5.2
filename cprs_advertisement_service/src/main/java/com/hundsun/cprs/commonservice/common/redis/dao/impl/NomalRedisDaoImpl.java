package com.hundsun.cprs.commonservice.common.redis.dao.impl;

import org.springframework.stereotype.Service;

import com.hundsun.cprs.commonservice.common.redis.dao.NomalRedisDao;

/**
 * 字符串类型的Redis
 * @author linbo
 * @version $Id: NomalRedisDaoImpl.java,v 1.1.1.1-RC1 2017年9月16日 Exp $
 */
@Service("nomalRedisDao")
public class NomalRedisDaoImpl extends BaseRedisDaoImpl<String> implements NomalRedisDao{
	
}
