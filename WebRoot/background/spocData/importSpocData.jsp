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
	   <li id="one1" onclick="setTab('one',1)">导入spoc测试题数据</li>
	   <li id="one2" onclick="setTab('one',2)">导入spoc讨论区数据</li>
	  </ul>
	 </div>
	 <br><br><br>
  <div class="menudiv">
<!-- spoc测试题数据  start -->
   <div id="con_one_1">
   <form action="${ctxPath }/importSpocScore.do" method="post" enctype="multipart/form-data"  >
	  <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
		 <tr>
		  <td>
		    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
			  <tr>
		        <td style="text-align:left;width:25%;" >
		          <font color="#777777" size="4"><strong>  请选择对应的研讨课：</strong></font>
		        </td>
		        <td style="text-align:center;width:35%;" >
	                <select  id="seId" name="seId" style="width:100%;">
			          <c:forEach items="${seminarVos}" var="svo">
			            <option value="${svo.seId }">${svo.seName }</option>
			          </c:forEach>
					</select>
	            </td>
	            <td style="text-align:center;width:40%;">
		           <input type="file" name="file" />
		           <button type="submit">确定导入</button>
		        </td>
	         </tr>
	      </table>
	    </td>
	   </tr>
   </table>
  </form>
</div>
<!-- spoc测试题数据  end -->
<!-- spoc讨论区  start -->
  <div id="con_one_2" style="display:none;">
     <form action="${ctxPath }/importSpocDiscuss.do" method="post" enctype="multipart/form-data"  >
		<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
		 <tr>
		  <td>
		    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
		     <tr>
		       <td style="text-align:left;width:50%;">
		           <input type="file" name="file" />
		           <button type="submit">确定导入</button>
		        </td>
		     </tr>	 
		 	</table>
		  </td>
		 </tr>
		</table>
	</form>
  </div>
<!--  spoc讨论区   end -->
</div>
</body>