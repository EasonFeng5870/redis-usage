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
	
	public String mset(String key, Map<String, String> map);
	public List<String> mget(String key, String... field);
	

}
