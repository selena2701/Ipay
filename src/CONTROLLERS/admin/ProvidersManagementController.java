
package CONTROLLERS.admin;

import database.adminRepo.AdminRepo;
import database.adminRepo.ProvidersRepo;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import MODELS.Provider;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ProvidersManagementController implements Initializable {

    //MODEL
    private final ProvidersRepo providersRepo = new ProvidersRepo();
    private final AdminRepo adminRepo = new AdminRepo();

    @FXML
    private TableView<Provider> providersTableView;

    @FXML
    private TextField fldFilter;

    @FXML
    private TableColumn<Provider, String> idColumn;

    @FXML
    private TableColumn<Provider, String> nameColumn;

    @FXML
    private TableColumn<Provider, String> phoneColumn;

    @FXML
    private TableColumn<Provider, String> addressColumn;

    @FXML
    private TableColumn<Provider, Date> dateJoinedColumn;

    @FXML
    private TableColumn<Provider, String> promotedByColumn;

    private final FXMLLoader addProviderLoader = new FXMLLoader(getClass().getResource("../../VIEWS/admin/add-or-modify-provider_Screen.fxml"));

    public ProvidersManagementController() throws Exception {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<>("dateJoined"));
        promotedByColumn.setCellValueFactory(new PropertyValueFactory<>("adminPromoted"));

        promotedByColumn.setCellValueFactory(providerStringCellDataFeatures -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(adminRepo.getAdminById(providerStringCellDataFeatures.getValue().getAdminPromoted()).getName());
            return property;
        });

        providersTableView.setRowFactory(providerTableView -> {
            TableRow<Provider> row = new TableRow<>();
            //Modify//
            MenuItem editMenuItem = new MenuItem("Modify");
            editMenuItem.setOnAction(event -> {

                try {
                    Parent root = addProviderLoader.load();

                    AddOrModifyProviderController controller = addProviderLoader.getController();
                    controller.setProviderId(row.getItem().getId());

                    Scene currentScene = providerTableView.getScene();
                    currentScene.setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            //Delete//
            MenuItem deleteMenuItem = new MenuItem("Delete");
            deleteMenuItem.setOnAction(event -> {
                try {
                    providersRepo.deleteProvider(row.getItem().getId());
                } catch (SQLException | ClassNotFoundException throwable) {
                    throwable.printStackTrace();
                }
            });

            ContextMenu secondaryContextMenu = new ContextMenu();
            secondaryContextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(secondaryContextMenu));
            return row;
        });

        providersTableView.setItems(providersRepo.getProviders());
        FilteredList<Provider> filterdt= new FilteredList<>(providersRepo.providers, b->true);
        fldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filterdt.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getId().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (person.getPhone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });

        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Provider> sortedData = new SortedList<>(filterdt);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(providersTableView.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        providersTableView.setItems(sortedData);
    }
    //Add
    @FXML
    private void newProviderOnClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../VIEWS/admin/add-or-modify-provider_Screen.fxml"));
        Parent root = loader.load();

        AddOrModifyProviderController controller = loader.getController();
        controller.setProviderId(null);

        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(root);
    }
}