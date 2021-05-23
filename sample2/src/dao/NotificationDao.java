package dao;

import model.Notification;

import java.sql.SQLException;
import java.util.List;

public interface NotificationDao {

	List<Notification> all() throws SQLException;
}
