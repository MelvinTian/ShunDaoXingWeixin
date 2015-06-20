function ListEditor(id, realId) {
			this.createTag = function() {
				var html = '<div listid="listid" id=\"'
						+ realId
						+ '\">'
						+ '<div class="ui-grid-b" >'
						+ '<div class="ui-block-a" style="border-top:1px solid #D8D8D8;border-left:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
						+ (realId + 1)
						+ '" name="'
						+ (realId + 1)
						+ '">标题</label></div>'
						+ '<div class="ui-block-b" style="border-top:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
						+ (realId + 2)
						+ '" name="'
						+ (realId + 2)
						+ '">摘要</label></div>'
						+ '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label class="label_font12" contentEditable="true" id="'
						+ (realId + 3)
						+ '" name="'
						+ (realId + 3)
						+ '">创建</label></div>'
						+ '</div>'
						+ '<div class="ui-grid-b" auth_list="true">'
						+ '<div class="ui-block-a" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-left:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="title" name="show_label_type_title" id="title" >Some Text</label></div>'
						+ '<div class="ui-block-b" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="descp" name="show_label_type_descp" id="descp">Some Text</label></div>'
						+ '<div class="ui-block-c" style="border-top:1px solid #D8D8D8;border-bottom:1px solid #D8D8D8;border-right:1px solid #D8D8D8;"><label auth_range="system" auth_data="createtime" name="show_label_type_createtime" id="createtime">Some Text</label></div>'
						+ '</div>' + '</div>';
				return html;
			}, this.contents = {
				"list_type" : {
					"attrTitle" : "链接属性",
					"allowed" : [ "listid" ],
					"id" : {
						"id" : "idId",
						"type" : "text",
						"label" : "ID / Name",
						"value" : "",
						"readonly" : "readonly",
						"style" : "display:none"
					},
					"listid" : {
						"id" : "listidId",
						"type" : "text",
						"label" : "列表ID",
						"value" : "listid",
						"onkeyup" : "changeListId(this,\'listid\');",
					},
					"columns" : {
						"id" : "columnsId",
						"type" : "select",
						"label" : "列数",
						"value" : [ {
							"label" : "3",
							"value" : "ui-grid-b",
							"selected" : true
						}, {
							"label" : "4",
							"value" : "ui-grid-c",
							"selected" : false
						}, {
							"label" : "5",
							"value" : "ui-grid-d",
							"selected" : false
						} ],
						"onchange" : "changeColumns(this,\'columns\');"
					}
				}//listtype	
			}
}
ListEditor.prototype = new Meditor();