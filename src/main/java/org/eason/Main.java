package org.eason;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eason.redis.operate.RedisClientOperation;
import org.eason.redis.operate.RedisClientOperationImpl;
import org.junit.Test;

public class Main {
	
	final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		Main main = new Main();
		main.TestMGet();
		main.TestMSet();
		main.TestSet();
		logger.info("This is finished!");
	}
	
	RedisClientOperation redisClientOperation = new RedisClientOperationImpl();

	public void TestMSet(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("zhangsan", "100");
		map.put("lisi", "101");
		map.put("zhouwu", "102");
		map.put("zhaoliu", "103");
		logger.info(redisClientOperation.mset("lx_eason_mset_1", map));
	}
	
	public void TestMGet(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("zhangsan", "100");
		map.put("lisi", "101");
		map.put("zhouwu", "102");
		map.put("zhaoliu", "103");
		logger.info(redisClientOperation.mget("lx_eason_mset_1", "zhangsan").get(0));
	}
	
	public void TestSet(){
		String val = "{\"fmt\":\"json\",\"sessionkey\":\"269853869537233\",\"os\":\"android\",\"userid\":\"140740349270472\",\"op\":\"getFrdbyAddrbook\",\"phonelist\":\"15011018687|测试三号,15011018686|测试号\",\"uid\":\"140740349270472\",\"ip\":\"192.168.31.11\",\"v\":\"2.3\"}";
		String key = "qkey_add_book_140740349270472";
		
		redisClientOperation.set(key, val);
		
	}
	
	@Test
	public void testHashTags(){
	    String key1 = "{eason.feng}.xxx";
	    String val1 = "xxxxxx";
	    
	    String key2 = "{eason.feng}.d";
	    String val2 = "dddddd";
	    
	    //keys {eason.feng} are the same, so it will store into same data node.
	    
	    redisClientOperation.set(key1, val1);
	    redisClientOperation.set(key2, val2);
	}
	
	@Test
	public void testNX(){
	    String key = "aaaxxxfff";
	    String val = "bbbsss";
	    redisClientOperation.set(key, val);
	    val = "cccc";
	    redisClientOperation.setOnlyNonExist(key, val);
	    logger.info(redisClientOperation.get(key));
	    redisClientOperation.set(key, val);
	    logger.info(redisClientOperation.get(key));
	}
	
	@Test
	public void testExpire(){
	    String key = "aaaxxxfff";
	    String val = "bbbsss";
	    redisClientOperation.setWithExpireTime(key, val, 5);
	    try {
		logger.info(redisClientOperation.get(key));
		Thread.sleep(10000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    logger.info(redisClientOperation.get(key));
	}
	
	@Test
	public void testLPush(){
	    String key = "abcdedfs";
//	    String val = "bbbsss";
//	    redisClientOperation.lpush(key, val, val);
	    logger.info(redisClientOperation.range(key, 0, 150));
	}
	

}
