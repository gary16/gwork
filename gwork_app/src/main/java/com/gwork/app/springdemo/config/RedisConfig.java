package com.gwork.app.springdemo.config;

import org.mybatis.generator.internal.db.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author jialiang.chen
 * @title: DatabaseConfig
 * @projectName gwork
 * @date 2019/5/17 18:59
 */
@Configuration
public class RedisConfig {

    @Bean(name = "poolConfig")
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(300);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    @Bean(name = "connectionFactory")
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName("10.8.42.206");
        connectionFactory.setPort(6379);
        JedisPoolConfig jedisPoolConfig = this.getJedisPoolConfig();
        connectionFactory.setPoolConfig(jedisPoolConfig);
        return connectionFactory;
    }

    @Bean(name = "redisTemplate")
   public StringRedisTemplate getStringRedisTemplate(){
        StringRedisTemplate  stringRedisTemplate = new StringRedisTemplate();
        JedisConnectionFactory jedisConnectionFactory = this.getConnectionFactory();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        return stringRedisTemplate;
   }

}
