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
                  课程名称 
                </th> 
                <th> 
                   评价名称 
                </th> 
                <th> 
                  评价描述 
                </th>
                <th> 
                  评价算分规则 
                </th> 
                 <th> 
                    评价标准信息
                </th> 
            </tr> 
           <c:forEach items="${evaluationVos}" var="ev">
                <tr> 
                    <td> 
                         ${ev.cname} 
                    </td> 
                    <td> 
                        ${ev.ename} 
                    </td> 
                    <td> 
                        ${ev.edescription} 
                    </td> 
                    <td> 
                        ${ev.ecalcul} 
                    </td> 
                    <td> 
                       <c:forEach items="${ev.scoreshows}" var="ss">
                          页面显示： ${ss.escoreShow}
                           <br/>
                      实际分值：  ${ss.escore}
                           <br/>
                       </c:forEach>
                       
                    </td> 
                </tr> 
            </c:forEach> 
        </table> 
    </body> 
</html>