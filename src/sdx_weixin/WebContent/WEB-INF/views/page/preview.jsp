<!doctype html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="utf-8">
<title>效果预览</title>
<link rel="stylesheet" href="css/jquery.mobile-1.4.2.min.css">
<link href="css/custom.css" rel="stylesheet" type="text/css" />
<link href="resource/css/skin.css" rel="stylesheet" type="text/css" />
<link href="resource/css/global.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery.mobile-1.4.2.min.js"></script>
<script type="text/javascript" src="js/qrcode.js"></script>
<script type="text/javascript">
	
	$(function(){
		var qrcode = new QRCode(document.getElementById("qrcode"), {
			width : 200,
			height : 200
		});
		qrcode.makeCode("http://11.0.46.26:8080/page/test.html");
	})
	
</script>
</head>

<body>
<div class="contentPr">
	<div class="l_qrCode">
		<div class="QrCode">
			<p class="qrTitle">手机扫描二维码预览页面</p>
			<div id="qrcode"  style="width:200px; height:200px; margin-top:15px;"></div>
		</div>
	</div>
	<div class="r_pview">
		<div class="phoneGray" id="data-theme-skin_ten">
			<div class="phoneRec">
			</div>
				<!-- <ul class="phoneScreen ui-content vertical w_h_Auto" id="details" style="position: relative;">
				</ul> -->
				<iframe src="gotoPrePage" id="koyoz" name="koyoz" frameborder="0" style=" overflow:hidden;height:440px;width:254px;margin:10px auto;display:block;">
            	
    			</iframe>
			<div class="phoneHome">
			</div>
		</div>
	</div>

</div>
</body>
</html>

