package com.taotao.sso.pojo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.sso.pojo.JedisClient;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * 单机版
 * 
 * @author zhangchao
 *
 */
public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public String get(String key) {
		String resultStr = jedisCluster.get(key);
		return resultStr;
	}

	@Override
	public String set(String key, String value) {
		String resultStr = jedisCluster.set(key, value);
		return resultStr;
	}

	@Override
	public String hget(String hkey, String key) {
		String resultStr = jedisCluster.hget(hkey, key);
		return resultStr;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		long resultNum = jedisCluster.hset(hkey, hkey, key);
		return resultNum;
	}

	@Override
	public long incr(String key) {
		long resultNum = jedisCluster.incr(key);
		return resultNum;
	}

	@Override
	public long expire(String key, int second) {
		long resultNum = jedisCluster.expire(key, second);
		return resultNum;
	}

	@Override
	public long ttl(String key) {
		long resultNum = jedisCluster.ttl(key);
		return resultNum;
	}

	@Override
	public long del(String key) {
		long resultNum = jedisCluster.del(key);
		return resultNum;
	}

	@Override
	public long hdel(String hkey, String key) {
		long resultNum = jedisCluster.hdel(hkey, key);
		return resultNum;
	}

}
