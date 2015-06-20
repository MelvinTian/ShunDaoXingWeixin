function DateEditor(com_type, realId) {
	this.createTag = function() {
		var dateTag = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<input class="datetype" readonly="readonly" ' + dateTag.attrs + ' id="'
				+ realId + '" name="" type="text" />';
		return html;
	}, this.contents = {
		"date_type" : {
			"attrTitle" : "日期选择属性",
			"allowed" : [ "value", "auth_data" ,"hidding","auth_isfill",
							"auth_isvalue", "auth_range"],
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
			"value" : {
				"id" : "valueId",
				"type" : "text",
				"label" : "默认值",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'value\');"
			},
			"auth_data" : {
				"id" : "auth_dataId",
				"type" : "auth_data",
				"label" : "绑定ID",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_data\');"
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
			}
		}// date_type
	}
}
DateEditor.prototype = new Meditor();