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
<title></title>
<style type="text/css">
	.msg-ui{
		font-size: 28px;
	}
</style>
</head>
<body>
	<div class="title">注册</div>
	<div class="msg-ui">
		${RESULT }
		<a href="../../page/login/login.jsp">去登录</a>
	</div>
</body>
</html>