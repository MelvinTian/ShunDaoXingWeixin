function FooterEditor(com_type, realId) {
	this.createTag = function() {
		var footer = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div data-role="footer"><h1 id="' + realId + '"'
				+ footer.attrs + ' name="' +com_type+"_"+ realId + '">' + '页脚' + '</h1>'
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
				"onkeyup" : "changeLabelTextByColumn(this,\'button_text\');"
			}
		}//footer
	}
}
FooterEditor.prototype = new Meditor();