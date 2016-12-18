    <%@ page language="java" contentType="text/html; charset=UTF-8"  
        pageEncoding="UTF-8"%>  
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
    <html>  
    <head>  
    <script type="text/javascript">  
      <%request.setAttribute("ctxPath", request.getContextPath());%>  
    </script>
    <script type="text/javascript" src="${ctxPath }/backgroundResources/js/jquery2.js"></script>  
    <script type="text/javascript">  
    $(function() {  
        var rpath = $('#rpath').val();  
        rpath=rpath.substring(0,rpath.length-11);//获取index.jsp所在的目录（物理路径）  
        var servletpath = $('#servletpath').val();
        var arr=servletpath.split("NewFile.jsp");  
        var ppp=arr[0].substring(0,arr[0].length-1);//先用split方法将index.jsp去掉，再去掉最后一个“/”  
        alert("999"+arr);
        //向Servlet发送请求  
        $.ajax({  
            type : 'post',  
            url : '${ctxPath }/classModuleCfg.do',  
            cache : false,  
            data : {  
                rpath:rpath  
            },  
            dataType : 'json',  
            success : function(result) {  
                for(var i=0;i<result.length;i++){  
                    //获取到此目录下的文件名，拼接出访问路径，构造a标签，然后放到files的div里  
                    $('#files').append('<a href="'+ppp+'/'+result[i]+'">'+result[i]+'</a><br/>');  
                }  
                  
            },  
            error : function(result) {  
                alert("error");  
            }  
        });  
  
    });  
</script>  
    </head>  
        <body>  
       <hr color="red">  
       <!--  获取index.jsp页面的物理路径 -->  
       <input  id="rpath" value=<%=application.getRealPath(request.getServletPath())%> type="hidden" />  
       <!--  获取访问路径，可能是index.jsp也可能是index.jsp所在的文件夹 -->  
       <input  id="servletpath" value=<%=request.getRequestURI()%> type="hidden" />  
       <!-- 用于放置文件的链接a标签 -->  
       <div id="files"></div>  
    </body>  
    </html>  