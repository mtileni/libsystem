/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bruce
 */
public class ConnectionManager
{   
          
    private static String url="jdbc:derby://localhost:1527/libsystem";
    private String driver="org.apache.derby.jdbc.ClientDriver";
    private static String username="root";
    private static String password="root";
    
    public ConnectionManager()
    {
 
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public  static  Connection getConnection()throws SQLException
    {
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection conn=DriverManager.getConnection(url, username, password);
        return conn;
    }
   /**
    * Closing database resources
    */
    public static void closeResources(ResultSet rs, PreparedStatement ps, Connection conn,Statement st) throws SQLException
    {
        if(rs !=null)
        {
            rs.close();
            rs=null;
        }
        if(ps !=null)
        {
            ps.close();
            ps=null;
        }
        if(st !=null)
        {
            st.close();
            st=null;

        } 
        if(conn !=null)
        {
            conn.close();
            conn=null;

        }
    }

}
