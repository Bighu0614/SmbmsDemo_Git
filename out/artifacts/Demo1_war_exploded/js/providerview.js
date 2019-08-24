var backBtn = null;

$("#back").click(function () {
	history.back(-1);
})

// $(function(){
// 	backBtn = $("#back");
// 	backBtn.on("click",function(){
// 		//alert("view : "+referer);
// 		if(referer != undefined
// 			&& null != referer
// 			&& "" != referer
// 			&& "null" != referer
// 			&& referer.length > 4){
// 		 window.location.href = referer;
// 		}else{
// 			history.back(-1);
// 		}
// 	});
// });