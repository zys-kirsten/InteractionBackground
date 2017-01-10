package com.interaction.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class JsonUtils {
	public static void toJson(HttpServletResponse response,String key,Object value) throws IOException{  

		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject=new JSONObject();  
		jsonObject.put(key, value);  
		String opts = jsonObject.toString();
		
		out.print(opts);
		out.flush();
		out.close();

	} 
}
