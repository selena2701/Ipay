package daoFactory;

import dao.NotificationDao;
import dao.UserDao;

import java.sql.Connection;

public abstract class DaoFactory {

  /* 
   * There will be a method for each DAO that can be
   * created. The concrete factories will have to
   * implement these methods.
   */
  public abstract Connection getJDBCConnection();
  public abstract UserDao getUserDao();
  public abstract NotificationDao getNotificationDao();
  
  public static DaoFactory getDatabase() {
      return new DatabaseConnection();
  }
}
