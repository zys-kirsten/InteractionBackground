var isIE=function () { 
    if (!!window.ActiveXObject || "ActiveXObject" in window)  
        return true;  
    else  
        return false;  
} 
var ie  =isIE();
if(ie){
	importjs("XmlObject_IE.js");
}else{
	importjs("XmlObject_WK.js");
}