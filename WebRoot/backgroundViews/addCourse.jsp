<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addCourse</title>
	<script type="text/javascript">  
      <%request.setAttribute("ctxPath", request.getContextPath());%>  
    </script>
    <link href="${ctxPath }/Resources/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctxPath }/Resources/css/mmss.css"/>
    <link rel="stylesheet" href="${ctxPath }/Resources/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctxPath }/Resources/css/style.css"/>
</head>
<body>
<!--  <div class="col-xs-9.5">
              </div> -->
<div class="right_content">          
     <h2>添加新广告</h2>
     
         <div class="form">
         <form action="backadvtmt.servlet?action=add" method="post" class="niceform" onsubmit="javascript:return checkAddAd();">
         
                <fieldset>
                    <dl>
                        <dt><label for="email">广告描述:</label></dt>
                        <dd><input type="text" name="description" id="description" size="54"  class="ipt"/></dd>
                    </dl>
                    <dl>
                        <dt><label for="password">广告类型:</label></dt>
                        <dd><input type="radio" name="type" id="" value="滚动图片" /><label class="check_label">滚动图片</label>
                            <input type="radio" name="type" id="" value="新产品展示" /><label class="check_label">新产品展示</label>
                        </dd>
                    </dl>
                    
                    <dl>
                        <dt><label for="password">商品名称:</label></dt>
                        <dd>
						  <select size="1" name="pid"  class="select" id="pnm">
						  <option >---请选择商品名称---</option> 
                           </select>                           
                        </dd>
                    </dl>                                       
                   
                     <dl class="submit">
                    <input type="submit" name="submit" id="submit" value="添加" />
                     </dl>
          
                </fieldset>
                
         </form>
         </div>  
    
    </div>

</body>
</html>