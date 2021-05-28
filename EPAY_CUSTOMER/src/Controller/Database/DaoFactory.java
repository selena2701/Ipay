package Controller.Database;

import java.sql.Connection;

public abstract class DaoFactory {

  /* 
   * There will be a method for each DAO that can be
   * created. The concrete factories will have to
   * implement these methods.
   */
  public abstract Connection getConnection();
  public abstract CustomerDao getUserDao();
  public abstract NotificationDao getNotificationDao();
  public abstract BillDao getBillDao();
  
  public static DaoFactory getDatabase() {
      return new DatabaseConnection();
  }
}
