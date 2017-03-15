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
<script type="text/javascript">
 $(document).ready(function(){
	 var kk = document.getElementById("mid").options;  
	 for (var j=0; j<kk.length; j++) {  
		 if (kk[j].value=='${functioncomponentVo.mid}') {  
			 kk[j].selected=true;  
			 break;  
		 }  
	 }
 }); 
 
 $(document).ready(function(){
	 var qq = document.getElementById("type").options;  
	 for (var j=0; j<qq.length; j++) {  
		 if (qq[j].value=='${functioncomponentVo.type}') {  
			 qq[j].selected=true;  
			 break;  
		 }  
	 }
 }); 
 </script>
</head>
<body>
<br>
<form action="${ctxPath }/addComponent.do" name="componentForm" method="post">
<input type="hidden" id="currentUrl" value="${ctxPath }">
&nbsp;&nbsp;
<font color="#777777" size="5"><strong>请填写构件信息: </strong></font>
<br><br>
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
    <c:set var="t" scope="session" value="${functioncomponentVo}"/>
      <c:if test="${not empty t}">
        <input type="hidden" id="fcid" name="fcid" value="${functioncomponentVo.fcid }"/>
      <tr>
        <td class="tableleft">构件名称</td>
        <td><input type="text" id="fcname" name="fcname" value="${functioncomponentVo.fcname }"/><font style="color:red"> *</font></td>
         <td class="tableleft">构件种类</td>
         <td>
           <select  id="type" name="type" style="width:100%;">
	         <option value="0">PC端构件</option>
	         <option value="1">移动端构件</option>
		   </select>
		 </td>
      </tr>
	  <tr>
	   <td class="tableleft">构件描述</td>
        <td><input type="text" id="description" name="description" value="${functioncomponentVo.description }"/><font style="color:red"> *</font></td>
        <td class="tableleft">构件所属菜单</td>
        <td>
          <select  id="mid" name="mid" style="width:100%;">
	          <c:forEach items="${menus}" var="ms">
	            <option value="${ms.mid }">${ms.mname }</option>
	          </c:forEach>
		 </select>
       </td>
      </tr>
      <tr>
        <td class="tableleft">构件地址</td>
        <td><input type="text" id="url" name="url" value="${functioncomponentVo.url}"/><font style="color:red"> *</font></td>
      </tr>	
      </c:if>
 	</table>
  </td>
 </tr>
 <tr align="center">
	<td >
	    <input type="submit" value="确定" class="btn btn-primary">&nbsp;&nbsp;
	    <a href="javascript:disAddComponent();" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>取消</a>
	</td>
</tr>
</table>
<br>
</form>