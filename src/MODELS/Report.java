package MODELS;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Report {
    private String period = "";
    private int numberTransactions = 0;
    private int totalUsers = 0;
    private double totalTransactionValue = 0;

    public Report() {
    }

    public Report(String period, int numberTransaction, int totalUsers, double totalTransactionValue) {
        this.period = period;
        this.numberTransactions = numberTransaction;
        this.totalUsers = totalUsers;
        this.totalTransactionValue = totalTransactionValue;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getNumberTransactions() {
        return numberTransactions;
    }

    public void setNumberTransactions(int numberTransactions) {
        this.numberTransactions = numberTransactions;
    }

    public double getTotalTransactionValue() {
        return totalTransactionValue;
    }

    public void setTotalTransactionValue(double totalTransactionValue) {
        this.totalTransactionValue = totalTransactionValue;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public static Report fromResultSet(ResultSet resultSet) throws SQLException {
        return new Report(resultSet.getString("PERIOD"), resultSet.getInt("SLGIAODICH"), resultSet.getInt("SLNGUOIDUNG"), resultSet.getDouble("TONGSOTIENGD"));
    }
}
