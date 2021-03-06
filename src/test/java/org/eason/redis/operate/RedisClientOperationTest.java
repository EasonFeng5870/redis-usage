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
	
	@Test
	public void TestSet(){
		String val = "{\"fmt\":\"json\",\"sessionkey\":\"269853869537233\",\"os\":\"android\",\"userid\":\"140740349270472\",\"op\":\"getFrdbyAddrbook\",\"phonelist\":\"15011018687|测试三号,15011018686|测试号\",\"uid\":\"140740349270472\",\"ip\":\"192.168.31.11\",\"v\":\"2.3\"}";
		String key = "qkey_add_book_140740349270472";
		
		redisClientOperation.set(key, val);
		
	}

}
