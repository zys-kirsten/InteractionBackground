<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>listCourse</title>
	<script type="text/javascript">  
      <%request.setAttribute("ctxPath", request.getContextPath());%>  
    </script>
    <link href="${ctxPath }/Resources/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctxPath }/Resources/css/mmss.css"/>
    <link rel="stylesheet" href="${ctxPath }/Resources/css/font-awesome.min.css"/>
</head>
<body>
           <div class="col-xs-9.5">
                <div class="input-group line left">
                    <input type="text" class="form-control" placeholder="输入关键字搜索" aria-describedby="basic-addon2">
                    <span class="input-group-addon" id="basic-addon2"><a href="#"><span class="glyphicon glyphicon-search"></span></a></span>
                </div>
               
                <br/><br/>
                <table class="table table-bordered table-striped text-center bg-info">
                    <thead >
                    <tr class="info">
                        <th class="text-center">课程编号</th>
                        <th class="text-center">课程名称</th>
                        <th class="text-center">开课学期</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                     <c:forEach items="${sessionScope.courses}" var="c">
                    <tr>
                        <td>${c.cnumber}</td>
                        <td>${c.cname}</td>
                        <td>${c.cterm}</td>
                        <td>
                            <a href="#" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#add"data-whatever="编辑">修改</a>
                            <a href="#" class="btn btn-primary btn-sm">删除</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <ul class="pagination right">
                    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                    </li>
                    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                </ul>
            </div>
            <!--右侧内容结束-->
        </div>
    </div>
            
       <%-- <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		        <td height="20" bgcolor="#FFFFFF" class="STYLE10" colspan="8">
		        <div align="left">
                     <a href="#"><i class="icon-list"></i> 子选项4</a>
                </div>
				</td>
		  </tr> 
		  <tr>
		    <td><table width="100%" border="1" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
		      <tr>
		        <td width="10%" height="20" bgcolor="d3eaef" ><div align="center"><span>课程编号</span></div></td>
		        <td width="10%" height="20" bgcolor="d3eaef"><div align="center"><span>课程名称</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef"><div align="center"><span>开课学期</span></div></td>
		        <td width="10%" height="20" bgcolor="d3eaef"><div align="center"><span>操作</span></div></td>
		      </tr>
		      <c:forEach items="${sessionScope.courses}" var="c">
		        <tr>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center">${c.cnumber}</div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${c.cname}</div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${c.cterm}</div></td>
			        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
			        	<a href="leaveBillAction_input.action?id=1" >修改</a>
						<a href="leaveBillAction_delete.action?id=1" >删除</a>
						<a href="orkflowAction_startProcess.action?id=1" >申请请假</a>
					</div></td>
			    </tr> 
			   </c:forEach>
		    </table></td>
		  </tr>
	</table> --%>
</body>
</html>