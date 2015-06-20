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
			"allowed" : ["auth_radio","hidding"],
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
				"onchange" : "changeHiddingByColumn(this,\'hidding\');"
			}
		}
	//label
	}
}
RadioEditor.prototype = new Meditor();