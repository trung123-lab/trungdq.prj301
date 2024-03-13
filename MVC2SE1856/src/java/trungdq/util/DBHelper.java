/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author trung
 */
public class DBHelper implements Serializable {
    public static Connection getConnection() 
            throws /*ClassNotFoundException,*/ SQLException, NamingException {
       //1.get current system file
       Context currentContext = new InitialContext();
       //2.get container context
       Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
       //3.get DataSource from container
       DataSource ds = (DataSource) tomcatContext.lookup("TDQ1");
       //4.getConnection
       Connection con = ds.getConnection();
       
       return con;

        /*//1. Load Driver (driver is available)
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create connection String
        String url = "jdbc:sqlserver://localhost:1433;"
              + "databaseName=Account";
        //3. Open connection
        Connection con = DriverManager.getConnection(url, "sa", "123456");
        
        return con;*/
    }
    
    
}
