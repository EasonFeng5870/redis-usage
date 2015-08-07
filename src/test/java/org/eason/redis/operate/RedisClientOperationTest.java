package org.eason.redis.operate;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RedisClientOperationTest {
	
	RedisClientOperation redisClientOperation = new RedisClientOperationImpl();

	@Test
	public void TestMSet(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("zhangsan", "100");
		map.put("lisi", "101");
		map.put("zhouwu", "102");
		map.put("zhaoliu", "103");
		System.out.println(redisClientOperation.mset("lx_eason_mset_1", map));
	}
	
	@Test
	public void TestMGet(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("zhangsan", "100");
		map.put("lisi", "101");
		map.put("zhouwu", "102");
		map.put("zhaoliu", "103");
		System.out.println(redisClientOperation.mget("lx_eason_mset_1", "1").get(0));
	}

}
