//根据关键字查询评价方式
function searchEvaluation(){
	 var lc = document.listEvaluationForm;
	 var projectUrl = lc.currentUrl.value;
	
	 if(lc.inputValue.value==""){
		 lc.inputValue.placeholder="请输入关键字";
		 lc.inputValue.focus();
		 return;
	 }
	 
	 var url = projectUrl+"/searchEvaluation.do";
	 data = {"condition":lc.condition.value,"inputValue":lc.inputValue.value};
	 $.post(url,data,function(msg){
		 if(msg=="success"){
			 window.location.href= projectUrl+"/background/evaluation/listEvaluation.jsp";
		 }else{
			 alert("操作失败！");
		 }
	 });
}
//=======================================================================
//根据关键字查询测试题
function searchQuestion(){
	 var lc = document.listQuestionForm;
	 var projectUrl = lc.currentUrl.value;
	
	 if(lc.inputValue.value==""){
		 lc.inputValue.placeholder="请输入关键字";
		 lc.inputValue.focus();
		 return;
	 }
	 
	 var url = projectUrl+"/searchQuestion.do";
	 data = {"condition":lc.condition.value,"inputValue":lc.inputValue.value};
	 $.post(url,data,function(msg){
		 if(msg=="success"){
			 window.location.href= projectUrl+"/background/question/listQuestion.jsp";
		 }else{
			 alert("操作失败！");
		 }
	 });
}

//===================================================================
//添加(修改)研讨课息验证
function addSeminar(){
	  var co = document.seminarForm;
	  if(co.seName.value==""){
		  co.seName.placeholder="不能为空!";
		  co.seName.focus();
		  return;
		}
	  if(co.seTheme.value==""){
		  co.seTheme.placeholder="不能为空!";
		  co.seTheme.focus();
		  return;
		}
	  	 
	  var projectUrl = co.currentUrl.value; 
	//  var currentUrl1 ="/IB";
	  var url= projectUrl+"/addSeminar.do";
	  
	 if(co.seId.value=="") {
		 //添加研讨课
		  data= {"seName":co.seName.value,"seTheme":co.seTheme.value,"seUp":co.seUp.value,"seDown":co.seDown.value,"seTime":co.seTime.value};
	 }else{
		 //修改研讨课
		  data= {"seName":co.seName.value,"seTheme":co.seTheme.value,"seUp":co.seUp.value,"seDown":co.seDown.value,"seTime":co.seTime.value,
				  "seId":co.seId.value};
	 }
	 $.post(url,data,function(msg){
	    	if(msg!="fail"){
	    		window.location.href= projectUrl+"/background/listSeminar.jsp";
	    	}else{
	    		alert("操作失败！");
	    	}
	       });
}

//添加研讨课界面点击“返回”
function disAddSeminar(){
	 var co = document.seminarForm;
	 var projectUrl = co.currentUrl.value; 
	window.location.href=projectUrl+"/background/listSeminar.jsp";
}

//=================================================================================
//添加(修改)课程信息验证
function addCourse(){
	  var co = document.courseForm;
	  if(co.cnumber.value==""){
		  co.cnumber.placeholder="不能为空!";
		  co.cnumber.focus();
		  return;
		}
	  if(co.cname.value==""){
		  co.cname.placeholder="不能为空!";
		  co.cname.focus();
		  return;
		}
	  if(co.cterm.value==""){
		  co.cterm.placeholder="不能为空!";
		  co.cterm.focus();
		  return;
		}
	  if(co.semNum.value==""){
		  co.semNum.placeholder="不能为空!";
		  co.semNum.focus();
		  return;
		}
	  var projectUrl = co.currentUrl.value; 
	  var url= projectUrl+"/addCourse.do";
	  
	 if(co.cid.value=="") {
		 //添加课程
		  data= {"cnumber":co.cnumber.value,"cname":co.cname.value,"cterm":co.cterm.value,"semNum":co.semNum.value};
	 }else{
		 //修改课程
		  data= {"cnumber":co.cnumber.value,"cname":co.cname.value,"cterm":co.cterm.value,"cid":co.cid.value,"semNum":co.semNum.value};
	 }
	 $.post(url,data,function(msg){
	    	if(msg!="fail"){
	    		window.location.href= projectUrl+"/background/course/listCourse.jsp";
	    	}else{
	    		alert("操作失败！");
	    	}
	       });
}

//添加课程界面点击“返回”
function disAddCourse(){
	 var co = document.courseForm;
	 var projectUrl = co.currentUrl.value; 
	window.location.href=projectUrl+"/background/course/listCourse.jsp";
}

//根据关键字查询课程
function searchCourse(){
	
	 var lc = document.listCourseForm;
	 var projectUrl = lc.currentUrl.value;
	//  var currentUrl2 ="/IB";
//	 alert(currentUrl.value);
//	 alert(lc.inputValue.value);
//	 alert(lc.condition.value);
	 if(lc.inputValue.value==""){
		 lc.inputValue.placeholder="请输入关键字";
		 lc.inputValue.focus();
		 return;
	 }
	 
	 var url = projectUrl+"/searchCourse.do";
	 data = {"condition":lc.condition.value,"inputValue":lc.inputValue.value};
	 $.post(url,data,function(msg){
		 if(msg=="success"){
			 window.location.href= projectUrl+"/background/course/listCourse.jsp";
		 }else{
			 alert("操作失败！");
		 }
	 });
}

