package daoFactory;

import dao.MysqlNotificationDao;
import dao.MysqlUserDao;
import dao.NotificationDao;
import dao.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends DaoFactory {
    private static String url = "jdbc:mysql://localhost:3306/dbc";
    private static String user = "root";
    private static String password = "Phanvuthuyduong2806";
    public  Connection getJDBCConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex){
            System.err.println("Connect thất bại");
        }
        return null;
    }

    @Override
    public UserDao getUserDao() {
        return new MysqlUserDao();
    }
    @Override
    public NotificationDao getNotificationDao() {return new MysqlNotificationDao();}
}
