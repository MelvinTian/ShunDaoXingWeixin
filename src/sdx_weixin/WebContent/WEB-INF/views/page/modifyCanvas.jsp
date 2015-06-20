<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="utf-8">
<title id="canvasTitle">canvas</title>
<link rel="stylesheet" href="css/jquery.mobile-1.4.2.min.css">
<link href="css/custom.css" rel="stylesheet" type="text/css" />
<link href="resource/css/skin.css" rel="stylesheet" type="text/css" />
<link href="resource/css/global.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="js/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="js/jquery-ui.theme.css"/>
<script src="js/jquery.js"></script>
<script src="resource/js/common.js"></script>
<script src="js/editorcommon/commoneditor.js"></script>
<script src="js/jquery-sortable.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<style type="text/css">
.ui-page-active{box-sizing:border-box!important;min-height:100%!important;}
</style>
</head>
<body id="bodyContent">
<input type="hidden" value="" id="activeId" />
<div id="canvas">
<%if (request.getAttribute("pageContent")!=null){%>
	${pageContent}
<% }%>
</div>
<script type="text/javascript">
function trigger(){
	$.mobile.pageContainer.trigger("create");
}
$(function(){
	var serviceId = $("#serviceId",parent.document).val();
	var pid = $("#pId",parent.document).val();	
	console.log(pid);
	if (pid!=""){
		$.post('laodModulePage', {
			"serviceId" : serviceId,
			"pid":pid
		}, function(data) {
			var skinId = $(".phoneGray",parent.document).attr('id');
			$("#bodyContent").attr("id",$.trim(skinId));
			$('#canvas').html(data.html);
			$('#reqMsgs',parent.document).html(data.protocol);
			$('#templateId',parent.document).val(data.templateId);
			$('#cssTemplateName',parent.document).val(data.cssTemplateName);
			$('#jsMsgs',parent.document).val(data.jsContent);
			parent.layer.close(parent.window.index);
			$.mobile.pageContainer.trigger('create');
		}, 'json'); 
	}else{
		var skinId = $(".phoneGray",parent.document).attr('id');
		$("#bodyContent").attr("id",$.trim(skinId));
		$.mobile.pageContainer.trigger('create');
	}
})
</script>
<script src="js/jquery.mobile-1.4.2.min.js"></script>
<script src="js/meditor/meditor.js"></script>
<script src="js/meditor/plugins/LinkTitleEditor.js"></script>
<script src="js/meditor/core/editorDisplay.js"></script>
<script src="js/draggable.js"></script>
</body>
</html>