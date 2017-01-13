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
  <c:if test="${not empty sessionScope.list.get(0).evaluationelement.eeid}">
    &nbsp;&nbsp;&nbsp;<font>${sessionScope.list.get(0).evaluationelement.eename}包含的因素：</font>
  </c:if>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th style="text-align:center;">指标名称</th>
        <th style="text-align:center;">操作</th>
    </tr>
    </thead>
	 <tbody>     
         <c:forEach items="${sessionScope.list}" var="l">
                    <tr>
                        <td style="text-align:center;">${l.eename}</td>
                        <c:choose>
                           <c:when test="${l.isLeaf==1}">
                             <td style="text-align:center;">
                               <a href="${ctxPath }/editEvaluationElement.do?eeid=${l.eeid}" class="btn btn-primary btn-sm">修改</a>
                               <a href="${ctxPath }/deleteEvaluationElement.do?eeid=${l.eeid}&fatherId=${sessionScope.fatherId}" class="btn btn-primary btn-sm">删除</a>
                             </td>
                           </c:when>
                           <c:otherwise>
                              <td style="text-align:center;">
                                <a href="${ctxPath }/enterNextEvaluationElement.do?eeid=${l.eeid}" class="btn btn-primary btn-sm">进入下级指标</a>
                              </td>
                           </c:otherwise>
                        </c:choose>
                    </tr>
          </c:forEach>
          
      </tbody>         
    </table>
    <br>
    <table align="center">
       <c:if test="${sessionScope.list.get(0).isLeaf==1}">
             <tr>
               <td style="text-align:center;">
                  <a href="${ctxPath }/background/evaluation/addEvaluationElement.jsp?fatherId=${sessionScope.fatherId}" class="btn btn-primary btn-sm">添加评价因素</a>
               </td>
               <td></td>
               <td style="text-align:center;">
                  <a href="${ctxPath }/returnUpEvaluationElement.do?eeid=${sessionScope.fatherId}" class="btn btn-primary btn-sm">返回上级指标</a>
              </td>
             </tr>
          </c:if>
          <c:if test="${sessionScope.list ==null || sessionScope.list.size()==0 }">
             <tr>
               <td style="text-align:center;">
                  <a href="${ctxPath }/background/evaluation/addEvaluationElement.jsp?fatherId=${sessionScope.fatherId}" class="btn btn-primary btn-sm">添加评价因素</a>
               </td>
               <td></td>
               <td style="text-align:center;">
                  <a href="${ctxPath }/returnUpEvaluationElement.do?eeid=${sessionScope.fatherId}" class="btn btn-primary btn-sm">返回上级指标</a>
               </td>
             </tr>
          </c:if>
    </table>

</body>
</html>
