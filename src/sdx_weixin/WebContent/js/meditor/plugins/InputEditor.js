function InputEditor(com_type, realId) {
	this.createTag = function() {
		var input = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<input readonly="readonly" ' + input.attrs + ' id="'
				+ realId + '" name="" type="text"  />';
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
				//"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_data\');"
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
				"onchange" : "changeHiddingByColumn(this,\'hidding\');"
			}
		}//input	
	}
}
InputEditor.prototype = new Meditor();