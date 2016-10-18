package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class CategoryDAO
{ 
    static Logger logger=Logger.getLogger(CategoryDAO.class);
    public boolean addCategory(BookCategory category) throws SQLException
    {
        Connection conn=null;
        PreparedStatement ps=null;
        boolean isInserted=false;
        boolean isFound=search(category);
        try
        {
            if(!isFound)
            {

                String sql="INSERT INTO category(CATEGORYNAME) VALUES(?)";
                conn=ConnectionManager.getConnection();
                conn.setAutoCommit(false);
                ps=conn.prepareCall(sql);
                ps.setString(1, category.getCategory());
                int status=ps.executeUpdate();
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
            }
        }
        finally
        {
            try
            {
                ConnectionManager.closeResources(null, ps, conn,null);
            }catch(SQLException e)
            {
                logger.error(e);
            }
        }
        
        return isInserted;
    }
    public boolean search(BookCategory category) throws SQLException
    {
        boolean isFound=false;
        Connection conn=null;
        ResultSet rst=null;
        PreparedStatement ps=null;
        String sql="SELECT CATEGORYNAME FROM CATEGORY WHERE UPPER(CATEGORYNAME)=UPPER(?)";
        try
        {
            conn=ConnectionManager.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1, category.getCategory());

            rst=ps.executeQuery();
            if(rst.next())
            {
                isFound=true;
            }
            else
            {
                isFound=false;
            }
        }finally
        {
            try
            {
                ConnectionManager.closeResources(rst, ps, conn,null);
            }catch(SQLException e)
            {
                logger.error(e);
            }
        }
        return isFound;
    }
    public boolean deleteCategory(BookCategory category) throws SQLException
    {
        int status=0;
        Connection conn=null;
        PreparedStatement ps=null;
        boolean isDeleted=false;
        String sql="DELETE FROM CATEGORY  WHERE CATEGORYID=?";
        try
        {
            conn=ConnectionManager.getConnection();
            conn.setAutoCommit(false);
            ps=conn.prepareCall(sql);
            ps.setInt(1, category.getIdCategory());
            status=ps.executeUpdate();

        }
        finally
        {
            try
            {   
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
                ConnectionManager.closeResources(null, ps, conn,null);
            }catch(SQLException e)
            {
                logger.error(e);
            }
        }
        return isDeleted;   
    }
    public List<BookCategory> allCategory() throws SQLException
    {
        List<BookCategory> bookCategoryList=null;
        BookCategory category=null;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rst=null;
        String sql="SELECT CATEGORYNAME,CATEGORYID FROM CATEGORY";
        try
        {
            conn=ConnectionManager.getConnection();
            ps=conn.prepareStatement(sql);
            rst=ps.executeQuery();
            bookCategoryList=new ArrayList<BookCategory>();
            while(rst.next())
            {
                category=new BookCategory();
                category.setCategory(rst.getString("CATEGORYNAME"));
                category.setIdCategory(rst.getInt("CATEGORYID"));
                bookCategoryList.add(category);
            }
        }
        finally
        {
            try
            {
                ConnectionManager.closeResources(rst, ps, conn,null);
            }catch(SQLException e)
            {
                logger.error(e);
            }
        }
        return bookCategoryList;
    }
    
}