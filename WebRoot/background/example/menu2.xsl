<?xml version="1.0" encoding="GBK"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" indent="no"/>
	<xsl:param name="id" />
	<xsl:param name="boxid" />
	<xsl:param name="menutype" />
	<xsl:template match="/" >
	<div  >
		<table cellspacing= "0"   cellpadding= "0" border="0" >
		 <xsl:apply-templates select="//*[@id=$id]" mode="root" /> 
		 </table>
	</div>
	</xsl:template>
	<!--root -->
	<xsl:template match="*" mode="root" >
<!--css start-->	
	    <style>
	     <!--menubar css-->
				.menubar{
					font-size: 6mm;
					background-color:#15A6E7;
					cursor:pointer;
					height:40px; 
		  		    text-align: center;
		  		    line-height: 35px;
		  		    color: #FFFFFF;
		  		    padding:0px 1px ;
				}
				.menubar .spancell{
				   border-right:#000 solid 0px;
				   border-top:#000 solid 0px;
				   border-bottom:#000 solid 0px;
				   padding:0px 16px ;
				   width:100%;
				   height:100%; 
				   v-align:middle;
				  
				
				}
					.menubar .barover{
						background-color:#15B6F8;
				}
				.menubar .barout{
				 	background-color:##15A6E7;
				 
				}
				
			 <!--menulist css-->
				.menulist{
					font-size: 6mm;
					cursor:pointer;
					background-color:#F2F2F2;
					
				}
				.menulist .listinner{
				
					margin:0px;  
					width:100%;
					height:100%;
				}
				.menulist .listout{
					background-color:#F2F2F2;
					font-size: 6mm;
				}
				.menulist .listover{
					background-color:#F9F9F9;
				}
	
				.menulist .listout .midspan{
					padding:0px 0px 6px 0px;
					background-color:#F2F2F2;
					font-size: 6mm;
				}
				.menulist .listover .midspan{
					padding:0px 0px 6px 0px;
					background-color:#F9F9F9;
					font-size: 6mm;
				}
				.menulist .leftspan{
					width:20px;
					display:inline-block;
					height:100%;
					background-color:#E0E0E0;
					margin:0px;
					padding:0px 0px 6px 0px;
				}
				.menulist .midspan{
					padding:0px 0px 6px 0px;
						font-size: 6mm;
				}
				
				.menulist .rightspan{
						padding:0px 0px 6px 0px;
						margin:10px;
				}
					

			</style>
<!--css end -->
			
		<xsl:choose>
		<xsl:when test="$menutype='menubar'">
		  <tr>
			<xsl:apply-templates select="./*" mode="menubar" /> 
	    </tr>
		</xsl:when>
		<xsl:otherwise>
			<xsl:apply-templates select="./*" mode="menulist" /> 
		</xsl:otherwise>
	</xsl:choose>
		 
  </xsl:template>
    <!-- 菜单导航条-->
		<xsl:template match="*" mode="menubar" >
			    <td class="menubar" nowrap="true"   >
			    <div name="barinner" style="height:100%" onmouseover="this.className='barover'" onmouseout="this.className='barout'" >
				    <div class="spancell" id="{@id}"  boxid="{$boxid}" code="{@code}" type="{@type}" target="{@target}" exec="{@exec}" style="align: center;"  >
				     <xsl:value-of select="@name" /> 
				    </div>
			    </div>
			    </td>
  	</xsl:template>
    <!-- 菜单细目-->
  			<xsl:template match="*" mode="menulist" >
		    <tr class="menulist" >
		    <td nowrap="true" class="listout"    onmouseover="this.className='listover'" onmouseout="this.className='listout'" >
			    <div class="listinner"  id="{@id}"   boxid="{$boxid}" code="{@code}" type="{@type}" target="{@target}" border="1" exec="{@exec}" >
				    <span class="leftspan" >.</span>
				    <span class="midspan" >
				    	<xsl:value-of select="@name" /> 
		        	</span>
		        <span class="rightspan" >
		        <xsl:if test="@leaf='0'">
		        <IMG src="web/sub.gif" width="6" height="7" style="vertical-align:middle;align:right"  /> 
		        </xsl:if>
		        </span>
			    </div>
		    </td>
		    </tr>
  	</xsl:template>
	
</xsl:stylesheet>




