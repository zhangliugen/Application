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
<script src="${ctx}/js/login/login.js" type="text/javascript"></script>
<link href="${ctx}/css/login/login.css" type="text/css" rel="stylesheet" />
<title>Welcome</title>
</head>
<body>
	<div class="title">迷途猫</div>
	<div id="pageA" class="page">
		<div class="tab-ui">
			<div class="tab-ui-class current">账号/密码登录</div>
			<div class="tab-ui-class">短信验证登录</div>
		</div>
		<div class="main-content">
			<div id="acct-pwd">
				<input type="text" placeholder="手机/用户名/邮箱" class="input-ui user-ui">
				<input type="text" placeholder="请输入密码"class="input-ui lock-ui">
			</div>
			<div id="sms-code" hidden>
				<input type="text" placeholder="请输入手机号" class="input-ui phon-ui">
				<div class="code-send">
					<input type="text" placeholder="请输入验证码" class="input-ui code-ui"><input type="text" class="send-ui" value="发短信" readonly/>
				</div>
			</div>
		</div>
		<div class="login-btn-ui">
			<div class="btn-ui">登录</div>
		</div>
		<div class="reg-for">
			<a class="register" href="../../page/register/register.jsp">立即注册</a> 
			<a class="forgetpwd" href="###">忘记密码</a>
		</div>
	</div>
</body>
</html>