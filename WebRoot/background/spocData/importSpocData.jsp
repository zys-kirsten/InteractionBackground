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
    
    <style type="text/css">
        body {font-size: 20px;
            padding-bottom: 40px;
            background-color:#e9e7ef;
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


    </style>
</head>
<body>
<br>
<form action="${ctxPath }/importSpocScore.do" method="post" enctype="multipart/form-data"  >
<input type="hidden" id="currentUrl" value="${ctxPath }">
   <table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
	 <tr>
	  <td>
	    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
		  <tr>
	        <td style="text-align:left;width:30%;" >
	          <font color="#777777" size="5"><strong>  请选择对应的研讨课：</strong></font>
	        </td>
	        <td style="text-align:center;width:70%;" >
                <select  id="seId" name="seId" style="width:100%;">
		          <c:forEach items="${seminarVos}" var="svo">
		            <option value="${svo.seId }">${svo.seName }</option>
		          </c:forEach>
				</select>
            </td>
	      </tr>
	     </table>
	    </td>
	   </tr>
   </table>
&nbsp;&nbsp;
<br><br>
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
      <tr>
        <td class="tableleft">导入在线学习平台练习题数据</td>
        <td>
           <input type="file" name="file" />
           <button type="submit">确定导入</button>
        </td>
      </tr>
      <%-- <tr>
       <td>
        <chart palette="4">
			<set label="Item A" value="4"/>
			<set label="Item B" value="5"/>
			<set label="Item C" value="2"/>
			<set label="Item D" value="4"/>
			<set label="Item E" value="5" isSliced="1"/>
			<set label="Item F" value="5" isSliced="1"/>
			<set label="Item G" value="4"/>
			<set label="Item H" value="5"/>
			<set label="Item I" value="2"/>
	    </chart>
       </td>
      </tr> --%>
     <tr>
       <td class="tableleft">导入在线学习平台讨论区数据</td>
       <td>
          <input type="file" name="file" />
          <button type="submit">确定导入</button>
       </td>
     </tr>	 
	 <!-- <tr>
        <td class="tableleft">导入在线学习平台观看视频数据</td>
        <td>
           <input type="file" name="file" />
           <button type="submit">确定导入</button>
        </td>
      </tr> -->
 	</table>
  </td>
 </tr>
</table>
<br>
</form>

<form action="${ctxPath }/importSpocDiscuss.do" method="post" enctype="multipart/form-data"  >
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
 <tr>
  <td>
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
     <tr>
       <td class="tableleft">导入在线学习平台讨论区数据</td>
       <td>
          <input type="file" name="file" />
          <button type="submit">确定导入</button>
       </td>
     </tr>	 
 	</table>
  </td>
 </tr>
</table>
</form>
</body>