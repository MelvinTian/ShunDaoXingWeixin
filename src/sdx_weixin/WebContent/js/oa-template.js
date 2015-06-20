$(function() {
	$("#tabs div").bind("click", function() {
		var index = $("#tabs div").index($(this)[0]);
		var divs = $(".tab");
		$(this).parent().children("div").attr("class", "tab-nav");
		$(this).attr("class", "tab-nav-action");
		divs.hide();
		divs.eq(index).show();
	});
	$(".cpList", parent.document).find("li").each(function() {
		$(this).on("dragstart", function(ev) {
			var dt = ev.originalEvent.dataTransfer;
			dt.setData('Text', ev.target.id);
		});
	});
	//拖拽
	if ($('#preview').length<=0){
		RFDraggable({destId:".tab",dragTag:"li"});
		$('.phoneScreen').on("mouseenter mouseup", ".highlight", function(event) {
			if (event.type == 'mouseup') { // 鼠标松开时右上角显示删除小图标
				$("<img class='delete' src='images/icon_remove.png'>").appendTo(this);
			}
			if (event.type == 'mouseenter') { // 鼠标进入时禁止控件鼠标事件并显示删除小图标
				$("<img class='delete' src='images/icon_remove.png'>").appendTo(this);
			}
		}).on("mouseleave", ".highlight", function() { // 鼠标离开时控件恢复正常
			$('.delete, .forbidden').remove();
		}).on("mousedown", ".forbidden", function() { // 鼠标按下时右上角小图标消失
			$('.delete').remove();
		}).on("click", ".highlight", function() { // 点击后控件为红色虚线边框选中状态
			$(this).addClass('hover').siblings().removeClass('hover');
		});
	}
	//点击删除事件
	$('.phoneScreen').on("click",".delete",function() {
		var parentLi = $(this).parent();
		$(parentLi).find('*').each(function(){
			$(this).remove();
		});
		$(parentLi).remove();
	});
	
	$(".click_li").click(function(){
		$(this).next('.click_li_div').toggle();
    	$(this).children("i").toggleClass("add_icon");
  	});
});


var outerHtml = function(oh) {
	return $('<div></div>').append(oh.clone()).html();
}

function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	ev.dataTransfer.setData("Text", ev.target.id);
}

function drop(ev) {
	ev.preventDefault();
}