var newPDF;
function attPreLoad(url){
	PDFJS.disableWorke = true;
 	var pdfDoc = null,
		scale = 2.4,
		pageNum = 1,
		pageCount = 1,
		timestamp = new Date().getTime(),
		pageRendering = false,
		pageNumPending = null,
		canvas = document.createElement('canvas'),
		ctx = canvas.getContext('2d'),
		iUrl = url;
		
	canvas.id = "canvas-" + timestamp;	
	canvas.setAttribute("class","the-canvas");
	$('.pdfView').append(canvas);
	
	/**
	* Asynchronously downloads PDF.
	*/
	PDFJS.getDocument(iUrl).then(function (pdfDoc_) {
		if(pdfDoc_ == undefined){
			alert("获取文件失败，请检查资源");	
			$(this).hide();
			$('#attPreview').hide();
			$('#loading_pdf').hide();
			$('.pdfView').empty();	
		}
		else{
			pdfDoc = pdfDoc_;
			//console.log(pdfDoc);
			pageCount = pdfDoc.numPages;
			//console.log(pageCount);
			document.getElementById('tolPage').textContent = pdfDoc.numPages;
			// Initial/first page rendering
			renderPage(pageNum);
		}
		function renderPage(num){
			pageRendering = true;
			$('#loading_pdf').show();
			pdfDoc.getPage(num).then(function(page){
				var viewport = page.getViewport(scale);
				canvas.height = viewport.height;
				canvas.width = viewport.width;
				 // Render PDF page into canvas context
				var renderContext = {
					canvasContext: ctx,
					viewport: viewport
				};
				var renderTask = page.render(renderContext);
				// Wait for rendering to finish
				renderTask.promise.then(function () {
					pageRendering = false;
					$('#loading_pdf').hide();
					if (pageNumPending !== null) {
						// New page rendering is pending
						renderPage(pageNumPending);
						pageNumPending = null;
					}
				});
				document.getElementById('curPage').value = pageNum;
			});
			// Update page counters	
		}
		function queueRenderPage(num) {
			if (pageRendering) {
				pageNumPending = num;
			} else {
				renderPage(num);
			}
		}
		$('body').undelegate();	
		$('body').delegate('#next','click',function(){
			console.log(pdfDoc);
			if (pageNum < pageCount) {
				pageNum++;
				queueRenderPage(pageNum);
			}	
		});
		$('body').delegate('#prev','click',function(){
			if (pageNum > 1) {
				pageNum--;
				queueRenderPage(pageNum);
			}
		});
		$('body').delegate('#jumpTo','click',function(){
			var newPage = Number($("#curPage").val());
			if(newPage >=1 && newPage <= pageCount){
				pageNum = newPage;
				pageRendering = false;
				queueRenderPage(pageNum);
			}else{
				document.getElementById('curPage').value = pageNum;
			}
		});
		$('body').delegate('#closePreview','click',function(){
			$(this).hide();
			$('#attPreview').hide();
			$('.pdfView').empty();			
			pdfDoc=undefined;
		});
	});
};

