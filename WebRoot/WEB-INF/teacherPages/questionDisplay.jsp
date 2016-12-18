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
                    题目内容 
                </th> 
                <th> 
                   题目所属章节 
                </th> 
                 <th> 
                    答案信息
                </th> 
            </tr> 
           <c:forEach items="${questionVos}" var="se">
                <tr> 
                    <td> 
                         ${se.cname} 
                    </td> 
                    <td> 
                        ${se.content} 
                    </td> 
                    <td> 
                        ${se.chapter} 
                    </td> 
                    <td> 
                       <c:forEach items="${se.answers}" var="a">
                          答案内容： ${a.acontent}
                           <br/>
                      答案解释：     ${a.aexplain}
                           <br/>
                    是否是正确答案：       ${a.correct}
                     <br/>
                       </c:forEach>
                       
                    </td> 
                </tr> 
            </c:forEach> 
        </table> 
    </body> 
</html>