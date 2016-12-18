<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>SPOC翻转课堂后台管理</title>
<script type="text/javascript">  
      <%request.setAttribute("ctxPath", request.getContextPath());%>  
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctxPath }/backgroundResources/Css/adminStyle.css" rel="stylesheet" />
<script type="text/javascript" src="${ctxPath }/backgroundResources/js/jquery1.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".div2").click(
						function() {
							$(this).next("div").slideToggle("slow").siblings(
									".div3:visible").slideUp("slow");
						});
			});
	function openurl(url) {
		var rframe = parent.document.getElementById("rightFrame");
		rframe.src = url;
	}
</script>
<style>
body {
	margin: 0;
	font-family: 微软雅黑;
	/* background-image: url(../backgroundResources/images/.jpg); */
	background-repeat: no-repea;
	background-size: cover;
	background-attachment: fixed;
	background-color: #DDDDDD
	
}

/* .top1 {
	position: absolute;
	top: 0px;
	width: 100%;
	height: 20px;
	text-align: center;
	color: #FFFFFF;
	font-size: 17px;
	font-height: 20px;
	font-family: 楷体;
	background-color: #888888
} */

.title {
float:left;
    margin:-32px 20px;
	font-size: 40px;
	color: #FFFFFF;
	font-height: 55px;
	font-family: 隶书;
}

.top2 {
	position: absolute;
	top: 0px;
	width: 100%;
	height: 97px;
	text-align: center;
	color: #ccffff;
	background-color: #888888
}

.left {
	position: absolute;
	left: 0px;
	top: 97px;
	width: 200px;
	height: 85%;
	border-right: 1px solid #9370DB;
	color: #000000;
	font-size: 20px;
	text-align: center;
	background-color: #B3B3B3
}

.right {
	position: absolute;
	left: 200px;
	top:97px;
	width: 85.2%;
	height: 85%;
	border-top: 0px solid #484860;
	font-size: 14px;
	text-align: center;
}

.end {
	position: absolute;
	bottom: 0px;
	width: 100%;
	height: 30px;
	text-align: center;
	color: #556B2F;
	font-size: 17px;
	font-height: 20px;
	font-family: 楷体;
	background-color: #C0C0C0
}

.div1 {
	text-align: center;
	width: 200px;
	padding-top: 10px;
}

.div2 {
	height: 40px;
	line-height: 40px;
	cursor: pointer;
	font-size: 18px;
	position: relative;
	border-bottom: #ccc 0px dotted;
}

.spgl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(${ctxPath }/backgroundResources/images/1.png);
}

.yhgl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(${ctxPath }/backgroundResources/images/4.png);
}

.gggl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(${ctxPath }/backgroundResources/images/4.png);
}

.zlgl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(${ctxPath }/backgroundResources/images/4.png);
}

.pjgl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(${ctxPath }/backgroundResources/images/4.png);
}

.tcht {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(${ctxPath }/backgroundResources/images/2.png);
}

.div3 {
	display: none;
	cursor: pointer;
	font-size: 15px;
}

.div3 ul {
	margin: 0;
	padding: 0;
}

.div3 li {
	height: 30px;
	line-height: 30px;
	list-style: none;
	border-bottom: #ccc 1px dotted;
	text-align: center;
}

.a {
	text-decoration: none;
	color: #000000;
	font-size: 15px;
}

.a1 {
	text-decoration: none;
	color: #000000;
	font-size: 18px;
}
</style>
</head>
<body>

	<div class="top2">
	<br/>
		<div class="title" >
			<h2>翻转课堂后台管理</h2>
		</div>
		<div class="fr top-link">
			<a href="admin_list.html" target="mainCont"><i
				class="adminIcon"></i>
				<span>
				   <c:set var="t" scope="session" value="${teacher}"/>
                      <c:if test="${not empty t}">
                             <c:out value="${t.tname}"/>
                      </c:if>
                </span>&nbsp;</a> 
		</div>
		
	</div>

	<div class="left">
		<div class="div1">
           <div class="div2">
				<div class="spgl"></div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				课程信息管理
			</div>
			<div class="div3">
				<li><a class="a" href="${ctxPath }/listCourse.do" target="rightFrame">查看已有课程</a></li>
				<li><a class="a" href="${ctxPath }/background/course/addCourse.jsp" target="rightFrame">添加课程</a></li>
			   
			</div>
			<a class="a1" href="../logout.do"><div class="div2">
					<div class="tcht"></div>
					退出
				</div></a>
		</div>
	</div>

	<div class="right">
		<iframe id="rightFrame" name="rightFrame" width="100%" height="100%"
			scrolling="auto" marginheight="0" marginwidth="0" align="center"
			style="border: 0px solid #CCC; margin: 0; padding: 0;"></iframe>
	</div>







</body>
</html>
