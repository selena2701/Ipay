package models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class Notification {
    private String id = "";
    private String description = "";
    private Date dateCreated = Date.from(Instant.EPOCH);
    private Date datePublished = Date.from(Instant.EPOCH);
    private String editedBy="";

    public Notification() {

    }

    public Notification(String id, String description, Date createdDay, Date publishedDay, String editedBy) {
        this.id = id;
        this.description = description;
        this.dateCreated = createdDay;
        this.datePublished = publishedDay;
        this.editedBy=editedBy;
    }

    public static Notification fromResultSet(ResultSet resultSet) throws SQLException {
        Notification notification = new Notification();
        notification.id = resultSet.getString("NO_ID");
        notification.dateCreated = resultSet.getDate("DateCreated");
        notification.datePublished = resultSet.getDate("DateSent");
        notification.description = resultSet.getString("Detail");
        notification.editedBy = resultSet.getString("NameAD");
        return notification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}