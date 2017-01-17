<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <script type="text/javascript">  
      <%request.setAttribute("ctxPath", request.getContextPath());%>  
    </script>
    <link rel="stylesheet" type="text/css" href="${ctxPath }/backgroundResources/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctxPath }/backgroundResources/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${ctxPath }/backgroundResources/Css/style.css" />
    <link href="${ctxPath }/backgroundResources/Css/adminStyle.css" rel="stylesheet" />
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/jquery2.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/bootstrap.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/ckform.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/common.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/showdate.js"></script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/classConfigure.js"></script>
    <style type="text/css">
        body {font-size: 20px;
             padding-bottom: 40px;
             background-color:#e9e7ef;
             font-size:17px;
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
        *{margin:0;padding:0;list-style-type:none;}
		.menu{height:35%;border-right:#cccccc solid 1px;}
		.menu li{float:left;width:10%;text-align:center;line-height:35px;height:35px;cursor:pointer;border-left:#cccccc solid 1px;color:#666;overflow:hidden;background:#E0E2EB;}
		.menu li.off{background:#FFFFFF;color:#336699;font-weight:bold;}
		.menudiv{height:200px;border-left:#cccccc solid 0px;border-right:#cccccc solid 1px;border-top:0;background:background-color: #DDDDDD}
		.menudiv div{padding:25px;line-height:28px;}
    </style>
</head>
<body>

	<table class="table table-bordered table-hover definewidth m10">
	 <tbody>     
         <c:forEach items="${sessionScope.classModuleVos}" var="cmvs">
                    <tr>
                        <td>${cmvs.moduleName}</td>
                        <td>
                        <c:forEach items="${cmvs.seminars}" var="seminar">
                             <c:out value="${seminar.seName}"></c:out>
                              <br>
                        </c:forEach>
                        </td>
                        <td>
                          <c:choose>
                            <c:when test="${seminar.seName}=1">课上分组</c:when>
                            <c:otherwise>课前分组</c:otherwise>
                          </c:choose>
                          
                        </td>
                        <td>
                            <a href="${ctxPath }/editClassModule.do?cmid=${cmvs.cmid}" class="btn btn-primary btn-sm">修改</a>
                            <a href="${ctxPath }/deleteClassModule.do?cmid=${cmvs.cmid}" class="btn btn-primary btn-sm">删除</a>
                        </td>
                    </tr>
          </c:forEach>
      </tbody>         
    </table>
</body>
</html>