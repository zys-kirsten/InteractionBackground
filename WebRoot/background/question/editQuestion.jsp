<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <script type="text/javascript">  
      <%request.setAttribute("ctxPath", request.getContextPath());%>  
    </script>
    <link rel="stylesheet" type="text/css" href="${ctxPath }/backgroundResources/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctxPath }/backgroundResources/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${ctxPath }/backgroundResources/Css/style.css" />
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/jquery2.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/bootstrap.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/ckform.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/common.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/jquerypicture.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/checkInfo.js"></script>
     <script type="text/javascript" src="${ctxPath }/backgroundResources/js/showdate.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
//select设定选中
 var kk = document.getElementById("chapter").options;  
 for (var j=0; j<kk.length; j++) {  
	 if (kk[j].value=='${questionVo.chapter}') {  
		 kk[j].selected=true;  
		 break;  
	 }  
 }  
//复选框设定选中
 var c0 = '${questionVo.answers.get(0).correct}';
 var c1 = '${questionVo.answers.get(1).correct}';
 var c2 = '${questionVo.answers.get(2).correct}';
 var c3 = '${questionVo.answers.get(3).correct}';
 var boxes = document.getElementsByName("correct");
 if(c0==1){
	boxes[0].checked=true;
 }else{
	boxes[0].checked=false;
 }
 if(c1==1){
		boxes[1].checked=true;
	 }else{
		boxes[1].checked=false;
	 }
 if(c2==1){
		boxes[2].checked=true;
	 }else{
		boxes[2].checked=false;
	 }
 if(c3==1){
		boxes[3].checked=true;
	 }else{
		boxes[3].checked=false;
	 }
}); 
</script> 
    <style type="text/css">
        body {font-size: 20px;
            padding-bottom: 40px;
            background-color:#e9e7ef;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<br>
<form action="${ctxPath }/addQuestion.do" name="questionForm" method="post">
<input type="hidden" id="currentUrl" value="${ctxPath }">
&nbsp;&nbsp;
<font color="#777777" size="5"><strong>请修改测试题信息: </strong></font>
<br><br>
<table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <c:set var="t" scope="session" value="${questionVo}"/>
				      <c:if test="${not empty t}">
				      <input type="hidden" id="qid" name="qid" value="${questionVo.qid}">
				      <tr>
				        <th style="text-align:center;width:25%;">题目内容</th>
				        <th style="text-align:center;width:25%;">所属章节</th>
				        <th style="text-align:center;width:50%;">答案</th>
				      </tr>
					  <tr>
				        <td style="text-align:center;">
				          <textArea id="content" name="content" style="width:100%;">${questionVo.content }</textArea>
				        </td>
				        <td style="text-align:center;">
                          <select id="chapter" name="chapter" style="width:100%;">
					          <option value="1">第一章</option>
							  <option value ="2" >第二章</option>
							  <option value ="3" >第三章</option>
							</select>
                        </td>
				        <td style="text-align:center;">
				         <table> 
		                     <tr> 
		                       <td> 
		                       <input type="hidden" name="answers[0].aid" value="${questionVo.answers.get(0).aid}">
		                                              正确： 
		                       </td> 
		                       <td> 
		                         <input type="checkbox" id="c0" name="correct" value="a0"/>
		                       </td> 
		                     </tr> 
		                     <tr> 
		                       <td> 
		                                              内容： 
		                       </td> 
		                       <td> 
		                           <input type="text"name="answers[0].acontent" value="${questionVo.answers.get(0).acontent}"/> 
		                       </td> 
		                      </tr> 
		                      <tr> 
		                        <td> 
		                                               解释：
		                        </td> 
		                        <td> 
		                           <input type="text" name="answers[0].aexplain" value="${questionVo.answers.get(0).aexplain}"/> 
		                        </td> 
		                      </tr>
		                      <tr></tr>
		                      <tr> 
		                       <td> 
		                       	<input type="hidden" name="answers[1].aid" value="${questionVo.answers.get(1).aid}">
		                                              正确： 
		                       </td> 
		                       <td> 
		                         <input type="checkbox" id="c1" name="correct" value="a1"/>
		                       </td> 
		                     </tr> 
		                     <tr> 
		                       <td> 
		                                              内容： 
		                       </td> 
		                       <td> 
		                           <input type="text"name="answers[1].acontent" value="${questionVo.answers.get(1).acontent}"/> 
		                       </td> 
		                      </tr> 
		                      <tr> 
		                        <td> 
		                                               解释：
		                        </td> 
		                        <td> 
		                           <input type="text" name="answers[1].aexplain" value="${questionVo.answers.get(1).aexplain}"/> 
		                        </td> 
		                      </tr>
		                      <tr></tr>
		                      <tr> 
		                       <td> 
		                      	<input type="hidden" name="answers[2].aid" value="${questionVo.answers.get(2).aid}">
		                                              正确： 
		                       </td> 
		                       <td> 
		                         <input type="checkbox" id="c2" name="correct" value="a2"/>
		                       </td> 
		                     </tr> 
		                     <tr> 
		                       <td> 
		                                              内容： 
		                       </td> 
		                       <td> 
		                           <input type="text"name="answers[2].acontent" value="${questionVo.answers.get(2).acontent}"/> 
		                       </td> 
		                      </tr> 
		                      <tr> 
		                        <td> 
		                                               解释：
		                        </td> 
		                        <td> 
		                           <input type="text" name="answers[2].aexplain" value="${questionVo.answers.get(2).aexplain}"/> 
		                        </td> 
		                      </tr>
		                      <tr></tr>
		                      <tr> 
		                       <td> 
		                       	<input type="hidden" name="answers[3].aid" value="${questionVo.answers.get(3).aid}">
		                                              正确： 
		                       </td> 
		                       <td> 
		                         <input type="checkbox" id="c3" name="correct" value="a3"/>
		                       </td> 
		                     </tr> 
		                     <tr> 
		                       <td> 
		                                              内容： 
		                       </td> 
		                       <td> 
		                           <input type="text"name="answers[3].acontent" value="${questionVo.answers.get(3).acontent}"/> 
		                       </td> 
		                      </tr> 
		                      <tr> 
		                        <td> 
		                                               解释：
		                        </td> 
		                        <td> 
		                           <input type="text" name="answers[3].aexplain" value="${questionVo.answers.get(3).aexplain}"/> 
		                        </td> 
		                      </tr>
		                      <tr></tr> 
                           </table> 
                        </td>
				      </tr>
				    </c:if>
				     </table>
				 <tr align="center">
					<td >
					   <input type="submit" value="确定" class="btn btn-primary">
					    <a href="#" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>取消</a>
					</td>
				</tr>
			</table>
<br>
</form>