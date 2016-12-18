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
        <form action="../addQuestion.do" method="post"> 
        
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
                     
                    <tr> 
                        <td> 
                                      题目内容：
                        </td> 
                        <td> 
                            <input type="text" name="content"/> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td> 
                                    题目所属章节 ：
                        </td> 
                        <td> 
                            <input type="text" name="chapter"/> 
                        </td> 
                    </tr> 
                     
                </table> 
            </fieldset> 
            <fieldset> 
            答案信息：
                <table> 
             
                    <tr> 
                        <td> 
                                     正确答案： 
                        </td> 
                        <td> 
                          <input type="radio" name="correct" value=/>
                        </td> 
                    </tr> 
                    
                    <tr> 
                        <td> 
                                     答案内容： 
                        </td> 
                        <td> 
                            <input type="text"name="answers[0].acontent"/> 
                        </td> 
                    </tr> 
                     
                    <tr> 
                        <td> 
                                      答案解释：
                        </td> 
                        <td> 
                            <input type="text" name="answers[0].aexplain"/> 
                        </td> 
                    </tr> 
                  
                  <tr></tr>
                     <tr> 
                        <td> 
                                     正确答案： 
                        </td> 
                        <td> 
                          <input type="radio" name="correct" />
                        </td> 
                    </tr> 
                    
                    <tr> 
                        <td> 
                                     答案内容： 
                        </td> 
                        <td> 
                            <input type="text"name="answers[1].acontent"/> 
                        </td> 
                    </tr> 
                     
                    <tr> 
                        <td> 
                                      答案解释：
                        </td> 
                        <td> 
                            <input type="text" name="answers[1].aexplain"/> 
                        </td> 
                    </tr> 
                  
                  <tr></tr>
                  
                    <tr> 
                        <td> 
                                     正确答案： 
                        </td> 
                        <td> 
                          <input type="radio" name="correct"  />
                        </td> 
                    </tr> 
                    
                    <tr> 
                        <td> 
                                     答案内容： 
                        </td> 
                        <td> 
                            <input type="text"name="answers[2].acontent"/> 
                        </td> 
                    </tr> 
                     
                    <tr> 
                        <td> 
                                      答案解释：
                        </td> 
                        <td> 
                            <input type="text" name="answers[2].aexplain"/> 
                        </td> 
                    </tr> 
                  
                  <tr></tr>
                  
                    <tr> 
                        <td> 
                                     正确答案： 
                        </td> 
                        <td> 
                          <input type="radio" name="correct"  />
                        </td> 
                    </tr> 
                    
                    <tr> 
                        <td> 
                                     答案内容： 
                        </td> 
                        <td> 
                            <input type="text"name="answers[3].acontent"/> 
                        </td> 
                    </tr> 
                     
                    <tr> 
                        <td> 
                                      答案解释：
                        </td> 
                        <td> 
                            <input type="text" name="answers[3].aexplain"/> 
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