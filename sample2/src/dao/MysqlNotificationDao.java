package dao;

import daoFactory.DaoFactory;
import model.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class UserDao has the responsibility 
 * of access the user's data
 * 
 * @author Diego Marczal
 * @version 2011.06.15
 */
public class MysqlNotificationDao implements NotificationDao {


	private static final String
			ALL = "SELECT * FROM e_notification";

	public List<Notification> all() throws SQLException {
		ArrayList<Notification> notifications = new ArrayList<Notification>();

		Connection c = DaoFactory.getDatabase().getJDBCConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		while(rset.next()){
			notifications.add(createNotification(rset));
		}

		pstmt.close();
		c.close();

		return notifications;
	}



	
	/* method to create users **/
	private Notification createNotification(ResultSet rset) throws SQLException{
		Notification notification = new Notification(rset.getString("NO_ID"));

		notification.setDatecreated(rset.getDate("DateCreated").toString());
		notification.setDatesent(rset.getDate("DateSent"));
		notification.setEditedby(rset.getString("EditedBy"));
		notification.setDetail(rset.getString("Detail"));

		return notification;
	}


}
