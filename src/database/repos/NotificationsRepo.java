package database.repos;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Notification;

import java.sql.*;

public class NotificationsRepo {

    private final ObservableList<Notification> notifications = FXCollections.observableArrayList();

    private Connection connection = null;

    public NotificationsRepo() {
        try {
            loadNotificationsFromDB();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }


    private void loadNotificationsFromDB() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        String query = "SELECT NO_ID,DateCreated,DateSent, Detail,NameAD FROM E_NOTIFICATION JOIN E_ADMIN ON E_NOTIFICATION.EditedBy=E_ADMIN.AD_ID";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        notifications.clear();
        while (result.next()) {
            Notification resultNotification = Notification.fromResultSet(result);
            if (!notifications.contains(resultNotification)) {
                notifications.add(resultNotification);
            }
        }
        connection.close();
    }

    public void addNotification(Notification notification) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO E_NOTIFICATION VALUES(?,?,?,?,?)");
        statement.setString(1, notification.getId());
        statement.setDate(2, new Date(notification.getDateCreated().getTime()));
        statement.setDate(3, new Date(notification.getDatePublished().getTime()));
        statement.setString(4, notification.getEditedBy());
        statement.setString(5, notification.getDescription());
        statement.execute();
        connection.close();
        loadNotificationsFromDB();
    }

    public void deleteNotification(String notificationId) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM E_NOTIFICATION WHERE NO_ID=?");
        statement.setString(1, notificationId);
        statement.execute();
        connection.close();
        loadNotificationsFromDB();
    }


    public ObservableList<Notification> getNotifications() {
        return notifications;
    }
}
