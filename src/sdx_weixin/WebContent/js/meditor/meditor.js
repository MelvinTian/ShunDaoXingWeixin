var MobileAttrs = {
	parseAttrs:function(com_type,obj){
		var attrs = new Meditor().getContent(com_type);
		//var attrs = json[com_type];//该类型属性组
		var attrHtml = '<div class="prTable">';
		for (var key in attrs){//循环每一个属性
	        var disInput = attrs[key];//当前属性
	        var text='';
	        var style='';
	        if (disInput['style']!='undefined'&&disInput['style']!=undefined){
	        	style='style="'+disInput['style']+'"';
	        }
	        switch (disInput['type']){
				case 'text':
					text = this.parseText(obj,disInput, style);
					break;
				case 'textarea':
					text = this.parseTextArea(disInput, style);
					break;
				case 'auth_data':
				case 'auth_da_text':
					text = this.parseAuthData(obj,disInput, style);
					break;
				case 'select':	
				case 'auth_range':	
				case 'auth_action':
				case 'auth_isfill':
				case 'data-transition':
					text = this.parseAuthSelectAction(obj,disInput, style);
					//console.log(text);
					break;	
				case 'listid':
					text = this.parseListId(obj,disInput, style);
					break;
				case 'auth_image':
					text = this.parseAuthImage(obj,disInput, style);
					
					break;
				case 'digitAddMinus':
					text = this.parseDigitAddMinus(obj,disInput, style);
					break;	
				case 'button':
					text = this.parseSettingsButton(obj,disInput, style);
					break;
				case 'multiSelect':
					text = this.pareMSelect(obj,disInput);
	        }	
	        attrHtml+=text;
		}
		attrHtml+="</div>";
		//console.log(attrHtml);
		$("#contentAttrs",parent.document).html(attrHtml);
		this.parseValue(obj);
		//this.parseFocus(obj);
	},
	parseFocus:function(obj){
		if ($(obj).attr('auth_data')!="undefined"){
			$("#auth_dataId").focus();
			$("#auth_dataId").bind('blur',function(){
				if ($(this).val()==""){
					layer.alert('请填写绑定ID!',8);
					$('#auth_dataId').focus();
					$(obj).parents('li').css('background-color','red');
					return false;
				}else{
					$(obj).parents('li').removeAttr('style');
				}
			});
		}
	},
	pareMSelect:function(obj,disInput){
		if ($(obj).attr('multiSelect')!="undefined"){
			var options = "";
			$('#multiBtnDiv').find('a').each(function(){
				options+='<option id=\"'+$(this).attr('id')+'\" onclick="Cominteraction.showMutiInfo(\''+$(this).attr('id')+'\');">'+$(this).html()+'</option>';
			});
			var text='<li><label>'+disInput['label']+'</label><input style="width:125px;" type="text" data-role="none"  id="multiSelectInputId" />'+
			'<input type="button" onclick="Cominteraction.addMutiOption();" style="width:40px;margin-right:5px;" value="新增" /><input onclick="Cominteraction.deleteMutiOption();" style="width:40px;" type="button" value="删除" /></li>';
			text+='<li>'+'<select id="multiSelectId" multiple="multiple">'+
			options+'</select>'+'</li>';
	        return text;
		}
	},
	parseText:function(obj,disInput,style){
		var val=disInput['value'];
		if ($(obj).is('input')&&$(obj).val()!=""){
			val = $(obj).val();
		}else if ($(obj).is('label')&&$(obj).html()!=""){
			if ($(obj).attr('auth_data')!=""&&$(obj).attr('auth_data')!=undefined){
				val = $(obj).attr('auth_data');
			}else{
				//val = $(obj).html();
			}
		}else if ($(obj).is('textarea')){
			if ($(obj).attr('auth_data')!=""&&$(obj).attr('auth_data')!=undefined){
				val = $(obj).attr('auth_data');
			}
		}
		var readonly = '';
		if (disInput['readonly']){
			readonly=' readonly="readonly" ';
		}
		var text='<li '+style+'><label for="'+disInput['id']+'" >'+disInput['label']+'</label><input data-role="none"  id="'+disInput['id']+'"'+' onkeyup="'+disInput['onkeyup']+'" value="'+val+'" type="'+disInput['type']+'" '+readonly+' /></li>';
        return text;
	},
	parseTextArea:function(disInput,style){
		var text='<li '+style+'><label for="'+disInput['id']+'" >'+disInput['label']+'</label><textarea data-role="none" class="tArea" id="'+disInput['id']+'"'+' onkeyup="'+disInput['onkeyup']+'" value="'+disInput['value']+'" ></textarea></li>';
		return text;
	},
	parseSettingsButton:function(obj,disInput, style){
		var text='<li '+style+'><input class="shangchuan_btn" data-role="none" value="'+disInput['value']+'" type="button" class="tArea" id="'+disInput['id']+'"'+' onclick="'+disInput['onclick']+'" /></li>';
		return text;
	},
	parseAuthImage:function(obj,disInput, style){
		var onclick = " onclick=uploadImg(); ";
		var text='<li '+style+'><label for="'+disInput['id']+'" >'+disInput['label']+'</label>'+
		'<input data-role="none"  type="file" name="file" id="'+disInput['id']+'_file" />'+
		'<input class="shangchuan_btn" data-role="none" id="'+disInput['id']+'_btn"'+onclick+' type=\"button\" value=\"上传\" /></li>';
		return text;
	},
	parseAuthData:function(obj,disInput,style){
		var val=disInput['value'];
		if ($(obj).attr(disInput['type'])!=""&&$(obj).attr(disInput['type'])!=undefined){
			val = $(obj).attr(disInput['type']);
		}
/*		var onblur = '';
		alert(disInput["onblur"]);
		if (disInput["onblur"]){
			onblur = " onblur=\""+disInput["onblur"]+";\" ";
		}*/
		var text='<li '+style+'><label for="'+disInput['id']+'" >'+disInput['label']+'</label><input data-role="none"  id="'+disInput['id']+'"'+' onkeyup="'+disInput['onkeyup']+'" value="'+val+'" type="text" /></li>';
        return text;
	},
	parseAuthSelectAction:function(obj,disInput,style){
		var values = disInput['value'];
		var onchange='';
		if (disInput['onchange']!='undefined'&&disInput['onchange']!=undefined){
			onchange=' onchange="'+disInput['onchange']+'"';
		}
		var val = '';
		if ($(obj).attr(disInput['type'])!=""&&$(obj).attr(disInput['type'])!=undefined){
			val = $(obj).attr(disInput['type']);
		}
		var text ='<li '+style+'><label for="'+disInput['id']+'" >'+disInput['label']+'</label><select id="'+disInput['id']+'" data-role="none" '+onchange+'>';
		for (var i=0;i<values.length;i++){
			var sel='';
			if (val==''){
				if (values[i]['selected']){
					sel = 'selected';
				}
			}else if (values[i]['value']==val){
				sel = 'selected';
			}
			text+='<option value="'+values[i]['value']+'"'+sel+'>'+values[i]['label']+'</option>';
		}
		text+='</select></li>';
		return text;
	},
	parseListId:function(obj,disInput,style){
		var val=disInput['value'];
		if (obj['listid']!=""){
			val = obj['listid'];
		}
		var text='<li '+style+'><label for="'+disInput['id']+'" >'+disInput['label']+'</label><input data-role="none"  id="'+disInput['id']+'"'+' onkeyup="'+disInput['onkeyup']+'" value="'+val+'" type="text" /></li>';
		return text;
	},
	parseDigitAddMinus:function(obj,disInput,style){
		//var val=disInput['value'];
		if (obj['value']!=""&&obj['value']!=undefined){
			val = obj['value'];
		}
		//var attrName = "\""+disInput['attrName']+"\"";
		//var addonclick='onClick=addValue('+attrName+');';
		//var minusonclick='onClick=minusValue('+attrName+');';
		//var onkey = 'onkeyup=changeStyleByColumn(this,'+attrName+');';
		/*var text='<li '+style+'><label>'+disInput['label']+'</label><input type="button" id="add" '+addonclick+' style="width:30px;"  value="+" />'+
			'<input type="text" style="width:160px; margin:0 6px;" id="'+disInput['attrName']+'Id" '+onkey+'  value="'+val+'" />'
			+'<input type="button" style="width:30px;" id="jian" '+minusonclick+' value="-" /></li>';*/
		var clc = 'onClick=addStyle(\"'+$(obj).attr('id')+'\");';
		var text=//"<script type=\"text/javascript\" src=\"js/ckeditor/ckeditor.js\"></script>"+
				"<li><textarea id=\"editor1\" style=\"height: 300px\" name=\"editor1\"></textarea></li>"+
				"<input class='shezhi_btn' data-role=\"none\" type=\"button\" value=\"设置\" "+clc+" />"+
					"<script type=\"text/javascript\">parent.window.CKEDITOR.replace('editor1');</script>";
		return text;
	},
	parseValue:function(obj){
		var attrs = $(obj).get(0).attributes;
		if (attrs!=undefined){
			for (var i=0;i<attrs.length;i++){
				att=attrs[i];
				if(att.specified){
					if ($('#'+att.name+'Id',parent.document)){
						if (att.name=='value'){
							$('#'+att.name+"Id",parent.document).val($(obj).val());
						}else{
							$('#'+att.name+"Id",parent.document).val(att.value);
						}
					}
				}//specified
			}//for
			var compName = $(obj).parents('li').attr('name');
			var config = {};
			config = new MeditorConfig(config);
			var fillckeditor = config.fillckeditor;
			var fillspecialckeditor = config.fillspecialckeditor;
			//console.log(obj);
			//console.log($(obj).html());
			var headfoot = $(obj).parents('div').attr('data-role');
			$.each(fillspecialckeditor,function(i,n){
				if (n==headfoot){
					if (parent.window.CKEDITOR.instances.editor1){
						parent.window.CKEDITOR.instances.editor1.setData($(obj).html());
					}
				}
			});
			$.each(fillckeditor,function(i,n){
				console.log(n+","+compName);
				if (n==compName){
					parent.window.CKEDITOR.instances.editor1.setData($(obj).html());
				}
			});
		}else{
			if ($('#listidId',parent.document)!=undefined&&obj['listid']!=undefined){
				$('#listidId',parent.document).val(obj['listid']);
			}
			if ($('#listrowidId',parent.document)!=undefined&&obj['listrowid']!=undefined){
				$('#listrowidId',parent.document).val(obj['listrowid']);
			}
		}
	}//parseValue
}
var MoblieEditor ={
		buildHtml:function(id,item){
			var html='';
			//debugger;
			var time = new Date().getTime();
			var realId = "idId_"+new String(time);
			item.clone().insertAfter(item);
			$(item).removeClass();
			$(item).addClass('highlight');
			$(item).attr("itemId", time);
			$(item).attr("name", id);
			$(item).attr("id", id+"_"+time);
			//$(item).attr("contentEditable", "true");
			var config = {};
			config = new MeditorConfig(config);
			var specialclass = config.specialclass;
			$.each(specialclass,function(i,n){
				for (var key in n){
					var pObj=n[key];
					if (id==key){
						for (var i=0;i<pObj.length;i++){
							$(item).addClass(pObj[i]);
						}
						break;
					}
				}
			});
			var plugins = config.plugins;
			$.each(plugins,function(i,n){
				for (var key in n){
					var pObj=window[n[key]];
					if (id==key){
						var editor = new pObj(id,realId);
						html = Meditor.create(editor);
						break;
					}
				}
			});
			console.log(html);
			return html;
		}
}
var activeId;
var  Cominteraction={
		getActive:function(){
			var atId = $('#pageFrame').contents().find('#activeId').val();
			return $('#pageFrame').contents().find('#'+atId);
		},
		getItInFrame:function(idInFrame){
			return $('#pageFrame').contents().find('#'+idInFrame);
		},
		changePro:function(com_type,obj){//点击组件显示属性栏,iframe内使用
			if (com_type){
				activeId=$(obj).attr("id");
				$('#activeId').val(activeId);
				var json = new Meditor().getContent(com_type); 
				$(".titleText",parent.document).html(json['attrTitle']);
				MobileAttrs.parseAttrs(com_type,obj);
			}
		},
		changeList:function(com_type,obj){//特定点击：点击列表组件显示属性栏,iframe内使用
			activeId=obj.id;
			$('#activeId').val(activeId);
			MobileAttrs.parseAttrs(com_type,obj);
		},addMutiOption:function(){
			var buttonName = $('#multiSelectInputId').val();
			if ($.trim(buttonName)==""){
				layer.alert('请输入按钮名称',8);
				return false;
			}
			var sel = document.getElementById('multiSelectId');
			for (var i=0;i<sel.options.length;i++){   
			    var current = sel.options[i];   
			    var btnText = current.text;
			    if (btnText==buttonName){
			    	layer.alert('按钮名称不能重复',8);
			    	return false;
			    }
			}
			var buttonId = (new Date()).getTime();
			$('#multiSelectId').append('<option id="'+buttonId+'" onclick="Cominteraction.showMutiInfo(\''+buttonId+'\');">'+buttonName+'</option>');
			$('#multiSelectInputId').val('');
		},deleteMutiOption:function(){
			var sel = document.getElementById('multiSelectId');
			for (var i=0;i<sel.options.length;i++){   
			    var current = sel.options[i];   
			    if (current.selected){  
			    	sel.options[i].remove();
			    	var id = current.id;
			    	$('#pageFrame').contents().find('#'+id).remove();
			    }
			}
		},showMutiInfo:function(aid){
			$('#pages_listId').parents('li').css("display","block");
			$('#multiSelectId').parents('li').css("display","block");
			$('#btn_requestId').parents('li').css("display","block");
			$('#transfor_paramsId').parents('li').css("display","block");
			$('#transfor_typeId').parents('li').css("display","block");
			$('#settingsId').parents('li').css("display","block");
			$('#btn_requestId').val(this.getItInFrame(aid).attr('btn_request'));
			$('#transfor_paramsId').val(this.getItInFrame(aid).attr('transfor_params'));
			$('#transfor_typeId').val(this.getItInFrame(aid).attr('transfor_type'));
			if (jsonPages != null) {
				var addOption = "";
				for ( var i = 0; i < jsonPages.length; i++) {
					addOption = '<option value="' + jsonPages[i]["resourceId"]
							+ '">' + jsonPages[i]["resourceName"] + '</option>';
				}
				$('#pages_listId').empty();
				$('#pages_listId').append(addOption);
			}
		},
		setMutiBtnInfo:function(){
			var sel = document.getElementById('multiSelectId');
			var attrs = '';
			var btnText = '';
			var $btnDiv = $('#pageFrame').contents().find('#multiBtnDiv a');
			if ($('#multiSelectId').val()==''){
				layer.alert('请选择按钮再设置',8);
				return false;
			}
			for (var i=0;i<sel.options.length;i++){   
			    var current = sel.options[i];   
			    if (current.selected){
			    	btnText = current.text;
			    	/*$btnDiv.each(function(){
			    		if ($(this).html()==btnText){
			    			$(this).remove();
			    			return;
			    		}
			    	});*/
			    	id = current.id;
			    	var dataAjax = '';
			    	if ($('#transfor_typeId').val()=='page'){
			    		dataAjax = ' data-ajax="false" '; 
			    	}
			    	attrs = dataAjax+' id="'+id+'" '+' onclick="sendAction(this);" pages_list="'+$('#pages_listId').val()+'" btn_request=\''+$('#btn_requestId').val()+'\' '+
			    	' transfor_params="'+$('#transfor_paramsId').val()+'" '+' button_text="'+btnText+'" transfor_type="'+$('#transfor_typeId').val()+'"';
			    }
			}
			$('#pageFrame').contents().find('#multiBtnDiv').append("<a "+attrs+">"+btnText+"</a>");
			var btnNames = '';
			$('#pageFrame').contents().find('#multiBtnDiv a').each(function(i){
				if (i==0){
					btnNames = $(this).html();
				}else{
					btnNames +=","+$(this).html(); 
				}
	    	});
			var msg = "按钮设置成功！已有按钮："+btnNames;
			layer.alert(msg,9);
		},
		addColumnsLabel:function(){
			var aId = this.getActive();
			var showLabelAttrs = new Meditor()
			.parseJsonAttrs(new ShowLabelEditor().contents['show_label_type']);
			var length = $('#columnnumId').val();
			var lines = "";
			for (var i=0;i<length;i++){
				var rRealId = (new Date()).getTime();
				lines += '<li id="show_label_type" name="show_label_type"><label class="label_lineheight" '+
				' id="idId_'+(rRealId+i)+ '" name="show_label_type_idId_'+(rRealId+i)+'" '+showLabelAttrs.attrs+ ' >测试标题</label></li>';
			}
			$(aId).find('ul').empty();
			$(aId).find('ul').append(lines);
		},
		changeTextByColumn:function(obj,column){//当属性输入框onkeyup时对元素的值进行同步修改,iframe外使用
//				var atId = $('#pageFrame').contents().find('#activeId').val();
				//var aId = $('#pageFrame').contents().find('#'+atId);
				var aId = this.getActive();
				if (obj.value!=''&&obj.value!=' '){
					if (column=="value"){
						$(aId).val(obj.value);//显示用
						$(aId).attr(column,obj.value);//代码用
					}else{
						$(aId).attr(column,obj.value);
					}
					var liId = $(aId).parents('li').attr('name');
					if (liId =='hidden_type'||liId =='textarea_type'||liId =='input_type'){
						if (column=='auth_data'){
							$(aId).attr("name",obj.value);
						}
					}
					if (liId=='textarea_type'){
						if (column=='isdisabled'&&obj.value=='1'){
							$(aId).attr('style','color:#000;font-weight:bolder;');
						}else if (column=='isdisabled'&&obj.value=='0'){
							$(aId).removeAttr('style');
						}
					}
				}else{
					$(aId).attr(column," ");
				}
		},changeLabelTextByColumn:function(obj,column){//改变文本内容，iframe外使用
			var aId = this.getActive();
			if ($(aId).attr(column)!=undefined){
				$(aId).attr(column,obj.value);
			}
			$(aId).html(obj.value);
		}//改变listId
		,changeListId:function(obj,column){
			var aId = this.getActive();
			if ($(aId).attr(column)!=undefined){
				$(aId).attr(column,obj.value);
			}
		}//改变样式
		,changeStyleByColumn:function(obj,column){
			var aId = this.getActive();
			var realStyle=$(aId).attr('style');
			var unit = "";
			if (column=="font-size"){
				unit ="px";
			}
			if (realStyle!=undefined&&realStyle!=""){
				var ar = this.splitByChar(realStyle,";");
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
				$(aId).attr('style',style);
			}else{
				$(aId).attr('style',column+":"+obj.value+unit+";");
			}
			$(aId).attr(column,obj.value);
		},
		splitByChar:function(r,ch){
			if (r.indexOf(ch)<0){
				return r;
			}
			return r.split(ch);
		}//改变table的边框样式
		,changeTableBorder:function(obj,column){
			var aId = this.getActive();
			$(aId).removeClass();
			$(aId).addClass(obj.value);
		}
		//改table边框的标题
		,changeTableTitle:function(obj,column){
			var aId = this.getActive();
			if (obj.value==""){
				$(aId).find('tr:first td').removeClass();
			}else{
				$(aId).find('tr:first td').addClass(obj.value);
			}
		}
		//改table的外边距
		,changeTableMargin:function(obj,column){
			var aId = this.getActive();
			if ($(aId).parents('li').attr('name')=='listrow_type'){
				if (obj.value=='1'){
					$(aId).parents('li').addClass("padding_left_right");
				}else if (obj.value=='2'){
					$(aId).parents('li').removeClass("padding_left_right").css('padding-bottom','7px');
				}
			}
		}
		//隐藏table列
		,changeHiddenColumn:function(obj,column){
			var table = this.getActive();
			var text = obj.value;
			if ($.trim(text)!=""){
				var arr = this.splitByChar(text,",");
				for (var i=1;i<=arr.length;i++){
					$(table).find('tr td:nth-child('+i+')').css("display","none");
				}
			}else{
				$(table).find('tr td').css('display',"");
			}
		}
		//是否组件隐藏
		,changeHiddingByColumn:function(obj,column){
			var aId = this.getActive();
			if(obj.value=='true'){
				$(aId).parents('li').addClass('hidding');
			}else{
				$(aId).parents('li').removeClass('hidding');
			}
			$(aId).attr(column,obj.value);
		}//修改table的列数和行数
		,changeRowColumns:function(obj,row,column){
			var listId = $('#listrowidId').val();
			var rowNum = $('#rowsId').val();
			var columnNum = $('#columnsId').val();
			var tabObj = $('#pageFrame').contents().find('table[listrowid='+listId+']').get(0);//外层DIV
			$(tabObj).empty();
			var tr="";
			var wid = 50;
			if (columnNum!=0){
				wid = 100/columnNum;
			}
			wid+='%';
			for (var i=1;i<=rowNum;i++){
				tr +="<tr>"; 
				/*if (columnNum==2){
					tr+='<td ondrop="drop(event)" ondragover="allowDrop(event)"><label hidding="false" style="" auth_range="protocol" auth_data="" name="label_type_idId_14169715906503" id="idId_15906503'+i+'" class="label_lineheight">信息</label></td>';
					tr+='<td ondrop="drop(event)" ondragover="allowDrop(event)"><label hidding="false" style="" auth_range="protocol" auth_data="" name="label_type_idId_14169715906503" id="idId_69715906503'+i+'" class="label_lineheight">信息</label></td>';
				}else{
					for (var j=1;j<=columnNum;j++){
						tr+='<td width="'+wid+'" ondrop="drop(event)" ondragover="allowDrop(event)"><label hidding="false" style="" auth_range="protocol" auth_data="" name="label_type_idId_14169715906503" id="idId_14169715'+j+'" class="label_lineheight">信息</label></td>';
					}
				}*/
				/*if (columnNum==2){
					tr+='<td ondrop="drop(event)" ondragover="allowDrop(event)"><label hidding="false" style="" auth_range="protocol" auth_data="" name="label_type_idId_14169715906503" id="idId_15906503'+i+'" class="label_lineheight">信息</label></td>';
					tr+='<td ondrop="drop(event)" ondragover="allowDrop(event)"><label hidding="false" style="" auth_range="protocol" auth_data="" name="label_type_idId_14169715906503" id="idId_69715906503'+i+'" class="label_lineheight">信息</label></td>';
				}*/
				if (i==2){
					for (var j=1;j<=columnNum;j++){
						tr+='<td width="'+wid+'" ondrop="drop(event)" ondragover="allowDrop(event)"><label hidding="false" style="" auth_range="protocol" auth_data="" name="label_type_idId_14169715906503" id="idId_14169715'+j+'" class="label_lineheight">信息</label></td>';
					}
				}else{
					for (var j=1;j<=columnNum;j++){
						tr+='<td width="'+wid+'" ondrop="drop(event)" ondragover="allowDrop(event)"><label hidding="false" style="" auth_range="protocol" auth_data="" name="label_type_idId_14169715906503" id="idId_14169715'+j+'" class="">信息</label></td>';
					}
				}
				tr+="</tr>";
			}
			$(tabObj).append(tr);
		}
		//修改listView的列数
		,changeColumns:function(obj,column){
			var listId = $('#listidId').val();
			var divClass = $('#pageFrame').contents().find('div[listid='+listId+']').get(0);//外层DIV
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
}

/*//加数值
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
}*/
//-------------------拖拽操作----------------------
function allowDrop(ev){
	ev.preventDefault();
}

$("#tablelabel_type").on("dragstart",function(ev){
	var dt = ev.originalEvent.dataTransfer;
	dt.setData('Text',"tablelabel_type");
});
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
	ev.stopPropagation(); // 阻止事件冒泡
}//drop

if (!window.MEDITOR) {
	window.MEDITOR = (function() {
		var MEDITOR = {
				basePath : (function(){
					var scripts = document.getElementsByTagName( 'script' );
					var path = '';
					for ( var i = 0; i < scripts.length; i++ ) {
						var match = scripts[ i ].src.match( /(^|.*?[\\\/])(?:_source\/)?core\/meditor.js(?:\?.*)?$/i );
						if ( match ) {
							path = match[ 1 ];
							break;
						}
					}
					return path;
				})()
		}
		return MEDITOR;
	})()
}

function Meditor() {
	this.createTag = function() {

	},
	this.initConfig=function(basePath){
		var headScript = $('head:first');
		var script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'mconfig.js';
		$(headScript).append(script);
		
		script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'core/cominteraction.js';
		$(headScript).append(script);
		
		script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'core/buildCanvas.js';
		$(headScript).append(script);
		
		script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'core/buildAttributions.js';
		$(headScript).append(script);
		
		script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'core/editorDisplay.js';
		$(headScript).append(script);
	},
	this.initPlugin=function(basePath){
		var config = {};
		config = new MeditorConfig(config);
		var plugins = config.plugins;
		$.each(plugins,function(i,n){
			var script = document.createElement( 'script' );
			script.type = 'text/javascript';
			for (var key in n){
				var sName = new String(n[key]);
				script.src = basePath+'plugins/'+sName+'.js';
				$('head:first').append(script);
			}
		});
	},
	this.parseJsonAttrs=function(tagJson){
		var jsonAttrs='';
		var attrs = '';
		var obj={};
		var allowed = tagJson['allowed'];//允许使用的属性数组
		for (var index=0;index<allowed.length;index++){
			var allowAttr = allowed[index];//允许使用的属性
			var defaultValue="";
			 if (tagJson[allowAttr]['type']=='select'||tagJson[allowAttr]['showtype']=='select'){
				var values = tagJson[allowAttr]['value'];
				for (var i=0;i<values.length;i++){
					if (values[i]['selected']){
						defaultValue = values[i]['value'];
						break;
					}
				}
			}else{
				defaultValue = tagJson[allowAttr]['value'];
			} 
			attrs+=allowAttr+'="'+defaultValue+'" ';
			if (jsonAttrs==''){
				jsonAttrs="\""+allowAttr+"\":"+"\""+defaultValue+"\"";
			}else{
				jsonAttrs +=","+"\""+allowAttr+"\":"+"\""+defaultValue+"\"";
			}
		}
		obj.attrs =attrs;
		obj.jsonAttrs = jsonAttrs;
		return obj;
	},this.getContent=function(com_type){
		var config = {};
		config = new MeditorConfig(config);
		var content = ''; 
		var plugins = config.plugins;
		$.each(plugins,function(i,n){
			for (var key in n){
				var pObj=eval(n[key]);
				if (com_type==key){
					var editor = new pObj();
					content = editor.contents[com_type];
					break;
				}
			}
		});
		return content;
	}
}
//new Meditor().initConfig(MEDITOR.basePath);
//new Meditor().initPlugin(MEDITOR.basePath);
Meditor.create = function(editor) {
	 if(editor instanceof Meditor) {
		 return editor.createTag();
	 }
}
MeditorConfig = function( config ) {
	//组件，key是组件在页面中的名称，value是组件对象
	config.plugins =
		[{'button_type':'ButtonEditor'},{'date_type':'DateEditor'},{'divider_type':'DividerEditor'},{'footer_type':'FooterEditor'},
		 {'header_type':'HeaderEditor'},{'input_type':'InputEditor'},{'hidden_type':'InputHiddenEditor'},
		 {'label_type':'LabelEditor'},{'link_type':'LinkEditor'},{'list_type':'ListEditor'},{'listrow_type':'ListRowEditor'},{'listview_type':'ListViewEditor'},
		 {'no_type':'NoButtonEditor'},{'onelinetwocolumn_type':'OneLineTwoColumnEditor'},
		 {'show_label_type':'ShowLabelEditor'},{'submitbtn_type':'SubmitButtonEditor'},{'tablelabel_type':'TableLabelEditor'},
		 {'textarea_type':'TextareaEditor'},{'twobutton_type':'TwoButtonEditor'},{'yes_type':'YesButtonEditor'}
		 ,{'radio_type':'RadioEditor'},{'select_type':'SelectEditor'},
		 {'headernav_type':'HeaderNavEditor'},{'linktitle_type':'LinkTitleEditor'},
		 {'headerleftbutton_type':'HeaderLeftButtonEditor'},
		 {'footerrightbutton_type':'FooterRightButtonEditor'},{'buttonnav_type':'ButtonNavEditor'},
		 {'onelinecolumns_type':'OneLineColumnsEditor'}];
	//设计器中的组件显示样式
	config.specialclass =
		[{'button_type':['padding_left_right']},{'date_type':['padding_left_right']},{'divider_type':['']},
		 {'footer_type':['footer_li_btn']},{'tablelabel_type':['']},{'headernav_type':['']},
		 {'header_type':[]},{'input_type':['padding_left_right','input_text']},
		 {'hidden_type':['padding_left_right','hidden_bg']},
		 {'label_type':['padding_left_right']},{'link_type':['padding_left_right']},
		 {'list_type':['padding_left_right']}, {'listrow_type':['padding_left_right','listrow_type']},{'listview_type':['padding_left_right']},
		 {'no_type':['padding_left_right']},{'onelinetwocolumn_type':['padding_left_right','text-label-bd-bottom']},
		 {'show_label_type':['padding_left_right','placeLabel','label_lineheight']},{'submitbtn_type':['padding_left_right']},
		 {'textarea_type':['padding_left_right']},{'twobutton_type':['padding_left_right']},{'yes_type':['padding_left_right']}
		 ,{'radio_type':['padding_left_right']},
		 {'select_type':['padding_left_right']},{'linktitle_type':['padding_left_right']},
		 {'headerleftbutton_type':['padding_left_right']},{'footerrightbutton_type':['padding_left_right']}
		 ,{'buttonnav_type':['padding_left_right']},{'onelinecolumns_type':['padding_left_right','multiseriate','ov_hidden']}];
	config.fillckeditor =
		['label_type','show_label_type','list_type','onelinetwocolumn_type','listrow_type',
		 'select_type','tablelabel_type','linktitle_type','onelinecolumns_type'];
	config.fillspecialckeditor =
		['header','footer'];
	return config;
};

function ButtonEditor(com_type, realId) {
	this.createTag = function() {
		var button = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<a id="' + realId + '"' + button.attrs + ' name="'
				+ 'button_type_' + realId + '">' + '按钮名称' + '</a>'
				+ '';
		return html;
	}, this.contents = {
		"button_type" : {
			"attrTitle" : "按钮属性",
			"allowed" : [ "button_text", "auth_action", "auth_da_text",
					"data-role", "pages_list", "data-transition"],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "按钮名称",
				"value" : "按钮",
				"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'button_text\');",
			},
			"auth_action" : {
				"id" : "auth_actionId",
				"type" : "auth_action",
				"label" : "执行方式",
				"showtype" : "select",
				"value" : [ {
					"label" : "链接",
					"value" : "auth_href",
					"selected" : true
				}, {
					"label" : "脚本",
					"value" : "auth_script",
					"selected" : false
				}, {
					"label" : "URL",
					"value" : "auth_openpage",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_action\');"
			},
			"auth_da_text" : {
				"id" : "auth_da_textId",
				"type" : "auth_da_text",
				"label" : "执行内容",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\');"
			},
			"data-role" : {
				"id" : "data-roleId",
				"type" : "data-role",
				"label" : "执行内容",
				"value" : "button",
				"style" : "display:none"
			},
			"data-transition" : {
				"id" : "data-transitionId",
				"type" : "data-transition",
				"label" : "执行效果",
				"showtype" : "select",
				"value" : [ {
					"label" : "淡入",
					"value" : "fade",
					"selected" : true
				}, {
					"label" : "翻转",
					"value" : "flip",
					"selected" : false
				}, {
					"label" : "抛出",
					"value" : "flow",
					"selected" : false
				}, {
					"label" : "弹出",
					"value" : "pop",
					"selected" : false
				}, {
					"label" : "从右到左滑动",
					"value" : "slide",
					"selected" : false
				}, {
					"label" : "从右到左滑动并淡入",
					"value" : "slidefade",
					"selected" : false
				}, {
					"label" : "从下到上滑动",
					"value" : "slideup",
					"selected" : false
				}, {
					"label" : "从上到下滑动",
					"value" : "slidedown",
					"selected" : false
				}, {
					"label" : "无效果",
					"value" : "none",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'data-transition\');"
			},
			"data-icon":{
				"id" : "data-iconId",
				"type" : "select",
				"label" : "图标",
				"showtype" : "select",
				"value" : [ {
					"label" : "左箭头",
					"value" : "arrow-l",
					"selected" : true
				}, {
					"label" : "右箭头",
					"value" : "arrow-r",
					"selected" : false
				}, {
					"label" : "抛出",
					"value" : "flow",
					"selected" : false
				}, {
					"label" : "弹出",
					"value" : "pop",
					"selected" : false
				}, {
					"label" : "从右到左滑动",
					"value" : "slide",
					"selected" : false
				}, {
					"label" : "从右到左滑动并淡入",
					"value" : "slidefade",
					"selected" : false
				}, {
					"label" : "从下到上滑动",
					"value" : "slideup",
					"selected" : false
				}, {
					"label" : "从上到下滑动",
					"value" : "slidedown",
					"selected" : false
				}, {
					"label" : "无效果",
					"value" : "none",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'data-icon\');"
			},
			"pages_list" : {
				"id" : "pages_listId",
				"type" : "select",
				"label" : "跳转页面",
				"value" : "",
				"onchange" : "(this,\'auth_da_text\')",
				"style" : "display:block"
			}
		}//button_type
	}//contents
}
ButtonEditor.prototype = new Meditor();
function ButtonNavEditor(com_type, realId) {
	this.createTag = function() {
		var button = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<a id="' + realId + '"' + button.attrs + ' name="'
		+ 'buttonnav_type_' + realId + '">' + '按钮名称' + '</a>'
		+ '';
		return html;
	}, this.contents = {
			"buttonnav_type" : {
				"attrTitle" : "按钮属性",
				"allowed" : [ "button_text", "auth_da_text",
				              "data-role", "pages_list","multiSelect","btn_request","transfor_params","transfor_type","settings"],
				              "id" : {
				            	  "id" : "idId",
				            	  "type" : "text",
				            	  "label" : "ID / Name",
				            	  "value" : "",
				            	  "style" : "display:none"
				              },
				              "name" : {
				            	  "id" : "nameId",
				            	  "type" : "text",
				            	  "label" : "Name",
				            	  "value" : "",
				            	  "style" : "display:none"
				              },
				              "button_text" : {
				            	  "id" : "button_textId",
				            	  "type" : "text",
				            	  "label" : "按钮名称",
				            	  "value" : "按钮",
				            	  "onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'button_text\');",
				              }/*,
				              "auth_action" : {
				            	  "id" : "auth_actionId",
				            	  "type" : "auth_action",
				            	  "label" : "执行方式",
				            	  "showtype" : "select",
				            	  "value" : [ {
				            		  "label" : "链接",
				            		  "value" : "auth_href",
				            		  "selected" : true
				            	  }, {
				            		  "label" : "脚本",
				            		  "value" : "auth_script",
				            		  "selected" : false
				            	  }, {
				            		  "label" : "URL",
				            		  "value" : "auth_openpage",
				            		  "selected" : false
				            	  } ],
				            	  "onchange" : "Cominteraction.changeTextByColumn(this,\'auth_action\');"
				              },
				              "auth_da_text" : {
				            	  "id" : "auth_da_textId",
				            	  "type" : "auth_da_text",
				            	  "label" : "执行内容",
				            	  "value" : "",
				            	  "onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\');"
				              }*/,
				              "data-role" : {
				            	  "id" : "data-roleId",
				            	  "type" : "data-role",
				            	  "label" : "执行内容",
				            	  "value" : "button",
				            	  "style" : "display:none"
				              },
				              "multiSelect":{
				            	  "label":"按钮信息",
				            	  "id":"multiSelectId",
				            	  "type":"multiSelect",
				            	  "value":""
				              },
				              "pages_list" : {
				            	  "id" : "pages_listId",
				            	  "type" : "select",
				            	  "label" : "跳转页面",
				            	  "value" : "",
				            	  "onchange" : "(this,\'auth_da_text\')",
				            	  "style" : "display:none"
				              },
				              "btn_request":{
				            	  "id" : "btn_requestId",
				            	  "type" : "textarea",
				            	  "label" : "请求协议",
				            	  "value" : "",
				            	  "style":"display:none"
				              },
				              "transfor_params":{
				            	  "id" : "transfor_paramsId",
				            	  "type" : "text",
				            	  "label" : "页面传递参数或方法[参数|JS方法(可选)]",
				            	  "value" : "",
				            	  "style":"display:none"
				              },"transfor_type":{
				            	  "id" : "transfor_typeId",
				            	  "type" : "select",
				            	  "label" : "页面跳转方式",
				            	  "value" :  [{
				            		  "label" : "跳转新页面",
				            		  "value" : "page",
				            		  "selected" : true
				            	  }, {
				            		  "label" : "弹出页面",
				            		  "value" : "dialog",
				            		  "selected" : false
				            	  },{
				            		  "label" : "直接提交",
				            		  "value" : "direct",
				            		  "selected" : false
				            	  },],
				            	  "onchange" : "Cominteraction.changeTextByColumn(this,\'transfor_type\');",
				            	  "style":"display:none"
				              },"auth_da_text":{
				            	  "id" : "auth_da_textId",
				            	  "type" : "text",
				            	  "label" : "JS方法",
				            	  "value" : "",
				            	  "style":"display:none"
				              },"settings" : {
									"id" : "settingsId",
									"type" : "button",
									"value" : "设置",
									"onclick" : "Cominteraction.setMutiBtnInfo();",
					            	"style":"display:none"
							  }
			}//button_type
	}//contents
}
ButtonNavEditor.prototype = new Meditor();
function DateEditor(com_type, realId) {
	this.createTag = function() {
		var dateTag = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<input class="datetype" readonly="readonly" ' + dateTag.attrs + ' id="'
				+ realId + '" name="' +com_type+"_"+ realId + '" type="date" />';
		return html;
	}, this.contents = {
		"date_type" : {
			"attrTitle" : "日期选择属性",
			"allowed" : [ "value", "auth_data" ,"hidding"],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"label" : {
				"id" : "labelId",
				"type" : "text",
				"label" : "名称",
				"value" : "date",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'label\');",
				"style" : "display:none"
			},
			/*"type" : {
				"id" : "typeId",
				"type" : "select",
				"label" : "类型",
				"value" : [ {
					"label" : "日期",
					"value" : "date",
					"selected" : true
				}, {
					"label" : "月份",
					"value" : "month",
					"selected" : false
				}, {
					"label" : "星期",
					"value" : "week",
					"selected" : false
				}, {
					"label" : "时间",
					"value" : "time",
					"selected" : false
				}, {
					"label" : "日期时间",
					"value" : "month",
					"selected" : false
				} ],
				"onchange" : "changeTextByColumn(this,\'type\');"
			},*/
			"value" : {
				"id" : "valueId",
				"type" : "text",
				"label" : "默认值",
				"value" : "date",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'value\');"
			},
			"auth_data" : {
				"id" : "auth_dataId",
				"type" : "auth_data",
				"label" : "绑定ID",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_data\');"
			},
			"hidding" : {
				"id" : "hiddingId",
				"type" : "select",
				"label" : "是否默认隐藏",
				"value" : [ {
					"label" : "是",
					"value" : "true",
					"selected" : false
				}, {
					"label" : "否",
					"value" : "false",
					"selected" : true
				} ],
				"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
			}
		}// date_type
	}
}
DateEditor.prototype = new Meditor();
function DividerEditor(com_type, realId) {
	this.createTag = function() {
		var divider = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div id="' + realId + '"' + divider.attrs + ' name="'
				+ 'divider_type_' + realId + '"></div>';
		return html;
	}, this.contents = {
		"divider_type" : {
			"attrTitle" : "分隔符属性",
			"allowed" : [ "style","hidding" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"style" : {
				"id" : "styleId",
				"type" : "text",
				"label" : "自定义样式",
				"value" : "width:100%;height: 10px;background: #f7f7f7;",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');"
			},
			"hidding" : {
				"id" : "hiddingId",
				"type" : "select",
				"label" : "是否默认隐藏",
				"value" : [ {
					"label" : "是",
					"value" : "true",
					"selected" : false
				}, {
					"label" : "否",
					"value" : "false",
					"selected" : true
				} ],
				"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
			}
		}//divider	
	}
}
DividerEditor.prototype = new Meditor();
function FooterEditor(com_type, realId) {
	this.createTag = function() {
		var footer = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div data-role="footer" data-position="fixed"><h1 id="' + realId + '"'
				+ footer.attrs + ' name="' +com_type+"_"+ realId + '"><label id="'+(realId+1)+'" name="label_type_'+(realId+1)+'">页脚</label>' + '</h1>'
				+ '</div>';
		return html;
	}, this.contents = {
		"footer_type" : {
			"attrTitle" : "页脚属性",
			"allowed" : [ "button_text" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"style" : {
				"id" : "styleId",
				"type" : "text",
				"label" : "自定义样式",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
				"style" : "display:none"
			},
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "页眉名称",
				"value" : "字段名称",
				"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'button_text\');"
			}
		}//footer
	}
}
FooterEditor.prototype = new Meditor();
function FooterRightButtonEditor(com_type, realId) {
	this.createTag = function() {
		var footer = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div data-role="footer" data-position="fixed"><h1 id="' + realId + '"'
		+ footer.attrs + ' name="' +com_type+"_"+ realId + '"><label id="'+(realId+1)+'" name="label_type_'+(realId+1)+'">页脚</label>' + '</h1>'
		+'<a id="'+(realId+2)+'" name="buttonnav_type_'+(realId+2)+'" href="#" class="ui-btn-right" data-role="button" data-icon="">处理</a>'
		+' <div id="multiBtnDiv" style="display:none" class="ui-grid-b DFOA_footer_more"><a href="#">重新拟稿</a><a href="#">转会签</a><a href="#">转会签</a></div>'
		+ '</div>';
		return html;
	}, this.contents = {
			"footerrightbutton_type" : {
				"attrTitle" : "页脚属性",
				"allowed" : [ "button_text" ],
				"id" : {
					"id" : "idId",
					"type" : "text",
					"label" : "ID / Name",
					"value" : "",
					"style" : "display:none"
				},
				"name" : {
					"id" : "nameId",
					"type" : "text",
					"label" : "Name",
					"value" : "",
					"style" : "display:none"
				},
				"style" : {
					"id" : "styleId",
					"type" : "text",
					"label" : "自定义样式",
					"value" : "",
					"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
					"style" : "display:none"
				},
				"button_text" : {
					"id" : "button_textId",
					"type" : "text",
					"label" : "页眉名称",
					"value" : "字段名称",
					"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'button_text\');"
				}
			}//footer
	}
}
FooterRightButtonEditor.prototype = new Meditor();
function HeaderEditor(com_type, realId) {
	this.createTag = function() {
		var header = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div data-role="header" data-position="fixed"><h1 id="' + realId + '"'
				+ header.attrs + ' name="' +com_type+"_"+ realId + '">' + '<label id="'+(realId+1)+'" name="label_type_'+(realId+1)+'"'+'>页眉</label>' + '</h1>'
				+ '</div>';
		return html;
	}, this.contents = {
		"header_type" : {
			"attrTitle" : "页眉属性",
			"allowed" : [ "button_text", "style" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"style" : {
				"id" : "styleId",
				"type" : "text",
				"label" : "自定义样式",
				"value" : " ",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
				"style" : "display:none"
			},
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "页眉名称",
				"value" : "字段名称",
				"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'button_text\');"
			}
		}//header	
	}
}
HeaderEditor.prototype = new Meditor();
function HeaderLeftButtonEditor(com_type, realId) {
	this.createTag = function() {
		var header = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div data-role="header" data-position="fixed">'+
		 '<a href="#" data-role="button" data-icon="back">返回</a>'
		+'<h1 id="' + realId + '"'
		+ header.attrs + ' name="' +com_type+"_"+ realId + '">' + '<label id="'+(realId+1)+'" name="label_type_'+(realId+1)+'"'+'>页眉</label>' + '</h1>'
		+ '</div>';
		return html;
	}, this.contents = {
			"headerleftbutton_type" : {
				"attrTitle" : "页眉属性",
				"allowed" : [ "button_text", "style" ],
				"id" : {
					"id" : "idId",
					"type" : "text",
					"label" : "ID / Name",
					"value" : "",
					"style" : "display:none"
				},
				"name" : {
					"id" : "nameId",
					"type" : "text",
					"label" : "Name",
					"value" : "",
					"style" : "display:none"
				},
				"style" : {
					"id" : "styleId",
					"type" : "text",
					"label" : "自定义样式",
					"value" : " ",
					"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
					"style" : "display:none"
				},
				"button_text" : {
					"id" : "button_textId",
					"type" : "text",
					"label" : "页眉名称",
					"value" : "字段名称",
					"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'button_text\');"
				}
			}//header	
	}
}
HeaderLeftButtonEditor.prototype = new Meditor();
function HeaderNavEditor(com_type, realId) {
	this.createTag = function() {
		var header = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div data-role="header" '+ header.attrs +'><h1 id="' + realId + '"'
				+ ' name="' + realId + '">' +'<div data-role="navbar"> <ul><li><a href="#" data-role="button">同意</a></li>  <li><a href="#" data-role="button">拒绝</a></li></ul></div>' + '</h1>'
				+ '</div>';
		return html;
	}, this.contents = {
		"headernav_type" : {
			"attrTitle" : "页眉属性",
			"allowed" : [ "button_text", "style","header-position" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"style" : {
				"id" : "styleId",
				"type" : "text",
				"label" : "自定义样式",
				"value" : " ",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
				"style" : "display:none"
			},
            "header-position" : {
                "id" : "nav-positionId",
                "type" : "select",
                "label" : "是否固定",
                "value" : [ {
                    "label" : "是",
                    "value" : "fixed",
                    "selected" : true
                }, {
                    "label" : "否",
                    "value" : "none",
                    "selected" : false
                }],
                "onchange" : "Cominteraction.changeTextByColumn(this,\'header-position\');"
            },
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "页眉名称",
				"value" : "字段名称",
				"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'button_text\');",
                "style" : "display:none"
			}
		}//header	
	}
}
HeaderNavEditor.prototype = new Meditor();
function InputEditor(com_type, realId) {
	this.createTag = function() {
		var input = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<input readonly="readonly" ' + input.attrs + ' id="'
				+ realId + '" name="' +com_type+"_"+ realId + '" type="text"  />';
		return html;
	}, this.contents = {
		"input_type" : {
			"attrTitle" : "输入框属性",
			"allowed" : [ "value", "placeholder", "auth_data", "auth_isfill",
					"auth_isvalue", "auth_range","isrequired","hidding" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "textinput",
				"style" : "display:none"
			},
			"label" : {
				"id" : "labelId",
				"type" : "text",
				"label" : "名称",
				"value" : "Text Name",
				"style" : "display:none"
			},
			"type" : {
				"id" : "typeId",
				"type" : "select",
				"label" : "输入类型",
				"value" : [ {
					"label" : "文本",
					"value" : "text",
					"selected" : true
				}, {
					"label" : "密码",
					"value" : "password",
					"selected" : false
				}, {
					"label" : "数字",
					"value" : "number",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'type\');"
			},
			"value" : {
				"id" : "valueId",
				"type" : "text",
				"label" : "默认值",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'value\');"
			},
			"placeholder" : {
				"id" : "placeholderId",
				"type" : "text",
				"label" : "占位符",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'placeholder\');"
			},
			"auth_range" : {
				"id" : "auth_rangeId",
				"type" : "auth_range",
				"label" : "值域范围",
				"showtype" : "select",
				"value" : [ {
					"label" : "全局级",
					"value" : "system",
					"selected" : false
				}, {
					"label" : "页面级",
					"value" : "page",
					"selected" : false
				}, {
					"label" : "协议级",
					"value" : "protocol",
					"selected" : true
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_range\');"
			},
			"auth_data" : {
				"id" : "auth_dataId",
				"type" : "auth_data",
				"label" : "绑定ID",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_data\');"
			},
			"auth_isfill" : {
				"id" : "auth_isfillId",
				"type" : "auth_isfill",
				"label" : "是否被填充数据",
				"showtype" : "select",
				"value" : [ {
					"label" : "是",
					"value" : "true",
					"selected" : true
				}, {
					"label" : "否",
					"value" : "false",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_isfill\');"
			},
			"auth_isvalue" : {
				"id" : "auth_isvalueId",
				"type" : "text",
				"label" : "默认值",
				"value" : "true",
				"style" : "display:none"
			},
			"isrequired" : {
				"id" : "isrequiredId",
				"type" : "select",
				"label" : "是否必填",
				"value" : [ {
					"label" : "是",
					"value" : "0",
					"selected" : true
				}, {
					"label" : "否",
					"value" : "1",
					"selected" : false
				}],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'isrequired\');"
			},
			"hidding" : {
				"id" : "hiddingId",
				"type" : "select",
				"label" : "是否默认隐藏",
				"value" : [ {
					"label" : "是",
					"value" : "true",
					"selected" : false
				}, {
					"label" : "否",
					"value" : "false",
					"selected" : true
				} ],
				"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
			}
		}//input	
	}
}
InputEditor.prototype = new Meditor();
function InputHiddenEditor(com_type, realId) {
	this.createTag = function() {
		var input = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<label ' + input.attrs + ' id="' + realId + '" name="'
		+com_type+"_"+ realId + '">隐藏域</label>';
		return html;
	}, this.contents = {
		"hidden_type" : {
			"attrTitle" : "输入框属性",
			"allowed" : [ "value", "type", "auth_data", "auth_isfill",
					"auth_isvalue", "auth_range" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "textinput",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"label" : {
				"id" : "labelId",
				"type" : "text",
				"label" : "名称",
				"value" : "Text Name",
				"style" : "display:none"
			},
			"type" : {
				"id" : "typeId",
				"type" : "hidden",
				"label" : "输入类型",
				"value" : "hidden",
				"onchange" : "Cominteraction.changeTextByColumn(this,\'type\');"
			},
			"value" : {
				"id" : "valueId",
				"type" : "text",
				"label" : "默认值",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'value\');"
			},
			"auth_isfill" : {
				"id" : "auth_isfillId",
				"type" : "auth_isfill",
				"label" : "是否被填充数据",
				"showtype" : "select",
				"value" : [ {
					"label" : "是",
					"value" : "true",
					"selected" : true
				}, {
					"label" : "否",
					"value" : "false",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_isfill\');"
			},
			"auth_isvalue" : {
				"id" : "auth_isvalueId",
				"type" : "text",
				"label" : "默认值",
				"value" : "true",
				"style" : "display:none"

			},
			"auth_range" : {
				"id" : "auth_rangeId",
				"type" : "auth_range",
				"label" : "值域范围",
				"showtype" : "select",
				"value" : [ {
					"label" : "全局级",
					"value" : "system",
					"selected" : false
				}, {
					"label" : "页面级",
					"value" : "page",
					"selected" : false
				}, {
					"label" : "协议级",
					"value" : "protocol",
					"selected" : true
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_range\');"
			},
			"auth_data" : {
				"id" : "auth_dataId",
				"type" : "auth_data",
				"label" : "绑定ID",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_data\');"
			}
		}//hidden	
	}
}
InputHiddenEditor.prototype = new Meditor();
function LabelEditor(com_type, realId) {
	this.createTag = function() {
		var label = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<label  id="' + realId + '" ' + label.attrs + ' name="'
		+com_type+"_"+ realId + '">' + this.contents[com_type]['value']['label'] + '</label>'
				+ '';
		//var reg=new RegExp("\"","g"); //创建正则RegExp对象
		//var hs = html.replace(reg,"\\\"");
		/*var st = sessionStorage.html;
		if (st!=undefined&&st!='undefined'){
			sessionStorage.html = "\""+realId+"\""+":"+"{"+"\"tagName\":\"label\""+","+"\"id\":\"\""+","+"\"name\":\"\""+","+"\"text\":\"\""+"}"+","+st;
		}else{
			sessionStorage.html="\""+realId+"\""+":"+"{"+"\"tagName\":\"label\""+","+"\"id\":\"\""+","+"\"name\":\"\""+","+"\"text\":\"\""+"}";
		}*/
		return html;
	}, this.contents = {
		"label_type" : {
			"attrTitle" : "文本属性",
			"allowed" : [ "style","columnname","hidding"],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"value" : {
				"id" : "valueId",
				"type" : "text",
				"label" : "字段名称",
				"value" : "字段名称",
				"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'value\');",
				"style" : "display:none"
			},
			"columnname" : {
				"id" : "columnNameId",
				"type" : "text",
				"label" : "columnName",
				"value" : "",
				"style" : "display:none"
			},
			"style" : {
				"id" : "styleId",
				"type" : "text",
				"label" : "自定义样式",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
				"style" : "display:none"
			},
			"font-size" : {
				"id" : "font-sizeId",
				"type" : "digitAddMinus",
				"label" : "字体大小",
				"value" : "",
				"attrName" : "font-size"
			},
			"hidding" : {
				"id" : "hiddingId",
				"type" : "select",
				"label" : "是否默认隐藏",
				"value" : [ {
					"label" : "是",
					"value" : "true",
					"selected" : false
				}, {
					"label" : "否",
					"value" : "false",
					"selected" : true
				} ],
				"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
			}
		}
	//label
	}
}
LabelEditor.prototype = new Meditor();
function LinkEditor(com_type, realId) {
	this.createTag = function() {
		var link = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<a id="' + realId + '"' + link.attrs + ' name="'
				+ 'link_type_' + realId + '">' + '链接名称' + '</a>'
				+ '';
		return html;
	}, this.contents = {
		"link_type" : {
			"attrTitle" : "链接属性",
			"allowed" : [ "button_text", "auth_action", "auth_da_text",
					"pages_list" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "链接名称",
				"value" : "链接",
				"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'button_text\');",
			},
			"auth_action" : {
				"id" : "auth_actionId",
				"type" : "auth_action",
				"label" : "执行方式",
				"showtype" : "select",
				"value" : [ {
					"label" : "链接",
					"value" : "auth_href",
					"selected" : true
				}, {
					"label" : "脚本",
					"value" : "auth_script",
					"selected" : false
				}, {
					"label" : "URL",
					"value" : "auth_openpage",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_action\');"
			},
			"auth_da_text" : {
				"id" : "auth_da_textId",
				"type" : "auth_da_text",
				"label" : "执行内容",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\');",
				"style" : "display:none"
			},
			"pages_list" : {
				"id" : "pages_listId",
				"type" : "select",
				"label" : "跳转页面",
				"value" : "",
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\')",
				"style" : "display:block"
			}
		}//link	
	}
}
LinkEditor.prototype = new Meditor();
function ListEditor(id, realId) {
			this.createTag = function() {
				var html = '<div listid="listid" id=\"'
						+ realId
						+ '\">'
						+ '<div class="ui-grid-b" >'
						+ '<div class="ui-block-a" style="border-top:1px solid #D8D8D8;border-left:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
						+ (realId + 1)
						+ '" name="'
						+ (realId + 1)
						+ '">标题</label></div>'
						+ '<div class="ui-block-b" style="border-top:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
						+ (realId + 2)
						+ '" name="'
						+ (realId + 2)
						+ '">摘要</label></div>'
						+ '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
						+ (realId + 3)
						+ '" name="'
						+ (realId + 3)
						+ '">创建</label></div>'
						+ '</div>'
						+ '<div class="ui-grid-b" auth_list="true">'
						+ '<div class="ui-block-a" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-left:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="title" name="show_label_type_title" id="title" >Some Text</label></div>'
						+ '<div class="ui-block-b" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="descp" name="show_label_type_descp" id="descp">Some Text</label></div>'
						+ '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="createtime" name="show_label_type_createtime" id="createtime">Some Text</label></div>'
						+ '</div>' + '</div>';
				return html;
			}, this.contents = {
				"list_type" : {
					"attrTitle" : "链接属性",
					"allowed" : [ "listid" ],
					"id" : {
						"id" : "idId",
						"type" : "text",
						"label" : "ID / Name",
						"value" : "",
						"readonly" : "readonly",
						"style" : "display:none"
					},
					"listid" : {
						"id" : "listidId",
						"type" : "text",
						"label" : "列表ID",
						"value" : "listid",
						"onkeyup" : "Cominteraction.changeListId(this,\'listid\');",
					},
					"columns" : {
						"id" : "columnsId",
						"type" : "select",
						"label" : "列数",
						"value" : [ {
							"label" : "3",
							"value" : "ui-grid-b",
							"selected" : true
						}, {
							"label" : "4",
							"value" : "ui-grid-c",
							"selected" : false
						}, {
							"label" : "5",
							"value" : "ui-grid-d",
							"selected" : false
						} ],
						"onchange" : "Cominteraction.changeColumns(this,\'columns\');"
					}
				}//listtype	
			}
}
ListEditor.prototype = new Meditor();
function ListRowEditor(id, realId) {
			this.createTag = function() {
				var showLabelAttrs = new Meditor()
				.parseJsonAttrs(new ShowLabelEditor().contents['show_label_type']);
				var html = '<table cellpadding="0" auth_list="true" cellspacing="0" listrowid="listrowid" id=\"'+ realId+ '\" class=""><tr><td width="50%" ondrop="drop(event)" ondragover="allowDrop(event)">'+
				'<label class="" id="'
				+ (realId+1) + '" name="show_label_type_' + (realId+1) + '" '
				+ showLabelAttrs.attrs + '>标题</label>'+'</td>'+
				'<td width="50%" ondrop="drop(event)" ondragover="allowDrop(event)">'+
				'<label class="" id="'
				+ (realId+2) + '" name="show_label_type_' + (realId+2) + '" '
				+ showLabelAttrs.attrs + '>测试标题</label>'
				+'</td></tr>'
						+'<tr><td ondrop="drop(event)" ondragover="allowDrop(event)">'+
						'<label class="label_lineheight" id="'
						+ (realId+3) + '" name="label_type_' + (realId+3) + '" '
						+ showLabelAttrs.attrs + '>摘要</label>'+
						'</td><td ondrop="drop(event)" ondragover="allowDrop(event)">'+
						'<label class="label_lineheight" id="'
							+ (realId+4) + '" name="label_type_' + (realId+4) + '" '
							+ showLabelAttrs.attrs + '>测试标题</label>'
						+'</td></tr></table>';
				return html;
			}, this.contents = {
				"listrow_type" : {
					"attrTitle" : "链接属性",
					"allowed" : [ "listrowid","rows","columns","settings","borderColor","borderMargin","hideTd"],
					"id" : {
						"id" : "idId",
						"type" : "text",
						"label" : "ID / Name",
						"value" : "",
						"readonly" : "readonly",
						"style" : "display:none"
					},
					"listrowid" : {
						"id" : "listrowidId",
						"type" : "text",
						"label" : "列表ID",
						"value" : "listrowid",
						"onkeyup" : "Cominteraction.changeListId(this,\'listrowid\');",
					},
					"rows" : {
						"id" : "rowsId",
						"type" : "text",
						"label" : "行数",
						"value" : "2",
						"readonly":"true"
						/*,
						"onkeyup" : "changeRows(this,\'rows\');"*/
					},
					"columns" : {
						"id" : "columnsId",
						"type" : "text",
						"label" : "列数",
						"value" : "2"/*,
						"onkeyup" : "Cominteraction.changeColumns(this,\'columns\');"*/
					},
					"settings" : {
						"id" : "settingsId",
						"type" : "button",
						"value" : "设置",
						"onclick" : "Cominteraction.changeRowColumns(this,\'rows\',\'columns\');"
					},
					"titleBgColor" : {
						"id" : "titleBgColorId",
						"type" : "select",
						"label" : "标题背景",
						"value" :  [ {
							"label" : "默认",
							"value" : "",
							"selected" : true
						}, {
							"label" : "蓝色",
							"value" : "bg_bluecolor",
							"selected" : false
						}, {
							"label" : "金属蓝",
							"value" : "bg_bluecolor",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTableTitle(this,\'titleBgColor\');"
					},
					"borderColor" : {
						"id" : "borderColorId",
						"type" : "select",
						"label" : "边框颜色",
						"value" :  [ {
							"label" : "默认",
							"value" : "",
							"selected" : true
						}, {
							"label" : "无",
							"value" : "bd_none",
							"selected" : false
						}, {
							"label" : "红色",
							"value" : "bd_red",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTableBorder(this,\'borderColor\');"
					},
					"borderMargin" : {
						"id" : "borderMarginId",
						"type" : "select",
						"label" : "是否有边距",
						"value" :  [ {
							"label" : "是",
							"value" : "1",
							"selected" : true
						}, {
							"label" : "否",
							"value" : "2",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTableMargin(this,\'borderMargin\');"
					},
					"hideTd" : {
						"id" : "hideTdId",
						"type" : "text",
						"label" : "隐藏列",
						"value" : "",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'hideTd\');",
						"style" : "display:none"
					},
					"setvalue" : {
						"id" : "setvalueId",
						"type" : "select",
						"label" : "隐藏列",
						"value" :  [  {
							"label" : "无",
							"value" : "",
							"selected" : true
						},{
							"label" : "第1列",
							"value" : "1",
							"selected" : false
						},{
							"label" : "前两列",
							"value" : "1,2",
							"selected" : false
						},{
							"label" : "前三列",
							"value" : "1,2,3",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeHiddenColumn(this,\'borderMargin\');"
					}
				}//listtype	
			}
}
ListRowEditor.prototype = new Meditor();
function ListViewEditor(com_type, realId) {
			this.createTag = function() {
				var listView = new Meditor().parseJsonAttrs(this.contents[com_type]);
				var html = '<ul data-autodividers="true" data-inset="true" data-shadow="true" id="m_listView_'
						+ realId
						+ '" data-role="listview"><li><a auth_view="true" id="'
						+ realId
						+ '"'
						+ listView.attrs
						+ ' name="'
						+ 'listview_type_'
						+ realId
						+ '">'
						+ "<img id=\"m_listView_img\" src=\"resource/images/defaultImg.png\">"
						+ "<h2 id=\"m_listView_title\">测试</h2>"
						+ "<p><span id=\"m_listView_descp\">收文传阅  张一 </span><span id=\"m_listView_ct\">2014/06/24</span>"
						+ "</p>"
						+ '</a>'
						+ '</li>'
						+ '</ul>'
						+ '';
				return html;
			},
			this.contents = {
				"listview_type" : {
					"attrTitle" : "链接属性",
					"allowed" : [ "listid", "auth_action", "auth_image",
							"auth_da_text", "button_text" ],
					"id" : {
						"id" : "idId",
						"type" : "text",
						"label" : "ID / Name",
						"value" : "",
						"readonly" : "readonly",
						"style" : "display:none"
					},
					"listid" : {
						"id" : "listidId",
						"type" : "text",
						"label" : "列表ID",
						"value" : "",
						"readonly" : "readonly",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'listid\');",
					},
					"name" : {
						"id" : "nameId",
						"type" : "text",
						"label" : "Name",
						"value" : "",
						"style" : "display:none"
					},
					"button_text" : {
						"id" : "button_textId",
						"type" : "text",
						"label" : "链接名称",
						"value" : "链接",
						"style" : "display:none"
					},
					"auth_image" : {
						"id" : "auth_imageId",
						"type" : "auth_image",
						"label" : "图标名称",
						"value" : ""
					},
					"auth_action" : {
						"id" : "auth_actionId",
						"type" : "auth_action",
						"label" : "执行方式",
						"showtype" : "select",
						"value" : [ {
							"label" : "链接",
							"value" : "auth_href",
							"selected" : true
						}, {
							"label" : "脚本",
							"value" : "auth_script",
							"selected" : false
						}, {
							"label" : "URL",
							"value" : "auth_openpage",
							"selected" : false
						} ],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_action\');"
					},
					"auth_da_text" : {
						"id" : "auth_da_textId",
						"type" : "auth_da_text",
						"label" : "执行内容",
						"value" : "",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\');"
					}
				}//listView
			}
}
ListViewEditor.prototype = new Meditor();
function NoButtonEditor(com_type, realId) {
	this.createTag = function() {
		var noBtn = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<a id="' + realId + '"' + noBtn.attrs + ' name="'
				+ 'no_type_' + realId + '">' + '不同意，退回' + '</a>'
				+ '';
		return html;
	}, this.contents = {
		"no_type" : {
			"attrTitle" : "同意属性",
			"allowed" : [ "style", "button_text", "data-role",
					"data-transition" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "链接名称",
				"value" : "拒绝",
				"style" : "display:none"
			},
			"style" : {
				"id" : "styleId",
				"type" : "text",
				"label" : "自定义样式",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');"
			},
			"data-role" : {
				"id" : "data-roleId",
				"type" : "data-role",
				"label" : "执行内容",
				"value" : "button",
				"style" : "display:none"
			},
			"data-transition" : {
				"id" : "data-transitionId",
				"type" : "data-transition",
				"label" : "执行效果",
				"showtype" : "select",
				"value" : [ {
					"label" : "淡入",
					"value" : "fade",
					"selected" : true
				}, {
					"label" : "翻转",
					"value" : "flip",
					"selected" : false
				}, {
					"label" : "抛出",
					"value" : "flow",
					"selected" : false
				}, {
					"label" : "弹出",
					"value" : "pop",
					"selected" : false
				}, {
					"label" : "从右到左滑动",
					"value" : "slide",
					"selected" : false
				}, {
					"label" : "从右到左滑动并淡入",
					"value" : "slidefade",
					"selected" : false
				}, {
					"label" : "从下到上滑动",
					"value" : "slideup",
					"selected" : false
				}, {
					"label" : "从上到下滑动",
					"value" : "slidedown",
					"selected" : false
				}, {
					"label" : "无效果",
					"value" : "none",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'data-transition\');"
			}
		}//no_type
	}
}
NoButtonEditor.prototype = new Meditor();
function OneLineTwoColumnEditor(id, realId) {
			this.createTag = function() {
				
				var labelAttrs = new Meditor()
						.parseJsonAttrs(new LabelEditor().contents['label_type']);
				var showLabelAttrs = new Meditor()
						.parseJsonAttrs(new ShowLabelEditor().contents['show_label_type']);
				var oltcRealId = realId;
				var lRealId = realId + 1;
				var sRealId = realId + 2;

				var html = '<label id="'
						+ oltcRealId
						+ '" name="'
						+ oltcRealId
						+ '">'
						+ '<span class="details_title_label"><label id="'
						+ lRealId
						+ '" name="label_type_'
						+ lRealId
						+ '" '
						+ labelAttrs.attrs
						+ '>文件标题:</label></span>'
						+ '<span class="details_text"><label class="label_lineheight" id="'
						+ sRealId + '" name="show_label_type_' + sRealId + '" '
						+ showLabelAttrs.attrs + '>测试标题</label></span>'
						+ '</label>';
				return html;
			}, this.contents = {
				"onelinetwocolumn_type" : {
					"attrTitle" : "一行两列属性",
					"allowed" : [ "style" ,"hidding"],
					"id" : {
						"id" : "idId",
						"type" : "text",
						"label" : "ID / Name",
						"value" : "",
						"style" : "display:none"
					},
					"name" : {
						"id" : "nameId",
						"type" : "text",
						"label" : "Name",
						"value" : "",
						"style" : "display:none"
					},
					"style" : {
						"id" : "styleId",
						"type" : "text",
						"label" : "自定义样式",
						"value" : "width:100%; border-top:1px solid #ddd;",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');"
					},
					"hidding" : {
						"id" : "hiddingId",
						"type" : "select",
						"label" : "是否默认隐藏",
						"value" : [ {
							"label" : "是",
							"value" : "true",
							"selected" : false
						}, {
							"label" : "否",
							"value" : "false",
							"selected" : true
						} ],
						"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
					}
				}// column
		}
}
OneLineTwoColumnEditor.prototype = new Meditor();
function OneLineColumnsEditor(id, realId) {
	this.createTag = function() {
		var labelAttrs = new Meditor()
		.parseJsonAttrs(new LabelEditor().contents['label_type']);
		var showLabelAttrs = new Meditor()
		.parseJsonAttrs(new ShowLabelEditor().contents['show_label_type']);
		var attr = new Meditor().parseJsonAttrs(this.contents['onelinecolumns_type']);
		var oltcRealId = realId;
		var lRealId = realId + 1;
		var rRealId = realId + 2;
		var html = '<div id="onelinecolumns_type_'+oltcRealId+'" name="onelinecolumns_type_'+oltcRealId+'" '+attr.attrs+'>'+
					'<div style="float: left">'+
					'<label id="idId_'+lRealId+'" name="label_type_idId_'+lRealId+'" '+labelAttrs.attrs+' style="width: 100%;">发稿人：</label>'+
					'</div>'+
					'<div style="float: right; width: 60%;" class="div_li">'+
					'<ul>'+
					'<li><label class="label_lineheight" '+
					' id="idId_'+rRealId+ '" name="show_label_type_idId_'+rRealId+'" '+showLabelAttrs.attrs+ ' >测试标题</label></li></ul></div></div>';
		return html;
	}, this.contents = {
			"onelinecolumns_type" : {
				"attrTitle" : "一行两列属性",
				"allowed" : [ "style" ,"hidding","settings","columnnum"],
				"id" : {
					"id" : "idId",
					"type" : "text",
					"label" : "ID / Name",
					"value" : "",
					"style" : "display:none"
				},
				"name" : {
					"id" : "nameId",
					"type" : "text",
					"label" : "Name",
					"value" : "",
					"style" : "display:none"
				},
				"style" : {
					"id" : "styleId",
					"type" : "text",
					"label" : "自定义样式",
					"value" : "width:100%; border-top:1px solid #f7f7f7;",
					"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
					"style":"display:none"
				},
				"hidding" : {
					"id" : "hiddingId",
					"type" : "select",
					"label" : "是否默认隐藏",
					"value" : [ {
						"label" : "是",
						"value" : "true",
						"selected" : false
					}, {
						"label" : "否",
						"value" : "false",
						"selected" : true
					} ],
					"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
				},"columnnum" : {
					"id" : "columnnumId",
					"type" : "text",
					"label" : "行数",
					"value" : ""
				},
				"settings" : {
						"id" : "settingsId",
						"type" : "button",
						"value" : "设置",
						"onclick" : "Cominteraction.addColumnsLabel();"
				}
			}// column
	}
}
OneLineColumnsEditor.prototype = new Meditor();
function RadioEditor(com_type, realId) {
	this.createTag = function() {
		var label = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = "<fieldset id=\"radioField\" data-role=\"controlgroup\" data-type=\"horizontal\">"+
					"<label for=\"male\">单选</label><input "+ label.attrs + " type=\"radio\" name=\"gender\" id=\"male\" value=\"male\">"+
		"<label for=\"female\">单选</label><input "+ label.attrs + " type=\"radio\" name=\"gender\" id=\"female\" value=\"female\"></fieldset>";
		return html;
	}, this.contents = {
		"radio_type" : {
			"attrTitle" : "单选属性",
			"allowed" : ["auth_radio","hidding",],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"auth_radio" : {
				"id" : "auth_radioId",
				"type" : "text",
				"label" : "字段名称",
				"value" : "true",
				"style" : "display:none"
			},
			"hidding" : {
				"id" : "hiddingId",
				"type" : "select",
				"label" : "是否默认隐藏",
				"value" : [ {
					"label" : "是",
					"value" : "true",
					"selected" : false
				}, {
					"label" : "否",
					"value" : "false",
					"selected" : true
				} ],
				"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
			}
		}
	//label
	}
}
RadioEditor.prototype = new Meditor();
function SelectEditor(com_type, realId) {
	this.createTag = function() {
		var label = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = "<fieldset  data-role=\"fieldcontain\"><label id=\""+realId+"\">选择</label>"+
        			"<select "+label.attrs+" name=\""+com_type+"_"+(realId+1)+"\" id=\""+(realId+1)+"\" data-native-menu=\"false\">"+
        			"<option value=\"mon\">选项一</option>"+
        			"<option value=\"tue\">选项二</option>"+
        			"</select>"+
        			"</fieldset>";
		return html;
	}, this.contents = {
		"select_type" : {
			"attrTitle" : "单选属性",
			"allowed" : ["auth_select","hidding"],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"auth_select" : {
				"id" : "auth_selectId",
				"type" : "text",
				"label" : "字段名称",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_select\');"
			},
			"hidding" : {
				"id" : "hiddingId",
				"type" : "select",
				"label" : "是否默认隐藏",
				"value" : [ {
					"label" : "是",
					"value" : "true",
					"selected" : false
				}, {
					"label" : "否",
					"value" : "false",
					"selected" : true
				} ],
				"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
			}
		}
	//label
	}
}
SelectEditor.prototype = new Meditor();
function ShowLabelEditor(com_type, realId) {
	this.createTag = function() {
		var showLabel = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<label  id="' + realId + '"' + showLabel.attrs + ' name="'+com_type+"_"
				+ realId + '">' + '显示字段值' + '</label>'
				+ '';
		/*
		 * var st = sessionStorage.html; if (st!=undefined&&st!='undefined'){
		 * sessionStorage.html =
		 * "\""+realId+"\""+":"+"{"+"\"tagName\":\"label\""+","+"\"id\":\""+realId+"\""+","+"\"name\":\""+realId+"\""+","+"\"value\":\"\""+","+jsonAttrs+"}"+","+st;
		 * }else{
		 * sessionStorage.html="\""+realId+"\""+":"+"{"+"\"tagName\":\"label\""+","+"\"id\":\""+realId+"\""+","+"\"name\":\""+realId+"\""+","+"\"value\":\"\""+","+jsonAttrs+"}"; }
		 */
		// var reg=new RegExp("\"","g"); //创建正则RegExp对象
		// var hs = html.replace(reg,"\\\"");
		// sessionStorage.html="{\""+realId+"\":\""+hs+"\"}";
		return html;
	},this.contents = {
				"show_label_type" : {
					"attrTitle" : "显示文本属性",
					"allowed" : [ "auth_data", "auth_range", "style","hidding"],
					"id" : {
						"id" : "idId",
						"type" : "text",
						"label" : "ID / Name",
						"value" : "",
						"style" : "display:none"
					},
					"name" : {
						"id" : "nameId",
						"type" : "text",
						"label" : "Name",
						"value" : "",
						"style" : "display:none"
					},
					"value" : {
						"id" : "valueId",
						"type" : "text",
						"label" : "字段名称",
						"value" : "字段名称",
						"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'value\');",
						"style" : "display:none"
					},
					"auth_range" : {
						"id" : "auth_rangeId",
						"type" : "auth_range",
						"label" : "值域范围",
						"showtype" : "select",
						"value" : [ {
							"label" : "全局级",
							"value" : "system",
							"selected" : false
						}, {
							"label" : "页面级",
							"value" : "page",
							"selected" : false
						}, {
							"label" : "协议级",
							"value" : "protocol",
							"selected" : true
						} ],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_range\');"
					},
					"auth_data" : {
						"id" : "auth_dataId",
						"type" : "auth_data",
						"label" : "绑定ID",
						"value" : "",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_data\');"
					},
					"style" : {
						"id" : "styleId",
						"type" : "text",
						"label" : "自定义样式",
						"value" : "",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
						"style" : "display:none"
					},
					"font-size" : {
						"id" : "font-sizeId",
						"type" : "digitAddMinus",
						"label" : "字体大小",
						"value" : "",
						"attrName" : "font-size"
					},
					"hidding" : {
						"id" : "hiddingId",
						"type" : "select",
						"label" : "是否默认隐藏",
						"value" : [ {
							"label" : "是",
							"value" : "true",
							"selected" : false
						}, {
							"label" : "否",
							"value" : "false",
							"selected" : true
						} ],
						"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
					}
				}// show_label_type
			}// contents
}
ShowLabelEditor.prototype = new Meditor();
function SubmitButtonEditor(com_type, realId) {
	this.createTag = function() {
		var submitBtn = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<a id="' + realId + '"' + submitBtn.attrs + ' name="'
				+ 'submitbtn_type_' + realId + '">' + '提交' + '</a>'
				+ '';
		return html;
	}, this.contents = {
		"submitbtn_type" : {
			"attrTitle" : "按钮属性",
			"allowed" : [ "button_text", "auth_action", "auth_da_text",
					"data-role", "data-transition" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "按钮名称",
				"value" : "按钮",
				"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'value\');",
				"style" : "display:none"
			},
			"auth_action" : {
				"id" : "auth_actionId",
				"type" : "auth_action",
				"label" : "跳转方式",
				"showtype" : "select",
				"value" : [ {
					"label" : "弹出框",
					"value" : "openDialog",
					"selected" : true
				}, {
					"label" : "新页面",
					"value" : "openNewPage",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_action\');"
			},
			"auth_da_text" : {
				"id" : "auth_da_textId",
				"type" : "auth_da_text",
				"label" : "跳转页面",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\');"
			},
			"data-role" : {
				"id" : "data-roleId",
				"type" : "data-role",
				"label" : "执行内容",
				"value" : "button",
				"style" : "display:none"
			},
			"data-transition" : {
				"id" : "data-transitionId",
				"type" : "data-transition",
				"label" : "执行效果",
				"showtype" : "select",
				"value" : [ {
					"label" : "淡入",
					"value" : "fade",
					"selected" : true
				}, {
					"label" : "翻转",
					"value" : "flip",
					"selected" : false
				}, {
					"label" : "抛出",
					"value" : "flow",
					"selected" : false
				}, {
					"label" : "弹出",
					"value" : "pop",
					"selected" : false
				}, {
					"label" : "从右到左滑动",
					"value" : "slide",
					"selected" : false
				}, {
					"label" : "从右到左滑动并淡入",
					"value" : "slidefade",
					"selected" : false
				}, {
					"label" : "从下到上滑动",
					"value" : "slideup",
					"selected" : false
				}, {
					"label" : "从上到下滑动",
					"value" : "slidedown",
					"selected" : false
				}, {
					"label" : "无效果",
					"value" : "none",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'data-transition\');"
			}
		}//button	
	}
}
SubmitButtonEditor.prototype = new Meditor();
function TableLabelEditor(com_type, realId) {
	this.createTag = function() {
		var label = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<label  id="' + realId + '" ' + label.attrs + ' name="'
		+com_type+"_"+ realId + '">' + this.contents[com_type]['value']['label'] + '</label>'
				+ '';
		return html;
	}, this.contents = {
		"tablelabel_type" : {
			"attrTitle" : "文本属性",
			"allowed" : [ "style","columnname"],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"value" : {
				"id" : "valueId",
				"type" : "text",
				"label" : "字段名称",
				"value" : "",
				"onkeyup" : "Cominteraction.changeLabelTextByColumn(this,\'value\');",
				"style" : "display:none"
			},
			"columnname" : {
				"id" : "columnnameId",
				"type" : "text",
				"label" : "列绑定ID",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'columnname\');",
			},
			"style" : {
				"id" : "styleId",
				"type" : "text",
				"label" : "自定义样式",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
				"style" : "display:none"
			},
			"font-size" : {
				"id" : "font-sizeId",
				"type" : "digitAddMinus",
				"label" : "字体大小",
				"value" : "",
				"attrName" : "font-size"
			}
		}
	//label
	}
}
TableLabelEditor.prototype = new Meditor();
function TextareaEditor(com_type, realId) {
			this.createTag = function() {
				var textarea = new Meditor().parseJsonAttrs(this.contents[com_type]);
				var html = '<textarea  id="' + realId + '" name="' +com_type+"_"+ realId
						+ '"' + textarea.attrs
						+ ' ></textarea>';
				return html;
			}, this.contents = {
				"textarea_type" : {
					"attrTitle" : "多行文本属性",
					"allowed" : [ "value", "auth_data", "auth_range","style","color","isdisabled","isrequired","auth_isfill",
									"hidding"],
					"id" : {
						"id" : "idId",
						"type" : "text",
						"label" : "ID / Name",
						"value" : "",
						"style" : "display:none"
					},
					"name" : {
						"id" : "nameId",
						"type" : "text",
						"label" : "Name",
						"value" : "",
						"style" : "display:none"
					},
					"label" : {
						"id" : "labelId",
						"type" : "text",
						"label" : "名称",
						"value" : "Text Input",
						"style" : "display:none"
					},
					"value" : {
						"id" : "valueId",
						"type" : "textarea",
						"label" : "默认值",
						"value" : "t",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'value\');"
					},
					"auth_range" : {
						"id" : "auth_rangeId",
						"type" : "auth_range",
						"label" : "值域范围",
						"showtype" : "select",
						"value" : [ {
							"label" : "全局级",
							"value" : "system",
							"selected" : false
						}, {
							"label" : "页面级",
							"value" : "page",
							"selected" : false
						}, {
							"label" : "协议级",
							"value" : "protocol",
							"selected" : true
						} ],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_range\');"
					},
					"isdisabled" : {
						"id" : "isdisabledId",
						"type" : "select",
						"label" : "是否可用",
						"value" : [ {
							"label" : "是",
							"value" : "0",
							"selected" : true
						}, {
							"label" : "否",
							"value" : "1",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'isdisabled\');"
					},
					"isrequired" : {
						"id" : "isrequiredId",
						"type" : "select",
						"label" : "是否必填",
						"value" : [ {
							"label" : "是",
							"value" : "0",
							"selected" : true
						}, {
							"label" : "否",
							"value" : "1",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'isrequired\');"
					},
					"auth_isfill" : {
						"id" : "auth_isfillId",
						"type" : "auth_isfill",
						"label" : "是否被填充数据",
						"showtype" : "select",
						"value" : [ {
							"label" : "是",
							"value" : "true",
							"selected" : true
						}, {
							"label" : "否",
							"value" : "false",
							"selected" : false
						} ],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_isfill\');"
					},
					"auth_data" : {
						"id" : "auth_dataId",
						"type" : "auth_data",
						"label" : "绑定ID",
						"value" : "",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_data\');"
					},
					"style" : {
						"id" : "styleId",
						"type" : "text",
						"label" : "自定义样式",
						"value" : "",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
						"style" : "display:none"
					},
					"color" : {
						"id" : "colorId",
						"type" : "select",
						"label" : "字体颜色",
						"value" : [ {
							"label" : "默认",
							"value" : "",
							"selected" : true
						}, {
							"label" : "蓝色",
							"value" : "#4fc1e9!important",
							"selected" : false
						}],
						"attrName" : "color",
						"onchange" : "Cominteraction.changeStyleByColumn(this,\'color\');"
					},
					"hidding" : {
						"id" : "hiddingId",
						"type" : "select",
						"label" : "是否默认隐藏",
						"value" : [ {
							"label" : "是",
							"value" : "true",
							"selected" : false
						}, {
							"label" : "否",
							"value" : "false",
							"selected" : true
						} ],
						"onchange" : "Cominteraction.changeHiddingByColumn(this,\'hidding\');"
					}
				}//textarea	
			}
}
TextareaEditor.prototype = new Meditor();
function TwoButtonEditor(id,realId) {
	this.createTag = function() {
		var twoBtn = new Meditor().parseJsonAttrs(new ButtonEditor().contents['button_type']);
		var sureRealId = realId;
		var cancelRealId = realId+1;
		var html ='<div class="page_details_content_list">'+
        '<div class="ui-grid-a">'+
            '<div class="ui-block-a">'+
              '<a id="'
			+sureRealId +'"'+' name="' +'button_type_'+sureRealId +'"'+twoBtn.attrs+'>确&nbsp;定</a>'+
            '</div>'+
            '<div class="ui-block-b">'+
            	'<a id="'+cancelRealId +'"'+' name="' +'button_type_'+cancelRealId +'"'+twoBtn.attrs+'>取&nbsp;消</a>'+
            '</div>'+
         '</div>'+
     '</div>';
		/*var st = sessionStorage.html;
		if (st!=undefined&&st!='undefined'){
			sessionStorage.html = "\""+sureRealId+"\""+":"+"{"+"\"tagName\":\"a\""+","+"\"id\":\""+sureRealId+"\""+","+"\"name\":\"button_type_"+sureRealId+"\""+","+"\"text\":\"\""+","+jsonAttrs+"}"+",";
			sessionStorage.html += "\""+cancelRealId+"\""+":"+"{"+"\"tagName\":\"a\""+","+"\"id\":\""+cancelRealId+"\""+","+"\"name\":\"button_type_"+cancelRealId+"\""+","+"\"text\":\"\""+","+jsonAttrs+"}"+","+st;
		}else{
			sessionStorage.html="\""+sureRealId+"\""+":"+"{"+"\"tagName\":\"a\""+","+"\"id\":\""+sureRealId+"\""+","+"\"name\":\"button_type_"+sureRealId+"\""+","+"\"text\":\"\""+","+jsonAttrs+"}"+",";
			sessionStorage.html+="\""+cancelRealId+"\""+":"+"{"+"\"tagName\":\"a\""+","+"\"id\":\""+cancelRealId+"\""+","+"\"name\":\"button_type_"+cancelRealId+"\""+","+"\"text\":\"\""+","+jsonAttrs+"}";
		}*/
		return html;
	}
}
TwoButtonEditor.prototype = new Meditor();
function YesButtonEditor(com_type, realId) {
	this.createTag = function() {
		var yesBtn = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<a id="' + realId + '"' + yesBtn.attrs + ' name="'
				+ 'yes_type_' + realId + '">' + '报【主管领导审核】' + '</a>'
				+ '';
		return html;
	}, this.contents = {
		"yes_type" : {
			"attrTitle" : "同意属性",
			"allowed" : [ "style", "button_text", "data-role",
					"data-transition" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "链接名称",
				"value" : "同意",
				"style" : "display:none"
			},
			"name" : {
				"id" : "nameId",
				"type" : "text",
				"label" : "Name",
				"value" : "",
				"style" : "display:none"
			},
			"style" : {
				"id" : "styleId",
				"type" : "text",
				"label" : "自定义样式",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');"
			},
			"data-role" : {
				"id" : "data-roleId",
				"type" : "data-role",
				"label" : "执行内容",
				"value" : "button",
				"style" : "display:none"
			},
			"data-transition" : {
				"id" : "data-transitionId",
				"type" : "data-transition",
				"label" : "执行效果",
				"showtype" : "select",
				"value" : [ {
					"label" : "淡入",
					"value" : "fade",
					"selected" : true
				}, {
					"label" : "翻转",
					"value" : "flip",
					"selected" : false
				}, {
					"label" : "抛出",
					"value" : "flow",
					"selected" : false
				}, {
					"label" : "弹出",
					"value" : "pop",
					"selected" : false
				}, {
					"label" : "从右到左滑动",
					"value" : "slide",
					"selected" : false
				}, {
					"label" : "从右到左滑动并淡入",
					"value" : "slidefade",
					"selected" : false
				}, {
					"label" : "从下到上滑动",
					"value" : "slideup",
					"selected" : false
				}, {
					"label" : "从上到下滑动",
					"value" : "slidedown",
					"selected" : false
				}, {
					"label" : "无效果",
					"value" : "none",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'data-transition\');"
			}
		}//yes
	}
}
YesButtonEditor.prototype = new Meditor();