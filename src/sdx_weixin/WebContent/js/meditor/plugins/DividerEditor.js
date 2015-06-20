function DividerEditor(com_type, realId) {
	this.createTag = function() {
		var divider = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div id="' + realId + '"' + divider.attrs + ' name="'
				+ 'divider_type_' + realId + '"></div>';
		return html;
	}, this.contents = {
		"divider_type" : {
			"attrTitle" : "分隔符属性",
			"allowed" : [ "style","hidding" ],
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
				"value" : "width:100%; margin: 3px 0;border-top:1px solid #ddd;",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');"
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
		}//divider	
	}
}
DividerEditor.prototype = new Meditor();