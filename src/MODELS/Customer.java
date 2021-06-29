package MODELS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Customer {
    private String id = "";
    private String name = "";
    private String username = "";
    private String phoneNumber = "";
    private String nationalId = "";
    private String address = "";
    private boolean gender = false; //true=male, false=female
    private LocalDate birthday = LocalDate.now();
    private Region.REGION_ENUM region = Region.REGION_ENUM.WestSide;
    private Date dateRegister = Date.from(Instant.EPOCH);

    public Customer() {

    }

    public Customer(String name, String username, String phoneNumber, String address, String nationalId, boolean gender, LocalDate birthday, Region.REGION_ENUM region, Date dateRegister) {
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.nationalId = nationalId;
        this.gender = gender;
        this.birthday = birthday;
        this.region = region;
        this.dateRegister = dateRegister;
    }

    public Customer(String id, String name, String username, String phoneNumber, String address, String nationalId, boolean gender, LocalDate birthday, Region.REGION_ENUM region, Date dateRegister) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.nationalId = nationalId;
        this.gender = gender;
        this.birthday = birthday;
        this.region = region;
        this.dateRegister = dateRegister;
    }

    public static Customer fromResultSet(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setId(result.getString("CusId"));
        customer.setName(result.getString("FullnameCus"));
        customer.setUsername(result.getString("Username"));
        customer.setPhoneNumber(result.getString("PhoneCus"));
        customer.setAddress(result.getString("AddressCus"));
        customer.setNationalId(result.getString("NationalID"));
        customer.setGender(result.getString("Gender").equals("Nam"));
        customer.setBirthday(result.getDate("Birthday").toLocalDate());
        customer.setRegion(Region.stringToRegion(result.getString("Region")));
        customer.setDateRegister(result.getDate("DateRegister"));
        return customer;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
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

    public Region.REGION_ENUM getRegion() {
        return region;
    }

    public void setRegion(Region.REGION_ENUM region) {
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
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId().equals(customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
