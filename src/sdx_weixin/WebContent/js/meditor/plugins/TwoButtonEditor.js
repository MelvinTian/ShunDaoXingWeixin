function TwoButtonEditor(id,realId) {
	this.createTag = function() {
		var twoBtn = new Meditor().parseJsonAttrs(new ButtonEditor().contents['button_type']);
		var sureRealId = realId;
		var cancelRealId = realId+1;
		var html ='<div class="page_details_content_list">'+
        '<div class="ui-grid-a">'+
            '<div class="ui-block-a">'+
              '<a id="'
			+sureRealId +'"'+' name="' +'button_type_'+sureRealId +'"'+twoBtn.attrs+'>确&nbsp;定</a>'+
            '</div>'+
            '<div class="ui-block-b">'+
            	'<a id="'+cancelRealId +'"'+' name="' +'button_type_'+cancelRealId +'"'+twoBtn.attrs+'>取&nbsp;消</a>'+
            '</div>'+
         '</div>'+
     '</div>';
		/*var st = sessionStorage.html;
		if (st!=undefined&&st!='undefined'){
			sessionStorage.html = "\""+sureRealId+"\""+":"+"{"+"\"tagName\":\"a\""+","+"\"id\":\""+sureRealId+"\""+","+"\"name\":\"button_type_"+sureRealId+"\""+","+"\"text\":\"\""+","+jsonAttrs+"}"+",";
			sessionStorage.html += "\""+cancelRealId+"\""+":"+"{"+"\"tagName\":\"a\""+","+"\"id\":\""+cancelRealId+"\""+","+"\"name\":\"button_type_"+cancelRealId+"\""+","+"\"text\":\"\""+","+jsonAttrs+"}"+","+st;
		}else{
			sessionStorage.html="\""+sureRealId+"\""+":"+"{"+"\"tagName\":\"a\""+","+"\"id\":\""+sureRealId+"\""+","+"\"name\":\"button_type_"+sureRealId+"\""+","+"\"text\":\"\""+","+jsonAttrs+"}"+",";
			sessionStorage.html+="\""+cancelRealId+"\""+":"+"{"+"\"tagName\":\"a\""+","+"\"id\":\""+cancelRealId+"\""+","+"\"name\":\"button_type_"+cancelRealId+"\""+","+"\"text\":\"\""+","+jsonAttrs+"}";
		}*/
		return html;
	}
}
TwoButtonEditor.prototype = new Meditor();