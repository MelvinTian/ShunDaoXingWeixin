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
<link type="text/css" rel="stylesheet" href="resource/css/zTreeStyle.css">
<script src="js/jquery.js"></script>
<script src="resource/js/common.js"></script>
<script src="js/editorcommon/commoneditor.js"></script>
<script src="js/jquery-sortable.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script src="js/draggable.js"></script>
<script type="text/javascript" src="resource/js/jquery.ztree.all-3.5.min.js"></script>
<style type="text/css">
.ui-mobile .ui-page-active{box-sizing:border-box;}
</style>
</head>
<body id="bodyContent">
<input type="hidden" value="" id="activeId" />
<div style="display:none" id="tmp"></div>
<div id="canvas">
<%if (request.getAttribute("pageContent")!=null){%>
	${pageContent}
	<!-- <script src="js/jquery.mobile-1.4.2.min.js"></script>
	<script type="text/javascript">
		$.mobile.pageContainer.trigger("create");
	</script> -->
<% }%>
</div>
<script type="text/javascript">
function trigger(){
	$.mobile.pageContainer.trigger("create");
}
function loadHtml(){
	var selPageId = $("#selPageId",parent.document).val();	
	var rawId = $("#rawId",parent.document).val();
	$.post('loadHtml', {
		"selPageId":selPageId,
		"rawId":rawId
	}, function(data) {
		$('#canvas').html(data);
		$.mobile.pageContainer.trigger('create');
	});	
}

$(function(){
	//$('div[data-role=page]').css("min-height","356px");
	var serviceId = $("#serviceId",parent.document).val();
	var pid = $("#pId",parent.document).val();	
	//var selPageId = $("#selPageId",parent.document).val();	
	var rawId = $("#rawId",parent.document).val();	
	$.post('laodModulePage', {
		"serviceId" : serviceId,
		"pid":pid,
		//"selPageId":selPageId,
		"rawId":rawId
	}, function(data) {
		var skinId = data.skinId;//$(".phoneGray",parent.document).attr('id');
		$('#canvas').html(data.html);
		$('#reqMsgs',parent.document).html(data.protocol);
		$('#templateId',parent.document).val(data.templateId);
		$('#cssTemplateName',parent.document).val(data.cssTemplateName);
		//$('#jsMsgs',parent.document).val(data.jsContent);
		parent.window.jsEditor.setValue(data.jsContent);
		$('#pageType',parent.document).val(data.pageType);
		if (typeof parent.layer.close === "function"){
			parent.layer.close(parent.window.index);
			$.mobile.pageContainer.trigger('create');
			$("body").attr("id",$.trim(skinId));
		}
	}, 'json'); 
	/* if (selPageId&&pid){
	} */
})
</script>
<script src="js/jquery.mobile-1.4.2.min.js"></script>
<script src="js/meditor/meditor.js"></script>
<script src="js/meditor/plugins/LinkTitleEditor.js"></script>
<script src="js/meditor/core/editorDisplay.js"></script>

</body>
</html>