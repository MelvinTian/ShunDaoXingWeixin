function HeaderNavEditor(com_type, realId) {
	this.createTag = function() {
		var header = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<div data-role="header" '+ header.attrs +'><h1 id="' + realId + '"'
				+ ' name="' + realId + '">' +'<div data-role="navbar"> <ul><li><a href="#" data-role="button">同意</a></li>  <li><a href="#" data-role="button">拒绝</a></li></ul></div>' + '</h1>'
				+ '</div>';
		return html;
	}, this.contents = {
		"headernav_type" : {
			"attrTitle" : "页眉属性",
			"allowed" : [ "button_text", "style","header-position" ],
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
            "header-position" : {
                "id" : "nav-positionId",
                "type" : "select",
                "label" : "是否固定",
                "value" : [ {
                    "label" : "是",
                    "value" : "fixed",
                    "selected" : true
                }, {
                    "label" : "否",
                    "value" : "none",
                    "selected" : false
                }],
                "onchange" : "Cominteraction.changeTextByColumn(this,\'header-position\');"
            },
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "页眉名称",
				"value" : "字段名称",
				"onkeyup" : "changeLabelTextByColumn(this,\'button_text\');",
                "style" : "display:none"
			}
		}//header	
	}
}
HeaderNavEditor.prototype = new Meditor();