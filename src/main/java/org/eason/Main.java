package org.eason;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eason.redis.operate.RedisClientOperation;
import org.eason.redis.operate.RedisClientOperationImpl;

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

}
