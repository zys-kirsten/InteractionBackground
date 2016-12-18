
function get(file){
	var rest;
		$.ajax({  
		         type : "GET",  
		         url : file,   
		         cache:false,
		         async : false, 
		         dataType: "text",
		         success : function(data){  
		           rest = data;  
		          }  
		          }); 
	return rest;   
}

importjs("XmlObject.js",null,get);




function $C(tagName){
	return document.createElement(tagName);
}
function Menu(){
	this.xmlObj = new XmlObject();
	this.menudom;
	this.divchain=[];
	this.showdivid;
	this.menustat="in";
	this.rootleft;
	this.roottop;
	this.defaultxsl;
	this.defaultStrxsl;
};
/**
加载 xml 菜单文件
*/
 Menu.prototype.load=function(xml){
	this.xmlObj.load(xml);
	this.menudom=this.xmlObj.xmldom.documentElement;
};
/**
加载 xml字符串 菜单文件
*/
 Menu.prototype.loadxml=function(xml){
	  this.xmlObj.loadxml(xml);
		this.menudom=this.xmlObj.xmldom.documentElement;
};
/**
加载 菜单解析xsl 菜单文件
*/
 Menu.prototype.loadxsl=function(xml){
	 
	  this.xmlObj.loadxsl(xml);
	  this.defaultxsl=xml;
	 
	  this.defaultStrxsl=this.xmlObj.xsldom.xml;
	  
	  
};
 Menu.prototype.initmenu=function(id){
	    this.addattrId(this.menudom,id);
      this.setleaf(this.menudom);
};
 Menu.prototype.show=function(showid){

	 
		this.showdivid=showid;
	    var rootid=this.random();
	   	this.initmenu(rootid);			
	   	var e_divxy=document.getElementById(this.showdivid);
	 	this.rootleft=e_divxy.offsetLeft;
	    this.roottop=e_divxy.offsetTop;
      this.setMenuDiv(rootid,0,0,'menubar');
      var menu=this;
      //document.onclick=menu.onclickdoc(menu);
    
      //解决同一页面多个菜单
      menu.bindEvent(document,"click",menu.onclickdoc(menu));
   
      
};
 Menu.prototype.show_topleft=function(top,left){

        var divroot = $C("div");
		divroot.style.position="relative";
		divroot.id=this.random();
		divroot.style.left=left;
		divroot.style.top=top;
		divroot.style.padding=0;
		divroot.style.border="#000 solid 0px";
		
		document.body.appendChild(divroot);
		this.showdivid=divroot.id;
	    var rootid=this.random();	
	   	this.initmenu(rootid);			
	   	var e_divxy=document.getElementById(this.showdivid);
	   	this.rootleft=e_divxy.offsetLeft;
	    this.roottop=e_divxy.offsetTop;
        this.setMenuDiv(rootid,0,0,'menubar');
        var menu=this;
        //document.onclick=menu.onclickdoc(menu);
        //解决同一页面多个菜单
        menu.bindEvent(document,"click",menu.onclickdoc(menu));
      
};
/*单击document触发事件*/
 Menu.prototype.onclickdoc=function(menu){
	function dclick(){   	
	   menu.close(menu);
   }
   return dclick;
};
/**关闭菜单*/
 Menu.prototype.close=function(menu)
{
	if(menu.menustat=="out"&& menu.divchain.length>1){
      		 menu.removeChildDiv(menu.divchain[0]);
    }
};
/*判断是否是在菜单内*/
 Menu.prototype.contains=function(el){

 	if (el == null) return false;
 	var menurootel=document.getElementById(this.showdivid);
	if (menurootel === el) return true;
	while (el = el.parentNode) {
		if (el === menurootel) return true;
	}
	return false;
       
};
/**
给 菜单节点添加 id属性值
*/
 Menu.prototype.addattrId=function(node,uniqueid)
{
		
	if(node.nodeType=="1"&&!node.getAttribute("id"))node.setAttribute("id", uniqueid);
	for(var i=0; node.hasChildNodes()&& i<node.childNodes.length ;i++ ){
		
	 this.addattrId(node.childNodes[i],this.random());
	}
} 
/**
标记菜单是否是叶子节点
*/
 Menu.prototype.setleaf=function(node)
{
	 if(node.nodeType=="1"){
		if(node.hasChildNodes())node.setAttribute("leaf", "0")
		else node.setAttribute("leaf", "1");
	 }
	for(var i=0; node.hasChildNodes()&& i<node.childNodes.length ;i++ ){
	 this.setleaf(node.childNodes[i]);
	}
}
/**
获取菜单节点
*/
 Menu.prototype.getMenuNode=function(id){

	var node;
	
	if(ie){
		 node=this.menudom.selectSingleNode("//node[@id='"+id+"']");
	}else{
		
		  var oEvaluator = new XPathEvaluator();  
          var node = oEvaluator.evaluate("//node[@id='"+id+"']", this.xmlObj.xmldom, null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;  
	}
	
	return node;
}
/**
是否是叶子节点
*/
 Menu.prototype.isleaf=function(nodeid)
{
    var node =this.getMenuNode(nodeid);
    if(node){
    	var leaf= node.getAttribute("leaf");
    	return (leaf=="1")
    }
    return false;
    
}
/**
获取子菜单div 及其定位,菜单类型(menutype)有  menubar(菜单导航条)、menulist(菜单细目)
*/
 Menu.prototype.setMenuDiv=function(id,divtop,divleft,menutype){

	  var e_id = document.getElementById(id);
	  
	  //1、删除单击子box
	  //如果存在id元素
	  if(e_id){
	  	this.removeChildDiv(e_id.getAttribute("boxid"));
		}
	  //2、添加子box
	  var boxid=this.random();
	  this.divchain.push(boxid);
	 
		//数据
	  
		var html =this.xmlObj.transformStrxsl(this.defaultStrxsl,{'id':id,'boxid':boxid,'menutype':menutype});
	
			
		var divdata=$C("div");
		divdata.innerHTML=html;
		divdata.style.position="absolute";
		divdata.id=this.random();
		divdata.style.left=0;
		divdata.style.top=0;
		divdata.style.height=0;
		divdata.style.width=0;
		divdata.style.padding=0;
		//divdata.style.border="#000 solid 1px";
        divdata.style.zIndex=1001;
        divdata.style.clear="both";  
		// 定义样式表，长宽高和定位等，用于遮盖select 位于divdata下层
		var shim =$C("iframe");
		    shim.style.zIndex=divdata.style.zIndex  -1;
			shim.style.left=0;
			shim.style.top=0;
			shim.style.height=0;
			shim.style.width=0;
			shim.style.position="absolute";
	        shim.style.filter ='progid:DXImageTransform.Microsoft.Alpha(style=10,opacity=10)';
	   		shim.style.clear="both";  
	  var div = $C("div");
		div.style.top=divtop+"px";
		div.style.left=divleft+"px";
		div.style.position="absolute";
	    div.style.padding=0;
		div.id=boxid;
		div.style.zIndex=1006; 
		//div.style.border="8px";  
		div.style.clear="both";  
	    
		//3、添加到指定的div上
		div.appendChild(divdata);
		div.appendChild(shim);
	//	$("#"+this.showdivid)[0].appendChild(div);
		
		
		
		
		document.getElementById(this.showdivid).appendChild(div);
		//调整shim大小
	  	shim.style.width=divdata.offsetWidth;
	  	shim.style.height=divdata.offsetHeight;
	  	divdata.style.padding=0;
	  	shim.style.padding=0;
	  	div.style.padding=0;
	  	
	  
	
		
		//alert(divdata.style.width);
		//4、给新增的divbox添加事件 
		this.addchildEvent(id,menutype);
}
/**
移除子菜单
*/
 Menu.prototype.removeChildDiv=function(boxid){
	
	for(var i=this.divchain.length;i>0;i--){
		if(boxid ==this.divchain[i-1])break;
	   var div = document.getElementById(this.divchain[i-1]);
	    div.innerHTML="";
	   div.parentNode.removeChild(div);
		 this.divchain.splice(i-1,1);
	}
}
/**
获取随机数，用于id
*/
 Menu.prototype.random=function()
{
	return Math.floor(Math.random()*0x7fffffff).toString(36);
} 

/** 给子节点添加事件*/
 Menu.prototype.addchildEvent=function(id,menutype){

	
	    var items=[];
	    if(ie){
		 items = this.menudom.selectNodes("//*[@id='"+id+"']/node");
	    }else{
	    	var sXPath="//*[@id='"+id+"']/node";
	    	 
	            var oEvaluator = new XPathEvaluator();  
	            var oResult = oEvaluator.evaluate(sXPath, this.xmlObj.xmldom, null,XPathResult.ORDERED_NODE_ITERATOR_TYPE, null);  
	            var aNodes = new Array;  
	            if (oResult != null) {  
	                var oElement = oResult.iterateNext();  
	                while (oElement) {  
	                    aNodes.push(oElement);  
	                    items.push(oElement); 
	              
	                    oElement = oResult.iterateNext();  
	                 
	                }  
	            }  
	           
	    
	    	
	    }
	  
		var i, c,n;
	  for (i = 0, c = items.length; i < c; ++i)
		{  
		  
			n = items[i];
			id =n.getAttribute("id");
		
			var el =document.getElementById(id)
			var menu = this;
		
			
			/**
			单击菜单条
			*/
			function clickdiv(ee){
			
				 function inernfun(){
		
					var xy = menu.getel_xy(ee);
					var id = ee.getAttribute("id");
					//菜单条和菜单细目坐标位置定位不同
					if(menutype=='menubar'){
						
						if(menu.isleaf(id)){
							//单击关闭菜单
							menu.menustat="out";//菜单设置为可关闭状态
							menu.close(menu);
							//单击叶子节点时处理
							menu.onclickleaf(ee);
						}else{
						    menu.setMenuDiv(id,xy.top+ee.offsetHeight,xy.left,'menulist');
						}
			
					}else{
						if(menu.isleaf(id)){
							//单击关闭菜单
							menu.menustat="out";//菜单设置为可关闭状态
							menu.close(menu);
							//单击叶子节点时处理
							menu.onclickleaf(ee);
						}else{
							menu.setMenuDiv(id,xy.top+5,xy.left+ee.offsetWidth,'menulist');
						}
				  }
					}
				return inernfun;
			}
			
			function onMenuout(){
				 menu.menustat="out";
				
				 window.setTimeout(back,100);
				 
				 function back(){
					 return menu.close(menu);
				 }
				 
			}
			
			function onMenuin(){
			  menu.menustat="in";
			
			}
			
			
			//el.addEventListener("click",clickdiv(el));
			
			menu.bindEvent(el,"click",clickdiv(el));
			//单击菜单
		//	el.onclick=clickdiv(el);
			
			//el.addEventListener("mouseout",onMenuout);
			menu.bindEvent(el,"mouseout",onMenuout);
			//移出菜单
			//el.onmouseout= onMenuout;
			
			//el.addEventListener("mouseover",onMenuin);
			menu.bindEvent(el,"mouseover",onMenuin);
			//进入菜单
			//el.onmouseover= onMenuin;
		}
	 
	}
	//el 节点对象

 Menu.prototype.getel_xy=function(el){
	 var cal_el=el;
     var _t=0;
     var _l=0;
     while(cal_el.offsetParent&& this.showdivid!=cal_el.getAttribute("id")) {
          _t += cal_el.offsetTop;
          _l += cal_el.offsetLeft;
          cal_el=cal_el.offsetParent;
      }
     return {top:_t,left:_l};
}
 Menu.getScrollTop=function(doc){
        doc = doc || document;
        return Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);
}
 Menu.getScrollLeft=function(doc){
        doc = doc || document;
        return Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
}
//注册事件
 Menu.prototype.bindEvent=function(elementsrc,eventtype,func){
	
	if(elementsrc.attachEvent){
		
		elementsrc.attachEvent("on"+eventtype,func);
	}else{
		
	  elementsrc.addEventListener(eventtype,func,false);
	}
}

///////////添加自定义菜单事件///////////

 Menu.prototype.onclickleaf=function(el){

/**
var data= new AppRequest('1.1','001');
//data.setHeader("Content-Type","text/xml");
data.setHeader("uicode",el.code);
data.setHeader("s_exec",'input');
data.setField("uicode",el.code);
var resp = httpPost(location.href,data);
debugger;
var form = new Form();
var html= form.html(resp.ui);
document.getElementById("MAIN.PANE").innerHTML=html;
//注册事件
form.bindEvent(resp.ui);
*/
	
	window.open(el.getAttribute("code"),el.getAttribute("target"));
}


	             
	               

