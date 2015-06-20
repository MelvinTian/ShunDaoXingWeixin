function YesButtonEditor(com_type, realId) {
	this.createTag = function() {
		var yesBtn = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<a id="' + realId + '"' + yesBtn.attrs + ' name="'
				+ 'yes_type_' + realId + '">' + '报【主管领导审核】' + '</a>'
				+ '<div class="forbidden"></div>';
		return html;
	}, this.contents = {
		"yes_type" : {
			"attrTitle" : "同意属性",
			"allowed" : [ "style", "button_text", "data-role",
					"data-transition" ],
			"id" : {
				"id" : "idId",
				"type" : "text",
				"label" : "ID / Name",
				"value" : "",
				"style" : "display:none"
			},
			"button_text" : {
				"id" : "button_textId",
				"type" : "text",
				"label" : "链接名称",
				"value" : "同意",
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
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'style\');"
			},
			"data-role" : {
				"id" : "data-roleId",
				"type" : "data-role",
				"label" : "执行内容",
				"value" : "button",
				"style" : "display:none"
			},
			"data-transition" : {
				"id" : "data-transitionId",
				"type" : "data-transition",
				"label" : "执行效果",
				"showtype" : "select",
				"value" : [ {
					"label" : "淡入",
					"value" : "fade",
					"selected" : true
				}, {
					"label" : "翻转",
					"value" : "flip",
					"selected" : false
				}, {
					"label" : "抛出",
					"value" : "flow",
					"selected" : false
				}, {
					"label" : "弹出",
					"value" : "pop",
					"selected" : false
				}, {
					"label" : "从右到左滑动",
					"value" : "slide",
					"selected" : false
				}, {
					"label" : "从右到左滑动并淡入",
					"value" : "slidefade",
					"selected" : false
				}, {
					"label" : "从下到上滑动",
					"value" : "slideup",
					"selected" : false
				}, {
					"label" : "从上到下滑动",
					"value" : "slidedown",
					"selected" : false
				}, {
					"label" : "无效果",
					"value" : "none",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'data-transition\');"
			}
		}//yes
	}
}
YesButtonEditor.prototype = new Meditor();