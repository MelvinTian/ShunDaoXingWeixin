function SubmitButtonEditor(com_type, realId) {
	this.createTag = function() {
		var submitBtn = new Meditor().parseJsonAttrs(this.contents[com_type]);
		var html = '<a id="' + realId + '"' + submitBtn.attrs + ' name="'
				+ 'submitbtn_type_' + realId + '">' + '提交' + '</a>'
				+ '<div class="forbidden"></div>';
		return html;
	}, this.contents = {
		"submitbtn_type" : {
			"attrTitle" : "按钮属性",
			"allowed" : [ "button_text", "auth_action", "auth_da_text",
					"data-role", "data-transition" ],
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
				"label" : "按钮名称",
				"value" : "按钮",
				"onkeyup" : "changeLabelTextByColumn(this,\'value\');",
				"style" : "display:none"
			},
			"auth_action" : {
				"id" : "auth_actionId",
				"type" : "auth_action",
				"label" : "跳转方式",
				"showtype" : "select",
				"value" : [ {
					"label" : "弹出框",
					"value" : "openDialog",
					"selected" : true
				}, {
					"label" : "新页面",
					"value" : "openNewPage",
					"selected" : false
				} ],
				"onchange" : "Cominteraction.changeTextByColumn(this,\'auth_action\');"
			},
			"auth_da_text" : {
				"id" : "auth_da_textId",
				"type" : "auth_da_text",
				"label" : "跳转页面",
				"value" : "",
				"onkeyup" : "Cominteraction.changeTextByColumn(this,\'auth_da_text\');"
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
		}//button	
	}
}
SubmitButtonEditor.prototype = new Meditor();