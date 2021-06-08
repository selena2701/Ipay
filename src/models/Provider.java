package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class Provider {
    private String id = "";
    private String name = "";
    private String phone = "";
    private String address = "";
    private Date dateJoined = Date.from(Instant.EPOCH);
    private String adminPromoted = "";

    public Provider() {
    }

    public Provider(String id, String name, String phone, String address, Date dateJoined, String adminPromoted) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.dateJoined = dateJoined;
        this.adminPromoted = adminPromoted;
    }

    public static Provider fromResultSet(ResultSet result) throws SQLException {
        return new Provider(result.getString("PRO_ID"), result.getString("NamePRO"), result.getString("PhonePRO"), result.getString("AddressPRO"), result.getDate("DateJoined"), result.getString("PromotedBy"));
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getAdminPromoted() {
        return adminPromoted;
    }

    public void setAdminPromoted(String adminPromoted) {
        this.adminPromoted = adminPromoted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider)) return false;
        Provider provider = (Provider) o;
        return getId().equals(provider.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
