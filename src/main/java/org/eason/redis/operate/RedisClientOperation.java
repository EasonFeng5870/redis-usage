package org.eason.redis.operate;

import java.util.List;
import java.util.Map;

import org.eason.cache.CacheService;

/**
 * this is for redis client operation store
 * 
 * @author fengyingsheng
 *
 */
public interface RedisClientOperation extends CacheService {
	public String get(String key);
	public List<String> mget(String key, String... field);
	
	public void set(String key, String data);
	public String mset(String key, Map<String, String> map);
	
	public void setOnlyNonExist(String key, String data);
	public void setWithExpireTime(String key, String data,int expireTime);
	
	
	public void lpush(String key, String... values);
	public String lpop(String key);
	
	public String rpop(String key);
	public void rpush(String key, String... values);

	public List<String> range(String key, int start, int end);
	
}