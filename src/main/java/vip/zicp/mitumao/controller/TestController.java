package vip.zicp.mitumao.controller;

import java.net.URL;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
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
@Controller
public class TestController {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sql;
	
	@Autowired
	@Qualifier("redisService")
	private IRedisService redis ;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("test.do")
	public void test() {
//		System.out.println("11");
//		List list = sql.selectList("USER.select");
//		System.out.println(list);
		try {
			redis.set("REGISTER_FLAG", "1234561",30);
			logger.info(redis.get("REGISTER_FLAG"));
			int a = 1/0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
//	  public static void main(String[] args) {
//	        DefaultProfile profile = DefaultProfile.getProfile("default", "<accessKeyId>", "<accessSecret>");
//	        IAcsClient client = new DefaultAcsClient(profile);
//	        CommonRequest request = new CommonRequest();
//	        request.setMethod(MethodType.POST);
//	        request.setDomain("dysmsapi.aliyuncs.com");
//	        request.setVersion("2017-05-25");
//	        request.setAction("SendSms");
//	        request.putQueryParameter("TemplateParam", "");
//	        request.putQueryParameter("PhoneNumbers", "13063419587");
//	        request.putQueryParameter("SignName", "mitumao");
//	        request.putQueryParameter("TemplateCode", "SMS_167973883");
//	        try {
//	            CommonResponse response = client.getCommonResponse(request);
//	            System.out.println(response.getData());
//	        } catch (ServerException e) {
//	            e.printStackTrace();
//	        } catch (ClientException e) {
//	            e.printStackTrace();
//	        }
		  
		  
//		  
//	    }
	
	public static void main(String[] args) {  
        // lat 39.97646       
        //log 116.3039   
        String add = getAdd("116.3039", "39.97646");  
       
    }  
      
    public static String getAdd(String log, String lat ){  
        //lat 小  log  大  
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)  
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";  
        String res = "";     
        try {     
            URL url = new URL(urlString);    
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
            conn.setDoOutput(true);    
            conn.setRequestMethod("POST");    
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
            String line;    
           while ((line = in.readLine()) != null) {    
               res += line+"\n";    
         }    
            in.close();    
        } catch (Exception e) {    
        	e.printStackTrace();
            System.out.println("error in wapaction,and e is " + e.getMessage());    
        }   
        System.out.println(res);  
        
        JSONObject jsonObject = JSONObject.parseObject(res);  
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("addrList"));  
        JSONObject j_2 = JSONObject.parseObject((String) jsonArray.get(0));  
        String allAdd = j_2.getString("admName");  
        //String arr[] = allAdd.split(",");  
       // System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]);  
        
        return allAdd;    
    }  
}
