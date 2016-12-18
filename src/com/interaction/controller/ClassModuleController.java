package com.interaction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interaction.pojo.Course;
import com.interaction.pojo.Evaluation;
import com.interaction.pojo.Seminar;
import com.interaction.service.ClassModuleService;
import com.interaction.service.EvaluationService;
import com.interaction.service.SeminarService;
import com.interaction.utils.SessionUtil;
import com.interaction.vo.ClassModuleVo;
import com.interaction.vo.EvaluationVo;
import com.interaction.vo.SeminarVo; 
    
@Controller   
public class ClassModuleController {   
	
	@Resource
	private EvaluationService evaluationServiceImpl;
	@Resource
	private ClassModuleService classModuleServiceImpl;
	@Resource
	private SeminarService seminarServiceImpl;
	
	//修改配置前回显配置信息
	@RequestMapping("/editClassModule")
	public String editClassModule(@RequestParam(value="cmid")Integer cmid){
		ClassModuleVo classModuleVo = classModuleServiceImpl.findById(cmid);
		List<EvaluationVo>  evaluationVos = evaluationServiceImpl.listByCourse(getCourse().getCid());
		List<SeminarVo> seminarVos = seminarServiceImpl.listByCourse(getCourse().getCid());
		SessionUtil.getMySession().setAttribute("classModuleVo", classModuleVo);
		SessionUtil.getMySession().setAttribute("evaluationVos", evaluationVos);
		SessionUtil.getMySession().setAttribute("seminarVos", seminarVos);
		return "configDetail/editClassModule";
	}
	
	//进入配置界面前显示该门课已有的评价方式以及研讨课信息
	@RequestMapping("/configClassModuleEval")
	public String listEvaluationAndSeminar(@RequestParam(value="cid")Integer cid){
		List<EvaluationVo>  evaluationVos = evaluationServiceImpl.listByCourse(cid);
		List<SeminarVo> seminarVos = seminarServiceImpl.listByCourse(cid);
		SessionUtil.getMySession().setAttribute("evaluationVos", evaluationVos);
		SessionUtil.getMySession().setAttribute("seminarVos", seminarVos);
		return "configDetail/addClassModule";
	}
    
	//添加(修改)配置信息  
	@RequestMapping("/addClassModule")
	public String addClassModule(ClassModuleVo classModuleVo){
		Integer cid = getCourse().getCid();
		String[] checkEname = SessionUtil.getRequest().getParameterValues("evalue");
		String[] checkSeName = SessionUtil.getRequest().getParameterValues("seminar");
		List<Evaluation> evaluations = new ArrayList<Evaluation>();
		List<Seminar> seminars = new ArrayList<Seminar>();
		if (checkEname !=null && checkEname.length !=0) {
			for(int i=0;i<checkEname.length;i++){
				Evaluation evaluation = evaluationServiceImpl.findByEname(cid,checkEname[i]);
				evaluations.add(evaluation);
			}
		}
		if (checkSeName !=null && checkSeName.length !=0) {
			for(int i=0;i<checkSeName.length;i++){
				Seminar seminar = seminarServiceImpl.findBySeName(cid,checkSeName[i]);
				seminars.add(seminar);
			}
		}
		
		classModuleVo.setEvaluations(evaluations);
		classModuleVo.setSeminars(seminars);
        classModuleVo.setCid(cid);
		int result = -1;
		if (classModuleVo.getCmid() == null) {
			result = classModuleServiceImpl.addClassModule(classModuleVo);//添加测试题
		}else{
			result = classModuleServiceImpl.updateClassModule(classModuleVo);//修改测试题
		}
		
		if(result != -1){
			listClassModule();
			return "configDetail/listClassModule";
		}else{
			return "error";
		}
	}
	
	//删除某一课堂模式
	@RequestMapping("/deleteClassModule")
	public String deleteClassModule(@RequestParam(value="cmid")Integer cmid){
		int result = classModuleServiceImpl.deleteClassModuleById(cmid);
		if (result == -1) {
			return "error";
		}
		listClassModule();
		return "configDetail/listClassModule";
	}
	//列出所有课堂模式
	@RequestMapping("/listClassModule")
	public String listClassModule(@RequestParam(value="cid")Integer cid){
		listClassModule();
		return "configDetail/listClassModule";
	}
	private void listClassModule() {
		Integer cid = getCourse().getCid();
		List<ClassModuleVo> classModuleVos = classModuleServiceImpl.listByCourse(cid);
		SessionUtil.getMySession().setAttribute("classModuleVos", classModuleVos);
	}

//	//显示config文件夹下的 已经配置的xml文件
//	 @RequestMapping("/classModuleCfg")
//	 public void classModuleCfg(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		
//		    response.setContentType("text/html;charset=utf-8");  
//		    String rpath = request.getParameter("rpath");  
//		    File file = new File(rpath+"/"); 
//		    
//		    String test[];  
//		    test = file.list();  
//		    String str = JSONArray.fromObject(test).toString();  
//		    response.getWriter().write(str); 
//	 }
//	
//    @RequestMapping("/classModuleCfg2")
//    public void classModuleCfg2(HttpServletRequest request,HttpServletResponse response) throws Exception{
//    	String[] checkboxValue = request.getParameterValues("checkpmt");
//    	String classModuleName = request.getParameter("classModuleName");
//    	String cid = request.getParameter("cid");
//    	
//    	Element root = DocumentHelper.createElement("component");  
//        Document document =  DocumentHelper.createDocument(root);  
//        //给根节点添加属性  
//        root.addAttribute("课堂模式名称",classModuleName);  
//          
//        //给根节点添加孩子节点  
//        Element element1 = root.addElement("course");  
//        element1.addAttribute("checkedID", cid).addAttribute("checkValue", "大学计算机")
//        .addAttribute("checkOnClick", "checkFunction()");  
//        for (int i = 0; i < checkboxValue.length; i++) {
//        	Element element2 = root.addElement("ui"); 
//			element2.addAttribute("uiID", "ui"+i).addAttribute("uiTitle",checkboxValue[i])
//			.addAttribute("uiName", checkboxValue[i]+".jsp");
//		}  
//        //把生成的xml文档存放在硬盘上  true代表是否换行  
//        OutputFormat format = new OutputFormat("    ",true);  
//        format.setEncoding("UTF-8");//设置编码格式  
//        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("F:/courseModule.xml"),format);  
//        xmlWriter.write(document);  
//        xmlWriter.close();  
//     
//    }
//	
//    @RequestMapping("/uploadStudent"  )  
//    public String upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
//        //创建一个通用的多部分解析器  
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
//        //判断 request 是否有文件上传,即多部分请求  
//        if(multipartResolver.isMultipart(request)){  
//            //转换成多部分request    
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
//            //取得request中的所有文件名  
//            Iterator<String> iter = multiRequest.getFileNames();  
//            while(iter.hasNext()){  
//                //记录上传过程起始时的时间，用来计算上传时间  
//                int pre = (int) System.currentTimeMillis();  
//                //取得上传文件  
//                MultipartFile file = multiRequest.getFile(iter.next());  
//                if(file != null){  
//                    //取得当前上传文件的文件名称  
//                    String myFileName = file.getOriginalFilename();  
//                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
//                    if(myFileName.trim() !=""){  
//                        System.out.println(myFileName);  
//                        //重命名上传后的文件名  
//                        String fileName = "My" + file.getOriginalFilename();  
//                        //定义上传路径  
//                        String path = "F:/" + fileName;  
//                        File localFile = new File(path);  
//                        file.transferTo(localFile);  
//                    }  
//                }  
//                //记录上传该文件后的时间  
//                int finaltime = (int) System.currentTimeMillis();  
//                System.out.println(finaltime - pre);  
//            }  
//              
//        }  
//        return "NewFile";  
//    } 
    
  //获得session中的课程信息
  	private Course getCourse(){
  		return (Course) SessionUtil.getMySession().getAttribute("course");
  	}
  	
}  