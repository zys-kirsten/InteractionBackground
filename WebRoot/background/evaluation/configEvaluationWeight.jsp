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
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/weightConfig.js"></script>
    
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
	   <li id="one1" onclick="setTab('one',1)">学生学习效果评价设置</li>
	   <li id="one2" onclick="setTab('one',2)">量化指标设置</li>
	   <li id="one3" onclick="setTab('one',3)">非量化指标设置</li>
	   <li id="one4" onclick="setTab('one',4)">学生自评设置</li>
	   <li id="one5" onclick="setTab('one',5)">学生评价设置</li>
	   <li id="one6" onclick="setTab('one',6)">教师评价设置</li>
	  </ul>
	 </div>
	 <br>
	 <form action="${ctxPath }/addClassModule.do"  method="post">
		 <div class="menudiv">
		 
		  <!-- 学生学习效果评价设置  start -->
		  <div id="con_one_1">
		    <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="cmid" name="cmid" value=""/>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <tr>
				        <td class="tableleft" style="text-align:center;">量化指标</td>
				         <td style="text-align:center;">
					        <select id="quantization" name="quantization" style="width:100%;" onblur="selectCheck1();">
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
				      </tr>
				       <tr>
				        <td class="tableleft" style="text-align:center;">非量化指标</td>
				         <td style="text-align:center;">
					        <select id="unquantization" name="unquantization" style="width:100%;"onblur="selectCheck2();">
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
	   <!-- 量化指标配置  start -->
		  <div id="con_one_2" style="display:none;">
		     <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <tr>
				        <th style="text-align:center;"></th>
				        <th style="text-align:center;">课堂练习题成绩</th>
				        <th style="text-align:center;">课堂抢答次数</th>
				        <th style="text-align:center;">课堂投票题成绩</th>
				        <th style="text-align:center;">教师额外加分</th>
				      </tr>
					  <tr>
				        <td class="tableleft" style="text-align:center;">课堂练习题成绩</td>
				        <td class="tableleft" style="text-align:center;">1</td>
				        <td style="text-align:center;">
					        <select id="proPerson" name="proPerson" style="width:100%;">
					          <option value ="1.0" selected="selected">1</option>
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
							  <option value ="1.0/7.0" >1/7</option>
							  <option value= "1.0/8.0" >1/8</option>
							  <option value ="1.0/9.0" >1/9</option>
							</select>
						</td>
				        <td style="text-align:center;">
					        <select id="proPerson" name="proPerson" style="width:100%;">
					          <option value ="1.0" selected="selected">1</option>
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
							  <option value ="1.0/7.0" >1/7</option>
							  <option value= "1.0/8.0" >1/8</option>
							  <option value ="1.0/9.0" >1/9</option>
							</select>
						</td>
				        <td style="text-align:center;">
					        <select id="proPerson" name="proPerson" style="width:100%;">
					          <option value ="1.0" selected="selected">1</option>
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
							  <option value ="1.0/7.0" >1/7</option>
							  <option value= "1.0/8.0" >1/8</option>
							  <option value ="1.0/9.0" >1/9</option>
							</select>
						</td>
				      </tr>
				        <tr>
				        <td class="tableleft" style="text-align:center;"></td>
				        <td class="tableleft" style="text-align:center;">课堂抢答次数</td>
				        <td class="tableleft" style="text-align:center;">1</td>
				        <td style="text-align:center;">
					        <select id="proPerson" name="proPerson" style="width:100%;">
					          <option value ="1.0" selected="selected">1</option>
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
							  <option value ="1.0/7.0" >1/7</option>
							  <option value= "1.0/8.0" >1/8</option>
							  <option value ="1.0/9.0" >1/9</option>
							</select>
						</td>
				        <td style="text-align:center;">
					        <select id="proPerson" name="proPerson" style="width:100%;">
					          <option value ="1.0" selected="selected">1</option>
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
							  <option value ="1.0/7.0" >1/7</option>
							  <option value= "1.0/8.0" >1/8</option>
							  <option value ="1.0/9.0" >1/9</option>
							</select>
						</td>
				      </tr>
				        <tr>
				        <td class="tableleft" style="text-align:center;"></td>
				        <td class="tableleft" style="text-align:center;"></td>
				        <td class="tableleft" style="text-align:center;">课堂投票题成绩</td>
				        <td class="tableleft" style="text-align:center;">1</td>
				        <td style="text-align:center;">
					        <select id="proPerson" name="proPerson" style="width:100%;">
					          <option value ="1.0" selected="selected">1</option>
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
							  <option value ="1.0/7.0" >1/7</option>
							  <option value= "1.0/8.0" >1/8</option>
							  <option value ="1.0/9.0" >1/9</option>
							</select>
						</td>
				      </tr>
				       <tr>
				        <td class="tableleft" style="text-align:center;"></td>
				        <td class="tableleft" style="text-align:center;"></td>
				        <td class="tableleft" style="text-align:center;"></td>
				        <td class="tableleft" style="text-align:center;">教师额外加分</td>
				        <td class="tableleft" style="text-align:center;">1</td>
				      </tr>
				 	</table>
				  </td>
				 </tr>
			</table>
		  </div>
		<!-- 量化指标配置  end -->
	    <!-- 非量化指标配置  start -->
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
				    </table>
			</table>
		  </div>
		<!-- 非量化指标配置  end -->
	    <!-- 学生自评设置  start -->
		  <div id="con_one_4" style="display:none;">
		    <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="cid" name="cid" value=""/>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
					  <tr>
				        <td class="tableleft" style="text-align:center;">题目个数</td>
				        <td style="text-align:center;"><input type="text" id="proNum" name="proNum" style="width:100%;"/></td>
				        <td class="tableleft" style="text-align:center;">题目分值</td>
				        <td style="text-align:center;"><input type="text" id="proScore" name="proScore" style="width:100%;"/></td>
				      </tr>
				      <tr>
				        <td class="tableleft" style="text-align:center;">答题时长</td>
				        <td style="text-align:center;"><input type="text" id="proTime" name="proTime" style="width:100%;"/></td>
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
		<!-- 学生自评设置  end -->
	     <!-- 学生评价设置 start -->
		  <div id="con_one_5" style="display:none;">
		     <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="cid" name="cid" value=""/>
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
					  <tr>
				        <td class="tableleft" style="text-align:center;">题目个数</td>
				        <td style="text-align:center;"><input type="text" id="resNum" name="resNum" style="width:100%;"/></td>
				        <td class="tableleft" style="text-align:center;">抢答分值</td>
				        <td style="text-align:center;"><input type="text" id="resScore" name="resScore" style="width:100%;"/></td>
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
		 <!-- 学生评价设置  end -->
		 <!-- 教师评价设置  start -->
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
				    </table>
			</table>
		  </div>
		<!-- 教师评价设置  end -->
		 </div>
   </form>
</body>
</html>