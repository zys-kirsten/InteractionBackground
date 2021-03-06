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
</head>
<body>
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
<div id="main" style="height:300px;border:1px solid #ccc;padding:10px;"></div>  
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
</body>
