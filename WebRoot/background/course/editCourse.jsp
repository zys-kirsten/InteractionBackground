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
<form action=" " name="courseForm" method="post">
<input type="hidden" id="currentUrl" value="${ctxPath }">
&nbsp;&nbsp;
<font color="#777777" size="5"><strong>请填写课程信息: </strong></font>
<br><br>
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
    <c:set var="t" scope="session" value="${course}"/>
        <c:if test="${not empty t}">
        <input type="hidden" id="cid" name="cid" value="${course.cid}"/>
      <tr>
        <td class="tableleft">课程编号</td>
        <td><input type="text" id="cnumber" name="cnumber" value="${course.cnumber}"/><font style="color:red"> *</font></td>
        <td class="tableleft">课程名称</td>
        <td><input type="text" id="cname" name="cname" value="${course.cname}"/><font style="color:red"> *</font></td>
      </tr>
	  <tr>
        <td class="tableleft">开课学期</td>
        <td><input type="text" id="cterm" name="cterm" value="${course.cterm}"/><font style="color:red"> *</font></td>
        <td class="tableleft">学生选择研讨课数目</td>
        <td><input type="text" id="semNum" name="semNum" value="${course.semNum}"/><font style="color:red"> *</font></td>
      </tr>	
      </c:if>
 	</table>
  </td>
 </tr>
 <tr align="center">
	<td >
	    <a href="javascript:addCourse();" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>确定</a>&nbsp;&nbsp;
	    <a href="javascript:disAddCourse();" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>取消</a>
	</td>
</tr>
</table>
<br>
</form>