$(document).ready(function(){
//	var path = window.location.host;
//	if(path == "localhost" || path == "25k248l723.zicp.vip" || path == "mitumao.zicp.net"){
//		return;
//	}
	afterInitSession();
});
/*
 * 引入同目录、同名js、css
 * 
 * 例：page/exercise/list.jsp
 * 引：js/exercise/list.js & css/exercise/list.css
 */
function afterInitSession() {
		var path = document.location.href;
		if(!(path.indexOf("page") != -1 && path.lastIndexOf(".jsp"))){
			return;
		}
		path = path.substring(path.indexOf("page"), path.lastIndexOf(".jsp"));
		path = path.substring(4);
		$("head").append('<script src="/js'+path+'.js" type="text/javascript"></script>');
		$("head").append('<link href="/css'+path+'.css" type="text/css" rel="stylesheet"/>');
};