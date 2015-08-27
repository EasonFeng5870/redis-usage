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

    public void set(String key, String data) {
	jedisCluster.setex(key, 900 * 1000, data);
    }

    public void setOnlyNonExist(String key, String data) {
	jedisCluster.setnx(key, data);
    }

    public void setWithExpireTime(String key, String data, int sec) {
	jedisCluster.setex(key, sec, data);
    }

    public String get(String key) {
	return jedisCluster.get(key);
    }

    public void lpush(String key, String... values) {
	jedisCluster.lpush(key, values);
    }

    public List<String> range(String key, int start, int end) {
	return jedisCluster.lrange(key, start, end);
    }

    public String lpop(String key) {
	return jedisCluster.lpop(key);
    }

    public String rpop(String key) {
	return jedisCluster.rpop(key);
    }

    public void rpush(String key, String... values) {
	jedisCluster.rpush(key, values);
	jedisCluster.close();
    }

}
