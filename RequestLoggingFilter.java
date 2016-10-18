///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
///**
// *
// * @author bruce
// */
//import java.io.IOException;
//import java.util.Enumeration;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.log4j.Logger;
///**
// * Servlet Filter implementation class RequestLoggingFilter
// */
//public class RequestLoggingFilter implements Filter 
//{
//
//	private ServletContext context;
//	static Logger logger=Logger.getLogger(RequestLoggingFilter.class);
//	public void init(FilterConfig fConfig) throws ServletException {
//		this.context = fConfig.getServletContext();
//		this.context.log("RequestLoggingFilter initialized");
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
//        {
//            try
//            {
//		HttpServletRequest req = (HttpServletRequest) request;
//		Enumeration<String> params = req.getParameterNames();
//		while(params.hasMoreElements())
//                {
//			String name = params.nextElement();
//			String value = request.getParameter(name);
//			//ogger.info("::Request Params::{"+name+"="+value+"}");
//		}
//		
//		Cookie[] cookies = req.getCookies();
//		if(cookies != null)
//                {
//		
//                            //logger.info(req.getLocalAddr());
//			
//		}
//		// pass the request along the filter chain
//		chain.doFilter(request, response);
//            }catch(IOException io)
//            {
//                logger.error(io);
//            }catch(ServletException se)
//            {
//                logger.error(se);
//            }
//	}
//
//	public void destroy() {
//		//we can close resources here
//	}
//
//}