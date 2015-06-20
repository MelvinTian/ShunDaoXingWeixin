function RFDraggable(options){
	var options = options||{};
	//console.log(options);
	var tag = options.dragTag||"LI";
	tag = tag.toUpperCase();
	var $dest = $(options.destId);
	$dest.each(function(){
		$(this).css("min-height","335px");
	});
	$dest.on("dragover", function(ev) {
		ev.preventDefault();
	});
	$dest.on("drop", function(ev) {
		ev.preventDefault();
		var df = ev.originalEvent.dataTransfer;
		var data = df.getData("Text");
		var el = ev.target;
		var ctx = $(this).get(0);
		var dataName = ev.originalEvent.dataTransfer.getData("Text");
		console.log(dataName);
		if (data=="tablelabel_type"){
			var data = ev.originalEvent.dataTransfer.getData("Text");
			var time = new Date().getTime();
			var realId = "idId_" + new String(time);
			var config = {};
			config = new MeditorConfig(config);
			var plugins = config.plugins;
			var html = '';
			$.each(plugins, function(i, n) {
				for ( var key in n) {
					var pObj = eval(n[key]);
					if ($.trim(data) == key) {
						var editor = new pObj(data, realId);
						html = Meditor.create(editor);
						break;
					}
				}
			});
			if (ev.target.tagName.toLowerCase() == 'label') {
				$('#' + ev.target.id).replaceWith(html);
			} else if (ev.target.tagName.toLowerCase() == 'p') {
				var td = $(ev.target).parents('td');
				$(td).empty();
				$(td).append(html);
			} else if (ev.target.tagName.toLowerCase() == 'td') {
				ev.target.innerHTML = html;
			}
			ev.stopPropagation(); // 阻止事件冒泡
		}else if (dataName=="header_type"||dataName=="headerleftbutton_type"){
			var litem = document.createElement('li');
			var item = $(litem);
			var html = MoblieEditor.buildHtml(dataName, item);
			$('.p_header').append(html);
			$.mobile.pageContainer.trigger("create");
		}else if (dataName=="footer_type"||dataName=="footerrightbutton_type"){
			var litem = document.createElement('li');
			var item = $(litem);
			var html = MoblieEditor.buildHtml(dataName, item);
			$('.p_footer').append(html);
			$.mobile.pageContainer.trigger("create");
		}else{
			do {
				var litem = document.createElement('li');
				var item = $(litem);
				var html = MoblieEditor.buildHtml(dataName, item);
				//console.log(html);
				$(item).html(html);
				if ($(this).children().length>0){
					if(tag === '' || el.nodeName == tag){
						if (el.nextElementSibling){
							el.parentNode.insertBefore($(item).get(0),el.nextElementSibling);
						}else{
							ctx.appendChild($(item).get(0));
						}
					}
				}else{
					ctx.appendChild($(item).get(0));
				}
				$.mobile.pageContainer.trigger("create");
			}
			while(el !== ctx && (el = el.parentNode));
		}
	});
	$dest.sortable();
	return (function(){
		
	})(options);
}
