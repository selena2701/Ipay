package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class Invoice {

    private String id = "";
    private int previousValue = 0;
    private int currentValue = 0;
    private double total = 0;
    private String electricityType = "";
    private Date fromDate = Date.from(Instant.EPOCH);
    private Date toDate = Date.from(Instant.EPOCH);
    private String customerID = "";

    public Invoice() {
    }

    public Invoice(String id, int previousValue, int currentValue, double total, String electricityType, Date fromDate, Date toDate, String customerID) {
        this.id = id;
        this.previousValue = previousValue;
        this.currentValue = currentValue;
        this.total = total;
        this.electricityType = electricityType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.customerID = customerID;
    }

    public static Invoice fromResultSet(ResultSet result) throws SQLException {
        return new Invoice(result.getString("ELEC_BILL_ID"), result.getInt("PreviousValue"), result.getInt("CurrentValue"), result.getDouble("Total"), result.getString("Electricity_Type"), result.getDate("FromDate"), result.getDate("ToDate"), result.getString("CUS_ID"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(int previousValue) {
        this.previousValue = previousValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getElectricityType() {
        return electricityType;
    }

    public void setElectricityType(String electricityType) {
        this.electricityType = electricityType;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return getId().equals(invoice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
