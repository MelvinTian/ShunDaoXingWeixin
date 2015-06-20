function ListRowEditor(id, realId) {
			this.createTag = function() {
				var showLabelAttrs = new Meditor()
				.parseJsonAttrs(new ShowLabelEditor().contents['show_label_type']);
				var html = '<table cellpadding="0" auth_list="true" cellspacing="0" listrowid="listrowid" id=\"'+ realId+ '\" class=""><tr><td width="50%" ondrop="drop(event)" ondragover="allowDrop(event)">'+
				'<label class="" id="'
				+ (realId+1) + '" name="show_label_type_' + (realId+1) + '" '
				+ showLabelAttrs.attrs + '>标题</label>'+'</td>'+
				'<td width="50%" ondrop="drop(event)" ondragover="allowDrop(event)">'+
				'<label class="" id="'
				+ (realId+2) + '" name="show_label_type_' + (realId+2) + '" '
				+ showLabelAttrs.attrs + '>测试标题</label>'
				+'</td></tr>'
						+'<tr><td ondrop="drop(event)" ondragover="allowDrop(event)">'+
						'<label class="label_lineheight" id="'
						+ (realId+3) + '" name="label_type_' + (realId+3) + '" '
						+ showLabelAttrs.attrs + '>摘要</label>'+
						'</td><td ondrop="drop(event)" ondragover="allowDrop(event)">'+
						'<label class="label_lineheight" id="'
							+ (realId+4) + '" name="label_type_' + (realId+4) + '" '
							+ showLabelAttrs.attrs + '>测试标题</label>'
						+'</td></tr></table>';
				return html;
			}, this.contents = {
				"listrow_type" : {
					"attrTitle" : "链接属性",
					"allowed" : [ "listrowid","rows","columns","settings","borderColor","borderMargin","hideTd"],
					"id" : {
						"id" : "idId",
						"type" : "text",
						"label" : "ID / Name",
						"value" : "",
						"readonly" : "readonly",
						"style" : "display:none"
					},
					"listrowid" : {
						"id" : "listrowidId",
						"type" : "text",
						"label" : "列表ID",
						"value" : "listrowid",
						"onkeyup" : "Cominteraction.changeListId(this,\'listrowid\');",
					},
					"rows" : {
						"id" : "rowsId",
						"type" : "text",
						"label" : "行数",
						"value" : ""/*,
						"onkeyup" : "changeRows(this,\'rows\');"*/
					},
					"columns" : {
						"id" : "columnsId",
						"type" : "text",
						"label" : "列数",
						"value" : ""/*,
						"onkeyup" : "changeColumns(this,\'columns\');"*/
					},
					"settings" : {
						"id" : "settingsId",
						"type" : "button",
						"value" : "设置",
						"onclick" : "Cominteraction.changeRowColumns(this,\'rows\',\'columns\');"
					},
					"titleBgColor" : {
						"id" : "titleBgColorId",
						"type" : "select",
						"label" : "标题背景",
						"value" :  [ {
							"label" : "默认",
							"value" : "",
							"selected" : true
						}, {
							"label" : "蓝色",
							"value" : "bg_bluecolor",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTableTitle(this,\'titleBgColor\');"
					},
					"borderColor" : {
						"id" : "borderColorId",
						"type" : "select",
						"label" : "边框颜色",
						"value" :  [ {
							"label" : "默认",
							"value" : "",
							"selected" : true
						}, {
							"label" : "无",
							"value" : "bd_none",
							"selected" : false
						}, {
							"label" : "红色",
							"value" : "bd_red",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTableBorder(this,\'borderColor\');"
					},
					"borderMargin" : {
						"id" : "borderMarginId",
						"type" : "select",
						"label" : "是否有边距",
						"value" :  [ {
							"label" : "是",
							"value" : "1",
							"selected" : true
						}, {
							"label" : "否",
							"value" : "2",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTableMargin(this,\'borderMargin\');"
					},
					"hideTd" : {
						"id" : "hideTdId",
						"type" : "text",
						"label" : "隐藏列",
						"value" : "",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'hideTd\');",
						"style" : "display:none"
					},
					"setvalue" : {
						"id" : "setvalueId",
						"type" : "select",
						"label" : "隐藏列",
						"value" :  [  {
							"label" : "无",
							"value" : "",
							"selected" : true
						},{
							"label" : "第1列",
							"value" : "1",
							"selected" : false
						},{
							"label" : "前两列",
							"value" : "1,2",
							"selected" : false
						},{
							"label" : "前三列",
							"value" : "1,2,3",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeHiddenColumn(this,\'borderMargin\');"
					}
				}//listtype	
			}
}
ListRowEditor.prototype = new Meditor();