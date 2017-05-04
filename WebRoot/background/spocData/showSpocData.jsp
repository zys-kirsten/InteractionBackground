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
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/echarts.js"></script>
    
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
	   <li id="one1" onclick="setTab('one',1)">spoc测试题数据分析</li>
	   <li id="one2" onclick="setTab('one',2)">spoc讨论区数据分析</li>
	   <li id="one3" onclick="setTab('one',3)">学生学习效果分析</li>
	  </ul>
	 </div>
	 <br><br>
  <div class="menudiv">
<!-- spoc测试题数据  start -->
   <div id="con_one_1">
   <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
	  <tr>
        <td style="text-align:left;width:30%;" >
          <font color="#777777" size="5"><strong>  请选择对应的研讨课：</strong></font>
        </td>
        <td style="text-align:center;width:30%;" >
               <select  id="seId" name="seId" style="width:100%;">
	          <c:forEach items="${seminarVos}" var="svo">
	            <option value="${svo.seId }">${svo.seName }</option>
	          </c:forEach>
			</select>
        </td>
        <td style="text-align:left;width:40%;" >
          <button onClick="showData();" class="btn btn-primary btn-sm">查看数据图</button>
        </td>
       </tr>
     </table>
    </td>
   </tr>
</table>
<div id="main" style="height:400px;border:1px solid #ccc;padding:10px;"></div>  
<script type="text/javascript">  
    // 直接页面写的调用ajax的方法  
    function callbackFn(myChart,jsonURL){  
    	var s = $('#seId option:selected') .val();
        $.ajax({  
             url:jsonURL, 
             data:{"seId":s},
             dataType:"json",  
             success:function(jsonData){  
                 myChart.setOption({  
                    series: [{  
                        // 根据名字对应到相应的系列  
                        name: '分数', 
                        type:'pie',
                        radius:'55%',
                        center:['50%','60%'],
                        data: [{value:jsonData.data[0],name:'0分'},
                               {value:jsonData.data[1],name:'1分'},
                               {value:jsonData.data[2],name:'2分'},
                               {value:jsonData.data[3],name:'3分'},
                               {value:jsonData.data[4],name:'4分'},
                               {value:jsonData.data[5],name:'5分'},
                               ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }]  
                });  
                // 设置加载等待隐藏  
                myChart.hideLoading();  
             }  
         });  
    }  
      // 初始化echar报表的方法  
      function initReport(myChart){  
            
          // 显示标题，图例和空的坐标轴  
          myChart.setOption({  
              title: {  
                  text: '学生测试题回答',
                  x:'center'
              },  
              tooltip: {
            	  trigger: 'item',
                  formatter: "{a} <br/>{b} : {c} ({d}%)"
              },  
              legend: {  
            	  orient: 'vertical',
                  left: 'left',
                  data: ['0分','1分','2分','3分','4分','5分']  
              },  
              series: [{  
                  name: '分数',  
                  type: 'pie',  
                  data: []  
              }]  
          });  
      }  
          
     function createTestReport(showDivId,jsonURL){  
         var myChart = echarts.init(document.getElementById(showDivId));  
         // 初始化report对象  
         initReport(myChart);  
      //   myChart.showLoading({text: '正在努力的读取数据中...'  });  
         // 调用后台获取json数据  
         callbackFn(myChart,jsonURL);  
     }  
    </script>   
         
    <script type="text/javascript">  
    function showData(){
       var showDivId = 'main';  
       var jsonURL = "${ctxPath }/generateGraph.do";  
       createTestReport(showDivId,jsonURL);  
    } 
    </script> 
</div>
<!-- spoc测试题数据  end -->
<!-- spoc讨论区  start -->
  <div id="con_one_2" style="display:none;">
 <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
	  <tr>
        <td style="text-align:left;width:30%;" >
          <font color="#777777" size="5"><strong>请选择某一学生：</strong></font>
        </td>
        <td style="text-align:center;width:30%;" >
               <select  id="sid" name="sid" style="width:100%;">
	          <c:forEach items="${students}" var="svo">
	            <option value="${svo.sid }">${svo.sname }</option>
	          </c:forEach>
			</select>
        </td>
        <td style="text-align:left;width:40%;" >
          <button onClick="showDiscuss();" class="btn btn-primary btn-sm">查看数据图</button>
        </td>
       </tr>
     </table>
    </td>
   </tr>
</table>
<div id="main2" style="height:400px;border:1px solid #ccc;padding:10px;"></div>  
<script type="text/javascript">  
    // 直接页面写的调用ajax的方法  
    function callbackFn2(myChart2,jsonURL2){  
    	var stu = $('#sid option:selected') .val();
        $.ajax({  
             url:jsonURL2, 
             data:{"sid":stu},
             dataType:"json",  
             success:function(jsonData){  
                 myChart2.setOption({  
                    xAxis: {  
                        data: jsonData[0].categories  
                    },  
                    series: [
                       {  
                        // 根据名字对应到相应的系列  
                        name: '主题',
                        type:'line',
                        data: jsonData[0].data  
                        },
                       {  
                           // 根据名字对应到相应的系列  
                           name: '评论数目', 
                           type:'line',
                           data: jsonData[1].data  
                        },
                        {  
                            // 根据名字对应到相应的系列  
                            name: '回复', 
                            type:'line',
                            data: jsonData[2].data  
                         },
                        {  
                            // 根据名字对应到相应的系列  
                            name: '被顶', 
                            type:'line',
                            data: jsonData[3].data  
                         },
                    ]  
                });  
                // 设置加载等待隐藏  
                myChart2.hideLoading();  
             }  
         });  
    }  
      // 初始化echar报表的方法  
      function initReport2(myChart2){  
            
          // 显示标题，图例和空的坐标轴  
          myChart2.setOption({  
              title: {  
                  text: '学生讨论区'  
              },  
              tooltip: {trigger:'axis'},  
              legend: {  
                  data:['主题','评论数目','回复','被顶']  
              },
              toolbox: {
                  show: true,
                  feature: {
                      dataZoom: {
                          yAxisIndex: 'none'
                      },
                      dataView: {readOnly: false},
                      magicType: {type: ['line', 'bar']},
                      restore: {},
                      saveAsImage: {}
                  }
              },
              xAxis: {
            	  type: 'category',
                  boundaryGap: false,
                  data: []  
              },  
              yAxis: {
            	  type: 'value',
                  axisLabel: {
                      formatter: '{value} 个'
                  }
              },  
              series: [{  
                  name: '数目',  
                  type: 'line',  
                  data: []  
              }]  
          });  
      }  
          
     function createTestReport2(showDivId2,jsonURL2){  
         var myChart2 = echarts.init(document.getElementById(showDivId2));  
         // 初始化report对象  
         initReport2(myChart2);  
      //   myChart.showLoading({text: '正在努力的读取数据中...'  });  
         // 调用后台获取json数据  
         callbackFn2(myChart2,jsonURL2);  
     }  
    </script>   
         
    <script type="text/javascript">  
    function showDiscuss(){
       var showDivId2 = 'main2';  
       var jsonURL2 = "${ctxPath }/generateDiscussGraph.do";  
       createTestReport2(showDivId2,jsonURL2);  
    } 
    </script> 
  </div>
<!--  spoc讨论区   end -->
<!-- 学生学习效果  start -->
  <div id="con_one_3" style="display:none;">
 <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
      <tr>
        <td style="text-align:left;width:20%;" >
          <font color="#777777" size="3"><strong>  请选择对应的研讨课：</strong></font>
        </td>
        <td style="text-align:center;width:20%;" >
               <select  id="seId3" name="seId3" style="width:100%;">
	          <c:forEach items="${seminarVos}" var="svo">
	            <option value="${svo.seId }">${svo.seName }</option>
	          </c:forEach>
			</select>
        </td>
        <td style="text-align:left;width:20%;" >
          <font color="#777777" size="3"><strong>请选择某一学生：</strong></font>
        </td>
        <td style="text-align:center;width:20%;" >
               <select  id="sid3" name="sid3" style="width:100%;">
	          <c:forEach items="${students}" var="svo">
	            <option value="${svo.sid }">${svo.sname }</option>
	          </c:forEach>
			</select>
        </td>
         <td style="text-align:left;width:20%;" >
          <button onClick="showStu();" class="btn btn-primary btn-sm">查看分析图</button>
         </td>
       </tr>
     </table>
    </td>
   </tr>
</table>
<div id="main3" style="height:400px;border:1px solid #ccc;padding:10px;"></div>  
<script type="text/javascript">  
    // 直接页面写的调用ajax的方法  
    function callbackFn3(myChart3,jsonURL3){  
    	var stu3 = $('#sid3 option:selected') .val();
    	var seId3 = $('#seId3 option:selected') .val();
        $.ajax({  
             url:jsonURL3, 
             data:{"sid":stu3,"seId":seId3},
             dataType:"json", 
             success:function(jsonData){  
                 myChart3.setOption({  
                	 series: [
                	          {
                	              name: '雷达',
                	              type: 'radar',
                	              data: [
                	                  {
                	                	 
                	                      value: jsonData.data,
                	                      name: '学生实际情况',
                	                  },
                	                  {
                	                      value:  [60, 60, 60, 60, 60, 60],
                	                      name: '合格标准',
                	                  },
                	              ]
                	          },
                	      ]
                });  
                // 设置加载等待隐藏  
                myChart3.hideLoading();  
             }  
         });  
    }  
      // 初始化echar报表的方法  
      function initReport3(myChart3){  
            
          // 显示标题，图例和空的坐标轴  
          myChart3.setOption({  
              title: {  
                  text: '学生学习效果'  
              },  
              tooltip: {},
              legend: {
                  data:['学生实际情况','合格标准']  
              },
              radar:  {
                          indicator: [
                              { name: '量化指标',max:100 },
                              { name: '自评',max:100  },
                              { name: '组内评价',max:100  },
                              { name: '组间评价',max:100  },
                              { name: '教师评价' ,max:100 }
                          ],
              },
                         
                  series: [{  
                      name: '雷达',  
                      type: 'radar',  
                      data: []  
                  }] 
          });  
      }  
          
     function createTestReport3(showDivId3,jsonURL3){  
         var myChart3 = echarts.init(document.getElementById(showDivId3));  
         // 初始化report对象  
         initReport3(myChart3);  
      //   myChart.showLoading({text: '正在努力的读取数据中...'  });  
         // 调用后台获取json数据  
         callbackFn3(myChart3,jsonURL3);  
     }  
    </script>   
         
    <script type="text/javascript">  
    function showStu(){
       var showDivId3 = 'main3';  
       var jsonURL3 = "${ctxPath }/generateStuGraph.do";  
       createTestReport3(showDivId3,jsonURL3);  
    } 
    </script> 
  </div>
<!--  spoc讨论区   end -->
</div>
</body>
