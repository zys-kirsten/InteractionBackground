<?xml version="1.0" encoding="UTF-8"?>   
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">  
      
    <xsl:template match="/">  
    <html>  
          
        <h2>  
            Description of teams...............  
            hahahh
        </h2>  
          
        <body> 
         
            <table width="100%" border="1dpx" class="table table-striped mt40">  
                <tr bgcolor="#5cacee">  
                    <td align="left">Mainkind</td>  
                    <td align="left">Monkey</td>  
                    <td align="left">Pig</td>  
                    <td align="left">celestial</td>  
                    <td class="last-col" align="left">Horse</td>  
                </tr>  
              
            <xsl:for-each select="teams/team">  
                <tbody>  
                <tr>  
                    <td><xsl:value-of select="mankind"/></td>  
                    <td><xsl:value-of select="monkey"/></td>  
                    <td><xsl:value-of select="pig"/></td>  
                    <td><xsl:value-of select="celestial"/></td>  
                    <td><xsl:value-of select="horse"/></td>  
                </tr>  
                </tbody>  
                
            </xsl:for-each>  
            </table>  
        </body>  
    </html>  
      
    </xsl:template>  
    </xsl:stylesheet>  