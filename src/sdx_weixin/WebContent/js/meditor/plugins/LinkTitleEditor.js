function LinkTitleEditor(com_type, realId) {
	this.createTag = function() {
		var link = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var labelAttrs = new Meditor()
		.parseJsonAttrs(new ShowLabelEditor().contents['label_type']);
		var showLabelAttrs = new Meditor()
		.parseJsonAttrs(new ShowLabelEditor().contents['show_label_type']);
		
		/*var html = '<a class="listview_a ui-btn ui-btn-icon-right ui-icon-carat-r" id="' + realId + '"' + link.attrs + ' name="'
				+ 'link_type_' + realId + '">' + "<label class='label_lineheight' id="+(realId+1)+" name=show_label_type_"+(realId+1)+">dddd</label>" + '</a>'
				+ '<div class="forbidden"></div>';*/
		var html = '<a href="#" class="listview_a" style="padding: 0;" id="' + realId + '"' + link.attrs +
					' name="link_type_' + realId + '">'+
					'<span class="details_title_label" style="float: left;width:30%">'+
						'<label id="'+(realId+1)+" name=label_type_"+(realId+1)+'" '+labelAttrs.attrs+' >'+
							'<p>标题:</p>'+
						'</label>'+
					'</span>'+
					'<span class="details_text" style="float: left;width:60%;">'+
						'<label class="label_lineheight" id="'+(realId+2)+'" name="show_label_type_'+(realId+2)+'"'+showLabelAttrs.attrs+'>测试标题1</label>'+
					'</span>'+
					'</a>';
		return html;
	}, this.contents = {
		"linktitle_type" : {
			"attrTitle" : "链接属性",
			"allowed" : [ "button_text", "auth_action", "auth_da_text",
					"pages_list" ],
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
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "链接名称",
				"value" : "链接",
				"onkeyup" : "changeLabelTextByColumn(this,\'button_text\');",
			},
			"auth_action" : {
				"id" : "auth_actionId",
				"type" : "auth_action",
				"label" : "执行方式",
				"showtype" : "select",
				"value" : [ {
					"label" : "链接",
					"value" : "auth_href",
					"selected" : true
				}, {
					"label" : "脚本",
					"value" : "auth_script",
					"selected" : false
				}, {
					"label" : "URL",
					"value" : "auth_openpage",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_action\');"
			},
			"auth_da_text" : {
				"id" : "auth_da_textId",
				"type" : "auth_da_text",
				"label" : "执行内容",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\');",
				"style" : "display:none"
			},
			"pages_list" : {
				"id" : "pages_listId",
				"type" : "select",
				"label" : "跳转页面",
				"value" : "",
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\')",
				"style" : "display:block"
			}
		}//link	
	}
}
LinkTitleEditor.prototype = new Meditor();