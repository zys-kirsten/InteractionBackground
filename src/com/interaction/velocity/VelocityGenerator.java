package com.interaction.velocity;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityGenerator {
	
	public static void main(String[] args) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		
		
		ve.init();
		Template actionTpt = ve.getTemplate("ActionTemplate.vm");
		Template listJspTpt = ve.getTemplate("ListJspTemplate.vm");
		Template addTpt = ve.getTemplate("AddTemplate.vm");
		Template modifyTpt = ve.getTemplate("ModifyTemplate.vm");
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("classNameLowCase", "teacher");
		ctx.put("classNameUpCase", "Teacher");
		String[][] attrs = {
				{"Integer","id"},
				{"String","name"},
				{"String","serializeNo"},
				{"String","titile"},
				{"String","subject"}
		};
		ctx.put("attrs", attrs);
		String rootPath = VelocityGenerator.class.getClassLoader().getResource("").getFile() ;
		merge(actionTpt,ctx,rootPath+"/TeacherAction.java");
		merge(listJspTpt,ctx,rootPath+"/teacherList.jsp");
		merge(addTpt,ctx,rootPath+"/teacherAdd.jsp");
		merge(modifyTpt,ctx,rootPath+"/teacherModify.jsp");
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
