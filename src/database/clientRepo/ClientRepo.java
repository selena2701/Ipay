package database.clientRepo;

import MODELS.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.*;

public class ClientRepo {

    private Connection connection;

    private Customer customer;

    private ObservableList<CreditCard> creditCards = FXCollections.observableArrayList();
    private ObservableList<Invoice> invoices = FXCollections.observableArrayList();


    public ClientRepo(String username) throws SQLException, ClassNotFoundException {
        customer = fetchCustomerData(username);
        profileLoader();
    }

    private void profileLoader() throws SQLException, ClassNotFoundException {
        loadAllCreditCardsFromDB();
        loadAllInvoicesFromDB();
    }


    private Customer fetchCustomerData(String username) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM E_CUSTOMER WHERE Username=?");
        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Customer customer = Customer.fromResultSet(resultSet);
            connection.close();
            return customer;
        }
        connection.close();
        return null;
    }

    //Load tableBill
    private void loadAllInvoicesFromDB() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM E_ELECTRICITY_BILL WHERE CUS_ID=?");
        statement.setString(1, customer.getId());

        ResultSet resultSet = statement.executeQuery();
        invoices.clear();
        while (resultSet.next()) {
            invoices.add(Invoice.fromResultSet(resultSet));
        }
        connection.close();
    }

    //Change status to Paid
    public void updateInvoice(String id) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE E_ELECTRICITY_BILL SET STATUSBILL=? WHERE ELEC_BILL_ID=?");
        pstmt.setString(1, "PAID");
        pstmt.setString(2, id);
        pstmt.executeUpdate();
        connection.close();
        loadAllInvoicesFromDB();
    }


    public void saveCustomer(Customer customer) throws SQLException, ClassNotFoundException{
        connection = DBConnection.connect();
        String gender = "Nam";

        PreparedStatement statement = connection.prepareStatement("UPDATE E_CUSTOMER SET " +
                "FullnameCUS = ?," +
                "PhoneCUS = ?," +
                "AddressCUS =?," +
                "NationalID = ?," +
                "Gender = ?," +
                "Birthday = ?," +
                "Region = ? WHERE CUS_ID = ?");
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getPhoneNumber());
        statement.setString(3, customer.getAddress());
        statement.setString(4, customer.getNationalId());
        if(!customer.getGender())
            gender ="Nu";
        statement.setString(5, gender);
        statement.setString(6, customer.getBirthday().toString());
        statement.setString(7, Region.regionToString(customer.getRegion()));
        statement.setString(8, customer.getId());

        statement.execute();
        connection.close();
    }

    private void loadAllCreditCardsFromDB() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM E_CREDITCARD WHERE CUS_ID=?");
        statement.setString(1, customer.getId());

        ResultSet resultSet = statement.executeQuery();
        creditCards.clear();
        while (resultSet.next()) {
            creditCards.add(CreditCard.fromResultSet(resultSet));
        }
        connection.close();
    }

    public void addCreditCard(CreditCard creditCard) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO E_CREDITCARD  VALUES(?,?,?,?,?,?,?)");
        statement.setString(1, creditCard.getId());
        statement.setString(2, creditCard.getCardholdername());
        statement.setString(3, creditCard.getCardnumber());
        statement.setString(4, creditCard.getAccountnumber());
        statement.setString(5, Bank.bankToString(creditCard.getBankname()));
        statement.setString(6, creditCard.getStatus());
        statement.setString(7, creditCard.getUserId());

        statement.execute();

        connection.close();
        loadAllCreditCardsFromDB();
    }
    public void deleteCreditCard(String creaditCardID) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM E_CREDITCARD WHERE CC_ID=?");
        statement.setString(1, creaditCardID);
        statement.execute();
        connection.close();
        loadAllCreditCardsFromDB();
    }
    public void setDefaultCreditCard(String creditCardID) throws SQLException, ClassNotFoundException{
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("UPDATE E_CREDITCARD SET STATUS =? WHERE STATUS=?");
        statement.setString(1, "");
        statement.setString(2, "Default");
        statement.execute();

        statement = connection.prepareStatement("UPDATE E_CREDITCARD SET STATUS =? WHERE CC_ID=?");
        statement.setString(1, "Default");
        statement.setString(2, creditCardID);
        statement.execute();
        connection.close();
        loadAllCreditCardsFromDB();
    }



    public ObservableList<CreditCard> getAllCreditCards() {
        return creditCards;
    }

    public ObservableList<Invoice> getInvoices() {
        return invoices;
    }

    public Customer getCustomer() {
        return customer;
    }
}
