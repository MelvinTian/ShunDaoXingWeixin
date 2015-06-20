var MoblieEditor ={
		buildHtml:function(id,item){
			var html='';
			var time = new Date().getTime();
			var realId = "idId_"+new String(time);
			item.clone().insertAfter(item);
			$(item).removeClass();
			$(item).addClass('highlight');
			$(item).attr("itemId", time);
			$(item).attr("name", id);
			//$(item).attr("contentEditable", "true");
			var config = {};
			config = new MeditorConfig(config);
			var specialclass = config.specialclass;
			$.each(specialclass,function(i,n){
				for (var key in n){
					var pObj=n[key];
					if (id==key){
						for (var i=0;i<pObj.length;i++){
							$(item).addClass(pObj[i]);
						}
						break;
					}
				}
			});
			var plugins = config.plugins;
			$.each(plugins,function(i,n){
				for (var key in n){
					var pObj=eval(n[key]);
					if (id==key){
						var editor = new pObj(id,realId);
						html = Meditor.create(editor);
						break;
					}
				}
			});
			return html;
		}
}