package Controller.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends DaoFactory {
    private static String url = "jdbc:sqlserver://localhost:1433/DBC";
    private static String user = "sa";
    private static String password = "112367";
    public  Connection getJDBCConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
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
    public CustomerDao getUserDao() {
        return new MysqlCustomerDao();
    }
    @Override
    public NotificationDao getNotificationDao() {return new MysqlNotificationDao();}
    @Override
    public BillDao getBillDao() {return new MysqlBillDao();}
}
