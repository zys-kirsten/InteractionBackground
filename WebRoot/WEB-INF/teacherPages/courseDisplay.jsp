<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 
    <head> 
        <title>courseDisplay</title> 
    </head> 
    <body> 
        
        <table border="1"> 
            <tr> 
                <th> 
                    任课教师 
                </th> 
                <th> 
                    课程编号 
                </th> 
                <th> 
                   课程名称 
                </th> 
                <th> 
                   开课学期
                </th> 
            </tr> 
           <c:forEach items="${courses}" var="c">
                <tr> 
                    <td> 
                         ${c.tname} 
                    </td> 
                    <td> 
                        ${c.cnumber} 
                    </td> 
                    <td> 
                        ${c.cname} 
                    </td> 
                    <td> 
                        ${c.cterm} 
                    </td> 
                </tr> 
            </c:forEach> 
        </table> 
    </body> 
</html>