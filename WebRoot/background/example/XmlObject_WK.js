
var XSLSESSION={};

function XmlObject(){	
this.current={}; 
this.current=this.xmldom =this.xmlDoc=document.implementation.createDocument("", "", null);
this.xsldom =null;
this.xslTemplate={};
this.xslProcessor={};
}
 XmlObject.createComponent=function()
{	
}

XmlObject.prototype.load=function(xml){

	  try {//FireFox  
		  this.xmldom = document.implementation.createDocument("", "", null);  
		  this.xmldom.async = false; 
		  this.xmldom.load(xml); 
         
      } catch(e) {//Chrome,Safari  
          var xmlhttp = new window.XMLHttpRequest();  
          xmlhttp.open("GET", xml, false);  
          xmlhttp.send(null);  
          this.xmldom = xmlhttp.responseXML;//.documentElement;  
      }  
	this.current=this.xmldom;  
	//this.xmlDoc=this.xmldom;  
}

XmlObject.prototype.loadXML=function(xmlstr){
	try{
		var domParser = new  DOMParser();
	    var dompp = domParser.parseFromString(xmlstr, 'text/xml');	
	    this.xmldom=dompp; 
	}catch(err){
		this.xmldom.loadXML(xmlstr);
	}
	this.current=this.xmldom;
	//this.xmlDoc=this.xmldom;  
}
XmlObject.prototype.loadxsl=function(xsl){
	
	  try {//FireFox  
		  this.xsldom = document.implementation.createDocument("", "", null);  
		  this.xsldom.async = false; 
		  this.xsldom.load(xsl); 
         
      } catch(e) {//Chrome,Safari  
          var xmlhttp = new window.XMLHttpRequest();  
          xmlhttp.open("GET", xsl, false);  
          xmlhttp.send(null);  
          this.xsldom = xmlhttp.responseXML;//.documentElement;  
      } 
      
	this.xslTemplate.stylesheet=this.xsldom;

	var xmlstr= (new XMLSerializer()).serializeToString(this.xsldom.documentElement);//序列化 
	this.xsldom.xml=xmlstr;


}

XmlObject.prototype.transformxsl=function(xsl,paras){

	if(XSLSESSION[xsl]!=null){
		return this.transformStrxsl(XSLSESSION[xsl],paras);
	}
	
	this.xsldom = document.implementation.createDocument("", "", null);
	if (xsl) {
		if (this.xsldom.load) {
			this.xsldom.async = false;
			this.xsldom.load(xsl);
		}else {//Chrome,Safari  
			//this.xsldom= get(xsl);
			
			var xmlhttp = new window.XMLHttpRequest();  
			xmlhttp.open("GET", xsl, false);  
			xmlhttp.send(null);  
			this.xsldom = xmlhttp.responseXML;//.documentElement;  
		}
	}
	XSLSESSION[xsl]=(new XMLSerializer()).serializeToString(this.xsldom.documentElement);//序列化 
	var xslProcessor =new XSLTProcessor();
	    xslProcessor.importStylesheet(this.xsldom);
	for(var i in paras){	
		xslProcessor.setParameter(null, i, paras[i]);
	}
	var doc =xslProcessor.transformToFragment(this.xmldom,document);
	return doc;
}


XmlObject.prototype.transformStrxsl=function(strxsl,paras){
	this.xsldom = document.implementation.createDocument("", "", null);

	try{
		this.xsldom.loadXML(strxsl);
		
	}catch(err){
		var domParser = new  DOMParser();
	    var dompp = domParser.parseFromString(strxsl, 'text/xml');	
	    this.xsldom=dompp;
	 

	}

	var xslProcessor =new XSLTProcessor();
	    xslProcessor.importStylesheet(this.xsldom);
	for(var i in paras){	
		xslProcessor.setParameter(null, i, paras[i]);
	}
	var doc =xslProcessor.transformToFragment(this.xmldom,document);
	
	var html= (new XMLSerializer()).serializeToString(doc);//序列化 
	
	return html;

}


XmlObject.prototype.cd=function(xp)
{   
	var node ;
	if( xp == ".."){
		node=this.current.parentNode ;
	}else {
		
		 var oEvaluator = new XPathEvaluator();
		 
		node = oEvaluator.evaluate(xp,this.current,null, XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;
	}
	if (node) {
		this.current = node;
		return this;
	}
	return false;
}

XmlObject.prototype.md=function(name, content, ipoint)
{

	var el = this.xmlDoc.createElement(name);
	this.add(el, ipoint);
	var v=this.xmlDoc.createTextNode(content);
	var c=this.current;
	this.cd(name);
	this.add(v);
	this.current=c;
	return el;
}
XmlObject.prototype.add=function(node, ipoint)
{
	var p = ipoint ? ipoint.parentNode : this.current;
	return p.insertBefore(node, ipoint || null);
}
/*req
 * {
	this.meta = {};
	this.data = {};
	this.setTarget(verb, tid);
}
 */
XmlObject.prototype.loadRequest=function(req)
{	
	if (req instanceof AppRequest){	
		var meta = req.meta;
		var data = req.data;	
		this.md('request','');
		this.cd('request');
		this.md('meta','');
		this.md('data','')
		this.cd('meta');
		for( var h in meta){
			this.md(h,meta[h]);
		}
		this.cd('..');
		this.cd('data');
		for( var d in data){
			this.md(d,data[d]);
		}
	}
}
XmlObject.prototype.random=function()
{
	return Math.floor(Math.random()*0x7fffffff).toString(36);
} 
function get(file,type){
	if(null==type)type="xml";
	var rest;
		$.ajax({  
		         type : "GET",  
		         url : file,   
		         cache:false,
		         async : false, 
		         dataType: type,
		         success : function(data){  
		           rest = data;  
		          }  
		          }); 
	return rest;   
}