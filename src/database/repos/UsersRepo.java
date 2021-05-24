package database.repos;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersRepo {

    private ObservableList<User> users = FXCollections.observableArrayList();

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
            users.add(User.fromResultSet(result));
        }
        connection.close();
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void addNewUser() {
        //TODO add new user
    }

}
