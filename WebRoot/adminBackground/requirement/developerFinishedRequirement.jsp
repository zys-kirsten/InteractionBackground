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
        <th>编号</th>
        <th>需求主题</th>
        <th>申请教师</th>
        <th>提交状态</th>
    </tr>
    </thead>
	 <tbody>     
         <tr>
           <td>1001</td>
           <td>小组管理</td>
           <td>张老师</td>
           <td>已提交</td>
         </tr>
         <tr>
           <td>1003</td>
           <td>数据统计</td>
           <td>好老师</td>
           <td>未提交</td>
         </tr>
      </tbody>         
    </table>

</body>
</html>
