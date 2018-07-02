package com.gwork.app.springdemo.distributionlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPool;

public class DistributionLock {

	@Autowired
	private JedisPool jedisPool;// 注入JedisPool
	
	private int waitMs;

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		
		return false;
	}

	public void unlock() {

	}

}
