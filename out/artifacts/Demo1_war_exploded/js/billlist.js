var billObj;
var path = document.getElementById("path").value;

//订单管理页面上点击删除按钮弹出删除框(billlist.jsp)
function deleteBill(obj){
	$.ajax({
		type:"POST",
		url:path+"/smbms/del",
		data:{billid:obj.attr("billid")},
		dataType:"text",
		success:function(data){
			if(data == "true"){//删除成功：移除删除行
				cancleBtn();
				changeDLGContent("删除成功");
				obj.parents("tr").remove();
				return;
			}else if(data == "false"){//删除失败
				//alert("对不起，删除订单【"+obj.attr("billcc")+"】失败");
				changeDLGContent("对不起，删除订单【"+obj.attr("billcc")+"】失败");
				return;
			}else if(data == "notexist"){
				//alert("对不起，订单【"+obj.attr("billcc")+"】不存在");
				changeDLGContent("对不起，订单【"+obj.attr("billcc")+"】不存在");
				return;
			}
		},
		error:function(data){
			alert("对不起，删除失败");
		}
	});
}

//Ajax异步刷新
function selectInfo() {
            var inputs = $.param($("form").serializeArray());
            $.post(path+"/smbms/load",inputs,function (data) {
				$(".providerTable tr:gt(0)").remove();
                    $.each(data,function (index,item) {
                    	$tr = $("<tr></tr>");
                    	$(".providerTable").append($tr);
						$tr.append( $("<td><span>"+item.billCode+"</span></td>"));
						$tr.append( $("<td><span>"+item.productName+"</span></td>"));
						$tr.append(  $("<td><span>"+item.proName+"</span></td>"));
						$tr.append( $("<td><span>"+item.totalPrice+"</span></td>"));
						if (item.isPayment==1){
							$tr.append($("<td><span>未付款</span></td>"))
						}
						if (item.isPayment==2){
							$tr.append($("<td><span>已付款</span></td>"))
						}
						$tr.append("<td><span>"+item.creationDate+"</span></td>");
						$tr.append("<td><span><a class='viewBill' onclick='sel("+item.id+")' href='javascript:;' billid="+item.id+" billcc="+item.billCode+"><img src=\"/Demo1/images/read.png\" alt=\"查看\" title=\"查看\"/></a></span>\n" +
							"\t\t\t\t\t<span><a class='modifyBill' onclick='upd("+item.id+")' href='javascript:;' billid="+item.id+" billcc="+item.billCode+"><img src=\"/Demo1/images/xiugai.png\" alt=\"修改\" title=\"修改\"/></a></span>\n" +
							"\t\t\t\t\t<span><a class='deleteBill' onclick='del(this)' href='javascript:;' billid="+item.id+" billcc="+item.billCode+"><img src=\"/Demo1/images/schu.png\" alt=\"删除\" title=\"删除\"/></a></span></td>");
					})
            },"json");
}
function sel(id) {
	window.location.href=path+"/smbms/view?billid="+id;
}
function upd(id) {
	window.location.href=path+"/smbms/modify?billid="+ id;
}
function del(ob) {
	billObj = $(ob);
	changeDLGContent("你确定要删除订单【"+billObj.attr("billcc")+"】吗？");
	openYesOrNoDLG();
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	$(".viewBill").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/smbms/view?billid="+ obj.attr("billid");
	});
	
	$(".modifyBill").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/smbms/modify?billid="+ obj.attr("billid");
	});
	
	$('#yes').click(function () {
		deleteBill(billObj);
	});

	$(".deleteBill").on("click",function(){
		billObj = $(this);
		changeDLGContent("你确定要删除订单【"+billObj.attr("billcc")+"】吗？");
		openYesOrNoDLG();
	});
	
	/*$(".deleteBill").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除订单【"+obj.attr("billcc")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/bill.do",
				data:{method:"delbill",billid:obj.attr("billid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除订单【"+obj.attr("billcc")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，订单【"+obj.attr("billcc")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});*/
});