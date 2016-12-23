onload=function(){
	//学生学习效果评价设置显示已有
	var q = document.getElementById("quantization").options;  
	   for (var i=0; i<q.length; i++) {  
	  	 if (q[i].value=='${evaluationWeightVo.quantization}') {  
	  		 q[i].selected=true;  
	  		 break;  
	  	 }  
	   }  
   var uq = document.getElementById("unquantization").options;  
   for (var i=0; i<uq.length; i++) {  
  	 if (uq[i].value=='${evaluationWeightVo.unquantization}') {  
  		 uq[i].selected=true;  
  		 break;  
  	 }  
   }  
   
 //量化指标评价设置显示已有
   var qi01 = document.getElementById("quantizationIndex[0][1]").options;  
   for (var i=0; i<qi01.length; i++) {  
  	 if (qi01[i].value=='${evaluationWeightVo.quantizationIndex[0][1]}') {  
  		qi01[i].selected=true;  
  		 break;  
  	 }  
   }
   var qi02 = document.getElementById("quantizationIndex[0][2]").options;  
   for (var i=0; i<qi02.length; i++) {  
  	 if (qi02[i].value=='${evaluationWeightVo.quantizationIndex[0][2]}') {  
  		 qi02[i].selected=true;  
  		 break;  
  	 }  
   }
   var qi03 = document.getElementById("quantizationIndex[0][3]").options;  
   for (var i=0; i<qi03.length; i++) {  
  	 if (qi03[i].value=='${evaluationWeightVo.quantizationIndex[0][3]}') {  
  		qi03[i].selected=true;  
  		 break;  
  	 }  
   }
   var qi12 = document.getElementById("quantizationIndex[1][2]").options;  
   for (var i=0; i<qi12.length; i++) {  
  	 if (qi12[i].value=='${evaluationWeightVo.quantizationIndex[1][2]}') {  
  		qi12[i].selected=true;  
  		 break;  
  	 }  
   }
   var qi13 = document.getElementById("quantizationIndex[1][3]").options;  
   for (var i=0; i<qi13.length; i++) {  
  	 if (qi13[i].value=='${evaluationWeightVo.quantizationIndex[1][3]}') {  
  		qi13[i].selected=true;  
  		 break;  
  	 }  
   }
   var qi23 = document.getElementById("quantizationIndex[2][3]").options;  
   for (var i=0; i<qi23.length; i++) {  
  	 if (qi23[i].value=='${evaluationWeightVo.quantizationIndex[2][3]}') {  
  		qi23[i].selected=true;  
  		 break;  
  	 }  
   }
}
