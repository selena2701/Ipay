package MODELS;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCard {
    private String id;
    private String cardholdername;
    private String cardnumber;
    private String accountnumber;
    private Bank.BANK_ENUM bankname = Bank.BANK_ENUM.ACB;
    private String status;
    private String userId;

    public CreditCard() {
    }

    public CreditCard(String id, String cardholdername, String cardnumber, String accountnumber, Bank.BANK_ENUM bankname, String status, String userId) {
        this.id = id;
        this.cardholdername = cardholdername;
        this.cardnumber = cardnumber;
        this.accountnumber = accountnumber;
        this.bankname = bankname;
        this.status = status;
        this.userId = userId;
    }

    public CreditCard(String userId, String ccid) {
        this.userId = userId;
        this.id = ccid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardholdername() {
        return cardholdername;
    }

    public void setCardholdername(String cardholdername) {
        this.cardholdername = cardholdername;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Bank.BANK_ENUM getBankname() {
        return bankname;
    }

    public void setBankname(Bank.BANK_ENUM bankname) {
        this.bankname = bankname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static CreditCard fromResultSet(ResultSet resultSet) throws SQLException {
        return new CreditCard(resultSet.getString("CreditCardId"), resultSet.getString("CardHolderName"), resultSet.getString("CardNumber"), resultSet.getString("AccountNumber"), Bank.stringToBank(resultSet.getString("BankName")), resultSet.getString("Status"), resultSet.getString("CusId"));
    }
}
