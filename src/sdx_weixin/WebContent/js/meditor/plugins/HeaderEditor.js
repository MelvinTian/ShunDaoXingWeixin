function HeaderEditor(com_type, realId) {
	this.createTag = function() {
		var header = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div data-role="header"><h1 id="' + realId + '"'
				+ header.attrs + ' name="' +com_type+"_"+ realId + '">' + '页眉' + '</h1>'
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
				"onkeyup" : "changeLabelTextByColumn(this,\'button_text\');"
			}
		}//header	
	}
}
HeaderEditor.prototype = new Meditor();