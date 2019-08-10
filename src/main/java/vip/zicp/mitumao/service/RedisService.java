package vip.zicp.mitumao.service;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vip.zicp.mitumao.base.IRedisService;
import vip.zicp.mitumao.util.SerializeUtil;


/**
 * 封装redis 缓存服务器服务接口
 * @author lijunyu
 *
 */
@Service
@SuppressWarnings("unchecked")
public class RedisService implements IRedisService{
	
	private static String redisCode = "utf-8";
	
	@Autowired
    private RedisTemplate redisTemplate;
	
	private RedisService() {

    }
	private static RedisService instance = new RedisService();

	public static RedisService getInstance() {
		return instance;
	}

	public long del(final String... keys) {
		// TODO Auto-generated method stub
		return (Long) redisTemplate.execute(new RedisCallback(){
			public Long doInRedis(RedisConnection connection) 
					throws DataAccessException {
				long result = 0;
				for (int i = 0; i < keys.length; i++) {
					result = connection.del(keys[i].getBytes());
				}
				 return result;
			}
		});
	}
	
	public void set(final byte[] key, final byte[] value, final long liveTime) {
		// TODO Auto-generated method stub
		redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
	}

	
	public void set(String key, String value, long liveTime) {
		// TODO Auto-generated method stub
		this.set(key.getBytes(), value.getBytes(), liveTime);
	}

	
	public void set(String key, String value) {
		// TODO Auto-generated method stub
		this.set(key, value, 300);
	}

	
	public void set(byte[] key, byte[] value) {
		// TODO Auto-generated method stub
		this.set(key, value, 300);
	}
	
	public <T> void setT(String key, T value) {
		setT(key.getBytes(),value,300);
	}
	public <T> void setT(String key, T value, long liveTime) {
		setT(key.getBytes(),value,liveTime);
	}
	
	public <T> void setT(final byte[] key, T value, long liveTime){
		set(key,SerializeUtil.serialize(value),liveTime);
	}
	
	public <T> T getT(String key){
		return (T) SerializeUtil.unserialize(get(key.getBytes()));
	}
	
	public byte[] get(final byte[] key) {
		// TODO Auto-generated method stub
		return (byte[])redisTemplate.execute(new RedisCallback() {
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
            	return connection.get(key);
            }
        });
	}
	
	public String get(final String key) {
		// TODO Auto-generated method stub
		return (String)redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                	if(connection.exists(key.getBytes())){
                		return new String(connection.get(key.getBytes()), redisCode);
                	}else{
                		return "";
                	}
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });
	}

	
	public Set keys(String pattern) {
		// TODO Auto-generated method stub
		return redisTemplate.keys(pattern);
	}

	
	public boolean exists(final String key) {
		// TODO Auto-generated method stub
		return (Boolean)redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
	}

	
	public String flushDB() {
		// TODO Auto-generated method stub
		return (String)redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
	}

	
	public long dbSize() {
		// TODO Auto-generated method stub
		return (Long)redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
	}

	
	public String ping() {
		// TODO Auto-generated method stub
		return (String)redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {

                return connection.ping();
            }
        });
	}
	
	
	public boolean resetLiveTime(String key){
		return resetLiveTime(key.getBytes(), 300);
	}
	
	public boolean resetLiveTime(String key,long liveTime){
		return resetLiveTime(key.getBytes(), liveTime);
	}
	
	public boolean resetLiveTime(final byte[] key,final long liveTime){
		return (Boolean)redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.expire(key, liveTime);
            }
        });
	}

}
