function LabelEditor(com_type, realId) {
	this.createTag = function() {
		var label = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<label  id="' + realId + '" ' + label.attrs + ' name="'
		+com_type+"_"+ realId + '">' + this.contents[com_type]['value']['label'] + '</label>'
				+ '<div class="forbidden"></div>';
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
				"onkeyup" : "changeLabelTextByColumn(this,\'value\');",
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
				"onchange" : "changeHiddingByColumn(this,\'hidding\');"
			}
		}
	//label
	}
}
LabelEditor.prototype = new Meditor();