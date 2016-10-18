/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookCategory;
import model.BookSubCategory;
import model.SubCategoryDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author bruce
 */
@WebServlet(name = "ViewSubMenuBookServlet", urlPatterns = {"/ViewSubMenuBookServlet"})
public class ViewSubMenuBookServlet extends HttpServlet 
{
     static Logger logger = Logger.getLogger(SessionFilter.class);
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
        
            String idCategory=request.getParameter("categoryId");
            BookCategory bCategory=new BookCategory();
            String error="";
            if(idCategory==null || idCategory.equals(""))
            {
                error="Ooops something went wrong";
            }
            else
            {
                try
                {
                    int id=Integer.parseInt(idCategory);
                    bCategory.setIdCategory(id);
                }catch(NumberFormatException ne)
                {
                    logger.error(ne);
                    response.sendRedirect("error.jsp");
                }
            }

                if(error.isEmpty())
                {
                    SubCategoryDAO subCategoryDao=new SubCategoryDAO();
                    HttpSession session = request.getSession();
                    List<BookSubCategory> subCategoryList=subCategoryDao.allSubCategory(bCategory);
                    session.setAttribute("subCategoryList", subCategoryList);
                    RequestDispatcher rd=request.getRequestDispatcher("viewSubCategory.jsp");
                    rd.forward(request, response);
                }else
                {
                    request.setAttribute("error", error);
                    RequestDispatcher rd=request.getRequestDispatcher("admin.jsp");
                    rd.forward(request, response);
                }
        }catch(SQLException e)
        {
            logger.error(e);
            response.sendRedirect("error.jsp");
        }catch(ServletException se)
        {
            logger.error(se);
            response.sendRedirect("error.jsp"); 
        }catch(IOException ioe)
        {
            logger.error(ioe);
            response.sendRedirect("error.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
