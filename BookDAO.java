package model;


import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;

/**
 * @author bruce
 */

public class BookDAO
{
    static Logger logger = Logger.getLogger(BookDAO.class);
    public boolean addBook(Books book) throws SQLException, SQLDataException
    {
        PreparedStatement ps=null;
        Connection conn=null;
        boolean isInserted=false;
        String sql="";
        sql = "INSERT INTO BOOK(BOOKNAME,BOOKAUTHOR,BOOKSUBCATEGORYID,IMAGE) VALUES(?,?,(SELECT BOOKSUBCATEGORYID FROM SUBCATEGORY WHERE UPPER(SUBCATEGORYNAME)=UPPER(?)),?)";
        int status=0;
        try
        {
            conn=ConnectionManager.getConnection();
            conn.setAutoCommit(false);
            ps=conn.prepareCall(sql);
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookAuthor());
            ps.setString(3, book.getId().getSubCategory());
            ps.setBlob(4,book.getImage().getFile());
            status=ps.executeUpdate();
        }
        finally
        {
            try
            {
                if(status>0)
                {
                conn.commit();
                isInserted=true;
                }
                else
                {
                    conn.rollback();
                    isInserted=false;
                }
                ConnectionManager.closeResources(null, ps, conn, null);
            }catch(SQLException e)
            {
                logger.error(e);
            }
        }
        return isInserted;
    }
    
    public List<Books> listOfBooks(BookSubCategory subCategory) throws SQLException
    {
        List<Books> bookList=new ArrayList();
        Books book=null;
        PreparedStatement ps=null;
        Connection conn=null;
        ResultSet rst=null;
        String sql="SELECT BOOKNAME,BOOKID,BOOKAUTHOR,IMAGE FROM BOOK WHERE BOOKSUBCATEGORYID=?";
        try
        {
            conn=ConnectionManager.getConnection();
            ps=conn.prepareCall(sql);
            ps.setInt(1, subCategory.getIdSubCategory());
            rst=ps.executeQuery();
            while(rst.next())
            {
                book=new Books();
                BookImage image=new BookImage();
                book.setBookAuthor(rst.getString("BOOKAUTHOR"));
                book.setBookName(rst.getString("BOOKNAME"));
                book.setIdBook(rst.getInt("BOOKID"));
                image.setLoadImage(rst.getBytes("IMAGE"));
                image.setImageBytes(DatatypeConverter.printBase64Binary(image.getLoadImage()));
                book.setImage(image);
                bookList.add(book);
            }
        }
        finally
        {
            try
            {
               ConnectionManager.closeResources(rst, ps, conn, null); 
            }
            catch(SQLException sq)
            {
                logger.error(sq);
            }   
        }
        return bookList;
    } 
    
    public boolean deleteBook(Books id) throws SQLException
    {
        boolean isDeleted=false;
        Connection conn=null;
        PreparedStatement ps=null;
        String sql=" DELETE FROM BOOK WHERE BOOKID=?";
        try
        {
            conn=ConnectionManager.getConnection();
            ps=conn.prepareCall(sql);
            ps.setInt(1, id.getIdBook());
            int status=ps.executeUpdate();
            if(status>0)
            {
                conn.commit();
                isDeleted=true;
            }
            else
            {
                conn.rollback();
                isDeleted=false;
            }
        }
        finally
        {
            try
            {
                ConnectionManager.closeResources(null, ps, conn, null);
            }catch(SQLException sq)
            {
                logger.error(sq);
            }
        }
        return isDeleted;
    }
    
    
    public static void main(String[] args)
    {
        BookSubCategory bsc=new BookSubCategory();
        Books book=new Books();
//        BookImage image=new BookImage();
//        image.setContentType("image/png");
//        image.setFileName("Pic");
//        bsc.setSubCategory("Economics");
//        book.setId(bsc);
//        book.setImage(image);
//        book.setBookName(" Maths Second Edition");
//        book.setBookAuthor("Jan Joe Jayze");
        book.setIdBook(352);
        BookDAO bookDAO=new BookDAO();
        try
        {
            boolean isInserted=bookDAO.deleteBook(book);
            if(isInserted)
            {
                System.out.println("Book deleted successfully");
            }
            else
            {
                 System.out.println("No Book found");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}