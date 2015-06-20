<!doctype html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="utf-8">
<title id="preview">preview</title>
</head>
<link rel="stylesheet" href="css/jquery.mobile-1.4.2.min.css">
<link href="css/custom.css" rel="stylesheet" type="text/css" />
<link href="resource/css/skin.css" rel="stylesheet" type="text/css" />
<link href="resource/css/global.css" rel="stylesheet" type="text/css" />
<link href="resource/css/template.css" rel="stylesheet" type="text/css" />
<link href="resource/css/eastoa/eastOA.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="resource/css/zTreeStyle.css">
<script src="js/jquery.js"></script>
<script src="resource/js/common.js"></script>
<script type="text/javascript" src="js/qrcode.js"></script>
<script type="text/javascript" src="resource/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#outerTabs").remove();
	$("#footerLi").remove();
})
</script>

<body id="bodyContent">

</body>
<script type="text/javascript">
$(function(){
	$("#bodyContent li").each(function(){
		if ($(this).attr("id")=="hidden_type"||$(this).attr("id")=="headernav_type"){
			$(this).remove();
		}
	});
	$('body').delegate('#tabs div','click',function(){
		var index = $("#tabs div").index($(this)[0]);
		var divs = $(".tab");
		$(this).parent().children("div").attr("class", "tab-nav");
		$(this).attr("class", "tab-nav-action");
		divs.hide();
		divs.eq(index).show();
	})
	$("#bodyContent li textarea").each(function(){
		if ($(this).attr("isdisabled")=="1"){//1是disabled,不可用
			$(this).attr('disabled',"true");
		}
	});
	var skinId = $(".phoneGray",parent.parent.document).attr('id');
	$("#bodyContent").attr("id",$.trim(skinId));
	var pageType = $("#pageType",parent.parent.document).val();
	var page = '';
	if (pageType=='blank'){
		page = parent.parent.genHtml('');
	}else if (pageType=='tab'){
		page = parent.parent.genTabHtml('');
	}
	console.log(page);
	var gHtml = parent.parent.generatePage(page);
	console.log(gHtml);
	var $html = $(gHtml);
	$html.find("li").removeClass('highlight');
	$('body').html(outerHtml($html));
	$('#details').css('height','auto');
	$('#details').css('width','auto');
	
	$('body').delegate('#chuli','click',function(){
		$('.DFOA_footer_more').slideToggle("slow");	
	});
	$('body').delegate('.dropdown a','click',function(){
		$(this).siblings(".dropdown_li").slideToggle()
		.parent().siblings().children('.dropdown_li').slideUp();
	})
});
var outerHtml=function(oh){  
	   return $('<div></div>').append(oh.clone()).html();  
};
</script>
<script src="js/jquery.mobile-1.4.2.min.js"></script>
</html>
<style type="text/css">
ul li{ margin:5px 0px;}
/* .ui-mobile, .ui-mobile body{overflow:hidden;} */
</style>