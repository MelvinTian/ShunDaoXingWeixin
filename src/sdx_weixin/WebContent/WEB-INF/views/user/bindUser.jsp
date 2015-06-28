<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>Bootstrap 101 Template</title>
<!-- Bootstrap -->
<link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>css/ladda-themeless.min.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script src="<%=basePath %>js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/spin.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/ladda.min.js" type="text/javascript"></script>
<title>用户绑定</title>
</head>
  <body>
    <h1>你好，世界！</h1>
	  <div class="col-lg-6">
	    <div class="input-group">
	      <input id="userMobile" type="text" class="form-control" placeholder="用户手机号" />
	      <span class="input-group-btn">
	        <button class="btn btn-default ladda-button" type="button" id="btnBindUser" data-style="expand-right"><span class="ladda-label">绑定</span></button>
	      </span>
	    </div><!-- /input-group -->
	  </div><!-- /.col-lg-6 -->
	</div><!-- /.row -->
	<script type="text/javascript">
	$(function (){
		$("#btnBindUser").click(function(e){
			var l = Ladda.create(this);
			l.start();
			var userMobile = $("#userMobile").val();
			$.get("<%=basePath %>user/bind/${openId}?userMobile=" + userMobile, function(result){
			    alert(result.userId);
			  }, "json").always(function() { l.stop(); });;
		});
	});
	</script>
  </body>
</html>