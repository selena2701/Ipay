package database.adminRepo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import MODELS.Notification;
import utils.DBConnection;

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
        String query = "SELECT NoId,DateCreated,DateSent, Detail,NameAd FROM E_NOTIFICATION JOIN E_ADMIN ON E_NOTIFICATION.EditedBy=E_ADMIN.AdminId ";
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
        System.out.println(notification.getDateCreated());
        statement.setString(1, notification.getId());
        statement.setDate(2, new Date(notification.getDateCreated().getTime()));
        statement.setDate(3, new Date(notification.getDatePublished().getTime()));
        statement.setString(4, notification.getEditedBy());
        statement.setString(5, notification.getDescription());
        statement.execute();
        connection.close();
        loadNotificationsFromDB();
    }

    //TODO recheck here to make sure things work precisely
    public void updateNotification(Notification notification) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("UPDATE E_NOTIFICATION SET DateSent=?,EditedBy=?,Detail=? WHERE NoId=?");
        statement.setObject(1, notification.getDatePublished());
        statement.setString(2, notification.getEditedBy());
        statement.setString(3, notification.getDescription());
        statement.setString(4, notification.getId());
        statement.execute();
        connection.close();
        loadNotificationsFromDB();
    }

    //TODO recheck here to make sure things work precisely
    public void deleteNotification(String notificationId) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM E_NOTIFICATION WHERE NoId=?");
        statement.setString(1, notificationId);
        statement.execute();
        connection.close();
        loadNotificationsFromDB();
    }

    public Notification getNotificationById(String id) {
        for (Notification notification : notifications) {
            if (notification.getId().equals(id)) {
                return notification;
            }
        }
        return null;
    }


    public ObservableList<Notification> getNotifications() {
        return notifications;
    }
}
