package Model;

import Controller.Database.NotificationDao;
import Controller.Database.DaoFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Notification {
    private String no_id;
    private String datecreated;
    private Date datesent;
    private String editedby;
    private String detail;

    public Notification(String no_id){
        this.no_id = no_id;
    }

    public String getNo_id() {
        return no_id;
    }

    public void setNo_id(String no_id) {
        this.no_id = no_id;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public Date getDatesent() {
        return datesent;
    }

    public void setDatesent(Date datesent) {
        this.datesent = datesent;
    }

    public String getEditedby() {
        return editedby;
    }

    public void setEditedby(String editedby) {
        this.editedby = editedby;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    @Override
    public String toString() {
        return  " no_id: "    + no_id +
                " date: "  + datecreated +
                " detail: " + detail;
    }

    public static List<Notification> all() throws SQLException {
        return notificationDAO().all();
    }

    private static NotificationDao notificationDAO(){
        DaoFactory dao = DaoFactory.getDatabase();
        return dao.getNotificationDao();
    }
}
