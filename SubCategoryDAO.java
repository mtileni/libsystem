package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
public class SubCategoryDAO
{
    Logger logger=Logger.getLogger(SubCategoryDAO.class);
    public boolean addSubCategory(BookSubCategory subCategory) throws SQLException
    {
        Connection conn=null;
        int status=0;
        PreparedStatement ps=null;
        boolean isFound=search(subCategory);
        boolean isInserted=false;
        String sql="INSERT INTO SUBCATEGORY(SUBCATEGORYNAME,CATEGORYID) VALUES(?,(SELECT CATEGORYID FROM CATEGORY WHERE upper(CATEGORYNAME)=upper(?)))";
        try
        {
            if(!isFound)
            {
                conn=ConnectionManager.getConnection();
                conn.setAutoCommit(false);
                ps=conn.prepareCall(sql);
                ps.setString(1,subCategory.getSubCategory());
                ps.setString(2, subCategory.getId().getCategory());
                status=ps.executeUpdate();
            }
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
                if(conn!=null)
                {
                    conn.rollback();
                    isInserted=false;
                }
               ConnectionManager.closeResources(null, ps, conn,null);
           }catch(SQLException e)
            {
                logger.error(e);
            } 
        }
        
        return isInserted;
    }
    
    public boolean search(BookSubCategory subCategory) throws SQLException
    {
        boolean isFound=false;
        Connection conn=null;
        ResultSet rst=null;
        PreparedStatement ps=null;
        String sql="SELECT SUBCATEGORYNAME FROM SUBCATEGORY WHERE SUBCATEGORYNAME=?";
        try
        {
            conn=ConnectionManager.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1, subCategory.getSubCategory());

            rst=ps.executeQuery();
            if(rst.next())
            {
                isFound=true;
            }
            else
            {
                isFound=false;
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
        return isFound;
    }
    
     public List<BookSubCategory> allSubCategory(BookCategory categoryID) throws SQLException
    {
        List<BookSubCategory> bookSubCategoryList=null;
        BookSubCategory subCategory=null;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rst=null;
        String sql="SELECT SUBCATEGORYNAME,BOOKSUBCATEGORYID FROM SUBCATEGORY WHERE CATEGORYID=?";
        try
        {
            conn=ConnectionManager.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1, categoryID.getIdCategory());
            rst=ps.executeQuery();
            bookSubCategoryList=new ArrayList<BookSubCategory>();
            while(rst.next())
            {
                subCategory=new BookSubCategory();
                subCategory.setSubCategory(rst.getString("SUBCATEGORYNAME"));
                subCategory.setIdSubCategory(rst.getInt("BOOKSUBCATEGORYID"));
                bookSubCategoryList.add(subCategory);
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
        return bookSubCategoryList;
    }
    
//    public static void main(String[] args)
//    {
//        BookSubCategory c=new BookSubCategory();
//        BookCategory bc=new BookCategory();
//        bc.setIdCategory(25);
//        SubCategoryDAO dao=new SubCategoryDAO();
//        try {
//            //        c.setSubCategory("Lifel1y");
////        c.setId(bc);
////        try {
////            boolean isInserted=dao.addSubCategory(c);
////            if(isInserted)
////            {
////                System.out.println(" Record inserted Successfully");
////            }
////        } catch (SQLException ex) 
////        {
////          ex.printStackTrace();
////        }
//            List<BookSubCategory> l=dao.allSubCategory(bc);
//            for(BookSubCategory bs:l)
//            {
//                System.out.println(" SubMenu "+bs.getIdSubCategory()+ " "+bs.getSubCategory());
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SubCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}



