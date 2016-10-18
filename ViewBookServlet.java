/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BookDAO;
import model.BookSubCategory;
import model.Books;
import org.apache.log4j.Logger;

/**
 *
 * @author bruce
 */
@WebServlet(name = "ViewBookServlet", urlPatterns = {"/ViewBookServlet"})
public class ViewBookServlet extends HttpServlet 
{   
     static Logger logger = Logger.getLogger(ViewBookServlet.class);

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
            String idSubCategory=request.getParameter("idSubCategory");
            String error="";
            BookSubCategory bs=new BookSubCategory();
            if(idSubCategory==null ||  idSubCategory.equals(""))
            {
                error="Ooops, Something went wrong";
            }
            else
            {
                Integer idSub=Integer.parseInt(idSubCategory);
                bs.setIdSubCategory(idSub);
            }
            
            if(error.isEmpty())
            {
                BookDAO bookDao=new BookDAO();
                List<Books> book=bookDao.listOfBooks(bs);
                request.setAttribute("bookList", book);
                RequestDispatcher rd=request.getRequestDispatcher("viewBooks.jsp");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute(error, bs);
                RequestDispatcher rd=request.getRequestDispatcher("admin.jsp");
                rd.forward(request, response);
            }
            
        }catch(Exception e)
        {
            logger.error(e);
            response.sendRedirect("error.jsp");
            e.printStackTrace();
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
