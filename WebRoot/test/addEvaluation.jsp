<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 
    <head> 
        <title>regist</title> 
    </head>

    <body> 
        <form action="../addEvaluation.do" method="post"> 
        
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
                                      评价名称：
                        </td> 
                        <td> 
                            <input type="text" name="ename"/> 
                        </td> 
                    </tr> 
                    
                    <tr> 
                        <td> 
                                    评价描述 ：
                        </td> 
                        <td> 
                            <input type="text" name="edescription"/> 
                        </td> 
                    </tr> 
                    
                     <tr> 
                        <td> 
                                    评价算分规则 ：
                        </td> 
                        <td> 
                            <input type="text" name="ecalcul"/> 
                        </td> 
                    </tr> 
                     
                </table> 
            </fieldset> 
            <fieldset> 
            评价标准设定：
                <table> 
             
                
                    <tr> 
                        <td> 
                                     页面显示分值： 
                        </td> 
                        <td> 
                            <input type="text"name="scoreshows[0].escoreShow"/> 
                        </td> 
                        
                        <td> 
                                     实际对应分值： 
                        </td> 
                        <td> 
                            <input type="text"name="scoreshows[0].escore"/> 
                        </td> 
                    </tr> 
                    
                    
                    <tr> 
                        <td> 
                                     页面显示分值： 
                        </td> 
                        <td> 
                            <input type="text"name="scoreshows[1].escoreShow"/> 
                        </td> 
                        
                        <td> 
                                     实际对应分值： 
                        </td> 
                        <td> 
                            <input type="text"name="scoreshows[1].escore"/> 
                        </td> 
                    </tr> 
                    
                    
                    <tr> 
                        <td> 
                                     页面显示分值： 
                        </td> 
                        <td> 
                            <input type="text"name="scoreshows[2].escoreShow"/> 
                        </td> 
                        
                        <td> 
                                     实际对应分值： 
                        </td> 
                        <td> 
                            <input type="text"name="scoreshows[2].escore"/> 
                        </td> 
                    </tr> 
                    
                    
                    <tr> 
                        <td> 
                                     页面显示分值： 
                        </td> 
                        <td> 
                            <input type="text"name="scoreshows[3].escoreShow"/> 
                        </td> 
                        
                        <td> 
                                     实际对应分值： 
                        </td> 
                        <td> 
                            <input type="text"name="scoreshows[3].escore"/> 
                        </td> 
                    </tr> 
                    
                    <tr></tr>
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