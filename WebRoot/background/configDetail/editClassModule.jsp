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
//select设定选中
 var kk = document.getElementById("teamLeader").options;  
 for (var j=0; j<kk.length; j++) {  
	 if (kk[j].value=='${classModuleVo.teamLeader}') {  
		 kk[j].selected=true;  
		 break;  
	 }  
 } 
 
 var aa = document.getElementById("proPerson").options;  
 for (var j=0; j<aa.length; j++) {  
	 if (aa[j].value=='${classModuleVo.proPerson}') {  
		 aa[j].selected=true;  
		 break;  
	 }  
 }
 
 var bb = document.getElementById("census").options;  
 for (var j=0; j<bb.length; j++) {  
	 if (bb[j].value=='${classModuleVo.census}') {  
		 bb[j].selected=true;  
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
	   <li id="one3" onclick="setTab('one',3)">评价配置</li>
	   <li id="one4" onclick="setTab('one',4)">随机测试题</li>
	   <li id="one5" onclick="setTab('one',5)">学生抢答</li>
	   <li id="one6" onclick="setTab('one',6)">应用到的研讨课</li>
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
				        <td class="tableleft" style="text-align:center;">分组个数</td>
				        <td style="text-align:center;"><input type="text" id="groupNum" name="groupNum" style="width:100%;" value="${classModuleVo.groupNum}"/></td>
				        <td class="tableleft" style="text-align:center;">随机组长</td>
				        <td style="text-align:center;">
					        <select id="teamLeader" name="teamLeader" style="width:100%;">
					          <option value="0" selected="selected">否</option>
							  <option value ="1" >是</option>
							</select>
						</td>
				      </tr>
				      <tr>
				        <td class="tableleft" style="text-align:center;">每组最多人数</td>
				        <td style="text-align:center;"><input type="text" id="grpMaxNum" name="grpMaxNum" style="width:100%;" value="${classModuleVo.grpMaxNum}"/></td>
				        <td class="tableleft" style="text-align:center;">每组最少人数</td>
				        <td style="text-align:center;"><input type="text" id="grpMinNum" name="grpMinNum" style="width:100%;" value="${classModuleVo.grpMinNum}"/></td>
				      </tr>		
				 	</table>
				  </td>
				 </tr>
				 <tr align="center"><td>分组因素权值设定</td></tr>
				 <tr>
				  <td>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <tr>
				        <th style="text-align:center;">学生特征信息</th>
				        <th style="text-align:center;">分组时所占权值</th>
				        <th style="text-align:center;">学生特征信息</th>
				        <th style="text-align:center;">分组时所占权值</th>
				      </tr>
					  <tr>
				        <td class="tableleft" style="text-align:center;">学习成绩</td>
				        <td style="text-align:center;"><input type="text" id="score" name="score" style="width:100%;" value="${classModuleVo.score}"/></td>
				        <td class="tableleft" style="text-align:center;">学习能力</td>
				        <td style="text-align:center;"><input type="text" id="ability" name="ability" style="width:100%;" value="${classModuleVo.ability}"/></td>
				      </tr>
				       <tr>
				        <td class="tableleft" style="text-align:center;">活跃程度</td>
				        <td style="text-align:center;"><input type="text" id="activity" name="activity" style="width:100%;" value="${classModuleVo.activity}"/></td>
				        <td class="tableleft" style="text-align:center;">综合素质</td>
				        <td style="text-align:center;"><input type="text" id="quality" name="quality" style="width:100%;" value="${classModuleVo.quality}"/></td>
				      </tr>
				       <tr>
				        <td class="tableleft" style="text-align:center;">性别</td>
				        <td style="text-align:center;"><input type="text" id="sex" name="sex" style="width:100%;" value="${classModuleVo.sex}"/></td>
				      </tr>
				 	</table>
				  </td>
				 </tr>
			</table>
		  </div>
		<!-- 分组  end -->
	    <!-- 评价  start -->
		  <div id="con_one_3" style="display:none;">
		    <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
					  <tr>
				        <td style="text-align:center;">评价名称</td>
				        <td style="text-align:center;">
				        <c:forEach items="${sessionScope.evaluationVos}" var="evos">
				           <input type="checkbox" name="evalue" value="${evos.ename}"/>${evos.ename}<br> 
				        </c:forEach>
                        </td>
				      </tr>
				      <tr>
				        <td style="text-align:center;">本模式包含</td>
				        <td style="text-align:center;">
				        <c:forEach items="${classModuleVo.evaluations}" var="es">
				             ${es.ename}<br> 
				        </c:forEach>
                        </td>
				      </tr>
				    </table>
			</table>
		  </div>
		<!-- 评价  end -->
	    <!-- 随机测试题  start -->
		  <div id="con_one_4" style="display:none;">
		    <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="cid" name="cid" value=""/>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
					  <tr>
				        <td class="tableleft" style="text-align:center;">题目个数</td>
				        <td style="text-align:center;"><input type="text" id="proNum" name="proNum" style="width:100%;" value="${classModuleVo.proNum}"/></td>
				        <td class="tableleft" style="text-align:center;">题目分值</td>
				        <td style="text-align:center;"><input type="text" id="proScore" name="proScore" style="width:100%;" value="${classModuleVo.proScore}"/></td>
				      </tr>
				      <tr>
				        <td class="tableleft" style="text-align:center;">答题时长</td>
				        <td style="text-align:center;"><input type="text" id="proTime" name="proTime" style="width:100%;" value="${classModuleVo.proTime}"/></td>
				        <td class="tableleft" style="text-align:center;">回答对象</td>
				        <td style="text-align:center;">
					        <select id="proPerson" name="proPerson" style="width:100%;">
					          <option value ="全部同学" selected="selected">全部同学</option>
					          <option value="一个同学" >一个同学</option>
							  <option value ="一组同学" >一组同学</option>
							</select>
						</td>
				      </tr>		
				 	</table>
				  </td>
				 </tr>
			</table>
		  </div>
		<!-- 随机测试题  end -->
	     <!-- 学生抢答  start -->
		  <div id="con_one_5" style="display:none;">
		     <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="cid" name="cid" value=""/>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
					  <tr>
				        <td class="tableleft" style="text-align:center;">题目个数</td>
				        <td style="text-align:center;"><input type="text" id="resNum" name="resNum" style="width:100%;" value="${classModuleVo.resNum}"/></td>
				        <td class="tableleft" style="text-align:center;">抢答分值</td>
				        <td style="text-align:center;"><input type="text" id="resScore" name="resScore" style="width:100%;" value="${classModuleVo.resScore}"/></td>
				      </tr>
				       <tr>
				        <td class="tableleft" style="text-align:center;">统计方式</td>
				        <td style="text-align:center;">
					        <select id="census" name="census" style="width:100%;">
					          <option value="条形图" selected="selected">条形图</option>
							  <option value ="饼图" >饼图</option>
							  <option value ="折线图" >折线图</option>
							</select>
						</td>
				      </tr>
				 	</table>
				  </td>
				 </tr>
			</table>
		  </div>
		 <!-- 学生抢答  end -->
		 <!-- 应用到的研讨课  start -->
		  <div id="con_one_6" style="display:none;">
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