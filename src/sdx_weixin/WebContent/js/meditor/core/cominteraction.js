var activeId;
var  Cominteraction={
		changePro:function(com_type,obj){//点击组件显示属性栏
			if (com_type){
				activeId=$(obj).attr("id");
				var json = new Meditor().getContent(com_type); 
				$(".titleText").html(json['attrTitle']);
				MobileAttrs.parseAttrs(com_type,obj);
			}
		},
		changeList:function(com_type,obj){//特定点击：点击列表组件显示属性栏
			activeId=obj.id;
			MobileAttrs.parseAttrs(com_type,obj);
		},
		changeTextByColumn:function(obj,column){//当属性输入框onkeyup时对元素的值进行同步修改
				if (obj.value!=''&&obj.value!=' '){
					if (column=="value"){
						$("#"+activeId).val(obj.value);//显示用
						$("#"+activeId).attr(column,obj.value);//代码用
					}else{
						$("#"+activeId).attr(column,obj.value);
					}
					var liId = $("#"+activeId).parents('li').attr('id');
					if (liId =='hidden_type'||liId =='textarea_type'||liId =='input_type'||liId =='date_type'){
						if (column=='auth_data'){
							$("#"+activeId).attr("name",obj.value);
						}
					}
					if (liId=='textarea_type'){
						if (column=='isdisabled'&&obj.value=='1'){
							$("#"+activeId).attr('style','color:#000;font-weight:bolder;');
						}else if (column=='isdisabled'&&obj.value=='0'){
							$("#"+activeId).removeAttr('style');
						}
					}
				}else{
					$("#"+activeId).attr(column," ");
				}
		}
}
//改变文本内容
function changeLabelTextByColumn(obj,column){
	if ($('#'+activeId).attr(column)!=undefined){
		$('#'+activeId).attr(column,obj.value);
	}
	$("#"+activeId).html(obj.value);
}
//改变listId
function changeListId(obj,column){
	if ($('#'+activeId).attr(column)!=undefined){
		$('#'+activeId).attr(column,obj.value);
	}
}
//改变样式
function changeStyleByColumn(obj,column){
	var realStyle=$('#'+activeId).attr('style');
	var unit = "";
	if (column=="font-size"){
		unit ="px";
	}
	if (realStyle!=undefined&&realStyle!=""){
		var ar = splitByChar(realStyle,";");
		var br = {};
		for (var key in ar){
			 if (ar[key]!=""){
				var m = ar[key].split(":");
				br[m[0]]=m[1]+";";
			 }
		}
		br[column] = obj.value+unit+';';
		var style = '';
		for (var k in br){
			style+=k+":"+br[k];
		}
		$('#'+activeId).attr('style',style);
	}else{
		$('#'+activeId).attr('style',column+":"+obj.value+unit+";");
	}
	$('#'+activeId).attr(column,obj.value);
}
//加数值
function addValue(attr){
	var n=$("#"+attr+"_digitValue").val();
    var num=parseInt(n)+1;
	if(num==0){
		alert("不能为0");
	}
    $("#"+attr+"_digitValue").val(num);
    var obj={};
    obj.value=num;
    changeStyleByColumn(obj,attr);
}
//减数值
function minusValue(attr){
	var n=$("#"+attr+"_digitValue").val();
    var num=parseInt(n)-1;
	if(num==0){
		alert("不能为0!"); 
		return
	}
    $("#"+attr+"_digitValue").val(num);
    var obj={};
    obj.value=num;
    changeStyleByColumn(obj,attr);
}
function splitByChar(r,ch){
	if (r.indexOf(ch)<0){
		return r;
	}
	return r.split(ch);
}

//改变table的边框样式
function changeTableBorder(obj,column){
	$('#'+activeId).removeClass();
	$('#'+activeId).addClass(obj.value);
}
//改table边框的标题
function changeTableTitle(obj,column){
	if (obj.value==""){
		$('#'+activeId).find('tr:first td').removeClass();
	}else{
		$('#'+activeId).find('tr:first td').addClass(obj.value);
	}
}

//改table的外边距
function changeTableMargin(obj,column){
	if ($('#'+activeId).parents('li').attr('id')=='listrow_type'){
		if (obj.value=='1'){
			$('#'+activeId).parents('li').addClass("padding_left_right");
		}else if (obj.value=='2'){
			$('#'+activeId).parents('li').removeClass("padding_left_right").css('padding-bottom','7px');
		}
	}
}
//隐藏table列
function changeHiddenColumn(obj,column){
	var table = $('#'+activeId);
	var text = obj.value;
	if ($.trim(text)!=""){
		var arr = splitByChar(text,",");
		for (var i=1;i<=arr.length;i++){
			$(table).find('tr td:nth-child('+i+')').css("display","none");
		}
	}else{
		$(table).find('tr td').css('display',"");
	}
}
//是否组件隐藏
function changeHiddingByColumn(obj,column){
	if(obj.value=='true'){
		$('#'+activeId).parents('li').addClass('hidding');
	}else{
		$('#'+activeId).parents('li').removeClass('hidding');
	}
	$('#'+activeId).attr(column,obj.value);
}
//修改table的列数和行数
function changeRowColumns(obj,row,column){
	var listId = $('#listrowidId').val();
	var rowNum = $('#rowsId').val();
	var columnNum = $('#columnsId').val();
	var tabObj = $('table[listrowid='+listId+']').get(0);//外层DIV
	$(tabObj).empty();
	var tr="";
	var wid = 50;
	if (columnNum!=0){
		wid = 100/columnNum;
	}
	wid+='%';
	for (var i=1;i<=rowNum;i++){
		tr +="<tr>"; 
		if (columnNum==2){
			tr+='<td ondrop="drop(event)" ondragover="allowDrop(event)">'+1+'</td>';
			tr+='<td ondrop="drop(event)" ondragover="allowDrop(event)">'+1+'</td>';
		}else{
			for (var j=1;j<=columnNum;j++){
				tr+='<td width="'+wid+'" ondrop="drop(event)" ondragover="allowDrop(event)">'+j+'</td>';
			}
		}
		tr+="</tr>";
	}
	$(tabObj).append(tr);
}
//修改listView的列数
function changeColumns(obj,column){
	var listId = $('#listidId').val();
	var divClass = $('div[listid='+listId+']').get(0);//外层DIV
	var divObj = $(divClass).children("div").get(0);//第一个div
	var divLastObj = $(divClass).children("div:last");//第二个div
	var realId = $(divClass).attr('id');
	var divObjLen = $(divObj).children().length;
	if (obj.value=='ui-grid-c'){//4列
		$(divObj).attr('class','ui-grid-c');
		$(divLastObj).attr('class','ui-grid-c');
		if (divObjLen>4){
			$(divObj).children("div:last").remove();
			$(divLastObj).children("div:last").remove();
		}else if (divObjLen<4){
			var div= '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
			+ (realId + 4)
			+ '" name="'
			+ (realId + 4)
			+ '">第四列</label></div>';
			var divLast = '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="createtime" name="show_label_type_createtime" id="createtime">Some Text</label></div>';
			$(divObj).append(div);
			$(divLastObj).append(divLast);
		}
	}else if (obj.value=='ui-grid-d'){//5列
		$(divObj).attr('class','ui-grid-d');
		$(divLastObj).attr('class','ui-grid-d');
		if (divObjLen==3){
			var div= '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
				+ (realId + 4)
				+ '" name="'
				+ (realId + 4)
				+ '">第四列</label></div>'+
				'<div class="ui-block-d" style="border-top:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
				+ (realId + 5)
				+ '" name="'
				+ (realId + 5)
				+ '">第五列</label></div>';
				var divLast = '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="createtime" name="show_label_type_createtime" id="createtime">Some Text</label></div>'+
								'<div class="ui-block-d" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="createtime" name="show_label_type_createtime" id="createtime">Some Text</label></div>';
				$(divObj).append(div);
				$(divLastObj).append(divLast);
		}else if (divObjLen==4){
			var div= '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
			+ (realId + 5)
			+ '" name="'
			+ (realId + 5)
			+ '">第五列</label></div>';
			var divLast = '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="createtime" name="show_label_type_createtime" id="createtime">Some Text</label></div>';
			$(divObj).append(div);
			$(divLastObj).append(divLast);
		}
	}else if (obj.value=='ui-grid-b'){//3列
		$(divObj).attr('class','ui-grid-b');
		$(divLastObj).attr('class','ui-grid-b');
		if (divObjLen==4){
			$(divObj).children("div:last").remove();
			$(divLastObj).children("div:last").remove();
		}else if (divObjLen==5){
			$(divObj).children("div:gt(2)").remove();
			$(divLastObj).children("div:gt(2)").remove();
		}
	}
}
//-------------------拖拽操作----------------------
function allowDrop(ev){
	ev.preventDefault();
}

function drag(ev){
	ev.dataTransfer.setData("Text",ev.target.id);
}

function drop(ev){
	ev.preventDefault();
	var data=ev.dataTransfer.getData("Text");
	var time = new Date().getTime();
	var realId = "idId_"+new String(time);
	var config = {};
	config = new MeditorConfig(config);
	var plugins = config.plugins;
	var html = '';
	$.each(plugins,function(i,n){
		for (var key in n){
			var pObj=eval(n[key]);
			if ($.trim(data)==key){
				var editor = new pObj(data,realId);
				html = Meditor.create(editor);
				break;
			}
		}
	});
	if (ev.target.tagName.toLowerCase()=='label'){
		$('#'+ev.target.id).replaceWith(html);
	}else if (ev.target.tagName.toLowerCase()=='p'){
		var td = $(ev.target).parents('td');
		$(td).empty();
		$(td).append(html);
	}else if (ev.target.tagName.toLowerCase()=='td'){
		ev.target.innerHTML=html;
	}
	//$('#'+realId).addClass('label_lineheight');
	ev.stopPropagation(); // 阻止事件冒泡
}//drop