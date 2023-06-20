/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucntt.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author nguye
 */
public class DBHelper {

    public static Connection makeConnection() throws  SQLException, NamingException {
          //1. Get current context
          Context currentContext = new InitialContext();
          //2. Get wed server context
          Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
          //3. Get DataSource de mo ket noi
          DataSource ds = (DataSource)tomcatContext.lookup("DSSE1710");
          //4. Open connection
          Connection con = ds.getConnection();
          
          return con;
//        Connection con = null;
//        // Load Driver // phai co add Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //Tao connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=UserManagement";
//        // Mo ket noi
//        con = DriverManager.getConnection(url, "sa", "12345");
//        // Return conn
//        return con;
    }
    
    
}
