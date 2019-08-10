$(document).ready(function(){
	var pageA = $("#pageA");
	showPage(pageA,[]);
	$("body").css({
			"background":"url('../../img/pwdLoginbackground.jpg') no-repeat",
			"background-size": "cover",
			"background-attachment":"fixed"
		});
	$(".tab-ui-class").on("click",currentTab);
});

function showPage(show,hide){
	if (hide) {
		for(var i=0;i<hide.length;i++){
			hide[i].hide();
		}
	}
	$(show).show();
}

function currentTab(){
	if($(this).html().indexOf("短信") != -1){
		$("body").css({
			"background":"url('../../img/smsLoginbackground.jpg') no-repeat",
			"background-size": "cover",
			"background-attachment":"fixed"
		});
		$("#sms-code").show();
		$("#acct-pwd").hide();
		$(".reg-for").hide();
	}else if($(this).html().indexOf("密码") != -1){
		$("body").css({
			"background":"url('../../img/pwdLoginbackground.jpg') no-repeat",
			"background-size": "cover",
			"background-attachment":"fixed"
		});
		$("#acct-pwd").show();
		$("#sms-code").hide();
		$(".reg-for").show();
	}
	$(".current").removeClass("current");
	$(this).addClass("current");
}