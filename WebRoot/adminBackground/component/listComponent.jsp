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

<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>构件名称</th>
        <th>构件描述</th>
        <th>构件开发者</th>
        <th>构件所属菜单</th>
        <th>构件地址</th>
        <th>构件种类</th>
        <th>构件状态</th>
        <th>操作</th>
    </tr>
    </thead>
	 <tbody>     
         <c:forEach items="${sessionScope.functioncomponents}" var="fcs">
                    <tr>
                        <td>${fcs.fcname}</td>
                        <td>${fcs.description}</td>
                        <td>${fcs.aname}</td>
                        <td>${fcs.mname}</td>
                        <td>${fcs.url}</td>
                        <c:choose>
                          <c:when test="${fcs.type==0}">
                            <td>PC端构件</td>
                          </c:when>
                          <c:otherwise>
                            <td>移动端构件</td>
                          </c:otherwise>
                        </c:choose>
                        <c:choose>
                          <c:when test="${fcs.state==0}">
                            <td>未启用</td>
                          </c:when>
                          <c:otherwise>
                            <td>启用</td>
                          </c:otherwise>
                        </c:choose>
                        <td>
                            <a href="${ctxPath }/editSeminar.do?seId=${svs.seId}&cid=${svs.cid}" class="btn btn-primary btn-sm">验证</a>
                            <a href="${ctxPath }/deleteSeminar.do?seId=${svs.seId}&cid=${svs.cid}" class="btn btn-primary btn-sm">删除</a>
                        </td>
                    </tr>
          </c:forEach>
      </tbody>         
    </table>

</body>
</html>
