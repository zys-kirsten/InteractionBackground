<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 
    <head> 
        <title>success</title> 
    </head> 
    <body> 
        <table border="1"> 
            <tr> 
                <th> 
                    Id 
                </th> 
                <th> 
                    pwd
                </th> 
                <th> 
                    name 
                </th> 
                <th> 
                    phone 
                </th> 
            </tr> 
     
                <tr> 
                    <td> 
                         ${teacher.tid} 
                    </td> 
                    <td> 
                        ${teacher.tpwd} 
                    </td> 
                    <td> 
                        ${teacher.tname} 
                    </td> 
                    <td> 
                        ${teacher.tphone} 
                    </td> 
                </tr> 
           
        </table> 
    </body> 
</html>