package Model;
import java.sql.Date;

public class E_Notification {
    String ID, Detail, EditBy ;
    Date DateCreated ,DateSent ;



    public E_Notification(String ID,  Date dateCreated, Date dateSent, String editBy, String detail) {
        this.ID = ID;
        this.Detail = detail;
        this.EditBy = editBy;
        this.DateCreated = dateCreated;
        this.DateSent = dateSent;
    }

    public String getID() {
        return ID;
    }

    public String getDetail() {
        return Detail;
    }

    public String getEditBy() {
        return EditBy;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public Date getDateSent() {
        return DateSent;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public void setEditdBy(String edittedBy) {
        EditBy = edittedBy;
    }

    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }

    public void setDateSent(Date dateSent) {
        DateSent = dateSent;
    }
}
