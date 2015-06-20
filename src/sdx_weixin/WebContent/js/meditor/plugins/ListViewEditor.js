function ListViewEditor(com_type, realId) {
			this.createTag = function() {
				var listView = new Meditor().parseJsonAttrs(this.contents[com_type]);
				var html = '<ul data-autodividers="true" data-inset="true" data-shadow="true" id="m_listView_'
						+ realId
						+ '" data-role="listview"><li><a auth_view="true" id="'
						+ realId
						+ '"'
						+ listView.attrs
						+ ' name="'
						+ 'listview_type_'
						+ realId
						+ '">'
						+ "<img id=\"m_listView_img\" src=\"resource/images/defaultImg.png\">"
						+ "<h2 id=\"m_listView_title\">测试</h2>"
						+ "<p><span id=\"m_listView_descp\">收文传阅  张一 </span><span id=\"m_listView_ct\">2014/06/24</span>"
						+ "</p>"
						+ '</a>'
						+ '</li>'
						+ '</ul>'
						+ '<div class="forbidden"></div>';
				return html;
			},
			this.contents = {
				"listview_type" : {
					"attrTitle" : "链接属性",
					"allowed" : [ "listid", "auth_action", "auth_image",
							"auth_da_text", "button_text" ],
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
						"value" : "",
						"readonly" : "readonly",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'listid\');",
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
						"style" : "display:none"
					},
					"auth_image" : {
						"id" : "auth_imageId",
						"type" : "auth_image",
						"label" : "图标名称",
						"value" : ""
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
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\');"
					}
				}//listView
			}
}
ListViewEditor.prototype = new Meditor();