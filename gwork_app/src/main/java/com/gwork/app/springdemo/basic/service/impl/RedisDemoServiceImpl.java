package com.gwork.app.springdemo.basic.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gwork.app.springdemo.basic.service.RedisDemoService;

/**
 * @author jialiang.chen
 * @date 2019-05-04 00:39:29
 */
@Service
public class RedisDemoServiceImpl implements RedisDemoService {

	@Autowired
	private RedisTemplate redisTemplate;
	@Override
	public String keys(String keyPrefix) {
		Set<String> strSet = redisTemplate.keys(keyPrefix);
		return JSON.toJSONString(strSet);
	}

}
