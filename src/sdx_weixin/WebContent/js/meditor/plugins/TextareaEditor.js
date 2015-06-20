function TextareaEditor(com_type, realId) {
			this.createTag = function() {
				var textarea = new Meditor().parseJsonAttrs(this.contents[com_type]);
				var html = '<textarea  id="' + realId + '" name="' +com_type+"_"+ realId
						+ '"' + textarea.attrs
						+ ' ></textarea><div class="forbidden"></div>';
				return html;
			}, this.contents = {
				"textarea_type" : {
					"attrTitle" : "多行文本属性",
					"allowed" : [ "value", "auth_data", "auth_range","style","color","isdisabled","isrequired","auth_isfill",
									"hidding"],
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
					"label" : {
						"id" : "labelId",
						"type" : "text",
						"label" : "名称",
						"value" : "Text Input",
						"style" : "display:none"
					},
					"value" : {
						"id" : "valueId",
						"type" : "textarea",
						"label" : "默认值",
						"value" : "t",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'value\');"
					},
					"auth_range" : {
						"id" : "auth_rangeId",
						"type" : "auth_range",
						"label" : "值域范围",
						"showtype" : "select",
						"value" : [ {
							"label" : "全局级",
							"value" : "system",
							"selected" : false
						}, {
							"label" : "页面级",
							"value" : "page",
							"selected" : false
						}, {
							"label" : "协议级",
							"value" : "protocol",
							"selected" : true
						} ],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_range\');"
					},
					"isdisabled" : {
						"id" : "isdisabledId",
						"type" : "select",
						"label" : "是否可用",
						"value" : [ {
							"label" : "是",
							"value" : "0",
							"selected" : true
						}, {
							"label" : "否",
							"value" : "1",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'isdisabled\');"
					},
					"isrequired" : {
						"id" : "isrequiredId",
						"type" : "select",
						"label" : "是否必填",
						"value" : [ {
							"label" : "是",
							"value" : "0",
							"selected" : true
						}, {
							"label" : "否",
							"value" : "1",
							"selected" : false
						}],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'isrequired\');"
					},
					"auth_isfill" : {
						"id" : "auth_isfillId",
						"type" : "auth_isfill",
						"label" : "是否被填充数据",
						"showtype" : "select",
						"value" : [ {
							"label" : "是",
							"value" : "true",
							"selected" : true
						}, {
							"label" : "否",
							"value" : "false",
							"selected" : false
						} ],
						"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_isfill\');"
					},
					"auth_data" : {
						"id" : "auth_dataId",
						"type" : "auth_data",
						"label" : "绑定ID",
						"value" : "",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_data\');"
					},
					"style" : {
						"id" : "styleId",
						"type" : "text",
						"label" : "自定义样式",
						"value" : "",
						"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');",
						"style" : "display:none"
					},
					"color" : {
						"id" : "colorId",
						"type" : "select",
						"label" : "字体颜色",
						"value" : [ {
							"label" : "默认",
							"value" : "",
							"selected" : true
						}, {
							"label" : "蓝色",
							"value" : "#4fc1e9!important",
							"selected" : false
						}],
						"attrName" : "color",
						"onchange" : "changeStyleByColumn(this,\'color\');"
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
				}//textarea	
			}
}
TextareaEditor.prototype = new Meditor();