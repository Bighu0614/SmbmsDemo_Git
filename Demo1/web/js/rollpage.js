var path = document.getElementById("path").value;


function page_nav(frm,num){
		var uName = document.getElementById("uName").value;
	var userRole = document.getElementById("UserRole").value;
		if (uName==null||uName==""){
			location.href=path+'/user.do/fanye?page='+num;
		} else if (userRole==null||userRole=="") {
			location.href=path+'/user.do/fanye?page='+num;
		}
		else {
			location.href=path+'/user.do/selectInfo?page='+num;
		}

}

function jump_to(frm,count,num){
    //alert(num);
	//验证用户的输入
	var regexp=/^[1-9]\d*$/;
	if(!regexp.test(num)){
		alert("请输入大于0的正整数！");
		return false;
	}else if((num-count) > 0){
		alert("请输入小于总页数的页码");
		return false;
	}else{
		page_nav(frm,num);
	}
}