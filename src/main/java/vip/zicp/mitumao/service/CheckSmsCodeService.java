package vip.zicp.mitumao.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import vip.zicp.mitumao.base.IRedisService;

@Service
public class CheckSmsCodeService {
	
	@Autowired
	@Qualifier("redisService")
	private IRedisService redis ;
	
	public void check(String key,String smsCode) throws Exception {
		String code = redis.get(key);
		System.out.println("redis中获取的验证码为："+code);
		if(StringUtils.isEmpty(code)) {
			throw new RuntimeException("验证码未获取或已失效!");
		}
		if(!smsCode.equals(code)) {
			throw new RuntimeException("验证码不正确!");
		}
	}
}
