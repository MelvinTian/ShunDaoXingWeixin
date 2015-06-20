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
	        }	
	        attrHtml+=text;
		}
		attrHtml+="</div>";
		$("#contentAttrs").html(attrHtml);
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
		var text='<li '+style+'><label for="'+disInput['id']+'" >'+disInput['label']+'</label><input data-role="none"  id="'+disInput['id']+'"'+' onkeyup="'+disInput['onkeyup']+'" value="'+val+'" type="'+disInput['type']+'" /></li>';
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
		var text="<li><textarea id=\"editor1\" style=\"height: 300px\" name=\"editor1\"></textarea></li>"+
				"<input class='shezhi_btn' data-role=\"none\" type=\"button\" value=\"设置\" "+clc+" />"+
					"<script type=\"text/javascript\">CKEDITOR.replace('editor1');</script>";
		return text;
	},
	parseValue:function(obj){
		var attrs = $(obj).get(0).attributes;
		if (attrs!=undefined){
			for (var i=0;i<attrs.length;i++){
				att=attrs[i];
				if(att.specified){
					if ($('#'+att.name+'Id')){
						if (att.name=='value'){
							$('#'+att.name+"Id").val($(obj).val());
						}else{
							$('#'+att.name+"Id").val(att.value);
						}
					}
				}//specified
			}//for
			var compName = $(obj).parents('li').attr('id');
			var config = {};
			config = new MeditorConfig(config);
			var fillckeditor = config.fillckeditor;
			$.each(fillckeditor,function(i,n){
				if (n==compName){
					CKEDITOR.instances.editor1.setData($(obj).html());
				}
			});
		}else{
			if ($('#listidId')!=undefined&&obj['listid']!=undefined){
				$('#listidId').val(obj['listid']);
			}
			if ($('#listrowidId')!=undefined&&obj['listrowid']!=undefined){
				$('#listrowidId').val(obj['listrowid']);
			}
		}
	}//parseValue
}