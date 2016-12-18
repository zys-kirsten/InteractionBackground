<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html> 
    <head> 
        <title>regist</title> 
    </head>

    <body> 
        <form action="../addCoures.do" method="post"> 
        
            <fieldset> 
                <table> 
                    <tr> 
                        <td> 
                                     教师id： 
                        </td> 
                        <td> 
                          <input type="text" name="teacher.tid"/> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td> 
                                     课程编号： 
                        </td> 
                        <td> 
                            <input type="text" name="cnumber"/> 
                        </td> 
                    </tr> 
                     
                    <tr> 
                        <td> 
                                      课程名称：
                        </td> 
                        <td> 
                            <input type="text" name="cname"/> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td> 
                                     开课学期 ：
                        </td> 
                        <td> 
                            <input type="text" name="cterm"/> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td></td> 
                        <td> 
                            <input type="submit"   value="注册" /> 
                        </td> 
                    </tr> 
                </table> 
            </fieldset> 
        </form> 
    </body> 
</html>