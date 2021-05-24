package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Admin {
    private String id="";
    private String name="";
    private String username="";
    private String promotedBy="";
    private Date datePromoted=Date.from(Instant.EPOCH);

    public Admin() {
    }

    public Admin(String name, String promotedBy, Date datePromoted) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.promotedBy = promotedBy;
        this.datePromoted = datePromoted;
    }

    public static Admin fromResultSet(ResultSet resultSet) throws SQLException {
        Admin admin=new Admin();
        admin.id=resultSet.getString("AD_ID");
        admin.name=resultSet.getString("NameAD");
        admin.username=resultSet.getString("UsernameAD");
        admin.promotedBy=resultSet.getString("PromotedBy");
        admin.datePromoted=resultSet.getDate("DatePromote");
        return admin;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatePromoted() {
        return datePromoted;
    }

    public String getPromotedBy() {
        return promotedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return getId().equals(admin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
