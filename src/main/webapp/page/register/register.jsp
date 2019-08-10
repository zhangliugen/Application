<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/include/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no">
<script src="${ctx}/js/register/register.js" type="text/javascript"></script>
<link href="${ctx}/css/register/register.css" type="text/css" rel="stylesheet" />
<title>注册</title>
</head>
<body>
	<div class="title">注册</div>
	<div id="pageA" class="page">
		<form id="REGISTER_FORM" action="${ctx }/register/registerHandler.do?action=register" method="post" accept-charset="utf-8">
			<div class="register-info">
				<label>用户名:</label>
				<input id="CUST_NAME" data-type="00" name="CUST_NAME" type="text" placeholder="请设置用户名" class="input-reg-ui">
				<span id="CUST_NAME_MSG" class="tip-ui"></span>
			</div>
			<div class="register-info">
				<label>手机号:</label>
				<input id="PHONE_NO" data-type="01" name="PHONE_NO" type="text" placeholder="可用于登录和找回密码" maxlength="11" class="input-reg-ui">
				<span id="PHONE_NO_MSG" class="tip-ui"></span>
			</div>
			<div class="register-info">
				<label>密&nbsp;&nbsp;&nbsp;码:</label>
				<input id="LOGIN_PWD" data-type="02" name="LOGIN_PWD" type="password" placeholder="请设置登录密码" class="input-reg-ui">
				<span id="LOGIN_PWD_MSG" class="tip-ui"></span>
			</div>
			<div class="register-info">
				<label>确认密码:</label>
				<input id="LOGIN_PWD_CONFIRM" data-type="03" name="LOGIN_PWD_CONFIRM" type="password" placeholder="请确认登录密码" class="input-reg-ui">
				<span id="LOGIN_PWD_CONFIRM_MSG" class="tip-ui"></span>
			</div>
			<div class="register-info">
				<label>验证码:</label>
				<input id="SMS_CODE" data-type="04" name="SMS_CODE" type="text" placeholder="请输入验证码" maxlength="6" class="input-reg-ui code-ui">
				<div id="SEND_CODE" class="send-code">发短信</div>
				<span id="SMS_CODE_MSG" class="tip-ui"></span>
			</div>
			<div class="register-btn-ui">
				<div class="btn-ui register">注册</div>
			</div>
		</form>
	</div>
	<!-- 隐藏域 将EL表达式的值提供于外联js -->
	<input id="ctx" hidden value="${ctx }">
</body>
</html>