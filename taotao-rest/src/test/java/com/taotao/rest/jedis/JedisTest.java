package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	public void testJedisSingle() {
		// 创建一个jedis对象
		Jedis jedis = new Jedis("192.168.47.132", 6379);
		// 调用jedis对象方法,方法名和redis的命令一致
		jedis.set("key1", "jedistest");
		String key1 = jedis.get("key1");
		System.out.println(key1);
		// 关闭redis
		jedis.close();
	}

	/**
	 * 连接池
	 */
	public void testJedisPool() {
		// 创建连接池
		JedisPool pool = new JedisPool("192.168.47.132", 6379);
		// 从连接池中获得jedis对象
		Jedis jedis = pool.getResource();
		// 调用jedis对象方法,方法名和redis的命令一致
		jedis.set("key2", "test");
		String key1 = jedis.get("key2");
		System.out.println(key1);
		// 关闭redis
		jedis.close();
	}

	/**
	 * 集群版测试
	 */
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.153", 7001));
		nodes.add(new HostAndPort("192.168.25.153", 7002));
		nodes.add(new HostAndPort("192.168.25.153", 7003));
		nodes.add(new HostAndPort("192.168.25.153", 7004));
		nodes.add(new HostAndPort("192.168.25.153", 7005));
		nodes.add(new HostAndPort("192.168.25.153", 7006));

		JedisCluster cluster = new JedisCluster(nodes);

		cluster.set("key1", "1000");
		String string = cluster.get("key1");
		System.out.println(string);

		cluster.close();
	}

	/**
	 * spring集成redis单机版测试
	 */
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}

	/**
	 * spring集成redis集群版测试
	 */
	public void testSpringJedisCluster() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
		String string = jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close();
	}

}
