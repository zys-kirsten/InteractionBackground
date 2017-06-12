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
		.menu li{float:left;width:15%;text-align:center;line-height:35px;height:35px;cursor:pointer;border-left:#cccccc solid 1px;color:#666;overflow:hidden;background:#E0E2EB;}
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
	   <li id="one4" onclick="setTab('one',4)">spoc视频数据分析</li>
	   <li id="one5" onclick="setTab('one',5)">课上练习题答题时间分析</li>
	   <li id="one3" onclick="setTab('one',3)">学生研讨课成绩分析</li>
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
<!-- spoc视频数据  start -->
  <div id="con_one_4" style="display:none;">
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
          <button onClick="showVideo();" class="btn btn-primary btn-sm">查看数据图</button>
        </td>
       </tr>
     </table>
    </td>
   </tr>
</table>
<div id="main4" style="height:400px;border:1px solid #ccc;padding:10px;"></div>  
<script type="text/javascript">
  // 基于准备好的dom，初始化echarts图表
  function showVideo(){
  var myChart = echarts.init(document.getElementById('main4'));
  var option = {
		    title: {
		        
		        
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['视频3.1','视频3.2','视频3.3','视频3.4','视频3.5','视频3.6','视频3.7','视频3.8','视频3.9']
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
		    xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data: ['0~0.1','0.1~0.2','0.2~0.3','0.3~0.4','0.4~0.5','0.5~0.6','0.6~0.7','0.7~0.8','0.8~0.9','0.9~1.0','1.0以上']
		    },
		    yAxis: {
		        type: 'value',
		        axisLabel: {
		            formatter: '{value}'
		        }
		    },
		    series: [
		        {
		            name:'视频3.1',
		            type:'line',
		            data:[6,2,3,0,1,1,3,3,2,15,35],
		           
		        },
		        {
		            name:'视频3.2',
		            type:'line',
		            data:[7,1,4,2,3,0,0,0,0,9,46],
		           
		        },
		        {
		            name:'视频3.3',
		            type:'line',
		            data:[9,0,2,1,0,3,1,2,4,15,36],
		           
		        },
		        {
		            name:'视频3.4',
		            type:'line',
		            data:[8,2,1,1,1,0,1,1,1,15,43],
		           
		        },
		        {
		            name:'视频3.5',
		            type:'line',
		            data:[9,2,2,2,0,2,2,0,1,13,41],
		           
		        },
		        {
		            name:'视频3.6',
		            type:'line',
		            data:[6,1,3,1,2,1,2,3,2,23,25],
		           
		        },
		        {
		            name:'视频3.7',
		            type:'line',
		            data:[4,1,0,6,1,1,1,2,5,17,32],
		           
		        },
		        {
		            name:'视频3.8',
		            type:'line',
		            data:[6,2,1,1,0,4,0,0,0,12,43],
		           
		        },
		        {
		            name:'视频3.9',
		            type:'line',
		            data:[7,2,0,3,2,2,0,3,2,10,40],
		           
		        },
		    ]
		};
  // 为echarts对象加载数据
   myChart.setOption(option);
  }
</script>
 
</div>
<!--  spoc视频数据   end -->
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
          <button onClick="showStuScore();" class="btn btn-primary btn-sm">查看成绩</button>
         </td>
       </tr>
     </table>
    </td>
   </tr>
</table>
<div id="mainStuScore" style="height:400px;border:1px solid #ccc;padding:10px;"></div>
<div id="mainStuSelf" style="height:400px;border:1px solid #ccc;padding:10px;"></div>
<div id="mainStuTeacher" style="height:400px;border:1px solid #ccc;padding:10px;"></div>
<div id="mainStuIn" style="height:400px;border:1px solid #ccc;padding:10px;"></div>
<div id="mainStuOut" style="height:400px;border:1px solid #ccc;padding:10px;"></div>  
<table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
      <tr>
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
          <button onClick="showStu();" class="btn btn-primary btn-sm">查看该生分析图</button>
         </td>
       </tr>
     </table>
    </td>
   </tr>
</table>
<div id="main3" style="height:400px;border:1px solid #ccc;padding:10px;"></div>  
<script type="text/javascript"> 
    function showStuScore(){
    	//学生成绩
    	 var barChar = echarts.init(document.getElementById('mainStuScore'));
         var option = {
      		   title: {  
                     text: '研讨课学生成绩分布'  
                 }, 
      		    color: ['#3398DB'],
      		    tooltip : {
      		        trigger: 'axis',
      		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
      		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      		        }
      		    },
      		    grid: {
      		        left: '3%',
      		        right: '4%',
      		        bottom: '3%',
      		        containLabel: true
      		    },
      		    xAxis : [
      		        {
      		            type : 'category',
      		            data : ['0~60', '60~70', '70~80', '80~90', '90~100'],
      		            axisTick: {
      		                alignWithLabel: true
      		            }
      		        }
      		    ],
      		    yAxis : [
      		        {
      		            type : 'value',
      		            axisLabel: {
       			            formatter: '{value}人'
       			        }
      		        }
      		    ],
      		    series : [
      		        {
      		            name:'人数',
      		            type:'bar',
      		            barWidth: '60%',
      		            data:[1, 10, 12, 5, 4]
      		        }
      		    ]
      		};

         barChar.setOption(option);
         
       //学生自评
    	 var stuSelf = echarts.init(document.getElementById('mainStuSelf'));
         var option = {
      		   title: {  
                     text: '研讨课学生自评结果分布'  
                 }, 
      		    color: ['red'],
      		    tooltip : {
      		        trigger: 'axis',
      		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
      		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      		        }
      		    },
      		    grid: {
      		        left: '3%',
      		        right: '4%',
      		        bottom: '3%',
      		        containLabel: true
      		    },
      		    xAxis : [
      		        {
      		            type : 'category',
      		            data : ['0~60', '60~70', '70~80', '80~90', '90~100'],
      		            axisTick: {
      		                alignWithLabel: true
      		            }
      		        }
      		    ],
      		    yAxis : [
      		        {
      		            type : 'value',
      		            axisLabel: {
       			            formatter: '{value}人'
       			        }
      		        }
      		    ],
      		    series : [
      		        {
      		            name:'人数',
      		            type:'bar',
      		            barWidth: '60%',
      		            data:[0, 0, 5, 10, 17]
      		        }
      		    ]
      		};

         stuSelf.setOption(option);
         
       //教师评价
    	 var stuTeacher = echarts.init(document.getElementById('mainStuTeacher'));
         var option = {
      		   title: {  
                     text: '研讨课教师评价结果分布'  
                 }, 
      		    color: ['black'],
      		    tooltip : {
      		        trigger: 'axis',
      		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
      		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      		        }
      		    },
      		    grid: {
      		        left: '3%',
      		        right: '4%',
      		        bottom: '3%',
      		        containLabel: true
      		    },
      		    xAxis : [
      		        {
      		            type : 'category',
      		            data : ['0~60', '60~70', '70~80', '80~90', '90~100'],
      		            axisTick: {
      		                alignWithLabel: true
      		            }
      		        }
      		    ],
      		    yAxis : [
      		        {
      		            type : 'value',
      		            axisLabel: {
       			            formatter: '{value}人'
       			        }
      		        }
      		    ],
      		    series : [
      		        {
      		            name:'人数',
      		            type:'bar',
      		            barWidth: '60%',
      		            data:[0, 4, 15, 10, 3]
      		        }
      		    ]
      		};

         stuTeacher.setOption(option);
         
       //组内评价成绩
    	 var stuIn = echarts.init(document.getElementById('mainStuIn'));
         var option = {
      		   title: {  
                     text: '研讨课组内评价结果分布'  
                 }, 
      		    color: ['green'],
      		    tooltip : {
      		        trigger: 'axis',
      		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
      		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      		        }
      		    },
      		    grid: {
      		        left: '3%',
      		        right: '4%',
      		        bottom: '3%',
      		        containLabel: true
      		    },
      		    xAxis : [
      		        {
      		            type : 'category',
      		            data : ['0~60', '60~70', '70~80', '80~90', '90~100'],
      		            axisTick: {
      		                alignWithLabel: true
      		            }
      		        }
      		    ],
      		    yAxis : [
      		        {
      		            type : 'value',
      		            axisLabel: {
       			            formatter: '{value}人'
       			        }
      		        }
      		    ],
      		    series : [
      		        {
      		            name:'人数',
      		            type:'bar',
      		            barWidth: '60%',
      		            data:[1, 10, 12, 5, 4]
      		        }
      		    ]
      		};

         stuIn.setOption(option);
         
       //学生组间评价成绩
    	 var stuOut = echarts.init(document.getElementById('mainStuOut'));
         var option = {
      		   title: {  
                     text: '研讨课组间评价结果分布'  
                 }, 
      		    color: ['pink'],
      		    tooltip : {
      		        trigger: 'axis',
      		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
      		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      		        }
      		    },
      		    grid: {
      		        left: '3%',
      		        right: '4%',
      		        bottom: '3%',
      		        containLabel: true
      		    },
      		    xAxis : [
      		        {
      		            type : 'category',
      		            data : ['0~60', '60~70', '70~80', '80~90', '90~100'],
      		            axisTick: {
      		                alignWithLabel: true
      		            }
      		        }
      		    ],
      		    yAxis : [
      		        {
      		            type : 'value',
      		            axisLabel: {
       			            formatter: '{value}人'
       			        }
      		        }
      		    ],
      		    series : [
      		        {
      		            name:'人数',
      		            type:'bar',
      		            barWidth: '60%',
      		            data:[1, 7, 14, 5, 5]
      		        }
      		    ]
      		};

         stuOut.setOption(option);
    }


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
                  text: '学生各项指标'  
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
<!--学生学习效果  end -->
<!-- 课上练习题答题时间  start -->
  <div id="con_one_5" style="display:none;">
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
          <button onClick="showtest();" class="btn btn-primary btn-sm">查看分析图</button>
         </td>
       </tr>
     </table>
    </td>
   </tr>
</table>
<div id="mainTest" style="height:400px;border:1px solid #ccc;padding:10px;"></div>
<br>
<div id="mainEachTest" style="height:400px;border:1px solid #ccc;padding:10px;"></div>  
<br>
<div id="mainEachTestCorrect" style="height:400px;border:1px solid #ccc;padding:10px;"></div> 
<br>
<div id="mainTestScore" style="height:400px;border:1px solid #ccc;padding:10px;"></div>  
<script type="text/javascript">  
    function showtest(){
    	//所有问题答题时间分布
       var barChar = echarts.init(document.getElementById('mainTest'));
       var option = {
    		   title: {  
                   text: '课上答题时间分布'  
               }, 
    		    color: ['#3398DB'],
    		    tooltip : {
    		        trigger: 'axis',
    		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    		        }
    		    },
    		    grid: {
    		        left: '3%',
    		        right: '4%',
    		        bottom: '3%',
    		        containLabel: true
    		    },
    		    xAxis : [
    		        {
    		            type : 'category',
    		            data : ['0s~20s', '20s~40s', '40s~60s', '60s~80s', '80s~100s', '100s~120s', '120s以上'],
    		            axisTick: {
    		                alignWithLabel: true
    		            }
    		        }
    		    ],
    		    yAxis : [
    		        {
    		            type : 'value',
    		            axisLabel: {
     			            formatter: '{value}人'
     			        }
    		        }
    		    ],
    		    series : [
    		        {
    		            name:'答题次数',
    		            type:'bar',
    		            barWidth: '60%',
    		            data:[15, 30, 52, 80, 70, 38,35]
    		        }
    		    ]
    		};

       barChar.setOption(option);
  //每个问题答题时间分布 
      var myChart = echarts.init(document.getElementById('mainEachTest'));
 	  var option = {
 			    title: {
 			    	text: '每个问题答题时间分布'
 			        
 			    },
 			    tooltip: {
 			        trigger: 'axis'
 			    },
 			    legend: {
 			        data:['问题1','问题2','问题3','问题4','问题5','问题6','问题7','问题8','问题9','问题10']
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
 			    xAxis:  {
 			        type: 'category',
 			        boundaryGap: false,
 			        data : ['0s~20s', '20s~40s', '40s~60s', '60s~80s', '80s~100s', '100s~120s', '120s以上'],
 			    },
 			    yAxis: {
 			        type: 'value',
 			        axisLabel: {
 			            formatter: '{value}人'
 			        }
 			    },
 			    series: [
 			        {
 			            name:'问题1',
 			            type:'line',
 			            data:[6,10,3,8,1,1,3],
 			           
 			        },
 			        {
 			            name:'问题2',
 			            type:'line',
 			            data:[2,6,9,2,3,5,5],
 			           
 			        },
 			        {
 			            name:'问题3',
 			            type:'line',
 			            data:[0,0,2,1,10,3,16],
 			           
 			        },
 			        {
 			            name:'问题4',
 			            type:'line',
 			            data:[1,2,13,8,1,4,3],
 			           
 			        },
 			        {
 			            name:'问题5',
 			            type:'line',
 			            data:[0,2,2,12,13,2,1],
 			           
 			        },
 			        {
 			            name:'问题6',
 			            type:'line',
 			            data:[1,0,3,11,12,3,2],
 			           
 			        },
 			        {
 			            name:'问题7',
 			            type:'line',
 			            data:[0,0,10,16,5,1,0],
 			           
 			        },
 			        {
 			            name:'问题8',
 			            type:'line',
 			            data:[0,0,4,16,8,4,0],
 			           
 			        },
 			        {
 			            name:'问题9',
 			            type:'line',
 			            data:[2,2,4,6,12,2,4],
 			           
 			        },
 			        {
 			            name:'问题10',
 			            type:'line',
 			            data:[3,8,2,0,10,6,1],
 			           
 			        },
 			    ]
 			};
 	  // 为echarts对象加载数据
 	   myChart.setOption(option);
 	  
 	//每个问题答题正确人数分布 
       var correctChart = echarts.init(document.getElementById('mainEachTestCorrect'));
  	  var option = {
  			    title: {
  			    	text: '每个问题回答正确人数'
  			        
  			    },
  			    tooltip: {
  			        trigger: 'axis'
  			    },
  			    legend: {
  			        data:['问题1','问题2','问题3','问题4','问题5','问题6','问题7','问题8','问题9','问题10']
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
  			    xAxis:  {
  			        type: 'category',
  			        boundaryGap: false,
  			        data:['问题1','问题2','问题3','问题4','问题5','问题6','问题7','问题8','问题9','问题10'],
  			    },
  			    yAxis: {
  			        type: 'value',
  			        axisLabel: {
  			            formatter: '{value}人'
  			        }
  			    },
  			  series : [
  	    		        {
  	    		            name:'答题次数',
  	    		            type:'bar',
  	    		            barWidth: '60%',
  	    		            data:[15, 30, 22, 8, 17, 30,31,25,32,29]
  	    		        }
  	    		    ]
  			};
  	  // 为echarts对象加载数据
  	   correctChart.setOption(option);
  	  
  	  //成绩统计
  	  var testScoreChart = echarts.init(document.getElementById('mainTestScore'));
 	  var option = {
 			    title : {
 			        text: '课上练习题成绩分布',
 			        x:'center'
 			    },
 			    tooltip : {
 			        trigger: 'item',
 			        formatter: "{a} <br/>{b} : {c} ({d}%)"
 			    },
 			    legend: {
 			        orient: 'vertical',
 			        left: 'left',
 			        data: ['0~60分','60~70分','70~80分','80~90分','90~100分']
 			    },
 			    series : [
 			        {
 			            name: '得分人数',
 			            type: 'pie',
 			            radius : '55%',
 			            center: ['50%', '60%'],
 			            data:[
 			                {value:5, name:'0~60分'},
 			                {value:3, name:'60~70分'},
 			                {value:14, name:'70~80分'},
 			                {value:13, name:'80~90分'},
 			                {value:7, name:'90~100分'}
 			            ],
 			            itemStyle: {
 			                emphasis: {
 			                    shadowBlur: 10,
 			                    shadowOffsetX: 0,
 			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
 			                }
 			            }
 			        }
 			    ]
 			};
 	  // 为echarts对象加载数据
 	   testScoreChart.setOption(option);
    } 
    </script> 
  </div>
<!--课上练习题答题时间   end -->
</div>
</body>
