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
                   研讨课名称 
                </th> 
                 <th> 
                    研讨课主题 
                </th> 
                <th> 
                   开课时间
                </th> 
                <th> 
                   选课学生姓名 
                </th>
                
            </tr> 
           <c:forEach items="${seminarclass}" var="sc">
                <tr> 
                    <td> 
                        ${sc.seName} 
                    </td> 
                    <td> 
                        ${sc.seTheme} 
                    </td> 
                    <td> 
                        ${sc.seTime} 
                    </td> 
                    <td> 
                        ${sc.sname} 
                    </td> 
                
                </tr> 
            </c:forEach> 
        </table> 
    </body> 
</html>