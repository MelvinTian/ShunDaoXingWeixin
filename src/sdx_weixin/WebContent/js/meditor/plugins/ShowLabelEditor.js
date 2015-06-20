function ShowLabelEditor(com_type, realId) {
	this.createTag = function() {
		var showLabel = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<label  id="' + realId + '"' + showLabel.attrs + ' name="'+com_type+"_"
				+ realId + '">' + '显示字段值' + '</label>'
				+ '<div class="forbidden"></div>';
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
						"onkeyup" : "changeLabelTextByColumn(this,\'value\');",
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
						"onchange" : "changeHiddingByColumn(this,\'hidding\');"
					}
				}// show_label_type
			}// contents
}
ShowLabelEditor.prototype = new Meditor();