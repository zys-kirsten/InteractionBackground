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
<form action=" " name="seminarForm" method="post">
<input type="hidden" id="currentUrl" value="${ctxPath }">
&nbsp;&nbsp;
<font color="#777777" size="5"><strong>请填写研讨课信息: </strong></font>
<br><br>
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
    <c:set var="t" scope="session" value="${seminar}"/>
        <c:if test="${not empty t}">
        <input type="hidden" id="seId" name="seId" value="${seminar.seId}"/>
      <tr>
        <td class="tableleft">研讨课名称</td>
        <td><input type="text" id="seName" name="seName" value="${seminar.seName}"/><font style="color:red"> *</font></td>
        <td class="tableleft">研讨课主题</td>
        <td><input type="text" id="seTheme" name="seTheme" value="${seminar.seTheme}"/><font style="color:red"> *</font></td>
      </tr>
	  <tr>
        <td class="tableleft">人数上限</td>
        <td><input type="text" id="seUp" name="seUp" value="${seminar.seUp}"/></td>
        <td class="tableleft">人数下限</td>
        <td><input type="text" id="seDown" name="seDown" value="${seminar.seDown}"/></td>
      </tr>	
      <tr>
        <td class="tableleft">时间</td>
        <td style="height: 46px; "> <div style="margin:0 auto;">
	             <input type="text" id="seTime" value="${seminar.seTime}" name="seTime" onClick="return Calendar('seTime');" class="text_time"/>
             </div> 
        </td>
      </tr>	
      </c:if>
 	</table>
  </td>
 </tr>
 <tr align="center">
	<td >
	    <a href="javascript:addSeminar();" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>确定</a>&nbsp;&nbsp;
	    <a href="javascript:disAddSeminar();" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>取消</a>
	</td>
</tr>
</table>
<br>
</form>