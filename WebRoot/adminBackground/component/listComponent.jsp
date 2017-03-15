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
    
 <script type="text/javascript">
//管理员验证功能构件
 function adminJudgeComponent(fcid){
	 var url = "${ctxPath}/adminJudgeComponent.do";
	 data = {"fcid":fcid};
	 $.post(url,data,function(msg){
		 if(msg=="success"){
           alert("功能存在，验证成功！");
		 }else{
		   alert("功能不存在，验证失败！");
		 }
	 });
	
 };

 </script>   
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
                            <td>
                               <button class="btn btn-primary btn-sm" onclick="adminJudgeComponent(${fcs.fcid});">验证</button>
                               <a href="${ctxPath }/adminStartComponent.do?fcid=${fcs.fcid}" class="btn btn-primary btn-sm">启用</a>
                               <a href="${ctxPath }/adminDeleteComponent.do?fcid=${fcs.fcid}" class="btn btn-primary btn-sm">删除</a>
                            </td>
                          </c:when>
                          <c:otherwise>
                            <td>启用</td>
                          </c:otherwise>
                        </c:choose>
                    </tr>
          </c:forEach>
      </tbody>         
    </table>

</body>
</html>
