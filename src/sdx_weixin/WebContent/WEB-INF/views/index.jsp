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
<title>页面管理</title>
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
</head>

<body>
    <div class="header fixed">
    	<h1 class="sysTitle fl">业务流程平台</h1>
        <ul class="nav fl">
        <!-- 	<li class="hover"><a href="manage.html">管理</a></li>
            <li><a href="custom.html"  >创建</a></li>
            <li><a href="mould.html">模板</a></li> -->
        </ul>
        <a class="quit fr" href="javascript:void(0)"  onclick="createClick()">退出</a>
    </div>
    <div class="content percent100 fixed">
		<div class="con_side fl">
			<div class="oa_options">
				<a  title="新增平台" id="oa_add" href="#" onclick="newPlatformClick()"><img src="images/u144_normal.png"/></a>
				<a   title="修改平台" id="oa_update" href="javascript:void(0)"  onclick="altPlatformClick()"><img src="images/u163_normal.png"/></a>
				<a  title="删除平台" id="oa_delete" href="javascript:void(0)"   onclick="deletePlatformClick()"><img src="images/u165_normal.png" /></a>
			</div>
			<ul class="oa_list"  id="platform_li">
			</ul>
		</div>
		<div class="con_main">
			<div class="main_header">
				<div class="fm_title"  id="showPlatName"></div>
				<div class="fm_options">
                	<a title="新增流程"  class="aToBtn bt_w96 bt_bg" id="new_service" name="new_service" href="javascript:void(0)" onclick="new_service()">新增流程</a>
                    <a title="修改流程"  class="aToBtn bt_w96 bt_bg" id="fm_update" name="#new_Service" href="javascript:void(0)" onclick="modify_service()">修改流程</a>
		            <a title="删除流程"  class="aToBtn bt_w96"  id="new_service" name="new_service" href="javascript:void(0)" onclick="delete_service()" >删除流程</a>
                    <div class="con_main_more">
					 	  <a title="更多"  class="aToBtn bt_w96 bt_bg" id="main_more" name="#new_Service" href="javascript:void(0)" onClick="modify_servi()">更多</a>
					      <ul>
					        <li>
					            <a title="加载接口数据"  class="aToBtn" id="loadComtype"  href="javascript:void(0)">加载接口数据</a>
					        </li>
					        <li>
					            <a title="内存清理"  class="aToBtn" id="clear_service"  href="javascript:void(0)" onclick="clear_service()">清理页面缓存</a>
					        </li>
					      </ul>
					</div>
                </div>
			</div>
			<div class="main_tab">
				<div class="oa_manage">
				<form id="showPlatform">
					<table>
						<tbody>
							<tr>
								<td width="110px">接入平台代码</td>
								<td width="190px"><input type="text" data-role="none" id="show_plat_code" readonly="readonly"/></td>
								<td width="110px">接入平台名称</td>
								<td width="190px"><input type="text" data-role="none" id="show_plat_name" readonly="readonly"/></td>
								<td width="95px" rowspan="3">平台描述</td>
								<td width="225px" rowspan="3"><textarea data-role="none" id="show_plat_desc" readonly="readonly"></textarea></td>
							</tr>
							<tr>
								<td>接入地址</td>
								<td><input type="text" data-role="none" id="show_plat_url" readonly="readonly"/></td>
								<td>接入时间</td>
								<td><input type="text" data-role="none" id="show_plat_time" readonly="readonly"/></td>
							</tr>
							<tr>
								<td>测试地址</td>
								<td><input type="text" data-role="none" id="show_plat_testurl" readonly="readonly"/></td>
								<td>状态</td>
								<td><input type="text" data-role="none"  id="show_plat_status" readonly="readonly"/></td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
				<div class="fm_table">
                	 				<table id="serviceGrid"></table>
									<div id="serviceGridpager"></div>
                </div>
			</div>
		</div>
    </div>
    <!-- 新建平台 -->
    <div class="alert" id="new_Platform">
    	<div class="at_head fixed">
        	<p class="at_title fl">新建平台</p>
            <a class="at_close fr" href="javascript:void(0)">×</a>
  		</div>
        <div class="at_content">
        <form id="newform">
        	<table class="at_form">
            	<tbody>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>平台代码</td>
                        <td class="at_tbInput"><input   type="text"  value=""  id="platform_code"   name="platform_code" class="validate[required,custom[onlyLetterNumber],ajax[ajaxPlatform]] input_text"  maxlength="10" /></td>
                    </tr>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>平台名称</td>
                        <td class="at_tbInput"><input  type="text"  value=""  id="platform_name"  name="platform_name" class="validate[required] input_text"  maxlength="10" /></td>
                    </tr>
					<tr>
                    	<td class="at_tbName">接入地址</td>
                        <td class="at_tbInput"><input  input type="text"  value=""  id="platform_url"   name="platform_url" /></td>
                    </tr>
					<tr>
                    	<td class="at_tbName">测试地址</td>
                        <td class="at_tbInput"><input  type="text"  value=""  id="test_url"   name="test_url" /></td>
                    </tr>
                    <tr>
                    	<td class="at_tbName">平台描述</td>
                        <td class="at_tbInput"><textarea  id="platform_desc"  name="platform_desc"></textarea></td>
                    </tr>
                </tbody>
            </table> 
            </form>
        </div>
        <div class="at_options">
            <button class="at_submit"   onclick="add_Plat()">确定</button>
            <button class="at_cancel">取消</button>
        </div>
    </div>
    
    <!-- 修改平台 -->
    <div class="alert" id="alt_Platform">
    	<div class="at_head fixed">
        	<p class="at_title fl">修改平台</p>
            <a class="at_close fr" href="javascript:void(0)">×</a>
  		</div>
        <div class="at_content">
        <form id="altform">
        	<table class="at_form">
            	<tbody>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>平台代码</td>
                        <td class="at_tbInput"><input   type="text"  value=""  id="modify_platform_code"   name="modify_platform_code" class="validate[required,custom[onlyLetterNumber]] input_text"  readonly="readonly"   maxlength="10" /></td>
                    </tr>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>平台名称</td>
                        <td class="at_tbInput"><input  type="text"  value=""  id="modify_platform_name"  name="modify_platform_name" class="validate[required] input_text"  readonly="readonly"   maxlength="10" /></td>
                    </tr>
					<tr>
                    	<td class="at_tbName">接入地址</td>
                        <td class="at_tbInput"><input  input type="text"  value=""  id="modify_platform_url"   name="modify_platform_url" /></td>
                    </tr>
					<tr>
                    	<td class="at_tbName">测试地址</td>
                        <td class="at_tbInput"><input  type="text"  value=""  id="modify_test_url"   name="modify_test_url" /></td>
                    </tr>
                    <tr>
                    	<td class="at_tbName">平台描述</td>
                        <td class="at_tbInput"><textarea  id="modify_platform_desc"  name="modify_platform_desc"></textarea></td>
                    </tr>
                </tbody>
            </table> 
            </form>
        </div>
        <div class="at_options">
            <button class="at_submit"   onclick="modify_Plat()">确定</button>
            <button class="at_cancel">取消</button>
        </div>
    </div>
    <!-- 新建流程 -->
	<div class="wrap">
	<div class="alert" id="new_service_dialog">
    	<div class="at_head fixed">
        	<p class="at_title fl">新建流程</p>
            <a class="at_close fr" href="javascript:void(0)">×</a>
  		</div>
        <div class="at_content">
        <form id="newserviceform">
        	<table class="at_form">
            	<tbody>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>实际流程代码</td>
                        <td class="at_tbInput"><input  type="text"  value=""  id="service_code"   name="service_code" class="validate[required,ajax[ajaxService]] input_text"  maxlength="30"/></td>
                    </tr>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>流程名称</td>
                        <td class="at_tbInput"><input   type="text"  value=""  id="service_name"  name="service_name" class="validate[required] input_text"  maxlength="10" /></td>
                    </tr>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>自维护流程代码</td>
                        <td class="at_tbInput"><input   type="text"  value=""  id="own_service_name"  name="outinterid" class="validate[required,custom[onlyLetterNumber],ajax[ajaxOutService]] input_text"  maxlength="10" /></td>
                    </tr>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>版本号</td>
                        <td class="at_tbInput"><input   type="text"  value=""  id="service_version"   name="service_version" class="validate[required,custom[onlyLetterNumberPoint]] input_text"/></td>
                    </tr>
                    <tr>
                    	<td class="at_tbName">流程描述</td>
                        <td class="at_tbInput"><textarea  id="service_desc"  name="service_desc"></textarea></td>
                    </tr>
                </tbody>
            </table> 
            </form>
        </div>
        <div class="at_options">
            <button class="at_submit"  onclick="add_Serivce()">确定</button>
            <button class="at_cancel">取消</button>
        </div>
    </div>
    <!-- 切换服务器地址 -->
	<div class="wrap">
	<div class="alert" id="switch_dialog">
    	<div class="at_head fixed">
        	<p class="at_title fl">切换服务器</p>
            <a class="at_close fr" href="javascript:void(0)">×</a>
  		</div>
        <div class="at_content">
        <form id="modifyserviceform">
            	<select id="serverType">
        		<option value="insideRFTest">内网测试地址</option>
        		<option value="insideRFFormal">内网正式地址</option>
        	</select> 
            </form>
        </div>
        <div class="at_options">
            <button class="at_submit"  onclick="switchIP()">确定</button>
            <button class="at_cancel">取消</button>
        </div>
    </div>
	</div>
	<!-- 修改流程 -->
	<div class="wrap">
	<div class="alert" id="modify_service_dialog">
    	<div class="at_head fixed">
        	<p class="at_title fl">修改流程</p>
            <a class="at_close fr" href="javascript:void(0)">×</a>
  		</div>
        <div class="at_content">
        <form id="modifyserviceform">
        	<table class="at_form">
            	<tbody>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>实际流程代码</td>
                        <td class="at_tbInput"><input  type="text"  value=""  id="modify_service_code"   name="modify_service_code" class="validate[required] input_text"   readonly="readonly" maxlength="30"/></td>
                    </tr>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>流程名称</td>
                        <td class="at_tbInput"><input   type="text"  value=""  id="modify_service_name"  name="modify_service_name" class="validate[required] input_text"    maxlength="10" /></td>
                    </tr>
                    <tr>
                    	<td class="at_tbName"><span class="starPos"></span>自维护流程代码</td>
                        <td class="at_tbInput"><input   type="text"  value=""  id="modify_own_interid"  name="modify_outinterid" class="validate[required,custom[onlyLetterNumber],ajax[ajaxOutService]] input_text"  readonly="readonly"  maxlength="10" /></td>
                    </tr>
					<tr>
                    	<td class="at_tbName"><span class="starPos"></span>版本号</td>
                        <td class="at_tbInput"><input   type="text"  value=""  id="modify_service_version"   name="modify_service_version" class="validate[required,custom[onlyLetterNumberPoint]] input_text"/></td>
                    </tr>
                    <tr>
                    	<td class="at_tbName">流程描述</td>
                        <td class="at_tbInput"><textarea  id="modify_service_desc"  name="modify_service_desc"></textarea></td>
                    </tr>
                </tbody>
            </table> 
            </form>
        </div>
        <div class="at_options">
            <button class="at_submit"  onclick="modify_Serivce()">确定</button>
            <button class="at_cancel">取消</button>
        </div>
    </div>
	</div>

	
	<script type="text/javascript">
	var path="<%=basePath%>";
	var  w = $(".main_tab").width();
	$(function(){
		//异步验证平台code是否已经存在
		$.validationEngineLanguage.allRules["ajaxPlatform"] = {
							 "url": "ajaxValidate"
		};
		//异步验证流程code是否已经存在
		$.validationEngineLanguage.allRules["ajaxService"] = {
				 "url": "ajaxServiceValidate"
};
		$.validationEngineLanguage.allRules["ajaxOutService"] = {
				 "url": "ajaxOutService"
};
		
		jQuery("#newform").validationEngine({
			
			ajaxField:["platform_code"],
			ajaxFormValidation: true,
		});
		jQuery("#altform").validationEngine({
		});
		jQuery("#newserviceform").validationEngine({
			ajaxField:["service_code","outInterId"],
			ajaxFormValidation: true,
		});
		jQuery("#modifyserviceform").validationEngine({
		});
		
		
		//流程列表
		jQuery("#serviceGrid").jqGrid({
			
            datatype: "json",
            height : 300,
			width : w-20,
            rownumbers:true,
            colNames:["ID","流程代码","流程名称","流程状态","当前版本","待审核版本","操作"],
            colModel:[
                {name:'ID',index:'ID',"hidden":true},
                {name:'SERVICECODE',index:'SERVICECODE',width:77},
                {name:'SERVICENAME',index:'SERVICENAME',width:77},
                {name:'SERVICESTATUS',index:'SERVICESTATUS',width:77},
                {name:'NOWVERSION',index:'NOWVERSION',width:77},
                {name:'WAITVERSION',index:'WAITVERSION',width:77},
                {name:'OPERATION',index:'OPERATION'}
            ],
            rowNum:10,
            rowList:[10,20,30],
            pager: '#serviceGridpager',
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
            	var ids = jQuery("#serviceGrid").jqGrid('getDataIDs');
                //添加预览的链接
                for(var i=0;i < ids.length;i++){
                    var rowDatas = $("#serviceGrid").jqGrid('getRowData', ids[i]);
                    //var itemcode = rowDatas.ITEMCODE;
                    //var itemid = rowDatas.ITEMID;
                    var  nowversion = "<a class=\"bt_p5\" href='javascript:void(0)'   onclick='nowVersionPreview("+ids[i]+")'>编辑</a>";
                    jQuery("#serviceGrid").jqGrid('setRowData',ids[i],{NOWVERSION:nowversion});
                    var  waitingversion = "<a class=\"bt_p5\" href='javascript:void(0)'   onclick='waitingVersionPreview("+ids[i]+")'>编辑</a>";
                    jQuery("#serviceGrid").jqGrid('setRowData',ids[i],{WAITVERSION:waitingversion});
                    var  operation = "<a class=\"bt_p5\" href='javascript:void(0)'   onclick='operation("+ids[i]+")'>发布</a>";
                    jQuery("#serviceGrid").jqGrid('setRowData',ids[i],{OPERATION:operation});
                    /*     var  waitversion = "<a class=\"bt_p5\" href='javascript:void(0)'>预览</a>";
                    var  operationhtml = "<a class=\"bt_p5\" href='javascript:void(0)'>发布</a>&nbsp;"
                    +"&nbsp;<a class=\"bt_p5\" href='javascript:void(0)'>审核</a>&nbsp;"
                    +"&nbsp;<a class=\"bt_p5\" href='javascript:void(0)'>禁用</a>";
                    jQuery("#serviceGrid").jqGrid('setRowData',ids[i],{OPERATION:operationhtml});
                   
                    jQuery("#serviceGrid").jqGrid('setRowData',ids[i],{WAITVERSION:waitversion}); */
                }
            },
        }).navGrid("#serviceGridpager",{edit:false,add:false,del:false});
		
		
		//加载平台信息
		loadPlatInfo();
	});	 
	//点击当前版本预览 
	function  nowVersionPreview(serviceid){
		var  url = path+"toRawResourcePage?serviceId="+serviceid;
		window.open (url) ;
	}
	//点击当前版本预览    конкретный
	function  waitingVersionPreview(serviceid)
	{
		var  url = path+"toRawResourceWaitingPage?serviceId="+serviceid;
		window.open (url) ;
	}
	//发布    
	function  operation(serviceid){
		$.ajax({
			url:path+"validateDoPackage?serviceId="+serviceid,
			method: "post",
			type:"json",
            success: function(data) {
            	var msg = jQuery.parseJSON(data);
            	if (msg.length<=0){
	            	parent.layer.alert("当前流程待审批版本无数据，不可发布！",8);
            	}else{
            		parent.layer.confirm('发布后，待审批页面将覆盖当前版本中【所有页面】，确定发布？', function(index) {
            			var i=parent.layer.load(0);
            			$.ajax({
            				url:path+"doPackage?serviceId="+serviceid,
            				method: "post",
            	            success: function(msg) {
            	            	parent.layer.close(i);
            	            	parent.layer.alert("发布成功！",9);
            	            },error:function(XMLHttpRequest, textStatus, errorThrown) {
            	           	  parent.layer.alert("发布失败！请尝试重新打包！",8);
            	             }
            			});
            		});
            	}
            },error:function(XMLHttpRequest, textStatus, errorThrown) {
           	  parent.layer.alert("查询失败！",8);
             }
		});
	}
	function clear_service(){
		var url =  path+"toRemoveSessionList";
 	   $.ajax({
          url: url,
          method: "post",
          success: function(msg) {
        	  layer.alert("清理完成！",9);
          },
             timeout:60000,
                 error:function(XMLHttpRequest, textStatus, errorThrown) {
                 }
      });
	}
	$('#loadComtype').click(function(){
		var url =  path+"loadComtype";
	 	   $.ajax({
	          url: url,
	          method: "post",
	          success: function(msg) {
	        	  if (msg=="true"){
		        	  layer.alert("加载完成！",9);
	        	  }else{
	        		  layer.alert("加载失败！",8);
	        	  }
	          },
	             timeout:60000,
	                 error:function(XMLHttpRequest, textStatus, errorThrown) {
	                 }
	      });
	});
	</script>
</body>
</html>
<script type="text/javascript">
    	$(function(){
			$('.con_main_more').mouseover(function(){
					$('.con_main_more ul').css('display','block');
			});
			$('.con_main_more').mouseout(function(){
					$('.con_main_more ul').css('display','none');
			})	;
		});
</script>