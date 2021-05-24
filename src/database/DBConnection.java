package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
   private static final String DB_DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
   private static final String DB_USER = "SA";
   private static final String DB_PASSWORD = "h0@n9th1nh";


   public static Connection connect() throws ClassNotFoundException, SQLException {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      String url="jdbc:sqlserver://localhost:1433;databaseName=DBConnection;username=SA;password=h0@n9th1nh";
      return DriverManager.getConnection(url);
   }

}
