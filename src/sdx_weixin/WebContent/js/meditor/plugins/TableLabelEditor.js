function TableLabelEditor(com_type, realId) {
	this.createTag = function() {
		var label = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<label  id="' + realId + '" ' + label.attrs + ' name="'
		+com_type+"_"+ realId + '">' + this.contents[com_type]['value']['label'] + '</label>'
				+ '<div class="forbidden"></div>';
		return html;
	}, this.contents = {
		"tablelabel_type" : {
			"attrTitle" : "文本属性",
			"allowed" : [ "style","columnname"],
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
				"value" : "",
				"onkeyup" : "changeLabelTextByColumn(this,\'value\');",
				"style" : "display:none"
			},
			"columnname" : {
				"id" : "columnnameId",
				"type" : "text",
				"label" : "列绑定ID",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'columnname\');",
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
			}
		}
	//label
	}
}
TableLabelEditor.prototype = new Meditor();