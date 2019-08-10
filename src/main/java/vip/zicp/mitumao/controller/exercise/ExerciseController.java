package vip.zicp.mitumao.controller.exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("exercise/exerciseHandle.do")
public class ExerciseController {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sql;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(params = "action=toList")
	public String list(HttpServletRequest request) {
		//打卡日历
		try {
			String direction = request.getParameter("direction");
			String currentDate = request.getParameter("currentDate");
			int maxDay = 0;	
			int firstDay = 0;	
			int currentDay = 0;
			Date now = new Date();//当前日期，不做改变，用于判断
			Date date = new Date();//当前日期，会发生改变
			Calendar calendar = Calendar.getInstance();//当月日历，会发生改变
			Calendar cnow = Calendar.getInstance();//当月日历，不做改变，用于判断
			if(StringUtils.isEmpty(currentDate)) {
				currentDate = new SimpleDateFormat("yyyy-MM").format(date);
				currentDay = calendar.get(Calendar.DATE);	//当前日期中的当前天
			}else {
				date = new SimpleDateFormat("yyyy-MM").parse(currentDate);
			}
			calendar.setTime(date);	//将日期转化为日历
			if("left".equals(direction)) {
				calendar.add(Calendar.MONTH, -1);
				Date beforeDate = calendar.getTime();
				currentDate = new SimpleDateFormat("yyyy-MM").format(beforeDate);
				if(new SimpleDateFormat("yyyy-MM").format(now).equals(currentDate)) {
					currentDay = cnow.get(Calendar.DATE);
				}
			}else if("right".equals(direction)) {
				calendar.add(Calendar.MONTH, +1);
				Date afterDate = calendar.getTime();
				currentDate = new SimpleDateFormat("yyyy-MM").format(afterDate);
				if(new SimpleDateFormat("yyyy-MM").format(now).equals(currentDate)) {
					currentDay = cnow.get(Calendar.DATE);
				}
			}
			maxDay = calendar.getActualMaximum(Calendar.DATE);	//当前日期中当前月对应的最大天数
			calendar.set(Calendar.DATE, 1); // 设置为当前月的第一天
			firstDay = calendar.get(Calendar.DAY_OF_WEEK);	//当前日期中当前月第一天对应的星期数
			request.setAttribute("firstDay", firstDay);
			request.setAttribute("currentDay", currentDay);
			request.setAttribute("maxDay", maxDay);
			request.setAttribute("date", currentDate);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return "exercise/list";
	}
	
	@RequestMapping(params = "action=queryIsDone")
	@ResponseBody
	public List query(String date) {
		Map params = new HashMap();
		params.put("start", date+" 00:00:00");
		params.put("end", date+" 23:59:59");
		List<Map> result = sql.selectList("EXERCISE.selectByDate", params);
		return result;
	}
	
	
	
	
	@RequestMapping(params = "action=toSport")
	@ResponseBody
	public Map sport(String fitnessType,String date) {
		Map map = new HashMap();
		Map params = new HashMap();
		params.put("user_name", "张小帅");
		params.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()));
		if("01".equals(fitnessType)) {
			params.put("fitness_item", "俯卧撑");
		}else if("02".equals(fitnessType)) {
			params.put("fitness_item", "深蹲+前抬腿+后抬腿+伞跳");
		}else if("03".equals(fitnessType)) {
			params.put("fitness_item", "平板支撑");
		}
		params.put("time_consuming", 0);
		params.put("isDone", "0");
		params.put("fitness_type", fitnessType);
		params.put("startTime", date+" 00:00:00");
		params.put("endTime", date+" 23:59:59");
		Map rec = sql.selectOne("EXERCISE.selectByDateAndType", params);
		if(MapUtils.isEmpty(rec)) {
			sql.insert("EXERCISE.insert", params);
		}
		return map;
	}
	
}
