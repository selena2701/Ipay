package database.clientRepo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import MODELS.CreditCard;
import MODELS.Customer;
import MODELS.Invoice;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        loadAllCreditCards();
        loadAllInvoices();
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

    private void loadAllCreditCards() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM E_CREDITCARD WHERE CUS_ID=?");
        statement.setString(1, customer.getId());

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            creditCards.add(CreditCard.fromResultSet(resultSet));
        }
        connection.close();
    }

    private void loadAllInvoices() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM E_ELECTRICITY_BILL WHERE CUS_ID=?");
        statement.setString(1, customer.getId());

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            invoices.add(Invoice.fromResultSet(resultSet));
        }
        connection.close();
    }


    public void updateInvoice(String id) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE E_ELECTRICITY_BILL SET STATUSBILL=? WHERE ELEC_BILL_ID=?");
        pstmt.setString(1, "Default");
        pstmt.setString(2, id);
        pstmt.executeUpdate();
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
