function OneLineTwoColumnEditor(id, realId) {
			this.createTag = function() {
				
				var labelAttrs = new Meditor()
						.parseJsonAttrs(new LabelEditor().contents['label_type']);
				var showLabelAttrs = new Meditor()
						.parseJsonAttrs(new ShowLabelEditor().contents['show_label_type']);
				var oltcRealId = realId;
				var lRealId = realId + 1;
				var sRealId = realId + 2;

				var html = '<label id="'
						+ oltcRealId
						+ '" name="'
						+ oltcRealId
						+ '">'
						+ '<span class="details_title_label"><label id="'
						+ lRealId
						+ '" name="label_type_'
						+ lRealId
						+ '" '
						+ labelAttrs.attrs
						+ '>文件标题:</label></span>'
						+ '<span class="details_text"><label class="label_lineheight" id="'
						+ sRealId + '" name="show_label_type_' + sRealId + '" '
						+ showLabelAttrs.attrs + '>测试标题</label></span>'
						+ '</label>';
				return html;
			}, this.contents = {
				"onelinetwocolumn_type" : {
					"attrTitle" : "一行两列属性",
					"allowed" : [ "style" ,"hidding"],
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
						"value" : "width:100%; border-top:1px solid #ddd;",
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
				}// column
		}
}
OneLineTwoColumnEditor.prototype = new Meditor();