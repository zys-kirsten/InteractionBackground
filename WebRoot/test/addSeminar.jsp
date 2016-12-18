<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html> 
    <head> 
        <title>regist</title> 
    </head>

    <body> 
        <form action="../addSeminar.do" method="post"> 
        
            <fieldset> 
                <table> 
                    <tr> 
                        <td> 
                                     课程id： 
                        </td> 
                        <td> 
                          <input type="text" name="cid"/> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td> 
                                     研讨课名称： 
                        </td> 
                        <td> 
                            <input type="text" name="seName"/> 
                        </td> 
                    </tr> 
                     
                    <tr> 
                        <td> 
                                      研讨课主题：
                        </td> 
                        <td> 
                            <input type="text" name="seTheme"/> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td> 
                                    研讨课人数上限 ：
                        </td> 
                        <td> 
                            <input type="text" name="seUp"/> 
                        </td> 
                    </tr> 
                      <tr> 
                        <td> 
                                    研讨课人数下限 ：
                        </td> 
                        <td> 
                            <input type="text" name="seDown"/> 
                        </td> 
                    </tr> 
                      <tr> 
                        <td> 
                                    研讨课开展时间 ：
                        </td> 
                        <td> 
                            <input type="text" name="seTime"/> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td></td> 
                        <td> 
                            <input type="submit"   value="添加" /> 
                        </td> 
                    </tr> 
                </table> 
            </fieldset> 
        </form> 
    </body> 
</html>