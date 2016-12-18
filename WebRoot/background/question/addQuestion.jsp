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
     <script type="text/javascript" src="${ctxPath }/backgroundResources/js/showdate.js"></script>
    
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
<form action="${ctxPath }/addQuestion.do" name="questionForm" method="post">
<input type="hidden" id="currentUrl" value="${ctxPath }">
&nbsp;&nbsp;
<font color="#777777" size="5"><strong>请填写测试题信息: </strong></font>
<br><br>
<table width="82%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				 <tr>
				  <td>
				  <input type="hidden" id="qid" name="qid" value="">
				    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
				      <tr>
				        <th style="text-align:center;width:25%;">题目内容</th>
				        <th style="text-align:center;width:25%;">所属章节</th>
				        <th style="text-align:center;width:50%;">答案</th>
				      </tr>
					  <tr>
				        <td style="text-align:center;">
				          <textArea id="content" name="content" style="width:100%;"></textArea>
				        </td>
				        <td style="text-align:center;">
                          <select id="chapter" name="chapter" style="width:100%;">
					          <option value="1" selected="selected">第一章</option>
							  <option value ="2" >第二章</option>
							  <option value ="3" >第三章</option>
							</select>
                        </td>
				        <td style="text-align:center;">
				         <table> 
		                     <tr> 
		                       <td> 
		                                              正确： 
		                       </td> 
		                       <td> 
		                         <input type="checkbox" name="correct" value="a0"/>
		                       </td> 
		                     </tr> 
		                     <tr> 
		                       <td> 
		                                              内容： 
		                       </td> 
		                       <td> 
		                           <input type="text"name="answers[0].acontent"/> 
		                       </td> 
		                      </tr> 
		                      <tr> 
		                        <td> 
		                                               解释：
		                        </td> 
		                        <td> 
		                           <input type="text" name="answers[0].aexplain"/> 
		                        </td> 
		                      </tr>
		                      <tr></tr>
		                      <tr> 
		                       <td> 
		                                              正确： 
		                       </td> 
		                       <td> 
		                         <input type="checkbox" name="correct" value="a1"/>
		                       </td> 
		                     </tr> 
		                     <tr> 
		                       <td> 
		                                              内容： 
		                       </td> 
		                       <td> 
		                           <input type="text"name="answers[1].acontent"/> 
		                       </td> 
		                      </tr> 
		                      <tr> 
		                        <td> 
		                                               解释：
		                        </td> 
		                        <td> 
		                           <input type="text" name="answers[1].aexplain"/> 
		                        </td> 
		                      </tr>
		                      <tr></tr>
		                      <tr> 
		                       <td> 
		                                              正确： 
		                       </td> 
		                       <td> 
		                         <input type="checkbox" name="correct" value="a2"/>
		                       </td> 
		                     </tr> 
		                     <tr> 
		                       <td> 
		                                              内容： 
		                       </td> 
		                       <td> 
		                           <input type="text"name="answers[2].acontent"/> 
		                       </td> 
		                      </tr> 
		                      <tr> 
		                        <td> 
		                                               解释：
		                        </td> 
		                        <td> 
		                           <input type="text" name="answers[2].aexplain"/> 
		                        </td> 
		                      </tr>
		                      <tr></tr>
		                      <tr> 
		                       <td> 
		                                              正确： 
		                       </td> 
		                       <td> 
		                         <input type="checkbox" name="correct" value="a3"/>
		                       </td> 
		                     </tr> 
		                     <tr> 
		                       <td> 
		                                              内容： 
		                       </td> 
		                       <td> 
		                           <input type="text"name="answers[3].acontent"/> 
		                       </td> 
		                      </tr> 
		                      <tr> 
		                        <td> 
		                                               解释：
		                        </td> 
		                        <td> 
		                           <input type="text" name="answers[3].aexplain"/> 
		                        </td> 
		                      </tr>
		                      <tr></tr> 
                           </table> 
                        </td>
				      </tr>
				     </table>
				 <tr align="center">
					<td >
					  <%--   <a href="${ctxPath }/addQuestion.do" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>确定</a>&nbsp;&nbsp;  --%>
					   <input type="submit" value="确定" class="btn btn-primary">
					    <a href="#" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span>取消</a>
					</td>
				</tr>
			</table>
<br>
</form>