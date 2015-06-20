if (!window.MEDITOR) {
	window.MEDITOR = (function() {
		var MEDITOR = {
				basePath : (function(){
					var scripts = document.getElementsByTagName( 'script' );
					var path = '';
					for ( var i = 0; i < scripts.length; i++ ) {
						var match = scripts[ i ].src.match( /(^|.*?[\\\/])(?:_source\/)?core\/meditor.js(?:\?.*)?$/i );
						if ( match ) {
							path = match[ 1 ];
							break;
						}
					}
					return path;
				})()
		}
		return MEDITOR;
	})()
}

function Meditor() {
	this.createTag = function() {

	},
	this.initConfig=function(basePath){
		var headScript = $('head:first');
		var script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'mconfig.js';
		$(headScript).append(script);
		
		script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'core/cominteraction.js';
		$(headScript).append(script);
		
		script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'core/buildCanvas.js';
		$(headScript).append(script);
		
		script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'core/buildAttributions.js';
		$(headScript).append(script);
		
		script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = basePath+'core/editorDisplay.js';
		$(headScript).append(script);
	},
	this.initPlugin=function(basePath){
		var config = {};
		config = new MeditorConfig(config);
		var plugins = config.plugins;
		$.each(plugins,function(i,n){
			var script = document.createElement( 'script' );
			script.type = 'text/javascript';
			for (var key in n){
				var sName = new String(n[key]);
				script.src = basePath+'plugins/'+sName+'.js';
				$('head:first').append(script);
			}
		});
	},
	this.parseJsonAttrs=function(tagJson){
		var jsonAttrs='';
		var attrs = '';
		var obj={};
		var allowed = tagJson['allowed'];//允许使用的属性数组
		for (var index=0;index<allowed.length;index++){
			var allowAttr = allowed[index];//允许使用的属性
			var defaultValue="";
			 if (tagJson[allowAttr]['type']=='select'||tagJson[allowAttr]['showtype']=='select'){
				var values = tagJson[allowAttr]['value'];
				for (var i=0;i<values.length;i++){
					if (values[i]['selected']){
						defaultValue = values[i]['value'];
						break;
					}
				}
			}else{
				defaultValue = tagJson[allowAttr]['value'];
			} 
			attrs+=allowAttr+'="'+defaultValue+'" ';
			if (jsonAttrs==''){
				jsonAttrs="\""+allowAttr+"\":"+"\""+defaultValue+"\"";
			}else{
				jsonAttrs +=","+"\""+allowAttr+"\":"+"\""+defaultValue+"\"";
			}
		}
		obj.attrs =attrs;
		obj.jsonAttrs = jsonAttrs;
		return obj;
	},this.getContent=function(com_type){
		var config = {};
		config = new MeditorConfig(config);
		var content = ''; 
		var plugins = config.plugins;
		$.each(plugins,function(i,n){
			for (var key in n){
				var pObj=eval(n[key]);
				if (com_type==key){
					var editor = new pObj();
					content = editor.contents[com_type];
					break;
				}
			}
		});
		return content;
	}
}
//new Meditor().initConfig(MEDITOR.basePath);
//new Meditor().initPlugin(MEDITOR.basePath);
Meditor.create = function(editor) {
	 if(editor instanceof Meditor) {
		 return editor.createTag();
	 }
}