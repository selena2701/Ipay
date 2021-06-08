package database.adminRepo;

import utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
<<<<<<< HEAD
import MODELS.Admin;
=======
import models.Admin;
>>>>>>> b46c0cc0da32f7bf4e24fa2aa3679d67f7533556

import java.sql.*;

public class AdminRepo {

    private Connection connection = null;

    private final ObservableList<Admin> admins = FXCollections.observableArrayList();

    public AdminRepo() throws SQLException, ClassNotFoundException {
        loadAdminsFromDB();
    }

    public ObservableList<Admin> getAdmins() {
        return admins;
    }

    private void loadAdminsFromDB() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.connect();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM E_ADMIN";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Admin admin = Admin.fromResultSet(resultSet);
            if (!admins.contains(admin)) {
                admins.add(admin);
            }
        }
    }

    public Admin getAdminById(String id) {
        for (Admin admin : admins) {
            if (admin.getId().equals(id)) {
                return admin;
            }
        }
        return null;
    }


}
