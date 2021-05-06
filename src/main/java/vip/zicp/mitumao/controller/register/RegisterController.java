package vip.zicp.mitumao.controller.register;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import vip.zicp.mitumao.base.IRedisService;
import vip.zicp.mitumao.security.MD5Encrypt;
import vip.zicp.mitumao.service.CheckSmsCodeService;
import vip.zicp.mitumao.util.RandomUtil;

@Controller
@RequestMapping("register/registerHandler.do")
public class RegisterController {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sql;
	
	@Autowired
	@Qualifier("redisService")
	private IRedisService redis ;
	
	@Autowired
	private CheckSmsCodeService checkSmsCodeService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping(params= {"action=register"})
	public String register(HttpServletRequest request,HttpServletResponse response) {
		try {
			
			Map params = new HashMap();
			String custName =  request.getParameter("CUST_NAME");
			String phoneNo =  request.getParameter("PHONE_NO");
			String loginPwd =  request.getParameter("LOGIN_PWD");
			String loginPwdConfirm =  request.getParameter("LOGIN_PWD_CONFIRM");
			String smsCode =  request.getParameter("SMS_CODE");
			String MD5loginPwd = MD5Encrypt.MD5(loginPwd);
			params.put("uuid", RandomUtil.createTransSeq());
			params.put("username", custName);
			params.put("password", MD5loginPwd);
			params.put("phone_no", phoneNo);
			sql.insert("USER.insert",params);
			request.setAttribute("STATUS", "1");
			request.setAttribute("RESULT", "注册成功!");
		} catch (Exception e) {
			request.setAttribute("STATUS", "0");
			request.setAttribute("RESULT", "注册失败!");
			logger.error(e.getMessage(), e);
		}
		return "register/msg";
	}
	@RequestMapping(params = {"action=checkUserInfo"})
	@ResponseBody
	public Map checkUserInfo(String type,String message) {
		Map map = new HashMap();
		Map param = new HashMap();
		if("00".equals(type)) { //检查用户名是否存在
			param.put("username", message);
			int count  = sql.selectOne("USER.selectUsername", param);
			if(count == 0) {
				map.put("STATUS", "1");
			}else {
				map.put("STATUS", "0");
			}
		}else if("01".equals(type)) {
			param.put("phone_no", message);
			int count  = sql.selectOne("USER.selectPhoneNo", param);
			if(count == 0) {
				map.put("STATUS", "1");
			}else {
				map.put("STATUS", "0");
			}
		}
		return map;
	}
	@RequestMapping(params = {"action=sendMessage"})
	@ResponseBody
	public Map sendMessage(String phone) {
		Map map = new HashMap();
		//aliyun AccessKey ID 阿里云提示在GitHub上泄露，故替换了
		DefaultProfile profile = DefaultProfile.getProfile("default", "aliyun AccessKey ID", "1bIqj0jZpLU2MIdNwdt0cgO7okiMs6");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        String code = RandomUtil.randomInt(6);
        JSONObject json = new JSONObject();
        json.put("code", code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(json));
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "迷途猫");
        request.putQueryParameter("TemplateCode", "SMS_167973883");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            redis.set("REGISTER_SMS_CODE", code, 120);
            System.out.println("你本次注册获取的验证码为："+code);
            map.put("STATUS", "1");
        } catch (ServerException e) {
        	map.put("STATUS", "0");
        	logger.error(e.getMessage(), e);
        } catch (ClientException e) {
        	map.put("STATUS", "0");
        	logger.error(e.getMessage(), e);
        }
		return map;
	}
	
	
	@RequestMapping(params = {"action=checkSmsCode"})
	@ResponseBody
	public Map checkSmsCode(String smscode) {
		Map map = new HashMap();
		try {
			checkSmsCodeService.check("REGISTER_SMS_CODE", smscode);
			map.put("STATUS", "1");
			map.put("MSG", "交易成功");
		} catch (Exception e) {
			map.put("STATUS", "0");
			map.put("MSG", e.getMessage());
		}
		return map;
	}
}

