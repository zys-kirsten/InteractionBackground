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
 var kk = document.getElementById("edescription").options;  
 for (var j=0; j<kk.length; j++) {  
	 if (kk[j].value=='${evaluationVo.edescription}') {  
		 kk[j].selected=true;  
		 break;  
	 }  
 }  
 
 var k = document.getElementById("ecalcul").options;  
 for (var i=0; i<k.length; i++) {  
	 if (k[i].value=='${evaluationVo.ecalcul}') {  
		 k[i].selected=true;  
		 break;  
	 }  
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
<form action="${ctxPath }/addEvaluation.do" name="questionForm" method="post">
<input type="hidden" id="currentUrl" value="${ctxPath }">
&nbsp;&nbsp;
<font color="#777777" size="5"><strong>请修改测试题信息: </strong></font>
<br><br>
<table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <c:set var="t" scope="session" value="${evaluationVo}"/>
				      <c:if test="${not empty t}">
				      <input type="hidden" id="eid" name="eid" value="${evaluationVo.eid}">
				      <tr>
				        <th style="text-align:center;width:20%;">评价名称</th>
				        <th style="text-align:center;width:20%;">评价方式</th>
				        <th style="text-align:center;width:40%;">评价显示及对应分值</th>
				        <th style="text-align:center;width:20%;">评价算分规则</th>
				      </tr>
					  <tr>
				        <td style="text-align:center;">
				           <input type="text" id="ename" name="ename" style="width:100%;" value="${evaluationVo.ename }"/>
				        </td>
				        <td style="text-align:center;">
                          <select id="edescription" name="edescription" style="width:100%;">
					          <option value="本组所有人" selected="selected">本组所有人</option>
							  <option value ="组内下一人" >组内下一人</option>
							  <option value ="组内上一人" >组内上一人</option>
							  <option value="其他组">其他组</option>
							</select>
                        </td>
				        <td style="text-align:center;">
				        <!--  <div id="newUpload1" style="top:0px;width:100%;height:50%;">   -->
				         <input type="hidden" name="scoreshows[0].ssid" value="${evaluationVo.scoreshows.get(0).ssid}">
				                  显示:<input type="text" name="scoreshows[0].escoreShow" style="width:30%;" value="${evaluationVo.scoreshows.get(0).escoreShow}"/>
					           分值:<input type="text" name="scoreshows[0].escore" style="width:30%;" value="${evaluationVo.scoreshows.get(0).escore}"/>
					           <br>  
					       <input type="hidden" name="scoreshows[1].ssid" value="${evaluationVo.scoreshows.get(1).ssid}">  
					           显示:<input type="text" name="scoreshows[1].escoreShow" style="width:30%;" value="${evaluationVo.scoreshows.get(1).escoreShow}"/>
					           分值:<input type="text" name="scoreshows[1].escore" style="width:30%;" value="${evaluationVo.scoreshows.get(1).escore}"/>
					           <br> 
					       <input type="hidden" name="scoreshows[2].ssid" value="${evaluationVo.scoreshows.get(2).ssid}">   
					           显示:<input type="text" name="scoreshows[2].escoreShow" style="width:30%;" value="${evaluationVo.scoreshows.get(2).escoreShow}"/>
					           分值:<input type="text" name="scoreshows[2].escore" style="width:30%;" value="${evaluationVo.scoreshows.get(2).escore}"/>
					           <br>
					       <input type="hidden" name="scoreshows[3].ssid" value="${evaluationVo.scoreshows.get(3).ssid}">    
					            显示:<input type="text" name="scoreshows[3].escoreShow" style="width:30%;" value="${evaluationVo.scoreshows.get(3).escoreShow}"/>
					           分值:<input type="text" name="scoreshows[3].escore" style="width:30%;" value="${evaluationVo.scoreshows.get(3).escore}"/>
                        </td>
                        <td style="text-align:center;">
                          <select id="ecalcul" name="ecalcul" style="width:100%;">
					          <option value="平均值" selected="selected">平均值</option>
							  <option value ="逐项相加" >逐项相加</option>
							</select>
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