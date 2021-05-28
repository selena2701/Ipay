package Controller;


import Model.Notification;

import java.sql.SQLException;
import java.util.List;

public class NotificationController {

	private static NotificationController instance = new NotificationController();

	private NotificationController(){}
	
	public static NotificationController getInstance() {
		return instance;
	}

	public List<Notification> allNotification() throws SQLException {
		return Notification.all();
	}

}
