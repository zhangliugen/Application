$(document).ready(function(){
	var pageA = $("#pageA");
	showPage(pageA,[]);
	initEvent();
});

function initEvent(){
	$(".register").on("click",toResgister);//去注册
	$("#CUST_NAME").on("blur",checkUserInfo);//检查用户信息是否存在
	$("#PHONE_NO").on("blur",checkUserInfo);//检查用户信息是否存在
	$("#LOGIN_PWD").on("blur",checkUserInfo);//检查用户信息是否存在
	$("#LOGIN_PWD_CONFIRM").on("blur",checkUserInfo);//检查用户信息是否存在
	$("#SMS_CODE").on("blur",checkUserInfo);//检查用户信息是否存在
	$("#SEND_CODE").on("click",sendMessage);//发送短信
}
//发送短信
var smsclick = false;
function sendMessage(){
	if(!$("#PHONE_NO").val()){
		$("#PHONE_NO_MSG").html("手机号还没输！");
		return;
	}
	if(smsclick == true){
		return;
	}
	smsclick = true;
	var ctx = $("#ctx").val();
	$.ajax({
		url:ctx+"/register/registerHandler.do?action=sendMessage",
		type:"POST",
		data:{
			"phone":$("#PHONE_NO").val()
		},
		dataType:"json",
		success:function(data){
			if(data.STATUS == 1){
				$("#SMS_CODE_MSG").html("短信发送成功！");
				countDown();
			}else{
				$("#SMS_CODE_MSG").html("短信发送失败！");
				smsclick = false;
			}
		}
	});
}

function countDown(){
	var code = $("#SEND_CODE");
	var time = 120;
	code.text(time).css("background-color","#808080");
	var t = setInterval(function(){
		time--;
		if(time<=0){
			clearInterval(t);
			smsclick = false;
			code.text("发短信").css("background-color","#0079f2");
			$("#SMS_CODE_MSG").html("");
		}else{
			code.text(time);
		}
	},1000);
}

//注册
var click = false;
function toResgister(){
	if(!$("#CUST_NAME").val()){
		$("#CUST_NAME_MSG").html("用户名不能为空！");
		return;
	}
	if(!$("#PHONE_NO").val()){
		$("#PHONE_NO_MSG").html("手机号不能为空！");
		return;
	}
	if(!$("#LOGIN_PWD").val()){
		$("#LOGIN_PWD_MSG").html("密码不能为空！");
		return;
	}
	if(!$("#LOGIN_PWD_CONFIRM").val()){
		$("#LOGIN_PWD_CONFIRM_MSG").html("确认密码不能为空！");
		return;
	}
	if(!$("#SMS_CODE").val()){
		$("#SMS_CODE_MSG").html("验证码不能为空！");
		return;
	}
	if($("#LOGIN_PWD").val().length <6){
		$("#LOGIN_PWD_MSG").html("密码最小长度为6！");
		return;
	}
	if($("#LOGIN_PWD").val().length != $("#LOGIN_PWD_CONFIRM").val().length || $("#LOGIN_PWD").val() != $("#LOGIN_PWD_CONFIRM").val()){
		$("#LOGIN_PWD_CONFIRM_MSG").html("两次密码不一致！");
		return;
	}
	if($("#SMS_CODE").val().length <6){
		$("#SMS_CODE_MSG").html("验证码最小长度为6！");
		return;
	}
	if($("#CUST_NAME_MSG").html() != "√"|| 
	   $("#PHONE_NO_MSG").html() != "√"||
	   $("#LOGIN_PWD_MSG").html() != "√"||
	   $("#LOGIN_PWD_CONFIRM_MSG").html() != "√"||
	   $("#SMS_CODE_MSG").html() != "√"){
		return;
	}
	if(click == true){
		return;
	}
	click == true;
	
	var ctx = $("#ctx").val();
	var code = $("#SMS_CODE").val();
	$.ajax({
		url:ctx+"/register/registerHandler.do?action=checkSmsCode",
		type:"POST",
		data:{
			"smscode":code
		},
		dataType:"json",
		success:function(data){
			if(data.STATUS == "1"){
				$("#SMS_CODE_MSG").html("验证成功！");
				$("#REGISTER_FORM").submit();
			}else{
				$("#SMS_CODE_MSG").html(data.MSG);
			}
		}
	});
	
	
	
	
	
	
	
}
//检查用户信息是否存在
function checkUserInfo(){
	var ctx = $("#ctx").val();
	var type = $(this).attr("data-type");
	var message = $(this).val();
	if(!message){
		$(this).siblings("span").html("");
		return;
	}else{
		if(type == '02' || type == '03'||type == '04'){
			$(this).siblings("span").html("√");
			return;
		}
	}
	$.ajax({
		url:ctx+"/register/registerHandler.do?action=checkUserInfo",
		type:"POST",
		data:{
			"type":type,
			"message":message
		},
		dataType:"json",
		success:function(data){
			var state = data.STATUS;
			if(type == '00'){
				if(state == 0){
					$("#CUST_NAME_MSG").html("用户名已存在！");
				}else{
					if(!/^[0-9A-Za-z\u4e00-\u9fa5]{2,7}$/.test($("#CUST_NAME").val())){
						$("#CUST_NAME_MSG").html("2-7位数字、字母、汉字组合");
					}else{
						$("#CUST_NAME_MSG").html("√");
					}
				}
			}else if(type == '01'){
				if(state == 0){
					$("#PHONE_NO_MSG").html("手机号已存在！");
				}else if(!/^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/.test($("#PHONE_NO").val())){
					$("#PHONE_NO_MSG").html("手机号格式错误！");
				}else{
					$("#PHONE_NO_MSG").html("√");
				}
			}
		}
	});
}






function showPage(show,hide){
	if (hide) {
		for(var i=0;i<hide.length;i++){
			hide[i].hide();
		}
	}
	$(show).show();
}