package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookCategory;
import model.CategoryDAO;
import org.apache.log4j.Logger;


/**
 * Servlet implementation class Controller
 */

public class Controller extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       static Logger logger = Logger.getLogger(Controller.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
        try
        {
                CategoryDAO categoryDao=new CategoryDAO();
                List<BookCategory> cList=categoryDao.allCategory();
                String button=request.getParameter("button");
    
                if(button.equalsIgnoreCase("addBook"))
		{ 
                        request.setAttribute("addBook", button);
			RequestDispatcher rd=request.getRequestDispatcher("AddBookServlet");
			rd.forward(request, response);
                }
                else
		if(button.equalsIgnoreCase("addBooks"))
		{ 
                       HttpSession session=request.getSession();
                        request.setAttribute("addbook", button);
                        session.setAttribute("menu", cList);
			RequestDispatcher rd=request.getRequestDispatcher("addBooks.jsp");
			rd.forward(request, response);
                }
                else
                if(button.equalsIgnoreCase("subMenu"))
		{ request.setAttribute("subMenu", button);
			RequestDispatcher rd=request.getRequestDispatcher("ViewSubMenuBookServlet");
			rd.forward(request, response);
                } 
                else
                if(button.equalsIgnoreCase("book"))
		{       
                    request.setAttribute("book", button);
                    RequestDispatcher rd=request.getRequestDispatcher("ViewBookServlet");
                    rd.forward(request, response);
                }
		else              
                if(button.equalsIgnoreCase("logout"))
		{       
                    request.setAttribute("logout", button);
                    RequestDispatcher rd=request.getRequestDispatcher("LogoutServlet");
                    rd.forward(request, response);

                }
                

        }catch(SQLException e)
        {
            logger.error(e);
            RequestDispatcher rd=request.getRequestDispatcher("error.jsp");
	    rd.forward(request, response);
            
        }
     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {

                processRequest(request,response);
        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
             processRequest(request,response);
        }

}
