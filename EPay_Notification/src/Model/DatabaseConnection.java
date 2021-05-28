package Model;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DatabaseConnection {
    private static Connection dbLink;
     public static Connection getConnection()
     {
         String Url = "jdbc:sqlserver://localhost:1433;"+ "databaseName=DBC;user=sa;password=112367";

         try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             dbLink=DriverManager.getConnection(Url);
         } catch (SQLException | ClassNotFoundException e) {
             Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
         }
         return dbLink;

     }

}
