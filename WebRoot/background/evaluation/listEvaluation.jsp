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
<form class="form-inline definewidth m20" name="listEvaluationForm" action=" " method="get">
<input type="hidden" id="currentUrl" value="${ctxPath }">
    <font color="#777777"><strong>查询评价方式:</strong></font>
    <select name="condition" style = "width:105px;">
      <option value="ename" selected="selected">按评价名称</option>
      <option value="edescription" >按评价方式</option>
      <option value="ecalcul" >按评分规则</option>
    </select>
    <input type="text" name="inputValue" id="inputValue" class="abc input-default">&nbsp;&nbsp; 
    <a href="javascript:searchEvaluation();" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>查询</a>&nbsp;&nbsp; 
</form> 
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>评价名称</th>
        <th>评价方式</th>
        <th>评价显示及对应分值</th>
        <th>评价算分规则</th>
        <th>操作</th>
    </tr>
    </thead>
	 <tbody>     
         <c:forEach items="${sessionScope.evaluationVos}" var="evs">
                    <tr>
                        <td>${evs.ename}</td>
                        <td>${evs.edescription}</td>
                        <td>
                        <c:forEach items="${evs.scoreshows}" var="scoreshow">
                              显示： <c:out value="${scoreshow.escoreShow}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;
                              分值：<c:out value="${scoreshow.escore}"></c:out>
                              <br>
                        </c:forEach>
                        </td>
                        <td>${evs.ecalcul}</td>
                        <td>
                            <a href="${ctxPath }/editEvaluation.do?eid=${evs.eid}" class="btn btn-primary btn-sm">修改</a>
                            <a href="${ctxPath }/deleteEvaluation.do?eid=${evs.eid}" class="btn btn-primary btn-sm">删除</a>
                        </td>
                    </tr>
          </c:forEach>
      </tbody>         
    </table>

</body>
</html>
