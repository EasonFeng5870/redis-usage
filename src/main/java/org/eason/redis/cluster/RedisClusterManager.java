package org.eason.redis.cluster;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eason.resourceLoader.ResourceLoader;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


/**
 * this is for load jedis cluster manger
 * 
 * @author fengyingsheng
 *
 */
public class RedisClusterManager {
	
	private static final Logger logger = Logger.getLogger(RedisClusterManager.class);
	
	private static final String CONF_FILE = "conf.properties";
	
	private static Properties getClusterList(){
		Properties properties = ResourceLoader.getConfigurations(CONF_FILE);
		return properties;
	}
	
	public static synchronized JedisCluster getJedisCluster(){
		Properties p = getClusterList();
		logger.debug("cluster list is: " + p);
		final String[] redisClusters = ((String) p.get("redis-cluster.url")).split(",");
		final Set<HostAndPort> set = new HashSet<HostAndPort>();
		for (int i = 0; i < redisClusters.length; i++) {
			final String[] hostAndPort = redisClusters[i].split(":");
			if(hostAndPort.length < 2){
				logger.error("please provide a compleate node like [ip:port]");
				continue;
			}
			set.add(new HostAndPort(hostAndPort[0], hostAndPort[1].matches("^[0-9]*$") ? Integer.parseInt(hostAndPort[1]) : 6397) );
		}
		
		if(set.size() < 1){
			logger.error("please provide at least one redis server node.");
			return null;
		}
		return new JedisCluster(set);
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(RedisClusterManager.getJedisCluster());
	}

}
