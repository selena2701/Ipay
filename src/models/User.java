package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class User {
    private String id = "";
    private String name = "";
    private String username = "";
    private String phoneNumber = "";
    private String nationalId = "";
    private String address = "";
    private boolean gender = false; //true=male, false=female
    private Date birthday = Date.from(Instant.EPOCH);
    private Region region = Region.SouthSide;
    private Date dateRegister = Date.from(Instant.EPOCH);

    public User() {

    }

    public User(String id, String name, String username, String phoneNumber, String nationalId, boolean gender, Date birthday, Region region, Date dateRegister) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.gender = gender;
        this.birthday = birthday;
        this.region = region;
        this.dateRegister = dateRegister;
    }

    public static User fromResultSet(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getString("CUS_ID"));
        user.setName(result.getString("FullnameCUS"));
        user.setUsername(result.getString("UsernameCUS"));
        user.setPhoneNumber(result.getString("PhoneCUS"));
        user.setAddress(result.getString("AddressCUS"));
        user.setNationalId(result.getString("NationalID"));
        user.setGender(result.getString("Gender").equals("Nam"));
        user.setBirthday(result.getDate("Birthday"));
        switch (result.getString("Region")) {
            case "Mien Tay":
                user.setRegion(Region.WestSide);
                break;
            case "Mien Trung":
                user.setRegion(Region.MidSide);
                break;
            case "Mien Bac":
                user.setRegion(Region.NorthSide);
                break;
            default:
                user.setRegion(Region.SouthSide);
                break;
        }
        user.setDateRegister(result.getDate("DateRegister"));
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
