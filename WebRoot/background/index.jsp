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
		 <c:out value="${course.cname}"/>&nbsp;&nbsp;
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			研讨课信息管理
			</div>
			<div class="div3">
				<ul>
					<li><a class="a" href="${ctxPath }/listSeminarByCourse.do?cid=${course.cid}" target="rightFrame">查看研讨课</a></li>
				    <li><a class="a" href="${ctxPath }/background/seminar/addSeminar.jsp" target="rightFrame">添加研讨课</a></li>
					
				</ul>
			</div>
			 <div class="div2">
				<div class="spgl"></div>
				&nbsp;&nbsp;
				测试题管理
			</div>
			<div class="div3">
				<ul>
					<li><a class="a" href="${ctxPath }/listCourseQuestion.do?cid=${course.cid}" target="rightFrame">查看测试题</a></li>
					<li><a class="a" href="${ctxPath }/listSeminarBeforeAddQuestion.do?cid=${course.cid}" target="rightFrame">添加测试题</a></li>
				</ul>
			</div>
			<div class="div2">
				<div class="spgl"></div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  评价因素管理
			</div>
			<div class="div3">
				<ul>
					<li><a class="a" href="${ctxPath }/ShowStudentAchieveWeight.do?cid=${course.cid}" target="rightFrame">学生学习效果评价设置</a></li>
					<li><a class="a" href="${ctxPath }/ShowUnquantizationWeight.do?eeid=${evaluationelements.get(0).eeid}" target="rightFrame">非量化指标评价设置</a></li>
					<li><a class="a" href="${ctxPath }/ShowQuantizationWeight.do?eeid=${evaluationelements.get(1).eeid}" target="rightFrame">量化指标评价设置</a></li>
					<li><a class="a" href="${ctxPath }/ShowSelfEvalWeight.do?eeid=${evaluationelements.get(2).eeid}" target="rightFrame">学生自评设置</a></li>
					<li><a class="a" href="${ctxPath }/ShowInEvalWeight.do?eeid=${evaluationelements.get(3).eeid}" target="rightFrame">组内评价设置</a></li>
					<li><a class="a" href="${ctxPath }/ShowOutEvalWeight.do?eeid=${evaluationelements.get(4).eeid}" target="rightFrame">组间评价设置</a></li>
					<li><a class="a" href="${ctxPath }/ShowTeacherEvalWeight.do?eeid=${evaluationelements.get(5).eeid}" target="rightFrame">教师评价设置</a></li>
					<li><a class="a" href="${ctxPath }/listEvaluationElement.do?cid=${course.cid}" target="rightFrame">评价因素管理</a></li>
					<%-- <li><a class="a" href="${ctxPath }/background/evaluation/addEvaluationElement.jsp" target="rightFrame">添加评价因素</a></li>
					<li><a class="a" href="${ctxPath }/listEvaluation.do?cid=${course.cid}" target="rightFrame">查看评价单</a></li>
					<li><a class="a" href="${ctxPath }/background/evaluation/addEvaluation.jsp" target="rightFrame">添加评价单</a></li> --%>
				</ul>
			</div>
			<div class="div2">
				<div class="yhgl"></div>
			
				配置管理
			</div>
			<div class="div3">
				<ul>
				    <li><a class="a" href="${ctxPath }/listClassModule.do?cid=${course.cid}" target="rightFrame">查看课堂模式</a></li>
					<li><a class="a" href="${ctxPath }/configClassModuleEval.do?cid=${course.cid}" target="rightFrame">配置课堂模式</a></li>
				</ul>
			</div> 
			
			<c:forEach items="${sessionScope.componentVos}" var="cvs">
			<div class="div2">
				<div class="spgl"></div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				${cvs.mname}
			</div>
			<div class="div3">
				<ul>
				<c:forEach items="${cvs.functioncomponentVos}" var="cv">
					<li><a class="a" href="${ctxPath }/${cv.url}" target="rightFrame">${cv.fcname }</a></li>
				</c:forEach>
				</ul>
			</div>
		</c:forEach>	
			
			<a class="a1" href="../logout.do"><div class="div2">
					<div class="tcht"></div>
					退出后台
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
