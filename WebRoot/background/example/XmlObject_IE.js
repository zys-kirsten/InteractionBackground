var XSLSESSION={};
function XmlObject(){
this.current={}; 
this.current=this.xmlDoc=this.xmldom =XmlObject.createComponent();       
this.xmldom.async=false;  
this.xsldom ={};
this.xslTemplate={};
this.xslProcessor={};
}
 XmlObject.createComponent=function()
{
	var V = [6, 4, 3];
	
	var A = [];
		A.push("Msxml2.DOMDocument.6.0");
		A.push("Msxml2.DOMDocument.4.0");
		A.push("Msxml2.DOMDocument.3.0");
	
	var arr =A;
	if (arr.selected) {
		return new ActiveXObject(arr.selected);
	}
	

	var X, E, i = -1, c = arr.length;

	while (++i < c) {
		try {
			X = new ActiveXObject(arr[i]);
			arr.selected = arr[i];
			return X;
		}
		catch (e) {
			E = e;
		}
	}

	throw E;
}

XmlObject.prototype.load=function(xml){
	this.xmldom.async = false; 
	this.xmldom.load(xml); 
	this.current=this.xmldom;
}
XmlObject.prototype.loadxsl=function(xsl){
	this.xsldom = new ActiveXObject("MSXML2.FreeThreadedDOMDocument");
	this.xslTemplate = new ActiveXObject("MSXML2.XSLTemplate");
	this.xsldom.async = false; 
	this.xsldom.load(xsl);
	this.xslTemplate.stylesheet=this.xsldom;
}
/**加载xml字符串*/
XmlObject.prototype.loadXML=function(xml){
	this.xmldom.loadXML(xml);
	this.current=this.xmldom;
}
XmlObject.prototype.transformxsl=function(xsl,paras){
	
	if(XSLSESSION[xsl]!=null){
		return this.transformStrxsl(XSLSESSION[xsl],paras);
	}
	this.xsldom = new ActiveXObject("MSXML2.FreeThreadedDOMDocument");
	this.xslTemplate = new ActiveXObject("MSXML2.XSLTemplate");
	this.xsldom.async = false; 
	this.xsldom.load(xsl);
	this.xslTemplate.stylesheet=this.xsldom;
	this.xslProcessor =this.xslTemplate.createProcessor();
	this.xslProcessor.input=this.xmldom ;
	XSLSESSION[xsl]=this.xsldom.xml;
	
	for(var i in paras){
		this.xslProcessor.addParameter(i, paras[i]);
	}
	this.xslProcessor.transform();
	return this.xslProcessor.output;
}

XmlObject.prototype.transformStrxsl=function(strxsl,paras){
	this.xsldom = new ActiveXObject("MSXML2.FreeThreadedDOMDocument");
	this.xslTemplate = new ActiveXObject("MSXML2.XSLTemplate");
	this.xsldom.async = false; 
	this.xsldom.loadXML(strxsl);
	
	this.xslTemplate.stylesheet=this.xsldom;
	this.xslProcessor =this.xslTemplate.createProcessor();
	this.xslProcessor.input=this.xmldom ;
	
	for(var i in paras){
		this.xslProcessor.addParameter(i, paras[i]);
	}
	this.xslProcessor.transform();
	
	return this.xslProcessor.output;
}

XmlObject.prototype.transform=function(paras){
	this.xslProcessor =this.xslTemplate.createProcessor();
	this.xslProcessor.input=this.xmldom ;
	for(var i in paras){
		this.xslProcessor.addParameter(i, paras[i]);
	}
	this.xslProcessor.transform();

	return this.xslProcessor.output;
}
XmlObject.prototype.cd=function(xp)
{
	var node = xp == ".." ? this.current.parentNode : this.current.selectSingleNode(xp);
	if (node) {
		this.current = node;
		return this;
	}
	return false;
}
/**
 * 在当前节点下或是指定节点前创建一个子元素，并设置其内容。返回新创建的节点。
 * 就像操作系统控制台命令: md, mkdir;
 * @method XmlObject.md
 * @param name		表示名称的字符串。名称格式：
 *						一个可选的命名空间前缀（以冒号结尾），
 *						跟着一个名称，
 *						跟着一个可选的命名空间（以符号@起始）。
 * @param content	元素内容。
 * @param ipoint	插入点。可以不指定。
 */
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
		this.md('data','');
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
