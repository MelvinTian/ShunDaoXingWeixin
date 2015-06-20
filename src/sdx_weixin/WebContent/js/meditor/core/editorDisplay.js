// 设计器样式控制
$(function(){
	var ElementTypes={
			com_type:{"input":["input_type","date_type"],"textarea":"textarea_type",
				"a":["button_type","link_type","listview_type","yes_type","no_type","submitbtn_type","buttonnav_type","linktitle_type"],
				"label":["hidden_type","show_label_type","tablelabel_type","label_type"]},
			getType:function(type){
				if (this.com_type[type]){
					return this.com_type[type];
				}
				return undefined;
			}
	};
	
	$('body #canvas').delegate('label', 'dblclick', function(event) {
		var name = $(this).attr('name');
		var tagName = "label";
		
		var comtype=ElementTypes.getType(tagName);
		if (comtype){
			if (comtype instanceof Array){
				for (var key in comtype){
					if ($(this).parents('li').attr('id')=='hidden_type'){
						Cominteraction.changePro('hidden_type', this);
						break;
					}else{
						if (name.indexOf(comtype[key]) >= 0) {
							Cominteraction.changePro(comtype[key], this);
							break;
						}
					}
				}
			}
		}
		event.stopPropagation(); // 阻止事件冒泡
	});
	document.getElementById('canvas').addEventListener('click',function(e){
		var tagName = e.target.tagName.toLowerCase();
		//var name = $(e.target).parents('li').attr('id');
		var name = $(e.target).attr('name');
		//console.log(tagName+","+name);
		/*if (!name){
			name = $(e.target).attr('name');
		}*/
		var comtype = ElementTypes.getType(tagName);
		if (comtype){
			if (comtype instanceof Array){
				for (var key in comtype){
					if (name&&name.indexOf(comtype[key]) >= 0) {
						Cominteraction.changePro(comtype[key], e.target);
						break;
					}
				}
			}else{
				Cominteraction.changePro(comtype, e.target);
			}
		}
	},false);
	
	$('body #canvas').delegate(
			'div',
			'click',
			function() {
				if ($(this).attr("id") != undefined) {
					if ($(this).attr("listid") != undefined) {
						var obj = {"listid":$(this).attr("listid"),"id":$(this).attr("id")};
						Cominteraction.changeList('list_type', obj);
					}
				}
	});
	$('body #canvas').delegate(
			'table',
			'click',
			function() {
				if ($(this).attr("id") != undefined) {
					if ($(this).attr("listrowid") != undefined){
						var obj = {"listrowid":$(this).attr("listrowid"),"id":$(this).attr("id")};
						Cominteraction.changeList('listrow_type', obj);
					}
				}
	});
	$('body #canvas').delegate(
			'li',
			'click',
			function() {
				if ($(this).attr("id") != undefined
						&& $(this).attr("name") == 'divider_type') {
					var obj = {"id":$(this).attr("id")};
					Cominteraction.changeList('divider_type', obj);
				} else if ($(this).attr("id") != undefined
						&& ($(this).attr("name") == 'header_type' || $(this).attr(
								"name") == 'footer_type')) {
					var obj = $(this).find(":header").first();
					Cominteraction.changePro($(this).attr("id"), $(obj));
				}else if ($(this).attr("id") != undefined
						&&$(this).attr('name')=='select_type'){
					Cominteraction.changePro('select_type', $(this).find("select:first"));
				}else if ($(this).attr("id") != undefined
						&&$(this).attr('name')=='onelinecolumns_type'){
					var obj = {"id":$(this).attr("id")};
					Cominteraction.changePro('onelinecolumns_type', obj);
				}
	});
	
})