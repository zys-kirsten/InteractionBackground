<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../Resources/img/logo.png"/>
    <title>SPOC翻转课堂后台管理</title>
    <link href="../Resources/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="../Resources/css/mmss.css"/>
    <link rel="stylesheet" href="../Resources/css/font-awesome.min.css"/>
    <script src="../Resources/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="../Resources/js/checkuser.js"></script>
</head>
<body>
<header>
    <div class="container-fluid navbar-fixed-top bg-primary">
        <ul class="nav navbar-nav  left">
            <li class="text-white h4">
                &nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-leaf"></span>&nbsp;&nbsp;
                <font size="5" ><b>SPOC翻转课堂后台管理</b></font>
            </li>
        </ul>
        <ul class="nav navbar-nav nav-pills right ">
            <li>
               <c:set var="t" scope="session" value="${teacher}"/>
                 <c:if test="${not empty t}">
                   <br/>
                    <b><font size="4" color="white"><c:out value="${t.tname}"/></font></b>&nbsp;&nbsp;&nbsp;&nbsp;
                 </c:if>
            </li>
            <li class="bg-info dropdown">
                <a class="dropdown-toggle" href="#" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user"></span>&nbsp;<span></span><span class="caret"></span>
                </a>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li class="text-center"><a href="#"><span class="text-primary">账号设置</span></a></li>
                    <li class="divider"><a href="#"></a></li>
                    <li class="text-center"><a href="../logout.do"><span class="text-primary">退出</span></a></li>
                </ul>
            </li>
        </ul>
    </div>
</header>

<section>
    <div class="container-fluid">
        <div class="row ">
            <!--左侧导航开始-->
            <div class="col-xs-2 bg-blue">
                <br/>
                <div class="panel-group sidebar-menu" id="accordion" aria-multiselectable="true">
                    <div class="panel panel-default menu-first menu-first-active">
                        <a data-toggle="collapse" data-parent="#accordion" href="index.html" aria-expanded="true"
                           aria-controls="collapseOne">
                            <i class="icon-home icon-large"></i> 主页
                        </a>
                    </div>
                    <div class="panel panel-default menu-first">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
                           aria-controls="collapseOne">
                            <i class="icon-book icon-large"></i> 课程信息管理
                        </a>
                        <div id="collapseOne" class="panel-collapse collapse " >
                            <ul class="nav nav-list menu-second">
                                <li><a href="../listCourse.do?tid=${teacher.tid}" target="iframe"><i class="icon-list"></i>课程信息列表</a></li>
                                <li><a href="addCourse.jsp" target="iframe"><i class="icon-edit"></i>添加课程</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="panel panel-default menu-first">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"
                           aria-expanded="false" aria-controls="collapseTwo">
                            <i class="glyphicon glyphicon-book"></i> 研讨课信息管理
                        </a>
                        
                        <div id="collapseTwo" class="panel-collapse collapse">
                            <ul class="nav nav-list menu-second">
                                <li><a href="#"><i class="icon-user"></i> 子选项1</a></li>
                                <li><a href="#"><i class="icon-edit"></i> 子选项2</a></li>
                                <li><a href="#"><i class="icon-trash"></i> 子选项3</a></li>
                                <li><a href="#"><i class="icon-list"></i> 子选项4</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="panel panel-default menu-first">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree"
                           aria-expanded="false" aria-controls="collapseThree">
                            <i class="glyphicon glyphicon-tasks"></i> 研讨班信息管理
                        </a>
                        <div id="collapseThree" class="panel-collapse collapse">
                            <ul class="nav nav-list menu-second">
                                <li><a href="#"><i class="icon-user"></i> 子选项1</a></li>
                                <li><a href="#"><i class="icon-edit"></i> 子选项2</a></li>
                                <li><a href="#"><i class="icon-trash"></i> 子选项3</a></li>
                                <li><a href="#"><i class="icon-list"></i> 子选项4</a></li>
                            </ul>
                        </div>
                     
                     <div class="panel panel-default menu-first">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
                           aria-controls="collapseOne">
                            <i class="glyphicon glyphicon-list-alt"></i> 测试题管理
                        </a>
                        <div id="collapseOne" class="panel-collapse collapse " >
                            <ul class="nav nav-list menu-second">
                                <li><a href="p1.html"><i class="icon-user"></i> 表格p1</a></li>
                                <li><a href="p2.html"><i class="icon-edit"></i> 图表p2</a></li>
                                <li><a href="p3.html"><i class="icon-trash"></i> p3</a></li>
                                <li><a href="#"><i class="icon-list"></i> 子选项4</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="panel panel-default menu-first">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
                           aria-controls="collapseOne">
                            <i class="glyphicon glyphicon-thumbs-up"></i> 评价单管理
                        </a>
                        <div id="collapseOne" class="panel-collapse collapse " >
                            <ul class="nav nav-list menu-second">
                                <li><a href="p1.html"><i class="icon-user"></i> 表格p1</a></li>
                                <li><a href="p2.html"><i class="icon-edit"></i> 图表p2</a></li>
                                <li><a href="p3.html"><i class="icon-trash"></i> p3</a></li>
                                <li><a href="#"><i class="icon-list"></i> 子选项4</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="panel panel-default menu-first">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
                           aria-controls="collapseOne">
                            <i class="icon-book icon-large"></i> 其他管理
                        </a>
                        <div id="collapseOne" class="panel-collapse collapse " >
                            <ul class="nav nav-list menu-second">
                                <li><a href="p1.html"><i class="icon-user"></i> 表格p1</a></li>
                                <li><a href="p2.html"><i class="icon-edit"></i> 图表p2</a></li>
                                <li><a href="p3.html"><i class="icon-trash"></i> p3</a></li>
                                <li><a href="#"><i class="icon-list"></i> 子选项4</a></li>
                            </ul>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <!--左侧导航结束-->
            <!----------------------------------------------------------------------------------------------------->
            <!--右侧内容开始-->
            <div class="container-fluid"> 
             
             <div class="col-xs-10">
                <br/>
                <table height="455" width="100%">
                   <tr>
                    <td> 
    	             <!-- <iframe id="iframe" name="iframe" src="main.jsp" scrolling="no" width="1090" height="455"></iframe> -->
    	             <iframe  id="iframe" name="iframe" frameborder="0" height="100%" width="100%"  scrolling="no"></iframe>
                   </td>
                 </tr>
                 </table>
            </div>
            </div>
            <!--右侧内容结束-->
        </div>
    </div>
</section>

<script src="../Resources/js/jquery-1.11.3.js"></script>
<script src="../Resources/js/bootstrap.js"></script>
<script>

    $(function () {
        $('dt').click(function () {
            $(this).parent().find('dd').show().end().siblings().find('dd').hide();
        });
    });
</script>
</body>
</html>