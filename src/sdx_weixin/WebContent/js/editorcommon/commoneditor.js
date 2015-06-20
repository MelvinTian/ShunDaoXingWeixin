sessionStorage.clear();
var index;
var jsonPages = null;
// 编辑模式切换
var show = 0;
var $defaultMode = $('.mode li').eq(show).addClass('hover');
$($defaultMode.find('a').attr('name')).show().siblings().hide();
     

$.post('getResInfo?serviceId=' + $('#serviceId').val(), function(data) {
	jsonPages = data;
}, 'json');

$('.mode li').click(function() {
	$(this).addClass('hover').siblings().removeClass('hover');
	var disMode = $(this).find('a').attr('name');
	$(disMode).show().siblings().hide();
	if (disMode=='.codeMode'){
		$('#backTop').css("display","block");
	}
	return false;
}).find('a').focus(function() {
	this.blur();
});

function gobacktop(){
	var container = $('#topp').next();
	$('html,body').animate({scrollTop: '0px'}, 800);
	$(container).scrollTop(0);
}

function htmlCode(){
	var selPageId = $("#selPageId").val();	
	var rawId = $("#rawId").val();
	$.post('loadHtmlEditor', {
		"selPageId":selPageId,
		"rawId":rawId
	}, function(data) {
		gHtml = data;
		htmlEditor.focus();
		htmlEditor.setValue(gHtml);
	});
	/*setTimeout(function(){
	},500);*/
}
function jsCode(){
	jsEditor.focus();
}
function changePage() {
	$('#auth_actionId').change(function() {
		var authType = $('#auth_actionId option:selected').val();
		switch (authType) {
		case "auth_openpage":
			$('#auth_da_textId').parent('li').css('display', 'block');
			$('#pages_listId').parent('li').css('display', 'none');
			break;
		case "auth_href":
			$('#pages_listId').parent('li').css('display', 'block');
			$('#auth_da_textId').parent('li').css('display', 'none');
			break;
		case "auth_script":
			$('#auth_da_textId').parent('li').css('display', 'block');
			$('#pages_listId').parent('li').css('display', 'none');
			break;
		}
	})
}

function createCode() {
	var pType = $('#pageType').val();
	var page = '';
	if (pType=='blank'){
		page = genHtml('\n\t');
	}else if (pType=='tab'){
		page = genTabHtml('\n\t');
	}
	var gHtml = generateCode(page);
	var h= '<pre class="brush: xml;">'+gHtml+'</pre>';
	$("#code").append(h);
	$('#code').append('<a id="backTop" title="回到顶部" onclick="gobacktop();" class="backtop"><img src="images/top.png"  /></a>');
	SyntaxHighlighter.highlight();
	$("<div style='display:none' id='topp' name='topp'>x</div>").insertBefore('.syntaxhighlighter');
}
/*$('#design').click(function() {
	document.getElementById('pageFrame').contentWindow.loadHtml();
});*/
//生成组件中的属性对
function generateAttrs(thisObj){
	var attr='';
	var attrs = $(thisObj).children().get(0).attributes;
	if (attrs!=undefined){
		for (var i=0;i<attrs.length;i++){
			att=attrs[i];
			if(att.specified){
				attr += att.name + "=\"" + att.value + "\" ";
			}//specified
		}//for
	}
	return attr;
}
//单页：装配Html页面元素
function genHtml(newline) {
	var gHtml = '';
	var pageHeader = '';
	var pageFooter = '';
	var pageContentClass='';
	var obj = {};
	var $frame = $('#pageFrame').contents();
	pageHeader = outerHtml($frame.find('div[data-role=header]'));
	var styles='';
	var scripts='';
	var links='';
	$frame.find('form').find('style').each(function(){
		styles+='<style>'+$(this).html()+'</style>';
	});
	$frame.find('form').find('script').each(function(){
		if ($(this).attr('id')){
			scripts+='<script id=\"'+$(this).attr('id')+'\" src=\"'+$(this).attr('src')+'\" ></script>';
		}else{
			scripts+='<script>'+$(this).html()+'</script>';
		}
	});
	$frame.find('form').find('link').each(function(){
		links+='<link href="'+$(this).attr('href')+'" rel="stylesheet" type="text/css" />';
	});
	//组装header,styles和scripts
	pageHeader += styles+scripts+links;
	//console.log("header:======"+pageHeader);
	pageFooter=outerHtml($frame.find('div[data-role=footer]'));
	pageContentClass=$frame.find('.phoneScreen').attr('class'); 	
	$('#pageFrame').contents().find('.phoneScreen')
				.children('li')
				.each(
						function() {
							var liId = $(this).attr('name');
							var attrs = '';
							if (liId == 'hidden_type') {
								attrs = generateAttrs(this);
								gHtml += "<li id=\"hidden_type\" name=\"hidden_type\" class=\"highlight hidden_bg\"><input "
										+ attrs + " /></li>"+newline;
							}else if (liId == 'textarea_type'){
								var attr = $(this).find('textarea').get(0).attributes;
								if (attr!=undefined){
									for (var i=0;i<attr.length;i++){
										att=attr[i];
										if(att.specified){
											if (att.name=='isdisabled'&&att.value=='1'){
												attrs += " disabled=\"true\" ";
											}else{
												attrs += att.name + "=\"" + att.value + "\" ";
											}
										}//specified
									}//for
								}
								gHtml += "<li id=\"textarea_type\" name=\"textarea_type\" class=\"highlight padding_left_right\"><textarea "
										+ attrs
										+ "></textarea></li>"+newline;
							} else if (liId == 'input_type') {
								var attr = $(this).find('input').get(0).attributes;
								if (attr!=undefined){
									for (var i=0;i<attr.length;i++){
										att=attr[i];
										if(att.specified){
											attrs += att.name + "=\"" + att.value + "\" ";
										}//specified
									}//for
								}
								gHtml += "<li id=\"input_type\" name=\"input_type\" class=\"highlight padding_left_right\"><input "
										+ attrs
										+ " /></li>"+newline;
							}else if (liId == 'date_type') {
								var attr = $(this).find('input').get(0).attributes;
								if (attr!=undefined){
									for (var i=0;i<attr.length;i++){
										att=attr[i];
										if(att.specified){
											attrs += att.name + "=\"" + att.value + "\" ";
										}//specified
									}//for
								}
								gHtml += "<li id=\"date_type\" name=\"date_type\" class=\"highlight padding_left_right\"><input "
									+ attrs
									+ " /></li>"+newline;
							} else {
								var oh = $(this).html();
								var id = $(this).attr('id');
								var iClass = $(this).attr('class');
								var iStyle = $(this).attr('style');
								if (!iStyle){
									iStyle = "";
								}
								gHtml += "<li class=\"" + iClass + "\" style=\""+iStyle+"\" id=\""
										+ id + "\" name=\"" + id + "\"" + ">"
										+ oh + "</li>"+newline;
							}
	});
	obj.pageHeader = pageHeader;
	obj.pageFooter = pageFooter;
	obj.pageContent = gHtml;
	obj.pageContentClass = pageContentClass;
	return obj;
}
var outerHtml=function(oh){  
	   return $('<div></div>').append(oh.clone()).html();  
}
//标签页，HTML
function genTabHtml(newline) {
	var pageHeader = '';
	var pageFooter = '';
	var pageContent = '';
	var obj = {};
	var $frame = $('#pageFrame').contents();
	//console.log($frame.find('.phoneScreen'));
	$frame.find('.phoneScreen').each(function() {
		var headerDiv="";
		var $pageForm = $frame.find('#pageForm');
		var $header = $frame.find('div[data-role="header"]');
		$header.find('div:eq(0)').removeClass().addClass('tab-nav-action');
		$header.find('div:gt(0)').removeClass().addClass('tab-nav');
		var $footer = $frame.find('div[data-role=footer]');
		headerDiv = outerHtml($header);
		var styles='';
		var scripts='';
		var links='';
		$pageForm.find('style').each(function(){
			styles+='<style>'+$(this).html()+'</style>';
		});
		$pageForm.find('script').each(function(){
			if ($(this).attr('id')){
				scripts+='<script id=\"'+$(this).attr('id')+'\" src=\"'+$(this).attr('src')+'\" ></script>';
			}else{
				scripts+='<script>'+$(this).html()+'</script>';
			}
		});
		$pageForm.find('link').each(function(){
			links+='<link href="'+$(this).attr('href')+'" rel="stylesheet" type="text/css" />';
		});
		//组装header,styles和scripts
		pageHeader = headerDiv+styles+scripts+links;
		//组装footer
		pageFooter=outerHtml($footer);
		var uls = [];
		//组装tab页和每个tab页的组件
		$(this).find('ul').each(function(i){
			var ul = '';
			var gHtml = '';
			if (i==0){
				ul='<ul class=\"tab \" style=\"display:block;\">';
			}else{
				ul='<ul class=\"tab innerClass\" style=\"display:none;\">';
			}
			$(this).find('>li').each(function(){
				var liId = $(this).attr('name');
				var attrs = '';
				if (liId == 'hidden_type') {
					attrs = generateAttrs(this);
					gHtml += "<li id=\"hidden_type\" name=\"hidden_type\" class=\"highlight hidden_bg\"><input "
						+ attrs + " /></li>"+newline;
				}else if (liId == 'textarea_type'){
					var attr = $(this).find('textarea').get(0).attributes;
					if (attr!=undefined){
						for (var i=0;i<attr.length;i++){
							att=attr[i];
							if(att.specified){
								if (att.name=='isdisabled'&&att.value=='1'){
									attrs += " disabled=\"true\" ";
									attrs +="style=\"color:#000;font-weight:bolder;\"";
								}else{
									attrs += att.name + "=\"" + att.value + "\" ";
								}
							}//specified
						}//for
					}
					gHtml += "<li id=\"textarea_type\" name=\"textarea_type\" class=\"highlight padding_left_right\"><textarea "
						+ attrs
						+ "></textarea></li>"+newline;
				} else if (liId == 'input_type') {
					var attr = $(this).find('input').get(0).attributes;
					if (attr!=undefined){
						for (var i=0;i<attr.length;i++){
							att=attr[i];
							if(att.specified){
								attrs += att.name + "=\"" + att.value + "\" ";
							}//specified
						}//for
					}
					gHtml += "<li id=\"input_type\" name=\"input_type\" class=\"highlight padding_left_right\"><input "
						+ attrs
						+ " /></li>"+newline;
				}else if (liId == 'date_type') {
					var attr = $(this).find('input').get(0).attributes;
					if (attr!=undefined){
						for (var i=0;i<attr.length;i++){
							att=attr[i];
							if(att.specified){
								attrs += att.name + "=\"" + att.value + "\" ";
							}//specified
						}//for
					}
					gHtml += "<li id=\"date_type\" name=\"date_type\" class=\"highlight padding_left_right\"><input "
						+ attrs
						+ " /></li>"+newline;
				}else {
					var oh = $(this).html();
					var id = $(this).attr('id');
					var iClass = $(this).attr('class');
					gHtml += "<li class=\"" + iClass + "\" id=\""
					+ id + "\" name=\"" + id + "\"" + ">"
					+ oh + "</li>"+newline;
				}//else
			})//ul li
			ul+=gHtml+"</ul>";
			uls[i]=ul;
		})//ul
		for (jIndex in uls){
			pageContent += uls[jIndex];
		}
	});//phoneScreen li
	obj.pageHeader = pageHeader;
	obj.pageFooter = pageFooter;
	obj.pageContent = pageContent;
	return obj;
}
//生成页面
function generatePage(obj) {
	var protocol = "<div id='page_protocol' style='display:none'>"
			+ $.trim($('#reqMsgs').val()) + 
			"</div>"
			+ "<div id='skinId' style='display:none'>"
			+ $.trim($(".phoneGray").attr("id")) + "</div>"
			+ "<div id='pageType' style='display:none'>"
			+ $.trim($("#pageType").val()) + "</div>"
			+ "<div id='cssTemplateName' style='display:none'>"
			+ $.trim($("#cssTemplateName").val()) + "</div>";
	var pageHtml = '<div data-role="page" id="page">'+"<form id=\"pageForm\">";
	var pageContent = '';
	pageHtml += obj.pageHeader;// header
	pageContent = "<div id=\"page_content1\" data-role=\"content\"><div id=\"details\" style=\"position: relative;\" ";
	if (obj.pageContentClass){
		pageContent+="class=\""+obj.pageContentClass+"\">";
	}else{
		pageContent+="class=\"phoneScreen phoneDetail\">";
	}
	pageContent+=obj.pageContent + "</div>";
	pageContent += protocol;
	pageContent += "</div>";// content div
	pageHtml += pageContent;
	pageHtml += obj.pageFooter;// footer
	pageHtml += "</form>";// form
	pageHtml += '</div>';// page div
	return pageHtml;
}
//生成CODE区域编码
function generateCode(obj) {
	var protocol = "<div id='page_protocol' style='display:none'>\n\t"
			+ $.trim($('#reqMsgs').val()) + 
			"</div>\n\t"
			+ "<div id='skinId' style='display:none'>"
			+ $.trim($(".phoneGray").attr("id")) + "</div>\n\t"
			+ "<div id='pageType' style='display:none'>\n\t"
			+ $.trim($("#pageType").val()) + "</div>\n\t"
			+ "<div id='cssTemplateName' style='display:none'>\n\t"
			+ $.trim($("#cssTemplateName").val()) + "</div>\n\t";
	var pageHtml = '<div data-role="page" id="page">\n\t'
			+ "<form id=\"pageForm\">\n\t";
	var pageContent = '';
	pageHtml += obj.pageHeader;// header
	pageContent = "<div id=\"page_content1\" data-role=\"content\">\n\t<div id=\"details\" style=\"position: relative;\" class=\"phoneScreen phoneDetail\">\n\t"
			+ obj.pageContent + "</div>\n\t";
	pageContent += protocol;
	pageContent += "</div>\n\t";// content div
	pageHtml += pageContent;
	pageHtml += "</form>\n\t";// form
	pageHtml += obj.pageFooter;// footer
	pageHtml += '</div>';// page div
	return pageHtml;
}

$('#savePage').click(function() {
	var pType = $('#pageType').val();
	var page = '';
	if (pType=='blank'){
		page = genHtml('');
	}else if (pType=='tab'){
		page = genTabHtml('');
	}
	gHtml = generatePage(page);
	//console.log(gHtml);
	layer.confirm('保存后会关闭当前页面，是否确定保存？', function(index) {
		$.post('toAddRaw', {
			rawHtml : gHtml,
			serviceId : $('#serviceId').val(),
			resourceName : $('#fileName').val(),
			jsId:"<script id='externalScript'>"+jsEditor.getValue()+"</script>",
			templateId:$('#templateId').val(),
			responseProtocol:$('#ansMsgs').val(),
			rawId:$('#rawId').val()
		}, function() {
			layer.close(index);
			window.opener.refreshPage();
			window.close();
		})
	});
})

$("#fo_creat").click(function() {
	//检查绑定ID
	$frame = $('#pageFrame').contents();
	var authEle =  $frame.find('[auth_data]');//有auth_data属性的元素 
	var hasEmpty = false;//flag
	var emptyEle = new Array;//存放id为空的元素
	//检查auth_data是否为空
	for(var i=0;i<authEle.length;i++){
		var attrAuth = authEle[i].getAttribute('auth_data');
		attrAuth = attrAuth.replace(/[ ]/g,"");
		if(attrAuth==''||attrAuth==' '){
			emptyEle.push(authEle[i]);
			hasEmpty = true;
		}else{
			//authEle[i].removeAttribute('style');
			$(authEle[i]).removeClass('auth_data_error');
		};	
	}
	if(hasEmpty){
		layer.alert('绑定ID不能为空',8);
		for(var i=0;i<emptyEle.length;i++){
			var $empty = $frame.find(emptyEle[i]); 
			//$empty.css({'color':'#F00','border':'1px solid #F00'});
			$empty.addClass('auth_data_error');
		}
	}else{
		$('#fileName').val("");
		index = $.layer({
			type : 1,
			area : [ '300px', '130px' ],
			title : "保存页面",
			closeBtn : [ 0, true ],
			page : {
				dom : '#add'
			}
		})
	};
	//}
})

function openDiv(width,height,title,selector){
	index = $.layer({
		type : 1,
		area : [ width, height ],
		title : title,
		closeBtn : [ 0, true ],
		page : {
			dom : selector
		}
	})
}
$('.cpList li').each(function(){
	$(this).attr("draggable","true");
});
//预览
$("#fo_presure").click(function() {
	var html = $(".phoneScreen").html();
	openDialog("预览", 'preview', 700, 600);
});

//换肤
function changeStyle(style) {
	$('#pageFrame').contents().find('body').attr('id',style);
	$(".phoneGray").attr("id", style);
}

function openDialog(title,url,width,height,menuId){
	var i=$.layer({
	    type : 2,
	    title : title,
	    iframe : {src : url},
	    border : [4 , 1 , '#DBD9D9', true],
	    area : [width , height],
	    offset : ['80px',''],
	    close : function(index){
			layer.close(index);
		},
		success:function(la){
			$(".xubox_layer").attr("iframe_menuId",menuId);
		}
	});
	return i;
}


var setting = {
		async: {
			enable: true,
			url:"laodModuleTree",
			autoParam:["id"]
		},
	data : {
		key : {
			title : "name"
		},
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : treeClick
	}
};

$('#fo_redo').click(function() {
	/*var liLength = $('#pageList_Id').find('li').length;
	var pageRealId = $('#selPageId').val();
	if (liLength<=0){
		layer.alert("请先建立Page！",8);
		return false;
	}
	if (!pageRealId){
		layer.alert("请选择一个Page！",8);
		return false;
	}*/
	loadTree("blank");
	openDiv('400px', 'auto' ,"选择模板",'#loadModule');
	$('#contentAttrs').empty();
});

$('#fo_uploadJs').click(function() {
	var obj = document.getElementById('file');  
	obj.outerHTML=obj.outerHTML;
	index = $.layer({
		type : 1,
		area : [ '300px', 'auto' ],
		title : "选择模板",
		closeBtn : [ 0, true ],
		page : {
			dom : '#uploadJs'
		}
	});
});
//清空画布
$('#fo_clear').click(function() {
	$('#pageFrame').contents().find('#canvas').empty();
	$('#contentAttrs').empty();
	$('.mode li').removeClass('hover');
	$('.mode li').eq(0).addClass('hover');
	$($defaultMode.find('a').attr('name')).show().siblings().hide();
});

var currentPage = document.location.href;
if (currentPage.indexOf('toNewRawResourcePage')>0){
	/*index = $.layer({
		type : 1,
		area : [ '300px', '330px' ],
		title : "新建页面向导",
		closeBtn : [ 0, false ],
		page : {
			dom : '#decorationDiv'
		}
	});*/
	//refreshPages();
}else{
	if ($('#pageType').val()=="blank"){
		loadTree("blank");
	}else if ($('#pageType').val()=="tab"){
		loadTree("tab");
	}
}


$('#cancelDecorationBtn').click(function() {
	layer.close(index);
});
$('.cancelPageBtn').click(function() {
	layer.close(index);
});

function loadTree(type){
	var treeNodes=null;
	$.ajax({
		async : false,
		cache:false,
		type: 'POST',
		dataType : "json",
		url: "laodModuleTree?pageType="+type,//请求的action路径
		error: function () {//请求失败处理函数
			alert('请求失败');
		},
		success:function(data){ //请求成功后处理函数。  
			treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes
		}
	});
	$.fn.zTree.init($("#tree"),setting,treeNodes);//初始tree
}

//选择模板
function treeClick(event, treeId, treeNode, clickFlag){
	var currentPage = document.location.href;
	if (currentPage.indexOf('toNewRawResourcePage')>0){
		$('#pageFrame').attr('src','canvas');
	}else if (currentPage.indexOf('gotoModifyRawResourcePage')>0){
		$('#pageFrame').attr('src','toModifyRawResourcePage?serviceId='+$('#serviceId').val()+'&rawId='+$('#rawId').val());
	}
	$('#pId').val(treeNode.id);
}

//ckeditor修改样式
function addStyle(id){
	//$($("#pageFrame").contents().get(0)).find("#"+id).html("");
	$($("#pageFrame").contents().get(0)).find("#"+id).html(CKEDITOR.instances.editor1.getData());
}

//上传图片
function uploadImg(){
	 if($("#auth_imageId_file").val()==""){
		    alert("上传文件不能为空!");
		    return false;
	 }
	$.ajaxFileUpload({
		url:"upload",//需要链接到服务器地址
		secureuri:false,
		fileElementId:"auth_imageId_file",//文件选择框的id属性
		dataType: 'json',   //也可以是json
		success: function (data) {
			if (data.fileName!=''){
				//$(window.parent.document).find("input[id=modelIcon]").val(data.fileName);
				$("img[id^=m_listView_img]").attr("src","readImage?imagePath="+data.fileName);
			}else{
				return false;
			}
		},error:function(XMLHttpRequest, textStatus, errorThrown){
		}
	});
}

function isRepeat(arr){
    var hash = {};
    var obj = {"flag":false,"repeat":""};
    for(var i in arr) {
        if(hash[arr[i]]){
        	obj.flag = true;
        	obj.repeat = arr[i];
        	return obj;
        }else{
        	hash[arr[i]] = true;
        }
    }
    return obj;
}

// phone或pad切换
$('#pad').click(function() {
	$('.phoneGray, .phoneRec, .phoneScreen, .phoneHome').removeClass('rot');
	$('.phoneScreen').animate({
		height : 384,
		width : 512
	}, 'fast');
	$('.phoneGray').animate({
		width : 512
	}, 'fast');
});
$('#phone').click(function() {
	$('.phoneScreen').animate({
		height : 454,
		width : 254
	}, 'fast');
	$('.phoneGray').animate({
		width : 256
	}, 'fast');
	$('.phoneGray, .phoneRec, .phoneScreen, .phoneHome').removeClass('rot');
});
// phone旋转
$('#rotation').click(function() {
	var curHeight = $('.phoneScreen').css('height');
	if (curHeight == "454px" || curHeight == "254px") {
		$('.phoneGray').animate({
			width : 507
		}, 100);
		$('.phoneGray, .phoneRec, .phoneScreen, .phoneHome').addClass('rot');
		$('.phoneScreen').animate({
			height : 254,
			width : 454
		}, 140);
	}
});

$('.pageComponent').children("div").each(function(){
	$(this).bind('click',function(){
		$(this).siblings().toggleClass('ui-icon-plus ui-btn-active');
	});
})
//选字段
//判断values值的类型
function isArray(object){
    return object && typeof object==='object' &&
        Array == object.constructor;
}
function isObject(object){
    return object && typeof object==='object' &&
        Object == object.constructor;
}

//去重
function unique1(arrKey){
    var items =arrKey;
    var valueArr = [];
    var hash = {};
    for (var i = 0; i <items.length; i++) {
        var item = items[i]
        if(item){
            var key = typeof(item) + item
            if (hash[key] !== 1) {
                valueArr.push(item)
                hash[key] = 1
            }
        }
    }
    return valueArr;
}
function creatPro(){
    var valArr=[];
    var val=$("#ansMsgs").val();
    var value = val.replace(/\s/g,'');
    var obj =JSON.parse(value);
    var arrKey = [];
    function getObjKeys(obj,parentName){
        var key;
        for(var i in obj){
            if(obj.hasOwnProperty(i)){
                key = i;
                if(parentName){
                    key = parentName + '.' + i;
                }
                arrKey.push(key);
                if(isArray(obj[i])){
                	var len = obj[i].length;
                	for(var j = 0;j<len;j++){
                		arguments.callee(obj[i][j]);
                	}
                }else if(isObject(obj[i])){
                    arguments.callee(obj[i],i);
                }
            }
        }
    }
    getObjKeys(obj);
    var arrKeys= unique1(arrKey);
    var keysId=[];
    localStorage.keysId = arrKeys;
    alert("设置完成");
}
function addIdList() {
    var inputId=$("#auth_dataId");
    if( inputId){
    	var valueIds=[];
        valueIds = localStorage.keysId.split(",");
        var len = valueIds.length;
        if(len !=0){
            //生成一个ul，遍历返回的id值动态生成li，填充到ul里面
            var newUl = document.createElement('ul');
            newUl.setAttribute("Id", "idList");
            var $ul = $(newUl);
            var $parent = $($("#auth_dataId").parent());
            for(var i=0;i<len;i++){
                var newLi = "<li>"+ valueIds[i]+"</li>";
                $ul.append(newLi);
            }
            $parent.append(newUl);
            $ul.addClass("idnone");
        }
    }
}
$('#contentAttrs').delegate('#auth_dataId', 'focus',function(){
    addIdList();
    $ul=$($("#idList"));
    $ul.removeClass("idnone");
    $ul.addClass("idblock");
    var ul = document.getElementById('idList');
    var lis = ul.getElementsByTagName('li');
    for(var i=0;i<lis.length;i++){
        lis[i].onclick = function(){
            var currentId = this.innerHTML;
            $("#auth_dataId").val(currentId);
            Cominteraction.changeTextByColumn(document.getElementById('auth_dataId'),'auth_data');
        }
    }
});