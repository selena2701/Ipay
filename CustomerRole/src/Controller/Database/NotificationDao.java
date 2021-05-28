package Controller.Database;

import Model.Notification;

import java.sql.SQLException;
import java.util.List;

public interface NotificationDao {

	List<Notification> all() throws SQLException;
}
