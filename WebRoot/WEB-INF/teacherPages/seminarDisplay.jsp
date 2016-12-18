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
                    课程名称 
                </th> 
                <th> 
                   研讨课名称 
                </th> 
                 <th> 
                    研讨课主题 
                </th> 
                <th> 
                   研讨课人数上限 
                </th> 
                <th> 
                   研讨课人数上限 
                </th>
                <th> 
                   开课时间
                </th> 
            </tr> 
           <c:forEach items="${seminarVos}" var="se">
                <tr> 
                    <td> 
                         ${se.tname} 
                    </td> 
                    <td> 
                        ${se.cname} 
                    </td> 
                    <td> 
                        ${se.seName} 
                    </td> 
                    <td> 
                        ${se.seTheme} 
                    </td> 
                    <td> 
                        ${se.seUp} 
                    </td> 
                    <td> 
                        ${se.seDown} 
                    </td> 
                    <td> 
                        ${se.seTime} 
                    </td> 
                </tr> 
            </c:forEach> 
        </table> 
    </body> 
</html>