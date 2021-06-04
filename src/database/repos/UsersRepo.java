package database.repos;

import models.Customer;
import utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersRepo {

    private ObservableList<Customer> customers = FXCollections.observableArrayList();

    private Connection connection = null;

    public UsersRepo() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        loadUserFromDB();
    }

    private void loadUserFromDB() throws SQLException {
        String query = "SELECT * FROM E_CUSTOMER";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            customers.add(Customer.fromResultSet(result));
        }
        connection.close();
    }

    public ObservableList<Customer> getUsers() {
        return customers;
    }

    public void addNewUser() {
        //TODO add new user
    }

}
