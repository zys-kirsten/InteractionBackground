package com.interaction.velocity;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.interaction.pojo.Teacher;


public class VelocityTest{

	public static void main(String[] args) {
		VelocityEngine ve = new VelocityEngine();
		
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
		//ve.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
		//ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		//ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");     
		
		
		
		
		ve.init();
		Template addTpt = ve.getTemplate("com/interaction/vm/ListTemplate.vm");
		VelocityContext ctx = new VelocityContext();
		
//		ctx.put("classNameUpCase", "Teacher");
//		ctx.put("classNameLowCase", "teacher");
		
		ctx.put("title", "请填写信息:");  
		ctx.put("ctxPath", "${ctxPath }");
		//ctx.put("className", "Teacher");
		ctx.put("yes", "确定");
		ctx.put("no","取消");
		
		
		String[] descs = {"账号","姓名","密码","电话"};
		ctx.put("descs", descs);
		
		Teacher teacher = new Teacher(1, "1", "1", "1");
		Teacher teacher2 = new Teacher(2, "2", "2", "2");
		List<Teacher> teachers = new ArrayList<>();
		teachers.add(teacher);
		teachers.add(teacher2);
		ctx.put("className", "${SessionScope.teachers}");
		Field[] fields = Teacher.class.getDeclaredFields();//获取属性名称数组
 
		String[] attrs = new String[fields.length];
		for (int i = 0; i < attrs.length; i++) {
			attrs[i] = "${v."+fields[i].getName()+"}";
		}
		ctx.put("attrs", attrs);
		
//		String[][] attrs = new String[fields.length][2];
//		for (int i = 0; i < fields.length; i++) {
//			attrs[i][0] = descs[i];
//			attrs[i][1] = fields[i].getName();
//		}
//		
//		ctx.put("attrs", attrs);

		String rootPath = System.getProperty("user.dir")+"/WebRoot"; 
		merge(addTpt,ctx,rootPath+"/list.jsp");
//		String rootPath = System.getProperty("user.dir")+"/src"; 
//		merge(addTpt,ctx,rootPath+"/com/interaction/dao/TeacherDAO.java");
		System.out.println("success...");
	}

	private static void merge(Template template, VelocityContext ctx, String path) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path);
			template.merge(ctx, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
}               
