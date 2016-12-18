<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 
    <head> 
        <title>Adobocode : Sample Spring MVC using JSTL iteration</title> 
    </head> 
    <body> 
        <h2>&nbsp; 
            Adobocode : Person List 明明用了JSTL啊！ 
        </h2> 
        <table border="1"> 
            <tr> 
                <th> 
                    Id 
                </th> 
                <th> 
                    Name 
                </th> 
                <th> 
                    email 
                </th> 
                <th> 
                    phone 
                </th> 
            </tr> 
           <c:forEach items="${students}" var="s">
                <tr> 
                    <td> 
                         ${s.sid} 
                    </td> 
                    <td> 
                        ${s.sname} 
                    </td> 
                    <td> 
                        ${s.semail} 
                    </td> 
                    <td> 
                        ${s.sphone} 
                    </td> 
                </tr> 
            </c:forEach> 
        </table> 
    </body> 
</html>