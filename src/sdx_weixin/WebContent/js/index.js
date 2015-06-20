	//全选 
	function  allSelect(obj)
	{
		 $(":checkbox[name=itemId]").each(function () {  
	         this.checked = obj.checked;  
	     });
		
		}
	
		//加载平台信息
		function loadPlatInfo(){
			$("#platform_li").html("");
			var  url = path+"paltformManage/loadPlat";
			$.ajax({
                url: url,
                dataType:"json",
                method: "post",
                success: function(json) {
                	createLeft(json);
                },
                   timeout:60000,
 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
 	                	  parent.layer.alert("失败，请联系管理员！",8);
 	                  }
            }); 
		}
		//tab切换
		function    liClick(obj){
			$(obj).addClass('hover').siblings().removeClass('hover');
			var  id= $(obj).attr("id");
			updateRight(id);
		};
		//构建左侧li
		function  createLeft(json){
        	for(var i = 0 ;i <json.length ; i++){
        		if(i==0){
        			var  li = "<li class='hover'   id='"+json[i].platid+"'  onclick='liClick(this)'>"+json[i].platname+"</li>";
        			$("#showPlatName").html(json[i].platname);
        			updateRight(json[i].platid);
        		}else{
        			var  li = "<li   id='"+json[i].platid+"' onclick='liClick(this)'>"+json[i].platname+"</li>";
        		}
        	    $("#platform_li").append(li);
        	}
		}
		//点击左侧更新右侧列表
		function updateRight(id){
			var  url = path+"paltformManage/updateRight";
			$.ajax({
                url: url,
                data:"id="+id,
                dataType:"json",
                method: "post",
                success: function(json) {
                	$("#showPlatform")[0].reset();
                	$("#showPlatName").html(json.platname);
                	$("#show_plat_code").val(json.platcode);
                 	$("#show_plat_name").val(json.platname);
                 	$("#show_plat_url").val(json.platurl);
                 	$("#show_plat_time").val(json.time);
                   	$("#show_plat_testurl").val(json.testurl);
                	$("#show_plat_status").val(json.status);
                	$("#show_plat_desc").html(json.platdesc);
                	var  urlg =  path+"paltformManage/getServiceGrid?id="+id;
                	 jQuery("#serviceGrid").jqGrid('setGridParam',{url:urlg,page:1}).trigger("reloadGrid");
                },
                   timeout:60000,
 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
 	                	  parent.layer.alert("失败，请联系管理员！",8);
 	                  }
            }); 
		}
		//弹出框
		var myBg;
		//新增平台
		function newPlatformClick(obj){
			$("#newform")[0].reset();
			$("#new_Platform").css('display','table-cell');
			myBg = document.createElement('div');
			myBg.setAttribute('id','myBg');
			document.body.appendChild(myBg);
			$('.at_close, .at_cancel').bind('click',closePop);		
			return false;
		};
		function deletePlatformClick(){
			parent.layer.confirm('请确认是否删除该平台', function(index){
	            parent.layer.close(index);
	            if($("#platform_li").children(".hover").length==0){
					  parent.layer.alert("无要删除的平台！",8);
					  return;
				}else{
					var  delid = $("#platform_li").children(".hover").attr("id");
					var  url = path+"paltformManage/deletePlat";
					   $.ajax({
			                url: url,
			                data : "id="+delid,
			                method: "post",
			                success: function(msg) {
			                	loadPlatInfo();
			                },
			                   timeout:60000,
			 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
			 	                	  parent.layer.alert("提交失败，请联系管理员！",8);
			 	                  }
			            }); 
				}
	        });
		}
		//确认添加平台
		function add_Plat(){
			if ($("#newform").validationEngine("validate")){
				var  formData = $("#newform").serialize();
				var  url = path+"paltformManage/addPlat";
				   $.ajax({
		                url: url,
		                data : formData,
		                method: "post",
		                success: function(msg) {
		                	closePop();
		                	loadPlatInfo();
		                },
		                   timeout:60000,
		 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
		 	                	  parent.layer.alert("提交失败，请联系管理员！",8);
		 	                  }
		            }); 
		}
			
		}
		//修改平台
		function altPlatformClick(){
			$("#altform")[0].reset();
			if($("#platform_li").children(".hover").length==0){
				  parent.layer.alert("无要修改的平台！",8);
				  return;
			}
			var  id = $("#platform_li").children(".hover").attr("id");
			//获取要修改的平台数据
			var  url = path+"paltformManage/updateRight";
			  $.ajax({
	                url: url,
	                data : "id="+id,
	                method: "post",
	                dataType:"json",
	                success: function(json) {
	                	$("#modify_platform_name").val(json.platname);
	                	$("#modify_platform_code").val(json.platcode);
	                 	$("#modify_platform_url").val(json.platurl);
	                   	$("#modify_test_url").val(json.testurl);
	                	$("#modify_platform_desc").html(json.platdesc);
	                },
	                   timeout:60000,
	 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
	 	                	  parent.layer.alert("失败，请联系管理员！",8);
	 	                  }
	            });
			$("#alt_Platform").css('display','table-cell');
			myBg = document.createElement('div');
			myBg.setAttribute('id','myBg');
			document.body.appendChild(myBg);
			$('.at_close, .at_cancel').bind('click',closePop);		
			return false;
		};
		//确认修改平台
		function modify_Plat(){
			var  id = $("#platform_li").children(".hover").attr("id");
			if ($("#altform").validationEngine("validate")){
				var  formData = $("#altform").serialize();
				var  url = path+"paltformManage/modifyPlat";
				   $.ajax({
		                url: url,
		                data : formData+"&id="+id,
		                method: "post",
		                success: function(msg) {
		                	closePop();
		                	updateRight(id);
		                },
		                   timeout:60000,
		 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
		 	                	  parent.layer.alert("提交失败，请联系管理员！",8);
		 	                  }
		            }); 
		}
		}
		//新增服务对话框
		function new_service(){
			if($("#platform_li").children(".hover").length==0){
				  parent.layer.alert("无要添加的服务！",8);
				  return;
			}
			$("#newserviceform")[0].reset();
			$("#new_service_dialog").css('display','table-cell');
			myBg = document.createElement('div');
			myBg.setAttribute('id','myBg');
			document.body.appendChild(myBg);
			$('.at_close, .at_cancel').bind('click',closePop);		
		}
		//新增服务点击确定
		function add_Serivce(){
			var  id = $("#platform_li").children(".hover").attr("id");
			if ($("#newserviceform").validationEngine("validate")){
				var  formData = $("#newserviceform").serialize();
				var  url = path+"paltformManage/addService";
				   $.ajax({
		                url: url,
		                data : formData+"&pid="+id,
		                method: "post",
		                success: function(msg) {
		                	var  urlg =  path+"paltformManage/getServiceGrid?id="+id;
		                	 jQuery("#serviceGrid").jqGrid('setGridParam',{url:urlg,page:1}).trigger("reloadGrid");
		                	 closePop();
		                },
		                   timeout:60000,
		 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
		 	                	  parent.layer.alert("提交失败，请联系管理员！",8);
		 	                  }
		            }); 
		}
		}
		//修改服务
		function  modify_service(){
			var ids=jQuery("#serviceGrid").jqGrid('getGridParam','selarrrow');
			$("#modifyserviceform")[0].reset();
			if(ids.length!=1){
				 parent.layer.alert("请选择一条服务记录进行修改！",8);
				 return;
			}else{
				if($("#platform_li").children(".hover").length==0){
					  parent.layer.alert("无要修改的服务！",8);
					  return;
				}
				//获取要修改的服务数据
				var  url = path+"paltformManage/getModifyServiceData";
				  $.ajax({
		                url: url,
		                data : "id="+ids[0],
		                method: "post",
		                dataType:"json",
		                success: function(msg) {
		                	 $("#modify_service_code").val(msg[0].serviceCode);
		                	 $("#modify_service_name").val(msg[0].serviceName);
		                	 $("#modify_service_version").val(msg[0].serviceVersion);
		                	 $("#modify_service_desc").html(msg[0].serviceDesc);
		                	 $("#modify_own_interid").val(msg[0].outinterid);
		                },
		                   timeout:60000,
		 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
		 	                	  parent.layer.alert("失败，请联系管理员！",8);
		 	                  }
		            });
			
				$("#modify_service_dialog").css('display','table-cell');
				myBg = document.createElement('div');
				myBg.setAttribute('id','myBg');
				document.body.appendChild(myBg);
				$('.at_close, .at_cancel').bind('click',closePop);		
			}
		}
		//修改服务点击确认
		function   modify_Serivce(){
			var  pid = $("#platform_li").children(".hover").attr("id");
			 var ids=jQuery("#serviceGrid").jqGrid('getGridParam','selarrrow');
				var id = ids[0];
			if ($("#modifyserviceform").validationEngine("validate")){
				var  formData = $("#modifyserviceform").serialize();
				var  url = path+"paltformManage/modifyService";
				   $.ajax({
		                url: url,
		                data : formData+"&id="+id,
		                method: "post",
		                success: function(msg) {
		                	var  urlg =  path+"paltformManage/getServiceGrid?id="+pid;
		                	 jQuery("#serviceGrid").jqGrid('setGridParam',{url:urlg,page:1}).trigger("reloadGrid");
		                	 closePop();
		                },
		                   timeout:60000,
		 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
		 	                	  parent.layer.alert("提交失败，请联系管理员！",8);
		 	                  }
		            }); 
		}
			
		}
		
		//删除服务
		function  delete_service(){
			var  id = $("#platform_li").children(".hover").attr("id");
			var ids=jQuery("#serviceGrid").jqGrid('getGridParam','selarrrow');
			if(ids.length==0){
				 parent.layer.alert("请至少选择一条服务记录进行删除！",8);
				 return;
			}else{
				parent.layer.confirm('请确认是否删除选中的服务？', function(index){
		            parent.layer.close(index);
		            var  url = path+"paltformManage/deleteService";
					   $.ajax({
			                url: url,
			                data : "ids="+ids,
			                method: "post",
			                success: function(msg) {
			                	var  urlg =  path+"paltformManage/getServiceGrid?id="+id;
			                	 jQuery("#serviceGrid").jqGrid('setGridParam',{url:urlg,page:1}).trigger("reloadGrid");
			                },
			                   timeout:60000,
			 	                  error:function(XMLHttpRequest, textStatus, errorThrown) {
			 	                	  parent.layer.alert("提交失败，请联系管理员！",8);
			 	                  }
			            }); 
		        });
			}
		}
		
		//关闭对话框
		var closePop = function(){
			$('.alert').css('display','none');
			myBg.style.display = 'none';
		};
		function sw(){
			/*$("#switch_dialog").css('display','table-cell');
			myBg = document.createElement('div');
			myBg.setAttribute('id','myBg');
			document.body.appendChild(myBg);
			$('.at_close, .at_cancel').bind('click',closePop);*/
			var url =  path+"switchServerIp";
		 	   $.ajax({
		          url: url,
		          method: "post",
		          success: function(msg) {
		        	  if (msg=="true"){
			        	  layer.alert("切换完成！",9);
			        	  closePop();
		        	  }else{
		        		  layer.alert("切换失败！",8);
		        		  closePop();
		        	  }
		          },
		             timeout:60000,
		                 error:function(XMLHttpRequest, textStatus, errorThrown) {
		                 }
		      });
		}
		/*function switchIP(){
			var switchType = $('#serverType').val();
			var url =  path+"switchServerIp?serverType="+switchType;
		 	   $.ajax({
		          url: url,
		          method: "post",
		          success: function(msg) {
		        	  if (msg=="true"){
			        	  layer.alert("切换完成！",9);
			        	  closePop();
		        	  }else{
		        		  layer.alert("切换失败！",8);
		        		  closePop();
		        	  }
		          },
		             timeout:60000,
		                 error:function(XMLHttpRequest, textStatus, errorThrown) {
		                 }
		      });
		}*/
		
	