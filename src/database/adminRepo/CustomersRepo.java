package database.adminRepo;

import MODELS.Customer;
import utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomersRepo {

    private ObservableList<Customer> customers = FXCollections.observableArrayList();

    private Connection connection = null;

    public CustomersRepo() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        loadCustomersFromDB();
    }

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

    public void addNewUser() {
        //TODO add new user
    }

}
