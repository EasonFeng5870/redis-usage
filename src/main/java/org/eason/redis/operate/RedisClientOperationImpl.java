package org.eason.redis.operate;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eason.redis.cluster.RedisClusterManager;

import redis.clients.jedis.JedisCluster;

public class RedisClientOperationImpl implements RedisClientOperation {
	
	private static final Logger logger = Logger.getLogger(RedisClientOperationImpl.class);
	
	private static JedisCluster jedisCluster = RedisClusterManager.getJedisCluster();

	public String mset(String key, Map<String, String> map) {
		try {
			return jedisCluster.hmset(key, map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	public List<String> mget(String key, String... field) {
		return jedisCluster.hmget("lx_eason_mset_1", field);
	}

}
