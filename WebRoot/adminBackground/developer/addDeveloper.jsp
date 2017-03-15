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
		.menu li{float:left;width:20%;text-align:center;line-height:35px;height:35px;cursor:pointer;border-left:#cccccc solid 1px;color:#666;overflow:hidden;background:#E0E2EB;}
		.menu li.off{background:#FFFFFF;color:#336699;font-weight:bold;}
		.menudiv{height:200px;border-left:#cccccc solid 0px;border-right:#cccccc solid 1px;border-top:0;background:background-color: #DDDDDD}
		.menudiv div{padding:25px;line-height:28px;}
    </style>
</head><body>
<br>
<form action="${ctxPath }/addDeveloper.do" method="post">
<input type="hidden" id="currentUrl" value="${ctxPath }">
&nbsp;&nbsp;
<font color="#777777" size="5"><strong>请填写开发者信息: </strong></font>
<br><br>
 <table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
       	  <tr>
	    	        <td><input type="hidden" id="id" name="aid" value=""/></td>
	          </tr>
       	  <tr>
	                <td class="tableleft">账号</td>
            <td><input type="text" id="aaccount" name="aaccount"/></td>
              </tr>
       	  <tr>
	                <td class="tableleft">昵称</td>
            <td><input type="text" id="aname" name="aname"/></td>
              </tr>
       	  <tr>
	                <td class="tableleft">密码</td>
            <td><input type="text" id="apwd" name="apwd"/></td>
              </tr>
       	  <tr>
	                <td class="tableleft">电话</td>
            <td><input type="text" id="aphone" name="aphone"/></td>
              </tr>
        	</table>
  </td>
 </tr>
<tr align="center">
	<td >
	    <input type="submit" value="确定" class="btn btn-primary">&nbsp;&nbsp;
        <input type="reset" value="取消" class="btn btn-primary">	
    </td>
 </tr></table>
<br>
</form>