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
<script type="text/javascript">
function selectCheck1(){
	var quantization = document.getElementById("1").options;
	var unquantization = document.getElementById("2").options;
	for (var i=0; i<quantization.length; i++) {  
		 if (quantization[i].selected==true) {
			 var temp = parseFloat((1.0-quantization[i].value).toPrecision(12));
			 for (var j=0; j<unquantization.length; j++) {  
				 if (unquantization[j].value==temp) {
					 unquantization[j].selected=true;
					 break;  
				 }  
			 }
			 break;  
		 }  
	 } 
}
</script>
</head>
<body>
	 <br>
	 <form action="${ctxPath }/CommitStudentAchieveWeight.do"  method="post">
		 <div class="menudiv">
	       <!-- 学生学习效果评价设置  start -->
		  <div id="con_one_1">
		    <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="cmid" name="cmid" value=""/>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <tr>
				      <td class="tableleft" style="text-align:center;"></td>
				       <c:forEach items="${sessionScope.elementssaws}" var="es">
				        <td class="tableleft" style="text-align:center;">${es.eename}</td>
						 </c:forEach>
				      </tr>
				       <tr>
				       <td class="tableleft" style="text-align:center;"></td>
				       <c:forEach items="${sessionScope.elementssaws}" var="es">
				         <td style="text-align:center;">
					        <select id="${es.eeid}" name="${es.eename}" style="width:100%;" onblur="selectCheck1();">
					          <option value ="0.0" selected="selected">0.0</option>
					          <option value="0.1" >0.1</option>
							  <option value ="0.2" >0.2</option>
							  <option value ="0.3" >0.3</option>
							  <option value ="0.4" >0.4</option>
							  <option value ="0.5" >0.5</option>
							  <option value ="0.6" >0.6</option>
							  <option value ="0.7" >0.7</option>
							  <option value ="0.8" >0.8</option>
							  <option value ="0.9" >0.9</option>
							  <option value ="1.0" >1.0</option>
							</select>
						</td>
						 </c:forEach>
				      </tr>
				      <tr>
				       <td class="tableleft" style="text-align:center;">已设置权值</td>
				         <c:forEach items="${sessionScope.elementssaws}" var="es">
				         <td style="text-align:center;">${es.weight }</td>
						 </c:forEach>
				      </tr>
				 	</table>
				  </td>
				 </tr>
				 <tr align="center">
					<td >
					    <input type="submit" class="btn btn-primary" value="确定">&nbsp;&nbsp;
					    <a href="#" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>取消</a>
					</td>
				</tr>
			</table>
		  </div>
	   <!-- 学生学习效果评价设置  end -->
		 </div>
   </form>
</body>
</html>