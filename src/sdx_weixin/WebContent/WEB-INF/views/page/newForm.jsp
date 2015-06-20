<!DOCTYPE HTML>
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache" />
 <META HTTP-EQUIV="Expires" CONTENT="0">
<title>新建表单_表单名称</title>
<link rel="stylesheet" href="css/jquery.mobile-1.4.2.min.css">
<link href="css/custom.css" rel="stylesheet" type="text/css" />
<link href="resource/css/skin.css" rel="stylesheet" type="text/css" />
<link href="resource/css/global.css" rel="stylesheet" type="text/css" />
<link href="resource/css/template.css" rel="stylesheet" type="text/css" />
<link href="js/layer/skin/layer.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/zTreeStyle/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="css/styles/shCoreDefault.css"/>
<link type="text/css" rel="stylesheet" href="js/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="js/jquery-ui.theme.css"/>
<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="js/codemirror/lib/codemirror.css">
 <link rel="stylesheet" href="js/codemirror/theme/rubyblue.css">
 <link rel="stylesheet" href="js/codemirror/addon/lint/lint.css">
<!--  <link rel="stylesheet" href="js/codemirror/addon/hint/css-hint.css">
 <script type="text/javascript" src="js/codemirror/addon/selection/active-line.js"></script> -->

<style>
#responseMsg{padding:10px}
#responseMsg h2{line-height: 45px;background:#f2f2f2; border:1px solid #ccc; border-bottom:none;border-radius: 5px 5px 0 0;padding-left:15px;}
#responseMsg ul{height:100%;overflow:hidden;border:1px solid #c1c1c1;border-radius: 0 0 5px 5px;background:#f2f2f2}
#responseMsg li{line-height: 45px; padding-left:15px;border-bottom: 1px solid #ccc;}
</style>
<style type="text/css">
#div1 {width:488px;height:70px;padding:10px;border:1px solid #aaaaaa;}
.syntaxhighlighter{height:528px;overflow:aoto;}
.xubox_page{width: 100%;}
#decorationDiv .ui-field-contain .ui-slider-switch span{font-size: 14px;}
.ui-btn-icon-left:after, .ui-btn-icon-right:after, .ui-btn-icon-top:after, .ui-btn-icon-bottom:after, .ui-btn-icon-notext:after{background-color: rgba(0, 0, 0, 0.13);}
.ui-page-theme-a .ui-btn, html .ui-bar-a .ui-btn, html .ui-body-a .ui-btn, html body .ui-group-theme-a .ui-btn, html head+body .ui-btn.ui-btn-a, .ui-page-theme-a .ui-btn:visited, html .ui-bar-a .ui-btn:visited, html .ui-body-a .ui-btn:visited, html body .ui-group-theme-a .ui-btn:visited, html head+body .ui-btn.ui-btn-a:visited{ font-family:'微软雅黑'; font-size:13px; color:#777; font-weight:normal;line-height: 22px;}
.ui-icon-minus:after{ background:url(css/images/ui-icons_888888_256x240.png) -60px -125px no-repeat;}
.ui-icon-plus:after{ background:url(css/images/ui-icons_888888_256x240.png) -29px -125px no-repeat;}
.icon_paren{ background:#DDDDDD;}
.icon_btn{ display:inline-block!important;-webkit-border-radius: .3125em!important;
border-radius: .3125em!important;}
.icont_plus:after {background:url(css/images/icons-18-black.png) 1px 2px no-repeat;}
.icont_delete:after {background:url(css/images/icons-18-black.png) -70px 2px no-repeat;}
.icont_refresh:after {background:url(css/images/icons-18-black.png) -321px 2px no-repeat;}
.icont_revamp:after {background:url(css/images/icons-18-black.png) -820px 2px no-repeat;}
.icont_home:after {background:url(css/images/icons-18-black.png) -570px 2px no-repeat;}
</style>
</head>

<body>
	<input type="hidden" id="pageType" value="" />
	<input type="hidden" id="templateId" value="" />
	<input type="hidden" id="cssTemplateName" value="" />
	<input type="hidden" id="pId" value="" />
	<input type="hidden" id="rawId" value="${rawTempId}" />
	<div class="header fixed">
		<h1 data-role="none" class="sysTitle fl">页面定制</h1>
        <div class="formOptions fr">
            <a class="aToBtn bt_w96" id="fo_clear" href="javascript:void(0)">清除画布</a> 
				<a class="aToBtn bt_w96" id="fo_redo" href="javascript:void(0)">选模板</a> <!-- <a
					class="aToBtn bt_w96" id="fo_save" href="javascript:void(0)">保存</a> -->
				<a class="aToBtn bt_w96" id="fo_presure" href="javascript:void(0)">预览</a>
				<!-- <a class="aToBtn bt_w96" id="fo_save" href="javascript:void(0)">保存表单</a> -->
				<a class="aToBtn bt_w96 bt_bg" id="fo_creat"
					href="javascript:void(0)">生成表单</a>
        </div>
	</div>
	<div id="content" class="content percent100">
		<!-- <div class="titleGroup fixed">
			<h2 class="formTitle fl" data-role="none"></h2>
			<div class="formOptions fr">
				<a class="aToBtn bt_w96" id="fo_clear" href="javascript:void(0)">清除画布</a> 
				<a class="aToBtn bt_w96" id="fo_uploadJs" href="javascript:void(0)">上传JS</a> 
				<a class="aToBtn bt_w96" id="fo_redo" href="javascript:void(0)">选模板</a> <a
					class="aToBtn bt_w96" id="fo_save" href="javascript:void(0)">保存</a>
				<a class="aToBtn bt_w96" id="fo_presure" href="javascript:void(0)">预览</a>
				<a class="aToBtn bt_w96 bt_bg" id="fo_creat"
					href="javascript:void(0)">生成表单</a>
			</div>
		</div> -->
		<div class="ef_component fl" style="height:577px; overflow:auto">
		    <div data-role="collapsible-set" class="pageComponet" style="margin-top:0px;">
			<!-- <div class="cp_extend" style="height: auto;border-bottom:none;"> -->
            	 <!-- <div data-role="collapsible" data-collapsed="false" class="cp_extend" style="height: auto;margin-top:0px; border-bottom:none; border-radius:0px;">
					<h3 class="cpTitle">Page管理</h3>               
					<div class="icon_paren">
                        <a href="#" data-role="button" title="新增PAGE" id="addPage" data-iconpos="notext" data-icon="plus" class="icon_btn icont_plus" ></a>
                        <a href="#" data-role="button" title="修改PAGE" id="upatePage" data-iconpos="notext" data-icon="refresh" class="icon_btn icont_revamp" ></a>
                        <a href="#" data-role="button" title="删除PAGE" id="deletePage" data-iconpos="notext" data-icon="delete" class="icon_btn icont_delete" ></a>
                        <a href="#" data-role="button" title="设置首页"  id="homePage" data-iconpos="notext" data-icon="home" class="icon_btn icont_home" ></a>
                        <a href="#" data-role="button" title="刷新列表" id="refreshPage" onclick="refreshPages()" data-iconpos="notext" data-icon="refresh" class="icon_btn icont_refresh" ></a>
                    </div>
                    <input type="hidden" id="selPageId" value="" />
					<ul class="cpList fixed vertical pagefile_title" id="pageList_Id" style="border-bottom:none;padding:0px;">
                         <li class="page_list">
                            <i></i><input type='hidden' value='1' />第一个Page
                        </li> 
                        <li class="page_list">
                            <i></i><input type='hidden' value='2' />第二个Page
                        </li>
                        <li class="page_list">
                            <i></i><input type='hidden' value='3' />第三个Page
                        </li> 
					</ul>
                </div> -->
			<!-- </div> -->
			
			<!-- <div class="cp_basic"> -->
				<div data-role="collapsible" data-collapsed="false" class="cp_basic"  style=" border-radius:0px;">
				<h3 class="cpTitle">基本组件</h3>
				<ul class="cpList fixed vertical">
					<!-- <li id='navbar_type' class="component fl">
						<div class="cpIcon no20"></div>
						<p>导航栏</p>
					</li> -->
				<!-- 	<li class="component fl">
						<div class="cpIcon no21"></div>
						<p>标签栏</p>
					</li> -->
					<li id="headernav_type" title="页眉导航"   class="component fl material-design">
						<div class="cpIcon no1"></div>
						<p>页眉导航</p>
					</li>
					<li id="header_type" title="页眉"  class="component fl material-design">
						<div class="cpIcon no1"></div>
						<p>页眉</p>
					</li>
					<li id="footer_type" title="页脚"  class="component fl material-design">
						<div class="cpIcon no2"></div>
						<p>页脚</p>
					</li>
					<li id="label_type" title="文本"  class="component fl">
						<div class="cpIcon no3"></div>
						<p>文本</p>
					</li>
					<li id="show_label_type"  title="显示数据的文本" class="component fl">
						<div class="cpIcon no4"></div>
						<p>显示文本</p>
					</li>
					<li id="onelinetwocolumn_type"  title="一行两列文本" class="component fl">
						<div class="cpIcon no5"></div>
						<p>一行两列</p>
					</li>
					<li id="input_type" title="文本输入框"  class="component fl">
						<div class="cpIcon no6"></div>
						<p>输入框</p>
					</li>
					<li id="hidden_type"  title="文本隐藏域" class="component fl">
						<div class="cpIcon no7"></div>
						<p>隐藏域</p>
					</li>
					<li id="textarea_type"  title="多行文本" class="component fl">
						<div class="cpIcon no8"></div>
						<p>多行文本</p>
					</li>
					<li id="divider_type"  title="分割线" class="component fl">
						<div class="cpIcon no9"></div>
						<p>分割线</p>
					</li>
					<li id="radio_type"  title="单选框" class="component fl">
						<div class="cpIcon no10"></div>
						<p>单选框</p>
					</li>
					<li id="select_type"  title="下拉选择" class="component fl">
						<div class="cpIcon no11"></div>
						<p>下拉选择</p>
					</li>
					<!-- <li id="check_type" class="component fl">
						<div class="cpIcon no4"></div>
						<p>多选框</p>
					</li> -->
					<li id="date_type"  title="日期" class="component fl">
						<div class="cpIcon no12"></div>
						<p>日期</p>
					</li>
					<!-- <li class="component fl">
						<div class="cpIcon no8"></div>
						<p>图片</p>
					</li> -->
					<li id="button_type"  title="一行显示一个按钮" class="component fl">
						<div class="cpIcon no13"></div>
						<p>单行按钮</p>
					</li>
					<li id="twobutton_type"  title="一行显示两个按钮" class="component fl">
						<div class="cpIcon no14"></div>
						<p>两个按钮</p>
					</li>
					<li id="submitbtn_type"  title="单行提交按钮" class="component fl">
						<div class="cpIcon no15"></div>
						<p>提交按钮</p>
					</li>
					<li id="link_type"  title="链接" class="component fl">
						<div class="cpIcon no16"></div>
						<p>链接</p>
					</li>
					<!-- <li class="component fl">
						<div class="cpIcon no11"></div>
						<p>附件</p>
					</li>
					<li class="component fl">
						<div class="cpIcon no12"></div>
						<p>分割线</p>
					</li> -->
					<li id="listview_type"  title="列表" class="component fl">
						<div class="cpIcon no17"></div>
						<p>列表</p>
					</li>
					<!-- <li id="list_type" title="指定列数的网格" class="component fl">
						<div class="cpIcon no18"></div>
						<p>列表网格</p>
					</li> -->
					<li id="listrow_type"  title="指定行数和列数的信息网格" class="component fl">
						<div class="cpIcon no19"></div>
						<p>信息网格</p>
					</li>
					<li id="linktitle_type"  title="标题链接" class="component fl">
						<div class="cpIcon no19"></div>
						<p>标题链接</p>
					</li>
					<li id="footerrightbutton_type"  title="页脚右按钮" class="component fl">
						<div class="cpIcon no19"></div>
						<p>页脚右按钮</p>
					</li>
					<li id="headerleftbutton_type"  title="页眉左按钮" class="component fl">
						<div class="cpIcon no19"></div>
						<p>页眉左按钮</p>
					</li>
					<li id="onelinecolumns_type"  title="一列多行" class="component fl">
						<div class="cpIcon no19"></div>
						<p>一列多行</p>
					</li>
					<!-- <div class="component more fl">
						
					</div> -->
				</ul>
				</div>
			<!-- </div> -->
			<!--新增内容、07/14-->
			<!-- <div class="cp_extend" style="height: auto;border-bottom:none;"> -->
				<div data-role="collapsible" class="cp_extend" style="height: auto;border-bottom:none; border-radius:0px;">
					<h3 class="cpTitle">插入网格组件</h3>
					<div class="cpList fixed">
						<ul id="tablelabel_type" title="拖入网格组件中的文本" class="component fl"  >
							<li class="cpIcon no3"></li>
							<p>列表文本</p>
						</ul>
					</div>
				</div>
			<!-- </div> -->
			<!--新增结束-->
			<!-- <div class="cp_extend" style="height: auto;border-bottom:none;"> -->
            	<div data-role="collapsible" class="cp_extend" style="height: auto;border-bottom:none; border-radius:0px;">
				<h3 class="cpTitle">扩展组件</h3>
				</div>
			<!-- </div> -->
			</div>
		</div>
		
		
		
		<div>
		</div>
		<div class="editForm fixed">
			<div class="ef_design fl">
				<div class="designTitle fixed">
					<ul class="mode fl">
						<li class="fl"><a name=".gridBg" id="design" href="javascript:void(0)"
							data-role="none">DESIGN</a></li>
						<li class="fl"><a name=".codeMode" id=".codeMode" href="javascript:void(0)"
							onclick="createCode();" data-role="none">VIEWCODE</a></li>
						<!-- <li class="fl"><a name=".htmlCodeMode" id=".htmlCodeMode" href="javascript:void(0)"
							onclick="htmlCode();" data-role="none">HTML</a></li>	 -->
						<li class="fl"><a name=".jsCodeMode" href="javascript:void(0)"
							onclick="jsCode();" data-role="none">JSCODE</a></li>	
						<li class="to_pro fr"><a name=".protocol"
							href="javascript:void(0)" data-role="none">协议</a></li>
					</ul>
					<ul class="showMode fr">
						<li class="fl"><a id="phone" href="javascript:void(0)"></a></li>
						<li class="fl"><a id="pad" href="javascript:void(0)"></a></li>
						<li class="fl"><a id="rotation" href="javascript:void(0)"></a></li>
					</ul>
				</div>
				<!-- <div style="display:none" id="tmp">
				
				</div> -->
				<div style="display:none" id="tmp1">
				
				</div>
				<div class="modeTab">
					<div class="gridBg" ondrop="drop(event)"
						ondragover="allowDrop(event)">
						<div class="phoneGray fixed" id="" style="overflow:hidden;">
							<div class="phoneRec"></div>
							<iframe id="pageFrame" name="pageFrame" class="phoneScreen" src="canvas" style="margin:10px auto;display:block;border:none;"></iframe>
							<div class="phoneHome"></div>
						</div>
					</div>
					<div id="saveMsgs" style="color: red;position:absolute;top:60px;left:578px;z-index:20;"></div> 
					<div id="code" class="codeMode">
						<!-- <pre><code class="html"><textarea id="codeMode" class="proText"></textarea></code></pre> -->
					</div>
					<div id="htmlCode" class="htmlCodeMode">
						<textarea id="htmlMsgs" class="proText" placeholder="Code goes here..." style="height:250px"></textarea>
					</div>
					<div id="jsCode" class="jsCodeMode">
						<textarea id="jsMsgs" class="proText" placeholder="Code goes here..." style="height:250px">
						</textarea>
					</div>
					<div class="protocol" style="height:100%;overflow: hidden">
                        <section>
                            <h3>请求消息格式：</h3>
                            <textarea id="reqMsgs" class="proText" style="height:220px">
                            </textarea>
                        </section>
                        <section>
                            <h3>响应消息格式：</h3>
                            <textarea id="ansMsgs" class="proText" style="height:220px;">
                            </textarea>
                        </section>
						<button id="proBtn" class="proOK fr" data-role="none" onclick="creatPro();">设置字段</button>
					</div>
					<div id="responseMsg">
						<h2>消息接口连接结果</h2>
						<ul>
						
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="ef_properties fr">
			<div class="text_pr">
				<h3 class="prTitle fixed">
					<p class="titleText fl">文本属性</p>
				</h3>
				<div id="contentAttrs" style="overflow-x:hidden;overflow-y:auto" class="prContent proContent"></div>
			</div>
			<div class="text_style">
				<h3 class="prTitle fixed">
					<p class="titleText fl">文本样式</p>
					<span><input type="button" style="padding:5px 15px;border:1px solid #ddd;background:gary;margin-left: 6px; border-radius:4px;margin-bottom: 5px;" data-role="none" value="清除皮肤" onclick="changeStyle('data-theme-skin');"></span>
				</h3>
				<div class="prContent" style="padding-left: 56px;">
					<table class="palette" cellpadding="0" cellspacing="2px" border="0">
						<tbody>
						    <!-- <tr>
								<td></td>
								<td><input type="button" value="清除皮肤" onclick="changeStyle('data-theme-skin');"></td>
								<td></td>
							</tr> -->
							<tr>
								<td onclick="changeStyle('data-theme-skin_one');" bgcolor="#fa6f57"></td>
								<td onclick="changeStyle('data-theme-skin_two');" bgcolor="#4fc1e9"></td>
								<td onclick="changeStyle('data-theme-skin_three');" bgcolor="#29c8aa"></td>
							</tr>
							<tr>
								<td onclick="changeStyle('data-theme-skin_four');" bgcolor="#8E44AD"></td>
								<td onclick="changeStyle('data-theme-skin_five');" bgcolor="#ff9966"></td>
								<td onclick="changeStyle('data-theme-skin_seven');" bgcolor="#34495e"></td>
							</tr>
							<tr>
								<td onclick="changeStyle('data-theme-skin_eight');" bgcolor="#bdc3c7"></td>
								<td onclick="changeStyle('data-theme-skin_nine');" bgcolor="#006699"></td>
								<td onclick="changeStyle('data-theme-skin_ten');" bgcolor="#5588c9"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="blankHolder"></div>
			</div>
		</div>
		<div id='add' style="display:none">
			<form action="" id="xform">
			<input type='hidden' value='${serviceId}' id='serviceId'  />
			<input type='hidden' value='' id='imgId'  />
        	<table width="100%"   border="0" id='tabAttr' style=" margin-top: 10px;table-layout:fixed">
                  <tr>
                    <td width="26%"><label style="text-align: right" for=""><span class="starpos"></span>文件名称:</label></td>
                    <td colspan="2"><input type="text" style="height:30px;" data-role="none" id='fileName' maxlength="16" name='fileName' class="validate[required,custom[onlyTableName],ajax[ajaxTable]] input_text"  value=""/></td>
                  </tr>
            </table>
            </form>
            <div class="popup_btn" style="text-align: right; border-top:1px solid #ccc;">
  				<a href="#" id='savePage'   style="background:#0099cc; color:#fff;margin-top: 7px;padding: 4px 10px; font-size:14px;display: inline-block;font-family: '微软雅黑';">保存</a>
        		<a href="#" class='cancelPageBtn' style="background:#0099cc; color:#fff;margin-top: 7px;padding: 4px 10px; font-size:14px;display: inline-block;font-family: '微软雅黑';margin-right:10px;">取消</a>
  			</div>      
		</div>
	</div>
	<div id='uploadJs' style="display:none">
			<form action="" id="xform">
			<input type='hidden' value='' id='jsId'  />
        	<table width="100%"   border="0" id='tabAttr' style=" margin-top: 10px;table-layout:fixed">
                  <tr>
                    <td width="26%"><label style="text-align: right" for=""><span class="starpos"></span>文件名称:</label></td>
                    <td colspan="2">'<input data-role="none" type="file" name="file" id="file" />
                  </tr>
            </table>
            </form>
            <div class="popup_btn" style="text-align: right; border-top:1px solid #ccc;">
  				<a href="#" id='uploadJsBtn'   style="background:#0099cc; color:#fff;margin-top: 7px;padding: 4px 10px; font-size:14px;display: inline-block;font-family: '微软雅黑';">上传</a>
        		<a href="#" id='cancelUploadBtn' style="background:#0099cc; color:#fff;margin-top: 7px;padding: 4px 10px; font-size:14px;display: inline-block;font-family: '微软雅黑';margin-right:10px;">取消</a>
  			</div>      
	</div>	
	<div id='addPageDiv' style="display:none">
			<form action="" id="addPageForm">
        	<table width="100%"   border="0" id='tabAttr' style=" margin-top: 10px;table-layout:fixed">
                  <tr>
                    <td width="26%"><label style="text-align: right" for=""><span class="starpos"></span>ID:</label></td>
                    <td colspan="2"><input data-role="none" type="text" name="newPageId" id="newPageId" style="height:25px;width: 180px;"/>
                  </tr>
                  <tr>
                    <td width="26%"><label style="text-align: right" for=""><span class="starpos"></span>名称:</label></td>
                    <td colspan="2"><input data-role="none" type="text" name="newPageName" id="newPageName"style="height:25px;width: 180px;"/>
                  </tr>
            </table>
            </form>
            <div class="popup_btn" style="text-align: right; border-top:1px solid #ccc;">
  				<a href="#" id='addPageBtn'   style="background:#0099cc; color:#fff;margin-top: 7px;padding: 4px 10px; font-size:14px;display: inline-block;font-family: '微软雅黑';">确定</a>
        		<a href="#" class='cancelPageBtn' style="background:#0099cc; color:#fff;margin-top: 7px;padding: 4px 10px; font-size:14px;display: inline-block;font-family: '微软雅黑';margin-right:10px;">取消</a>
  			</div>      
	</div>	
	<div id='updatePageDiv' style="display:none">
			<form action="" id="updatePageForm">
        	<table width="100%"   border="0" id='tabAttr' style=" margin-top: 10px;table-layout:fixed">
                  <tr>
                    <td width="26%"><label style="text-align: right" for=""><span class="starpos"></span>ID:</label></td>
                    <td colspan="2"><input data-role="none" type="text" name="newPageId" id="upNewPageId" style="height:25px;width: 180px;"/>
                  </tr>
                  <tr>
                    <td width="26%"><label style="text-align: right" for=""><span class="starpos"></span>名称:</label></td>
                    <td colspan="2"><input data-role="none" type="text" name="newPageName" id="upNewPageName"style="height:25px;width: 180px;"/>
                  </tr>
            </table>
            </form>
            <div class="popup_btn" style="text-align: right; border-top:1px solid #ccc;">
  				<a href="#" id='updatePageBtn'   style="background:#0099cc; color:#fff;margin-top: 7px;padding: 4px 10px; font-size:14px;display: inline-block;font-family: '微软雅黑';">确定</a>
        		<a href="#" class='cancelPageBtn' style="background:#0099cc; color:#fff;margin-top: 7px;padding: 4px 10px; font-size:14px;display: inline-block;font-family: '微软雅黑';margin-right:10px;">取消</a>
  			</div>      
	</div>	
	<div id="loadModule" style="display:none;overflow-y:auto;">
		<div style="clear: both">
			<ul id="tree" class="ztree"></ul>
		</div>
	</div>
	<div id="htmlTmp" style="display:none;">
	</div>
	<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery-sortable-min.js"></script>
<script src="js/jquery.mobile-1.4.2.min.js"></script>
<script src="js/meditor/meditor.js"></script>
<script type="text/javascript" src="js/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="js/zTree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script src="js/layer/layer.min.js"></script>
<!-- <script src="js/meditor/built.min.js"></script> -->
<!-- <script src="js/ajaxfileupload.js"></script> -->
<script src="js/shBrush.min.js"></script>

<!-- <script type="text/javascript" src="js/scripts/shCore.js"></script>
<script type="text/javascript" src="js/scripts/shBrushXml.js"></script>
<script type="text/javascript" src="js/scripts/shBrushJScript.js"></script> -->
<script type="text/javascript">
SyntaxHighlighter.config.stripBrs = true;
SyntaxHighlighter.defaults['toolbar'] = false;
SyntaxHighlighter.defaults['quick-code'] = true;
</script>
 

 <script src="js/codemirror/lib/codemirror.js"></script>
<script src="js/codemirror/mode/xml/xml.js"></script>
<script src="js/codemirror/mode/javascript/javascript.js"></script>
<script src="js/codemirror/mode/css/css.js"></script>
<script src="js/codemirror/mode/vbscript/vbscript.js"></script>
<script src="js/codemirror/mode/htmlmixed/htmlmixed.js"></script>
<script src="js/codemirror/addon/hint/javascript-hint.js"></script>
<script src="js/codemirror/addon/display/fullscreen.js"></script>
<script src="js/codemirror/addon/selection/active-line.js"></script>
<script src="js/codemirror/addon/edit/matchtags.js"></script>
<script src="js/codemirror/addon/fold/xml-fold.js"></script>
<script src="js/codemirror/addon/display/placeholder.js"></script>
<script src="js/codemirror/addon/lint/lint.js"></script>
<script src="js/codemirror/addon/lint/javascript-lint.js"></script>
<script src="js/codemirror/addon/lint/jshint.js"></script>
	<script src="js/editorcommon/commoneditor.js">
	</script>
	 <script>
      var htmlEditor = CodeMirror.fromTextArea(document.getElementById("htmlMsgs"), {mode: "htmlmixed",lineNumbers:true,extraKeys: {
          "F11": function(cm) {
              cm.setOption("fullScreen", !cm.getOption("fullScreen"));
            },
            "Esc": function(cm) {
              if (cm.getOption("fullScreen")) cm.setOption("fullScreen", false);
            },"Ctrl-J": "toMatchingTag"/* ,"Ctrl-S":function(cm){
            	alert(1);
            	cm.execCommand("save");
            }  */
          },styleActiveLine: true,matchTags: {bothTags: true},
      });
      htmlEditor.on("blur",function(){
			var selPageId = $("#selPageId").val();	
			var rawId = $("#rawId").val();
    		$.post('updateHtmlEditor', {
    			"selPageId":selPageId,
    			"rawId":rawId,
    			"html":htmlEditor.getValue()
    		}, function(data) {
    			
    		});
      });
      var jsEditor = CodeMirror.fromTextArea(document.getElementById("jsMsgs"), {mode:"javascript",lineNumbers:true
    	  ,extraKeys: {
              "F11": function(cm) {
                  cm.setOption("fullScreen", !cm.getOption("fullScreen"));
                },
                "Esc": function(cm) {
                  if (cm.getOption("fullScreen")) cm.setOption("fullScreen", false);
                },"Ctrl-J": "toMatchingTag"
              },styleActiveLine: true,matchTags: {bothTags: true},gutters: ["CodeMirror-lint-markers"],
              lint: true
      });
      //alert(editor1.getValue());

    </script>
</body>
</html>
