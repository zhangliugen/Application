<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/include/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no">	
<title>每日计划</title>
<link type="text/css" rel="stylesheet" href="${ctx }/css/exercise.css">
<script type="text/javascript" src="${ctx }/js/jquery-2.1.1.min.js"></script>	
<script type="text/javascript" src="${ctx }/js/exercise.js"></script>
</head>
<body>
	
	<!-- pageA -->
	<div id="pageA" class="page">
		<div class="title">
			<span>日历</span>
			<span class="back-bt" onclick="gomain()">主菜单</span>
		</div>
		<div class="date">
			<a href="${ctx }/exercise/exerciseHandle.do?action=toList&direction=left&currentDate=${date }">《</a>
			&nbsp;&nbsp;&nbsp;&nbsp;${date }&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${ctx }/exercise/exerciseHandle.do?action=toList&direction=right&currentDate=${date }">》</a>
		</div>
		<table id="table">
			<tr>
				<td>日</td><td>一</td><td>二</td><td>三</td><td>四</td><td>五</td><td>六</td>
			</tr>
		</table>
	</div>
	
	<!-- pageB -->
	<div id="pageB" class="page">
		<div class="title">
			<span>今日计划</span>
			<span class="back-bt" onclick="back()">返回</span>
		</div>
		<div class="fitness" data-value="01">俯卧撑<input class="fitness-isDone" value="未完成" readonly></div>
		<div class="fitness" data-value="02">深蹲+前抬腿+后抬腿+伞跳<input class="fitness-isDone" value="未完成" readonly></div>
		<div class="fitness" data-value="03">平板支撑<input class="fitness-isDone" value="未完成" readonly></div>
	</div>
	
	<!-- pageC -->
	<div id="pageC" class="page">
		<div class="fitness-item" data-value="01">
			<div class="title">
				<span>俯卧撑</span>
				<span class="back-bt" onclick="backItem()">返回</span>
			</div>
			
			<div class="clock">
				<span id="minute">00</span>:<span id="second">00</span>.<span id="millisecond">00</span>
			</div>
			<div class="clock-but">
				<div class="button" id="count-reset" data-val="0">计次</div>
				<div class="button" id="start-stop" data-val="0">启动</div>
			</div>
			
		</div>
		<div class="fitness-item" data-value="02">
			<div class="title">
				<span>深蹲+前抬腿+后抬腿+伞跳</span>
				<span class="back-bt" onclick="backItem()">返回</span>
			</div>
			
		</div>
		<div class="fitness-item" data-value="03">
			<div class="title">
				<span>平板支撑</span>
				<span class="back-bt" onclick="backItem()">返回</span>
			</div>
			
		</div>
	</div>
	
	
	
	<!-- 隐藏域 将EL表达式的值提供于外联js -->
	<input id="currentDay" hidden value="${currentDay }">
	<input id="maxDay" hidden value="${maxDay }">
	<input id="firstDay" hidden value="${firstDay }">
	<input id="currentDate" hidden value="${date }">
	<input id="ctx" hidden value="${ctx }">
</body>
</html>