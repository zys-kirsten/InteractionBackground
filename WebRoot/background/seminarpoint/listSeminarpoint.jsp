<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="${ctxPath }/backgroundResources/js/checkInfo.js"></script>
    <style type="text/css">
        body {font-size: 20px;
		font-size: 20px;
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

        td {
              text-align:center; /*设置水平居中*/
              vertical-align:middle;/*设置垂直居中*/
           }

    </style>
</head>
<body >
<br>
 <font color="#777777" size="5"><strong>&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.seminar.seName} </strong></font>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th style="text-align:center;">知识点</th>
        <th style="text-align:center;">操作</th>
    </tr>
    </thead>
	 <tbody>     
         <c:forEach items="${sessionScope.seminarpoints}" var="sps">
                    <tr>
                        <td style="text-align:center;">${sps.content}</td>
                        <td style="text-align:center;">
                          <a href="${ctxPath }/editSeminarpoint.do?spid=${sps.spid}" class="btn btn-primary btn-sm">修改</a>
                          <a href="${ctxPath }/deleteSeminarpoint.do?spid=${sps.spid}&seId=${sps.seminar.seId}" class="btn btn-primary btn-sm">删除</a>
                        </td>
                    </tr>
          </c:forEach>
          
      </tbody>         
    </table>
    <br>
    <table align="center">
       <tr>
         <td style="text-align:center;">
            <a href="${ctxPath }/background/seminarpoint/addSeminarpoint.jsp?seId=${sessionScope.seminar.seId}" class="btn btn-primary btn-sm">添加知识点</a>
         </td>
       </tr>
    </table>

</body>
</html>
