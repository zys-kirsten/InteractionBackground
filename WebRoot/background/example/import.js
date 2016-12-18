/**
 * import js
 * @param url
 */
var g_impstack=[];
var reg_import = /(?:^|\r|\n)\s*(?:importjs\(\s+([^;\s]+)\);?)/g;
Array.prototype.indexOf=function(v){
	for(var i=0;i<this.length;i++){if(this[i]==v)return i;}
	return -1;
}
function importjs(jsuri,checkstack,getCodeFunc){
	try {
		if (checkstack == null) {checkstack = [];}
		
		if (checkstack.indexOf(jsuri) >= 0) {
			checkstack.push(uri);
			var s = checkstack.join(" -> ");
			throw new Error(-1, "Recursive import: " + s);
		}else {
			checkstack.push(jsuri);
		}
		var jscode;
		if(null==getCodeFunc){
			jscode= get(jsuri,"text");	
		}else{
			jscode=getCodeFunc.apply(this, [jsuri]);
		}
		var arr = [];
		while (m = reg_import.exec(jscode)) {
			if (m[1]) { arr.push(m[1]); }
		}
		for (var i = 0; i < arr.length; i++) {
			if(g_impstack.indexOf(arr[i])>=0){continue;}
			importjs(arr[i],checkstack,getCodeFunc);
		}
	
		if(g_impstack.indexOf(jscode)>=0)return;
		
		execScriptjs(jscode);
		g_impstack.push(jsuri);	
	}
	catch (e) {
		throw e;
	}
	
}
function execScriptjs (text){
    if (!text){
        return;    
    }
    // 构建script元素
    var script = document.createElement("SCRIPT");
    script.setAttribute("type","text/javascript");
    script.text = text;
    var head = document.head||document.getElementsByTagName("head")[0]
    // 执行开始
    head.appendChild(script);
    //执行结束
    //head.removeChild(script);
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
