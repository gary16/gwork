package com.gwork.app.springdemo.distributionlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class DistributionLock {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(DistributionLock.class);
	
	@Autowired
	private JedisPool jedisPool;// 注入JedisPool
	
	private int waitMs;

	public boolean tryLock(String key,int waitSecond,int lockSecond) throws InterruptedException {
		Jedis jedis = jedisPool.getResource();
		boolean flg=false;
		 try {
			 if(waitSecond<=0) {
				 flg = jedis.setnx(key, "1")==1 ;
			 }else {
				 for(long i=0;i<waitSecond;i++) {
					 flg = jedis.setnx(key, "1")==1 ;
					 if(flg) {
						 break;
					 }
					 Thread.sleep(1000);
				 }
			 }
			 if(flg) {
				 jedis.expire(key, lockSecond) ;
			 }
		 }
		 catch(Exception e){
			 LOGGER.error(e.getMessage());
		 }
		return flg;
		
	}
	
	public boolean tryLock(String key,int waitTime) throws InterruptedException {
		return this.tryLock(key, waitTime, 6000);
	}
	
	public boolean tryLock(String key) throws InterruptedException {
		return this.tryLock(key, 0, 6000);
	}

	public void unlock(String key) {

	}

}
