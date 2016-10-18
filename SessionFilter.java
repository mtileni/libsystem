
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import org.apache.log4j.Logger;

/**
 *
 * @author bruce
 */
public class SessionFilter implements Filter
{
    private ServletContext context;
    static Logger logger = Logger.getLogger(SessionFilter.class);
  
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.context = filterConfig.getServletContext();
    }
   
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException 
    {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        try
        {
            boolean isLogin = request.getRequestURI().contains("/login.jsp");
            HttpSession session = request.getSession(false);         
            if (!isLogin  &&(session == null || session.getAttribute("userDetails") == null)) 
            {
                response.sendRedirect("login.jsp"); // No logged-in user found, so redirect to login page.
                return;
            }
            else 
             {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0);  
            }
            chain.doFilter(req, res);
        }catch(ServletException e)
        {
            logger.error(e);
            response.sendRedirect("error.jsp");
        }
    }

   
    public void destroy() 
    {
        
    }
}