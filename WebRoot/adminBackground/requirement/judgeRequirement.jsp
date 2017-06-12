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
       
         <tr>
           <td>需求主题: 小组管理</td>
         </tr>
         <tr>
           <td><textarea rows="5" cols="6" readOnly="readonly">课前分组结束以后对研讨课的小组信息进行管理</textarea></td>
         </tr>
         <tr>
           <td>发送至:&nbsp; <select style="width:100px;">
						  <option value ="volvo">开发者1</option>
						  <option value ="saab">开发者2</option>
						</select>
				&nbsp;<button class="btn btn-primary btn-sm" onclick="">通过</button>
		  </td>
         </tr>
         <tr>
           <td>
                       返回给需求者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <button class="btn btn-primary btn-sm" onclick="">驳回</button>
           </td>
         </tr>
    </table>

</body>
</html>
