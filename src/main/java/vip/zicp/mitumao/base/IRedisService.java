package vip.zicp.mitumao.base;

import java.util.Set;

public interface IRedisService {

	/**
	 * 通过key删除
	 * @param keys
	 * @return
	 */
	public abstract long del(String... keys);
	
	public abstract <T> void setT(String key, T value);
	public abstract <T> void setT(String key, T value, long liveTime);
	
	public abstract <T> T getT(String key);
	
	/**
	 * 添加key value 并且设置存活时间(byte)
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public abstract void set(byte[] key, byte[] value, long liveTime);
	
	/**
	 * 添加key value 并且设置存活时间
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public abstract void set(String key, String value, long liveTime);
	
	/**
	 * 添加key value 默认10分钟
	 * @param key
	 * @param value
	 */
	public abstract void set(String key, String value);
	
	/**
	 * 添加key value (字节)(序列化)
	 * @param key
	 * @param value
	 */
	public abstract void set(byte[] key, byte[] value);
	
	/**
	 * 获取redis value (String)
	 * @param key
	 * @return
	 */
	public abstract String get(String key);
	
	/**
     * 通过正则匹配keys
     * 
     * @param pattern
	 * @return 
     * @return
     */
    public abstract Set keys(String pattern);

    /**
     * 检查key是否已经存在
     * 
     * @param key
     * @return
     */
    public abstract boolean exists(String key);

    /**
     * 清空redis 所有数据
     * 
     * @return
     */
    public abstract String flushDB();

    /**
     * 查看redis里有多少数据
     */
    public abstract long dbSize();

    /**
     * 检查是否连接成功
     * 
     * @return
     */
    public abstract String ping();
    
    /**
     * 重置redis存活时间
     * @param key
     * @return
     */
    public abstract boolean resetLiveTime(String key);
    
    public abstract boolean resetLiveTime(String key,long liveTime);
	
}
