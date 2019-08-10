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
<title>Welcome</title>
<style type="text/css">
	body{
		margin: 0;
		padding: 0;
	}
	.title{
		width:100%;
		text-align: center;
		background-color: #56A00E;
		height: 40px;
		line-height: 40px;
		font-size: 16px;
		font-weight: bold;
		text-shadow:0 1px 1px #335413;
		color: #fff;
		padding: 0;
	}
	.menu{
		width: 100%;
		height:50px;
		background: url("img/menu.jpeg") 0 0 no-repeat;
		background-size:100% 100%;
		line-height: 50px;
		text-indent: 3em;
		cursor: pointer;
	}
	.arrowtoright{
		position: absolute;
		right: 30px;
		padding-top: 5px;
	}
</style>
<script type="text/javascript">
	function redirect(data) {
		if(data=="1"){
			window.location.href="exercise/exerciseHandle.do?action=toList";
		}/* else if(data=="2"){
			window.location.href="http://192.168.0.17:8091/Note/page/FTPtest.jsp";
		}else if(data=="3"){
			var xmlhttp = "";
			if(window.XMLHttpRequest){
				xmlhttp = new XMLHttpRequest();
			}else{
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function(){
				if(xmlhttp.readyState==4 && xmlhttp.status==200){
					var content = JSON.parse(xmlhttp.responseText);
					var str = prompt("请输入密码","");
					if(str == content.password){
						window.location.href = content.address3;
					}else{
						alert("更多精彩功能将在后期添加!");
					}
				}
			};
			xmlhttp.open("GET", "test.txt", true);
			xmlhttp.send();
		} */
	}
	
</script>
</head>
<body>
	<div class="title">
		菜   单
	</div>
	<div class="menu" onclick="redirect(1)">每日计划<img class="arrowtoright" src="img/arrowtoright.png"/></div>
	
	<!-- 隐藏域 将EL表达式的值提供于外联js -->
	<input id="ctx" hidden value="${ctx }">
</body>
</html>