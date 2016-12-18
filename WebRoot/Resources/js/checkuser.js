//研讨课登录前选择课程
function chooseCourse(){
var th = document.form1;
	 var projectUrl = th.currentUrl.value;
	if(th.taccount.value==""){
		th.taccount.placeholder="不能为空!";
		th.taccount.focus();
		return;
	}
	if(th.tpwd.value==""){
		th.tpwd.placeholder="不能为空!";
		th.tpwd.focus();
		return;
	}
	var url = projectUrl+"/chooseCourse.do";
	data= {"taccount":th.taccount.value,"tpwd":th.tpwd.value}
	$.post(url,data,function(msg){		
		if(msg == "success"){
			window.location.href=projectUrl+"/background/seminarLogin.jsp";

		}else if(msg == "wrong"){
			th.tpwd.value="";
			th.tpwd.placeholder="密码错误!";
			th.tpwd.focus();
			return;
		}else if(msg == "success2"){
			alert("您还没创建课程！请普通登录！");
			window.location.href="commonLogin.jsp";
		}else{
			th.taccount.value="";
			th.tpwd.value="";
			th.tpwd.placeholder="密码";
			th.taccount.placeholder="用户名不存在!";
			th.taccount.focus();
			return;
		}
	})
}

//研讨课登录
function dologin(){
    var th = document.form1;
    var projectUrl = th.currentUrl.value;
	var url = projectUrl+"/loginCheck.do";
	data= {"cname":document.getElementById("cname").value}
	$.post(url,data,function(msg){		
		if(msg == "success"){
			window.location.href=projectUrl+"/background/index.jsp";

		}else{
			alert("操作错误！");
			window.location.href="login.jsp";
		}
	})
}
//普通登录
function doCommonlogin(){
	var th = document.form1;
	
	if(th.taccount.value==""){
		th.taccount.placeholder="不能为空!";
		th.taccount.focus();
		return;
	}
	if(th.tpwd.value==""){
		th.tpwd.placeholder="不能为空!";
		th.tpwd.focus();
		return;
	}
	var url = "commonLoginCheck.do";
	data= {"taccount":th.taccount.value,"tpwd":th.tpwd.value}
	$.post(url,data,function(msg){		
		if(msg == "success"){
			//$.post("login.do",data)
			window.location.href="background/commonIndex.jsp";

		}else if(msg == "wrong"){
			th.tpwd.value="";
			th.tpwd.placeholder="密码错误!";
			th.tpwd.focus();
			return;
		}else{
			th.taccount.value="";
			th.tpwd.value="";
			th.tpwd.placeholder="密码";
			th.taccount.placeholder="用户名不存在!";
			th.taccount.focus();
			return;
		}
	})
}

//注册之前检查邮箱是否已经注册
function checkEmail(){
    var th = document.formregister;
    var url = "registerCheck.do";
	data= {"taccount":th.taccount.value}
	$.post(url,data,function(msg){		
		if(msg == "fail"){
			document.getElementById("taccount").value="";
			th.taccount.placeholder="该邮箱已经注册!";
			th.taccount.focus();
			return;
		}
	})	
}

//注册
function regist(){

	var th = document.formregister;
	
	if(th.taccount.value==""){
		th.taccount.placeholder="不能为空!";
		th.taccount.focus();
		return;
	}
	if(th.tname.value==""){
		th.tname.placeholder="不能为空!";
		th.tname.focus();
		return;
	}
	if(th.tpwd.value==""){
		th.tpwd.placeholder="不能为空!";
		th.tpwd.focus();
		return;
	}
	if(th.repwd.value==""){
		th.repwd.placeholder="不能为空!";
		th.repwd.focus();
		return;
	}
	if (th.tpwd.value != th.repwd.value) {
		th.repwd.value = "";
		th.repwd.placeholder="两次密码不一致!";
		th.repwd.focus();
		return;
	}
	
	var url = "register.do";
	data= {"taccount":th.taccount.value,"tpwd":th.tpwd.value,"tname":th.tname.value}
	$.post(url,data,function(msg){		
		if(msg == "success"){
			alert("注册成功！");
			window.location.href="login.jsp";
		}else{
			alert("注册失败！");
			window.location.href="register.jsp";
		}
	})
}