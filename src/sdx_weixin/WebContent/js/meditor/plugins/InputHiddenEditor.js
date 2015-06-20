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