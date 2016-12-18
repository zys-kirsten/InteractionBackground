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
<font color="#777777" size="5"><strong>请填写课堂评价信息: </strong></font>
<br><br>
<table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="eid" name="eid" value="">
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <tr>
				        <th style="text-align:center;width:20%;">评价名称</th>
				        <th style="text-align:center;width:20%;">评价方式</th>
				        <th style="text-align:center;width:40%;">评价显示及对应分值</th>
				        <th style="text-align:center;width:20%;">评价算分规则</th>
				      <tr>
				        <td style="text-align:center;">
				          <input type="text" id="ename" name="ename" style="width:100%;"/>
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
				                  显示:<input type="text" name="scoreshows[0].escoreShow" style="width:30%;"/>
					           分值:<input type="text" name="scoreshows[0].escore" style="width:30%;"/>
					           <br>    
					           显示:<input type="text" name="scoreshows[1].escoreShow" style="width:30%;"/>
					           分值:<input type="text" name="scoreshows[1].escore" style="width:30%;"/>
					           <br>    
					           显示:<input type="text" name="scoreshows[2].escoreShow" style="width:30%;"/>
					           分值:<input type="text" name="scoreshows[2].escore" style="width:30%;"/>
					           <br>    
					            显示:<input type="text" name="scoreshows[3].escoreShow" style="width:30%;"/>
					           分值:<input type="text" name="scoreshows[3].escore" style="width:30%;"/>
                        </td>
                        <td style="text-align:center;">
                          <select id="ecalcul" name="ecalcul" style="width:100%;">
					          <option value="平均值" selected="selected">平均值</option>
							  <option value ="逐项相加" >逐项相加</option>
							</select>
                        </td>
				      </tr>
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