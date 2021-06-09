package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/dbc_connection";
        String user = "root";
        String password = "Phanvuthuyduong2806";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

}

