package controller;

import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
        private static final long serialVersionUID = 1L;
        static Logger logger = Logger.getLogger(LoginServlet.class);
        /**
         * @see HttpServlet#HttpServlet()
         */
         public LoginServlet() 
         {
            super();
        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            String page="";
            try
            {
                UserBean user=new UserBean();
                String button=request.getParameter("button");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String errors="";
		if(button.equals("Login"))
                {
                    if(username==null || username.equals(""))
                    {
                            errors="Username and Password are required.";
                    }
                    else
                    {
                            user.setUsername(username);
                    }
                    if(password==null || password.equals(""))
                    {
                            errors="Username and Password are required.";
                    }
                    else
                    {
                            user.setPassword(password);
                    }
                    if(errors.isEmpty())
                    {
                        CategoryDAO categoryDao=new CategoryDAO();              
                        UserDAO userD=new UserDAO();
                        try
                        {
                            List<BookCategory> cList=categoryDao.allCategory();
                            boolean isLogin=userD.login(user);
                            if(isLogin)
                            {
                                HttpSession session = request.getSession();
                                session.setAttribute("userDetails", user);
                                session.setAttribute("menu", cList);
                                session.setMaxInactiveInterval(30*60);
                                Cookie userName = new Cookie("user", user.toString());
                                userName.setMaxAge(30);
                                response.addCookie(userName);
                                 page="admin.jsp";
                            }
                            else
                            {
                                request.setAttribute("error", "Your Username and/or Password are invalid.");
                                page="login.jsp"; 
                                logger.info("Invalid user credentials");
                            }
                            RequestDispatcher rd=request.getRequestDispatcher(page);
                            rd.forward(request, response);
                        }catch(SQLException sq)
                        {
                           logger.error(sq);
                           page="error.jsp";

                        }      
                    }
                    else
                    {
                        request.setAttribute("error", errors);
                        page="login.jsp";
                    }
                }
            }catch(IOException e)
            {
                logger.error(e);
                page="error.jsp";
            }catch(ServletException se)
            {
               logger.error(se);
                page="error.jsp"; 
            }
            RequestDispatcher rd=request.getRequestDispatcher(page);
            rd.forward(request, response);
        }
}
