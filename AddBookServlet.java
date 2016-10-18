/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.BookCategory;
import model.BookDAO;
import model.BookImage;
import model.BookSubCategory;
import model.Books;
import model.CategoryDAO;
import model.SubCategoryDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author bruce
 */
@WebServlet(name = "AddBookServlet", urlPatterns = {"/AddBookServlet"})

public class AddBookServlet extends HttpServlet
{
    static Logger logger = Logger.getLogger(AddBookServlet.class);
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
        
            // Data from the form
            String category=request.getParameter("category");
            String subCategory=request.getParameter("scategory");
            String bookName=request.getParameter("bookName");
            String bookAuthor=request.getParameter("bookAuthor");
            Part filePart = request.getPart("file");
            String page="";
            int status=0;
            InputStream inputStream = null; // input stream of the upload file
        try
        {
            // object declaration
            BookCategory bc=new BookCategory();
            BookSubCategory bcs=new BookSubCategory();
            BookImage image=new BookImage();
            Books book=new Books();
            
            HashMap<String, String> errors=new HashMap();

            // User input validation    

            // obtains the upload file part in this multipart request

            if (filePart.getContentType().equals("application/octet-stream"))
            {
                errors.put("imageErr","select book picture");
            }
            else   
            if(!(filePart.getContentType().equals("image/jpg") || filePart.getContentType().equals("image/png") ||filePart.getContentType().equals("image/jpeg")))
            {
                errors.put("imageErr","Invalid File, Supported files: jpg,png,jpeg only");
            }
            else
            {
                inputStream = filePart.getInputStream();
                if (inputStream != null)
                {
                    
                    image.setFile(inputStream);
      
                    book.setImage(image);
                }
            }

            if(category== null || category.equals(""))
            {
                errors.put("category","This field can not be empty");
            }
            else
            if(category.length()>80)
            {
                errors.put("category","Category length must be less than 80 characters");

            }
            else
            {
                bc.setCategory(category);
                bcs.setId(bc);
            }
            
            if(subCategory==null || subCategory.equals(""))
            {
                errors.put("subc", "This field can not be empty");
            }
            else
            if(subCategory.length()>80)
            {
                errors.put("subc","Sub Category length must be less than 80 characters");

            }
            else
            {
                bcs.setSubCategory(subCategory);
            }
            
            if(bookName==null || bookName.equals(""))
            {
                errors.put("bookName", "This field can not be empty");
            }
            if(bookName.length()>100)
            {
                errors.put("bookName","Book Name: length must be less than 100 characters");

            }
            else
            {
                book.setBookName(bookName);
            }
            if(bookAuthor==null || bookAuthor.equals(""))
            {
                errors.put("bookAuthor", "This field can not be empty");
            }
            if(bookAuthor.length()>100)
            {
                errors.put("bookName","Book Author: length must be less than 100 characters");

            }
            else
            {
                book.setBookAuthor(bookAuthor);
            }
            if(errors.isEmpty())
            {   
                
                CategoryDAO categoryDao=new CategoryDAO();
                SubCategoryDAO subCategoryDao=new SubCategoryDAO();
                BookDAO bookDao=new BookDAO();
                book.setId(bcs);
               
                categoryDao.addCategory(bc);
                subCategoryDao.addSubCategory(bcs);
                boolean isBookAddedbook=bookDao.addBook(book);
                if(isBookAddedbook )
                {
                    status=1;
                }
            }
            else
            {
                page="addBooks.jsp";
                request.setAttribute("errors",errors);
                RequestDispatcher rd=request.getRequestDispatcher(page);
                rd.forward(request, response);
            }
            
        }catch(ServletException se)
        {
            logger.error(se);
            status=0;
        }catch(IOException ioe)
        {
            logger.error(ioe);
            status=2;
        }
        catch(SQLException sq)
        {
            logger.error(sq);
            status=2;
        }
        response.sendRedirect("ResponseController?s="+status);
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
            throws ServletException, IOException 
    {
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
