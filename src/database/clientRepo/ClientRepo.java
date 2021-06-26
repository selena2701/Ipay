package database.clientRepo;

import MODELS.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.exolab.castor.types.DateTime;
import utils.DBConnection;
import utils.helper.Encryption;

import java.sql.*;
import java.time.LocalDate;

public class ClientRepo {

    private Connection connection;

    private Customer customer;

    private ObservableList<CreditCard> creditCards = FXCollections.observableArrayList();
    private ObservableList<Invoice> invoices = FXCollections.observableArrayList();
    private ObservableList<Notification> notifications = FXCollections.observableArrayList();


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

    private void loadAllNotificationFromDB() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM E_NOTIFICATION");

        ResultSet resultSet = statement.executeQuery();
        notifications.clear();
        while (resultSet.next()) {
            notifications.add(Notification.fromResultSetBadge(resultSet));
        }
        connection.close();
    }

    //Change status to Paid
    public void updateInvoice(String id) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        PreparedStatement pstmt = connection.prepareStatement("UPDATE E_ELECTRICITY_BILL SET STATUSBILL=?, DATEPAID = ? WHERE ELEC_BILL_ID=?");
        pstmt.setString(1, "PAID");
        pstmt.setTimestamp(2, date);
        pstmt.setString(3, id);
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

    public Boolean verify(String userName, String password) throws SQLException, ClassNotFoundException {

        String hashPassword = Encryption.encrypt(password);
        Connection connection = DBConnection.connect();

        String sql = "SELECT * FROM USER_ACCOUNT WHERE Username=? AND Password_Login=?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, userName);
        statement.setString(2, hashPassword);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
            return true;
        connection.close();
        return false;
    }


    public void changePassword(String username, String newpassword) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();

        PreparedStatement statement = connection.prepareStatement("UPDATE USER_ACCOUNT SET Password_Login = ? WHERE Username = ?");
        statement.setString(1, Encryption.encrypt(newpassword));
        statement.setString(2, username);
        statement.executeUpdate();

        connection.close();
    }
    public Boolean checkNotificationSeen(String cus_id, String no_id) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM BADGE_NOTIFICATION WHERE CUS_ID = ? AND NO_ID = ?");
        statement.setString(1, cus_id);
        statement.setString(2, no_id);

        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();

    }

    public void saveNotificationSeen(String cus_id, String no_id) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO BADGE_NOTIFICATION  VALUES(?,?)");
        statement.setString(1, cus_id);
        statement.setString(2, no_id);

        if(!checkNotificationSeen(cus_id, no_id)){
            statement.execute();
        }

        connection.close();
    }

    public Integer displayNewNotification(String cus_id) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        int count = 0;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM BADGE_NOTIFICATION WHERE CUS_ID = ?");
        statement.setString(1, cus_id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            count++;
        }
        loadAllNotificationFromDB();
        return notifications.size() - count;
    }
    public ObservableList<Notification> getNotification() {
        return notifications;
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
