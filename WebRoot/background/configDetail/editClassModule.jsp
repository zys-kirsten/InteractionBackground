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
		.menu li{float:left;width:14%;text-align:center;line-height:35px;height:35px;cursor:pointer;border-left:#cccccc solid 1px;color:#666;overflow:hidden;background:#E0E2EB;}
		.menu li.off{background:#FFFFFF;color:#336699;font-weight:bold;}
		.menudiv{height:200px;border-left:#cccccc solid 0px;border-right:#cccccc solid 1px;border-top:0;background:background-color: #DDDDDD}
		.menudiv div{padding:25px;line-height:28px;}
    </style>
<script type="text/javascript">
 $(document).ready(function(){
	 var kk = document.getElementsByName("groupTime");  
	 for (var j=0; j<kk.length; j++) {  
		 if (kk[j].value=='${classModuleVo.groupTime}') {  
			 kk[j].checked=true;  
			 break;  
		 }  
	 }  
 }); 
</script>   
<script type="text/javascript">
function setTab(name,cursel){
 cursel_0=cursel;
 for(var i=1; i<=links_len; i++){
  var menu = document.getElementById(name+i);
  var menudiv = document.getElementById("con_"+name+"_"+i);
  if(i==cursel){
   menu.className="off";
   menudiv.style.display="block";
  }
  else{
   menu.className="";
   menudiv.style.display="none";
  }
 }
}
onload=function(){
   var links = document.getElementById("mytab").getElementsByTagName('li')
   links_len=links.length;
   setTab('one',1);
}
</script>
</head>
<body>

   <div class="menu" id="mytab">
	  <ul>
	   <li id="one1" onclick="setTab('one',1)">课堂模式配置</li>
	   <li id="one2" onclick="setTab('one',2)">分组配置</li>
	   <li id="one3" onclick="setTab('one',3)">随机测试题</li>
	   <li id="one4" onclick="setTab('one',4)">应用到的研讨课</li>
	  </ul>
	 </div>
	 <br>
	 <form action="${ctxPath }/addClassModule.do"  method="post">
		 <div class="menudiv">
		 <c:set var="t" scope="session" value="${classModuleVo}"/>
		 <c:if test="${not empty t}">
		  <!-- 课堂模式  start -->
		  <div id="con_one_1">
		    <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="cmid" name="cmid" value="${classModuleVo.cmid}"/>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <tr>
				        <td class="tableleft" style="text-align:center;">课堂模式名称</td>
				        <td style="text-align:center;"><input type="text" id="moduleName" name="moduleName" style="width:100%;" value="${classModuleVo.moduleName}"/></td>
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
	   <!-- 课堂模式  end -->
	   <!-- 分组  start -->
		  <div id="con_one_2" style="display:none;">
		     <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
					  <tr>
				        <td class="tableleft" style="text-align:center;">分组时间</td>
				        <td style="text-align:center;">
				            <label><input name="groupTime" type="radio" value="1"  checked="checked"/>课上分组</label> 
					        <label><input name="groupTime" type="radio" value="0" />课前分组 </label> 
						</td>
				      </tr>
					  <tr>
				        <td class="tableleft" style="text-align:center;">分组个数</td>
				        <td style="text-align:center;"><input type="text" id="groupNum" name="groupNum" style="width:100%;" value="${classModuleVo.groupNum}"/></td>
				      </tr>
				 	</table>
				  </td>
				 </tr>
			</table>
		  </div>
		<!-- 分组  end -->
	    <!-- 随机测试题  start -->
		  <div id="con_one_3" style="display:none;">
		    <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="cid" name="cid" value=""/>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
					  <tr>
				        <td class="tableleft" style="text-align:center;">题目个数</td>
				        <td style="text-align:center;"><input type="text" id="proNum" name="proNum" style="width:100%;" value="${classModuleVo.proNum}"/></td>
				      </tr>
				      <tr>
				        <td class="tableleft" style="text-align:center;">答题时长</td>
				        <td style="text-align:center;"><input type="text" id="proTime" name="proTime" style="width:100%;" value="${classModuleVo.proTime}"/></td>
				      </tr>		
				 	</table>
				  </td>
				 </tr>
			</table>
		  </div>
		<!-- 随机测试题  end -->
		 <!-- 应用到的研讨课  start -->
		  <div id="con_one_4" style="display:none;">
		    <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
					  <tr>
				        <td style="text-align:center;">本门课程的研讨课</td>
				        <td style="text-align:center;">
				        <c:forEach items="${sessionScope.seminarVos}" var="svos">
				           <input type="checkbox" name="seminar" value="${svos.seName}"/>${svos.seName}<br> 
				        </c:forEach>
                        </td>
				      </tr>
				      <tr>
				        <td style="text-align:center;">本模式包含</td>
				        <td style="text-align:center;">
				        <c:forEach items="${classModuleVo.seminars}" var="ss">
				           ${ss.seName}<br> 
				        </c:forEach>
                        </td>
				      </tr>
				    </table>
			</table>
		  </div>
		<!-- 应用到的研讨课  end -->
		</c:if>
		 </div>
   </form>
</body>
</html>