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
				"onchange" : "changeHiddingByColumn(this,\'hidding\');"
			}
		}
	//label
	}
}
SelectEditor.prototype = new Meditor();