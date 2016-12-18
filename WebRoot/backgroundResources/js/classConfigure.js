function classModuleCfg(){
	 var co = document.classModule;
	 var projectUrl = co.currentUrl.value;
	 var url= projectUrl+"/classModuleCfg.do";
	 $.post(url,function(msg){
	    	if(msg!="fail"){
	    		alert("操作成功");
	    	}else{
	    		alert("操作失败！");
	    	}
	       });
}