package database.adminRepo;

import MODELS.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersRepo {

    public ObservableList<Customer> customers = FXCollections.observableArrayList();

    private Connection connection = null;

    public UsersRepo() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        loadCustomersFromDB();
    }

    //Load data
    private void loadCustomersFromDB() throws SQLException {
        String query = "SELECT * FROM E_CUSTOMER";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            customers.add(Customer.fromResultSet(result));
        }
        connection.close();
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }
}
