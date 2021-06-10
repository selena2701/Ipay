package database.authRepo;

import MODELS.Customer;
import MODELS.Region;
import utils.DBConnection;
import utils.helper.Encryption;
import utils.exceptions.UserExistsException;

import java.sql.*;

public class AuthRepo {
    /*
     * This function is used for logging user in with appropriate role
     * This will return [null] if (fail), [true] if (admin) and [false] if (customer)
     * */
    public static Boolean login(String userName, String password) throws SQLException, ClassNotFoundException {
        Boolean isUser= null;
        String hashPassword = Encryption.encrypt(password);
        Connection connection = DBConnection.connect();
        String sql = "SELECT * FROM USER_ACCOUNT WHERE Username=? AND Password_Login=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userName);
        statement.setString(2, hashPassword);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            isUser = resultSet.getInt("Role") == 1;
        connection.close();
        return isUser;
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
        PreparedStatement addUserToAccountStatement = connection.prepareStatement("INSERT INTO USER_ACCOUNT VALUES (?,?,0)");
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

        PreparedStatement statement = connection.prepareStatement("" +
                "SET DATEFORMAT DMY; " +
                "INSERT INTO E_CUSTOMER " +
                "(CUS_ID,FullnameCUS,Username,PhoneCUS,AddressCUS,NationalID,Gender,Birthday,Region)" +
                "VALUES(?,?,?,?,?,?,?,CONVERT (VARCHAR ,?,103),?)");

        statement.setString(1, findNextCustomerId());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getUsername());
        statement.setString(4, customer.getPhoneNumber());
        statement.setString(5, customer.getAddress());
        statement.setString(6, customer.getNationalId());
        statement.setString(7, customer.getGender() ? "Nam" : "Nu");
        statement.setString(8, "13/04/1999");
        statement.setString(9, Region.regionToString(customer.getRegion()));
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
