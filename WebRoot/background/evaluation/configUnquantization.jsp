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
		.menu li{float:left;width:14%;text-align:center;line-height:35px;height:35px;cursor:pointer;border-left:#cccccc solid 1px;color:#666;overflow:hidden;background:#E0E2EB;}
		.menu li.off{background:#FFFFFF;color:#336699;font-weight:bold;}
		.menudiv{height:200px;border-left:#cccccc solid 0px;border-right:#cccccc solid 1px;border-top:0;background:background-color: #DDDDDD}
		.menudiv div{padding:25px;line-height:28px;}
    </style>
</head>
<body>
	 <br>
	 <form action="${ctxPath }/CommitStudentAchieveWeight.do"  method="post">
		 <div class="menudiv">
	       <!-- 非量化指标配置  start -->
		  <div id="con_one_3" >
		    <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <tr>
				      <th style="text-align:center;"></th>
				      <c:forEach items="${sessionScope.elementsuws}" var="es">
				        <th style="text-align:center;">${es.eename}</th>
				      </c:forEach>
				      </tr>
				      <c:forEach items="${sessionScope.elementsuws}" var="es">
					  <tr>
				        <td class="tableleft" style="text-align:center;">${es.eename}</td>
				        <td style="text-align:center;">
					        <select name="uq_self[0]" style="width:100%;">
					          <option value ="1.0" >1</option>
					          <option value ="2.0" >2</option>
							  <option value ="3.0" >3</option>
							  <option value ="4.0" >4</option>
							  <option value ="5.0" >5</option>
							  <option value ="6.0" >6</option>
							  <option value ="7.0" >7</option>
							  <option value= "8.0" >8</option>
							  <option value ="9.0" >9</option>
							  <option value ="1.0/2.0" >1/2</option>
							  <option value ="1.0/3.0" >1/3</option>
							  <option value ="1.0/4.0" >1/4</option>
							  <option value ="1.0/5.0" >1/5</option>
							  <option value ="1.0/6.0" >1/6</option>
							  <option value ="1.0/7.0" selected="selected">1/7</option>
							  <option value= "1.0/8.0" >1/8</option>
							  <option value ="1.0/9.0" >1/9</option>
							</select>
						</td>
				        <td style="text-align:center;">
					        <select name="uq_self[0]" style="width:100%;">
					          <option value ="1.0" >1</option>
					          <option value ="2.0" >2</option>
							  <option value ="3.0" >3</option>
							  <option value ="4.0" >4</option>
							  <option value ="5.0" >5</option>
							  <option value ="6.0" >6</option>
							  <option value ="7.0" >7</option>
							  <option value= "8.0" >8</option>
							  <option value ="9.0" >9</option>
							  <option value ="1.0/2.0" >1/2</option>
							  <option value ="1.0/3.0" >1/3</option>
							  <option value ="1.0/4.0" >1/4</option>
							  <option value ="1.0/5.0" >1/5</option>
							  <option value ="1.0/6.0" >1/6</option>
							  <option value ="1.0/7.0" selected="selected">1/7</option>
							  <option value= "1.0/8.0" >1/8</option>
							  <option value ="1.0/9.0" >1/9</option>
							</select>
						</td>
				        <td style="text-align:center;">
					        <select name="uq_self[1]" style="width:100%;">
					          <option value ="1.0" >1</option>
					          <option value ="2.0" >2</option>
							  <option value ="3.0" >3</option>
							  <option value ="4.0" >4</option>
							  <option value ="5.0" >5</option>
							  <option value ="6.0" >6</option>
							  <option value ="7.0" >7</option>
							  <option value= "8.0" >8</option>
							  <option value ="9.0" >9</option>
							  <option value ="1.0/2.0" >1/2</option>
							  <option value ="1.0/3.0" >1/3</option>
							  <option value ="1.0/4.0" >1/4</option>
							  <option value ="1.0/5.0" selected="selected">1/5</option>
							  <option value ="1.0/6.0" >1/6</option>
							  <option value ="1.0/7.0" >1/7</option>
							  <option value= "1.0/8.0" >1/8</option>
							  <option value ="1.0/9.0" >1/9</option>
							</select>
						</td>
				      </tr>
				      </c:forEach>
				 	</table>
				  </td>
				 </tr>
			</table>
		  </div>
		<!-- 非量化指标配置  end -->
		 </div>
   </form>
</body>
</html>