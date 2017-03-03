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
<form action="${ctxPath }/addMenu.do"  method="post">
<br><br>
<table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" name="mid" value="">
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <tr>
				        <td style="text-align:center;">
				                   菜单名称
				        </td>
				        <td style="text-align:center;">
                          <input type="text" name="mname">
                        </td>
                        <td style="text-align:center;">
				                  父级菜单
				        </td>
				        <td style="text-align:center;">
                          <select  id="fatherId" name="fatherId" style="width:100%;">
                              <option value="-1">无父级菜单</option>
					          <c:forEach items="${menuVos}" var="mvs">
					            <option value="${mvs.mid }">${mvs.mname }</option>
					          </c:forEach>
						 </select>
                        </td>
				      </tr>
				     </table>
				 <tr align="center">
					<td >
					   <input type="submit" value="确定" class="btn btn-primary">
					    <a href="#" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>取消</a>
					</td>
				</tr>
			</table>
<br>
</form>