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
<title>使用中原生页面管理</title>
<link href="<%=basePath %>Folder_Default/styles/content.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/custom.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/validation/validationEngine.jquery.css"  rel="stylesheet"  type="text/css" />
<link href="<%=basePath %>css/jqGrid/ui.jqgrid.css" "  rel="stylesheet"  type="text/css" />
<link href="<%=basePath %>css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/imgBar/imgbar.css"  rel="stylesheet" type="text/css" />

<script src="<%=basePath %>js/jquery-1.11.1.min.js"  type="text/javascript" ></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script src="<%=basePath %>js/layer/layer.min.js"      type="text/javascript" ></script>
<script src="<%=basePath %>js/validation/languages/jquery.validationEngine-zh_CN.js" charset="utf-8"></script>
<script src="<%=basePath %>js/validation/jquery.validationEngine.js" charset="utf-8"></script>
<script src="<%=basePath %>js/jqGrid/jquery.jqGrid.min.js"  type="text/javascript" ></script>
<script src="<%=basePath %>js/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="<%=basePath %>js/index.js" type="text/javascript"></script>
<script type="text/javascript">
	var path="<%=basePath%>";
	var serviceId = '${serviceId}';
  	var  url =  path+"paltformManage/getServiceOnRawGrid?serviceId="+serviceId;
  	//刷新或退出
  	window.onbeforeunload = function(){
  		var url =  path+"toRemoveSessionList";
    	   $.ajax({
             url: url,
             method: "post",
             success: function(msg) {
                 window.close();
             },
                timeout:60000,
                    error:function(XMLHttpRequest, textStatus, errorThrown) {
                    }
         }); 
  	};
	$(function(){
		//页面列表
		jQuery("#rawGrid").jqGrid({
			url:url,
            datatype: "json",
            height : 450,
			width : 800,
            rownumbers:true,
            colNames:["ID","服务ID","页面名称","版本","创建时间","是否主页"],
            colModel:[
                {name:'ID',index:'ID',"hidden":true},
                {name:'SERVICEID',index:'SERVICEID',"hidden":true},
                {name:'RESOURCENAME',index:'RESOURCENAME'},
                {name:'VERSION',index:'VERSION'},
                {name:'CREATETIME',index:'CREATETIME'},
                {name:'ISINDEX',index:'ISINDEX'
                }
            ],
            rowNum:-1,
            //rowList:[10,20,30],
            //pager: '#rawGridpager',
            sortable:true,
            //sortname: 'CREATIONDATE',
			viewrecords: true,
            multiselect: true,
            sortorder: "desc",
            jsonReader: { 
			root: "rows", 
			page: "page", 
			total: "total", 
			records: "records", 
			repeatitems: false,
			cell: "cell",  
			id: "ID"
			},
            gridComplete: function(){
     
            },
        }).navGrid("#rawGridpager",{edit:false,add:false,del:false});
	});	
	//保存
	function save_raw(){
		parent.layer.confirm('确定是否保存修改？', function(index){
            parent.layer.close(index);
            var i=parent.layer.load(0);
            var url =  path+"toSaveWaitingFromRaw?id="+serviceId;
	     	   $.ajax({
	              url: url,
	              method: "post",
	              success: function(msg) {
	            	  parent.layer.close(i);
	            	  var  urll= path+"paltformManage/getServiceOnRawGrid?serviceId="+serviceId;
	            	 	 jQuery("#rawGrid").jqGrid('setGridParam',{url:urll,page:1}).trigger("reloadGrid");
	            	 	 parent.layer.alert("保存成功！",1);
	              },
	                 timeout:60000,
	                     error:function(XMLHttpRequest, textStatus, errorThrown) {
	                    	 parent.layer.alert("提交失败，请联系管理员！",8);
	                     }
	          }); 
        });
	}
	//设置首页
	function set_index(){
		var ids=jQuery("#rawGrid").jqGrid('getGridParam','selarrrow');
		if(ids.length!=1){
			 parent.layer.alert("请选择一条页面进行设置！",8);
			 return;
		}else{
			parent.layer.confirm('请确认是否设置选中的页面作为首页？', function(index){
	            parent.layer.close(index);
	            var url =  path+"toModifyIndex?id="+ids[0];
	     	   $.ajax({
	              url: url,
	              method: "post",
	              success: function(msg) {
	            	  var  urll= path+"paltformManage/getServiceRawGrid?serviceId="+serviceId;
	            	 	 jQuery("#rawGrid").jqGrid('setGridParam',{url:urll,page:1}).trigger("reloadGrid");
	              },
	                 timeout:60000,
	                     error:function(XMLHttpRequest, textStatus, errorThrown) {
	                    	 parent.layer.alert("提交失败，请联系管理员！",8);
	                     }
	          }); 
	        });
		}
	}
	//新增页面
	function new_raw(){
		var  url = path+"toNewRawResourcePage?serviceId="+serviceId;
		window.open (url) ;
	}
	//修改页面
	function modify_raw(){
		var ids=jQuery("#rawGrid").jqGrid('getGridParam','selarrrow');
		if(ids.length!=1){
			 parent.layer.alert("请选择一条页面记录进行修改！",8);
			 return;
		}else{
	            var  url = path+"toModifyOnRawResourcePage?rawId="+ids[0]+"&serviceId="+serviceId
	            window.open (url) ;
		}
		
	}
	//刷新页面
	function  refreshPage(){
		  var  urll= path+"paltformManage/getServiceOnRawGrid?serviceId="+serviceId;
 	 	 jQuery("#rawGrid").jqGrid('setGridParam',{url:urll,page:1}).trigger("reloadGrid");
	}
	//删除页面
	function delete_raw(){
		var ids=jQuery("#rawGrid").jqGrid('getGridParam','selarrrow');
		if(ids.length==0){
			 parent.layer.alert("请至少选择一条页面记录进行删除！",8);
			 return;
		}else{
			parent.layer.confirm('请确认是否删除选中的页面？', function(index){
	            parent.layer.close(index);
	            var  url = path+"toDeleteRaw";
				   $.ajax({
		                url: url,
		                data : "ids="+ids,
		                method: "post",
		                success: function(msg) {
		                	 var  urll= path+"paltformManage/getServiceRawGrid?serviceId="+serviceId;
		            	 	 jQuery("#rawGrid").jqGrid('setGridParam',{url:urll,page:1}).trigger("reloadGrid");
		                },
		                   timeout:60000,
		 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
		 	                	  parent.layer.alert("提交失败，请联系管理员！",8);
		 	                  }
		            }); 
	        });
		}
	}
	//退出管理
	function quit_raw(){
		parent.layer.confirm('确定是否退出管理？', function(index){
			var url =  path+"toRemoveSessionList";
            parent.layer.close(index);
            $.ajax({
                url: url,
                method: "post",
                success: function(msg) {
                    window.close();
                },
                   timeout:60000,
 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
 	                	  parent.layer.alert("提交失败，请联系管理员！",8);
 	                  }
            }); 
     
        });
	}
	</script>
</head>
<body>
<div class=""  style="padding-top: 10px;margin-left: 250px"">
 					<a title="保存到待审批"  class="aToBtn bt_w96 bt_bg" id="fm_update" name="" href="javascript:void(0)" onclick="save_raw()">保存到待审批</a>
 					<a title="退出管理"  class="aToBtn bt_w96 bt_bg" id="fm_update" name="" href="javascript:void(0)" onclick="quit_raw()">退出管理</a>
                </div>
<div class="fm_options">
 					<!-- <a title="设置首页"  class="aToBtn bt_w96 bt_bg" id="" name="" href="javascript:void(0)" onclick="set_index()">设置首页</a>
                	<a title="新增页面"  class="aToBtn bt_w96 bt_bg" id="" name="" href="javascript:void(0)" onclick="new_raw()">新增页面</a> -->
                    <a title="修改页面"  class="aToBtn bt_w96 bt_bg" id="" name="" href="javascript:void(0)" onclick="modify_raw()">修改页面</a>
                    <!-- <a title="删除页面"  class="aToBtn bt_w96"  id="" name="" href="javascript:void(0)" onclick="delete_raw()" >删除页面</a> -->
                </div>
<div class=""  style="margin-top: 80px;margin-left: 250px">
        							<table id="rawGrid"></table>
									<div id="rawGridpager"></div>
        </div>
</body>
</table>
</html>
