package database.adminRepo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import MODELS.Provider;
import utils.DBConnection;

import java.sql.*;
import java.util.Date;

public class ProvidersRepo {

    public final ObservableList<Provider> providers = FXCollections.observableArrayList();

    private Connection connection = null;

    public ProvidersRepo() throws Exception {
        loadProviderFromDB();
    }

    //Load Provider From DB
    private void loadProviderFromDB() throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        String query = "SELECT * FROM E_PROVIDER";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        providers.clear();
        while (result.next()) {
            Provider resultProvider = Provider.fromResultSet(result);
            if (!providers.contains(resultProvider)) {
                providers.add(resultProvider);
            }
        }
        connection.close();
    }

    public ObservableList<Provider> getProviders() {
        return providers;
    }


    //Add Provider
    public void addNewProvider(String id, String name, String phone, String address, Date dateJoined, String promotedBy) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO E_PROVIDER VALUES (?,?,?,?,?,?)");
        statement.setString(1, id);
        statement.setString(2, name);
        statement.setString(3, phone);
        statement.setString(4, address);
        statement.setObject(5, new java.sql.Date(dateJoined.getTime()));
        statement.setString(6, promotedBy);
        statement.execute();
        connection.close();

        loadProviderFromDB();
    }


    //Delete Provider
    public void deleteProvider(String providerId) throws SQLException, ClassNotFoundException {
        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM E_PROVIDER WHERE ProId=?");
        statement.setString(1, providerId);
        statement.execute();
        connection.close();

        loadProviderFromDB();
    }


    //Modify Provider
    public void updateProvider(Provider provider) throws SQLException, ClassNotFoundException {

        connection = DBConnection.connect();
        PreparedStatement statement = connection.prepareStatement("UPDATE E_PROVIDER SET NamePro=?,PhonePro=?,AddressPro=?,PromotedBy=? WHERE ProId=?");
        statement.setString(1, provider.getName());
        statement.setString(2, provider.getPhone());
        statement.setString(3, provider.getAddress());
        statement.setString(4, provider.getAdminPromoted());
        statement.setString(5, provider.getId());
        statement.execute();
        connection.close();

        loadProviderFromDB();
    }


    public Provider getProviderById(String id) {
        for (Provider provider : providers) {
            if (provider.getId().equals(id)) {
                return provider;
            }
        }
        return null;
    }

}
