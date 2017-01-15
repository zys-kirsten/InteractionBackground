package com.interaction.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String d2s(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String str=sdf.format(date); 
		return str;
	}
	
	public static Date s2d(String str) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟   
		java.util.Date date=sdf.parse(str);
		return date;
	}
	
	/** 
     * 获得yyyy-MM-dd格式的当前日期 
     */  
    public static String getNowTime() {  
        Date date = new Date();  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        return dateFormat.format(date);  
    }  
}
