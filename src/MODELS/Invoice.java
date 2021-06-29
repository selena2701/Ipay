package MODELS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class Invoice {
    private String id = "";
    private int previousValue = 0;
    private int currentValue = 0;
    private int consumedValue = 0;
    private double VAT = 0.5;
    private double total = 0;
    private String electricityType = "";
    private Date fromDate = Date.from(Instant.EPOCH);
    private Date toDate = Date.from(Instant.EPOCH);
    private String customerID = "";
    private Date paidDate = Date.from(Instant.EPOCH);
    private boolean paid = false; //True if Paid, False if Unpaid

    public Invoice() {
    }

    public Invoice(String id, int previousValue, int currentValue, int consumedValue, double VAT, double total, String electricityType, Date fromDate, Date toDate, Date paidDate, boolean isPaid, String customerID) {
        this.id = id;
        this.previousValue = previousValue;
        this.currentValue = currentValue;
        this.consumedValue = consumedValue;
        this.VAT = VAT;
        this.total = total;
        this.electricityType = electricityType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.paidDate = paidDate;
        this.paid = isPaid;
        this.customerID = customerID;
    }

    public static Invoice fromResultSet(ResultSet result) throws SQLException {
        return new Invoice(result.getString("ElecBillId"), result.getInt("PreviousValue"), result.getInt("CurrentValue"), result.getInt("ConsumeValue"), result.getDouble("VAT"), result.getDouble("Total"), result.getString("ElectricityType"), result.getDate("FromDate"), result.getDate("ToDate"), result.getDate("DatePaid"), result.getString("StatusBill").equals("PAID"), result.getString("CusId"));
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

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public int getConsumedValue() {
        return consumedValue;
    }

    public void setConsumedValue(int consumedValue) {
        this.consumedValue = consumedValue;
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
