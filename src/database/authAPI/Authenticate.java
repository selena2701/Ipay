package database.authAPI;

import models.Customer;
import models.Region;
import utils.DBConnection;
import utils.Encryption;
import utils.exceptions.UserExistsException;

import java.sql.*;
import java.time.Instant;

public class Authenticate {


    /*
     * This function is used for logging user in with appropriate role
     * This will return [null] if (fail), [true] if (admin) and [false] if (customer)
     * */
    public static Boolean login(String userName, String password) throws SQLException, ClassNotFoundException {
        Boolean isAdmin = null;

        String hashPassword = Encryption.encrypt(password);

        Connection connection = DBConnection.connect();
        String sql = "SELECT * FROM USER_ACCOUNT WHERE Username=? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() && resultSet.getString("Password_Login").equals(hashPassword))
            isAdmin = resultSet.getInt("RoleUser") == 1;

        connection.close();
        return isAdmin;
    }


    /*
     * Check if user is existed first then add to Both User_Account and Customer Tables
     * Throw UserExistsException if user is existed
     * */
    public static void signUp(Customer customer, String rawPassword) throws SQLException, ClassNotFoundException, UserExistsException {
        addUserToAccountTable(customer.getUsername(), rawPassword);
        addUserToCustomerTable(customer);
    }

    private static void addUserToAccountTable(String username, String rawPassword) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.connect();
        PreparedStatement addUserToAccountStatement = connection.prepareStatement("INSERT INTO USER_ACCOUNT VALUES (?,?,1)");
        addUserToAccountStatement.setString(1, username);
        addUserToAccountStatement.setString(2, Encryption.encrypt(rawPassword));
        addUserToAccountStatement.executeUpdate();
        connection.close();
    }

    public static boolean checkUserExists(String username) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.connect();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER_ACCOUNT WHERE Username=?");
        statement.setString(1, username);

        if (statement.executeQuery().next()) {
            connection.close();
            return true;
        }
        connection.close();
        return false;
    }

    private static void addUserToCustomerTable(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.connect();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO E_CUSTOMER VALUES(?,?,?,?,?,?,?,?,?,CONVERT(VARCHAR,?,103))");
        statement.setString(1, findNextCustomerId());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getUsername());
        statement.setString(4, customer.getPhoneNumber());
        statement.setString(5, customer.getAddress());
        statement.setString(6, customer.getNationalId());
        statement.setString(7, customer.getGender() ? "Nam" : "Nu");

        System.out.println(Date.valueOf(customer.getBirthday()));

        statement.setDate(8, Date.valueOf("2001-12-08"));
        statement.setString(9, Region.regionToString(customer.getRegion()));
        statement.setDate(10, new Date(Date.from(Instant.now()).getTime()));
        statement.executeUpdate();
        connection.close();
    }

    private static String findNextCustomerId() throws SQLException, ClassNotFoundException {

        String result = "";
        Connection connection = DBConnection.connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT CUS_ID FROM E_CUSTOMER ORDER BY CUS_ID DESC");

        if (!resultSet.next()) {
            result = "CUS1";
            connection.close();
            return result;
        }
        String currentId = resultSet.getString("CUS_ID");
        result = "CUS" + (Integer.parseInt(currentId.substring(3, 4)) + 1);
        connection.close();
        return result;

    }
}
