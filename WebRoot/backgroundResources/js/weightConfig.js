function selectCheck1(){
	var quantization = document.getElementById("quantization").options;
	var unquantization = document.getElementById("unquantization").options;
	for (var i=0; i<quantization.length; i++) {  
		 if (quantization[i].selected==true) {
			 var temp = parseFloat((1.0-quantization[i].value).toPrecision(12));
			 for (var j=0; j<unquantization.length; j++) {  
				 if (unquantization[j].value==temp) {
					 unquantization[j].selected=true;
					 break;  
				 }  
			 }
			 break;  
		 }  
	 } 
}
function selectCheck2(){
	var quantization = document.getElementById("quantization").options;
	var unquantization = document.getElementById("unquantization").options;
	for (var i=0; i<unquantization.length; i++) {  
		 if (unquantization[i].selected==true) {
			 var temp = parseFloat((1.0-unquantization[i].value).toPrecision(12));
			 for (var j=0; j<quantization.length; j++) {  
				 if (quantization[j].value==temp) {
					 quantization[j].selected=true;
					 break;  
				 }  
			 }
			 break;  
		 }  
	 } 
}