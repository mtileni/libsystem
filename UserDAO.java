/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author bruce
 */
public class UserDAO
{
    public boolean login(UserBean user) throws SQLException
    {
        boolean isFound=false;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rst=null;
        String sql="SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME=? AND PASSWORD= ?";
        try
        {
            conn=ConnectionManager.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
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
            ConnectionManager.closeResources(rst, ps, conn, null);
        }
        return isFound;
    }
    
//    public static void main(String[] args)
//    {
//        UserBean user=new UserBean();
//        user.setPassword("brucelly@2014");
//        user.setUsername("bruce");
//        UserDAO dao=new UserDAO();
//        try
//        { 
//            boolean isLogin=dao.login(user);
//            if(isLogin)
//            {
//                System.out.println("You successfully Logged in");
//            }
//            else
//            {
//               System.out.println("Incorrect Password"); 
//            }
//        }catch(SQLException e)
//        {
//            
//        }
//    }
}
