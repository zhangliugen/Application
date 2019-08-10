var timer = "";//计时器
var hour = 0;//小时
var minute = 0;//分钟
var second = 0;//秒
var millisecond = 0;//毫秒
function showPage(show,hide){
	if (hide) {
		for(var i=0;i<hide.length;i++){
			hide[i].hide();
		}
	}
	$(show).show();
}
//js按照规定的长度给指定的数字前面补0
function prefixIntrger(num,length){ 
	return (Array(length).join('0')+num).slice(-length); 
}

function gomain(){
	window.location.href="http://25k248l723.zicp.vip";
}
function backItem(){
	var pageA = $("#pageA");
	var pageB = $("#pageB");
	var pageC = $("#pageC");
	showPage(pageB,[pageA,pageC]);
}
function back(){
	var pageA = $("#pageA");
	var pageB = $("#pageB");
	var pageC = $("#pageC");
	showPage(pageA,[pageB,pageC]);
}
$(document).ready(function(){
	var pageA = $("#pageA");
	var pageB = $("#pageB");
	var pageC = $("#pageC");
	showPage(pageA,[pageB,pageC]);
	var currentDay = $("#currentDay").val();
	var maxDay = $("#maxDay").val();
	var firstDay = $("#firstDay").val();
	var count = 0;
	for(var i=1;i<=maxDay;i++){
		if ((i - (8 - firstDay)) % 7 == 0) {
			count++;
			$("#table").append('<tr id="tr'+count+'"></tr>');
			if(maxDay != i && maxDay-i<7){
				count++;
				$("#table").append('<tr id="tr'+count+'"></tr>');
			}
		}
		
	}
	var k = 0;
	for(var i=1;i<=count;i++){
		for(var j=0;j<7;j++){
			k++;
			$("#tr"+i).append('<td id="td'+k+'" name="td"></td>');
		}
	}
	for(i=0;i<maxDay;i++){
		var afterFirstDay = parseInt(firstDay)+i;
		$("#td"+afterFirstDay).html(i+1);
		if(i+1 == currentDay){
			$("#td"+afterFirstDay).css("color","red");
		}
	}
	
	//给带ID的td注册绑定事件
	$("td[id][name='td']").on("click",function(){
		if($(this).html() == ""){
			return;
		}else{
			var day = $(this).html();
			if(day.length == 1){
				day = "0"+day;
			}
			var getDay = $("#currentDate").val()+"-"+day;//当前点击获取到的日期 yyyy-MM-dd
			var now = new Date().Format("yyyy-MM-dd");//获取当前日期 yyyy-MM-dd
			if(getDay == now){//js 直接比较
				showPage(pageB,[pageA,pageC]);
				var ctx = $("#ctx").val();
				$.ajax({
					url:ctx+"/exercise/exerciseHandle.do?action=queryIsDone",
					type:"POST",
					data:{
						"date":now
					},
					dataType:"json",
					success:function(data){
						for(var i=0;i<data.length;i++){
							var map = data[i];
							var isDone = map.isDone;
							if(map.fitness_type == '01'){
								if(isDone == '1'){
									$(".fitness[data-value='01'] input").val("已完成").css("color","green");
								}
							}else if(map.fitness_type == '02'){
								if(isDone == '1'){
									$(".fitness[data-value='02'] input").val("已完成").css("color","green");
								}
							}else if(map.fitness_type == '03'){
								if(isDone == '1'){
									$(".fitness[data-value='03'] input").val("已完成").css("color","green");
								}
							}
						}
					}
				});
				
				$(".fitness").on("click",function(){
					var val = $(this).attr("data-value");
					$.ajax({
						url:ctx+"/exercise/exerciseHandle.do?action=toSport",
						type:"POST",
						data:
						{
							"date":getDay,
							"fitnessType":val
						},
						dataType:"json",
						success:function(data){
							showPage(pageC,[pageA,pageB]);
							showPage($(".fitness-item[data-value="+val+"]"),[$(".fitness-item[data-value!="+val+"]")]);
						}
					});
				});
				
				$("#start-stop").on("click",function(){
					if($(this).attr("data-val") == 0){//启动
						timer = setInterval(function(){
							millisecond=millisecond+10;
						      if(millisecond>=1000){
						        millisecond=0;
						        second=second+1;
						      }
						      if(second>=60)
						      {
						        second=0;
						        minute=minute+1;
						      }
	
						      if(minute>=60)
						      {
						        minute=0;
						        hour=hour+1;
						      }
						      $("#minute").text(prefixIntrger(minute,2));
						      $("#second").text(prefixIntrger(second,2));
						      $("#millisecond").text(prefixIntrger(millisecond/10,2));
						},10);
						$(this).html("暂停").attr("data-val","1");
						$("#count-reset").html("计次").attr("data-val","0");
					}else{//暂停
						 clearInterval(timer);
						 $(this).html("启动").attr("data-val","0");
						 $("#count-reset").html("复位").attr("data-val","1");
					}
				});
				
				
				var clickCount = 0;
				$("#count-reset").on("click",function(i){
					if($(this).attr("data-val") == 0){//计次
						if($("#start-stop").attr("data-val") == 0){
							return;
						}
						clickCount++;
						$(".fitness-item[data-value='01']").append('<div class="count">计次'+clickCount+$(".clock").text()+'</div>');
					}else{//复位
						$(".fitness-item[data-value='01'] .count").remove();
						$("#minute").text("00");
					    $("#second").text("00");
					    $("#millisecond").text("00");
					    clickCount = 0;
					    hour = 0;//小时
					    minute = 0;//分钟
					    second = 0;//秒
					    millisecond = 0;//毫秒
					    clearInterval(timer);
					}
				});
			}
		}
	});
});
//格式化日期yyyy-MM-dd HH:mm:ss
Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

