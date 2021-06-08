package database.adminRepo;

import utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import MODELS.Invoice;

import java.sql.*;

public class InvoicesRepo {

    private final ObservableList<Invoice> invoices= FXCollections.observableArrayList();

    private Connection connection=null;

    public InvoicesRepo () throws SQLException, ClassNotFoundException {
        loadInvoicesFromDB();
    }

    private void loadInvoicesFromDB() throws SQLException, ClassNotFoundException {
        connection=DBConnection.connect();
        String query="SELECT * FROM E_ELECTRICITY_BILL";
        Statement statement=connection.createStatement();
        ResultSet result=statement.executeQuery(query);

        invoices.clear();
        while (result.next()){
            Invoice resultInvoice=Invoice.fromResultSet(result);
            if(!invoices.contains(resultInvoice)){
                invoices.add(resultInvoice);
            }
        }
        connection.close();
    }

    public void deleteInvoice(String id) throws SQLException, ClassNotFoundException {
        connection=DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM E_ELECTRICITY_BILL WHERE ELEC_BILL_ID=?");
        statement.setString(1,id);
        statement.execute();
        connection.close();
        loadInvoicesFromDB();

    }

    public ObservableList<Invoice> getInvoices() {
        return invoices;
    }
}
