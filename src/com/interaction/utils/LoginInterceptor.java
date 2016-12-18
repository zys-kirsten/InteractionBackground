package com.interaction.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class LoginInterceptor extends OncePerRequestFilter  
{  
      
    /** 登录验证过滤器 */  
      
    @Override  
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  
        throws ServletException, IOException {

    	// 不过滤的uri  
        String[] notFilter = new String[] {"/IB/backgroundResources"};
//            new String[] {"/backgroundResources","/background/images","/background/js"};  
          
        // 请求的uri  
        String uri = request.getRequestURI();  
        // 是否过滤  
        boolean doFilter = true;  
        for (String s : notFilter)  
        {  
            if (uri.indexOf(s) != -1)  
            {  
                // 如果uri中包含不过滤的uri，则不进行过滤  
                doFilter = false;  
                break;  
            }  
        }  
          
        if (doFilter)  
        {  
            // 执行过滤  
            // 从session中获取登录者实体  
        	System.out.println("执行过滤");
        	 Object obj = request.getSession().getAttribute("teacher");  
        	 if (null == obj)  
            {  
                response.sendRedirect("../login.jsp");  
                return;  
            }  
            else  
            {  
                // 如果session中存在登录者实体，则继续  
                filterChain.doFilter(request, response);  
            }  
        }  
        else  
        {  
            // 如果不执行过滤，则继续  
            filterChain.doFilter(request, response);  
        }  
    }  
}

//拦截器  有错误
//public class LoginInterceptor extends HandlerInterceptorAdapter{  
//   
//    @Override    
//    public boolean preHandle(HttpServletRequest request,    
//            HttpServletResponse response, Object handler) throws Exception {    
//
//    	System.out.println("拦截器");
//        String teacher =  (String)request.getSession().getAttribute("teacher");   
//        if(teacher == null){  
//            request.getRequestDispatcher("login.jsp").forward(request, response);  
//            return false;  
//        }else  
//            return true;     
//    }    
//    
//    /** 
//     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
//     * 可在modelAndView中加入数据，比如当前时间 
//     */  
//    @Override    
//    public void postHandle(HttpServletRequest request,    
//            HttpServletResponse response, Object handler,    
//            ModelAndView modelAndView) throws Exception {     
//    }    
//    
//    /**  
//     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
//     *   
//     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
//     */    
//    @Override    
//    public void afterCompletion(HttpServletRequest request,    
//            HttpServletResponse response, Object handler, Exception ex)    
//            throws Exception {    
//    }    
//  
//}    
