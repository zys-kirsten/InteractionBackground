<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html> 
    <head> 
        <title>regist</title> 
    </head>

    <body> 
        <form action="../register.do" method="post"> 
        
            <fieldset> 
                <table> 
                    <tr> 
                        <td> 
                                     账号： 
                        </td> 
                        <td> 
                          <input type="text" name="taccount"/> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td> 
                                     密码： 
                        </td> 
                        <td> 
                            <input type="text" name="tpwd"/> 
                        </td> 
                    </tr> 
                     
                    <tr> 
                        <td> 
                                      昵称：
                        </td> 
                        <td> 
                            <input type="text" name="tname"/> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td> 
                                     电话 ：
                        </td> 
                        <td> 
                            <input type="text" name="tphone"/> 
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