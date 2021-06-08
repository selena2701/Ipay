package CONTROLLERS.admin;

import database.adminRepo.AdminRepo;
import database.adminRepo.ProvidersRepo;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
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

public class ProvidersManagerController implements Initializable {

    //MODEL
    private final ProvidersRepo providersRepo = new ProvidersRepo();
    private final AdminRepo adminRepo = new AdminRepo();

    @FXML
    private TableView<Provider> providersTableView;

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

    private final FXMLLoader addProviderLoader = new FXMLLoader(getClass().getResource("../../VIEWS/admin/add-provider.fxml"));

    public ProvidersManagerController() throws Exception {
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

            MenuItem editMenuItem = new MenuItem("Modify");
            editMenuItem.setOnAction(event -> {

                try {
                    Parent root = addProviderLoader.load();

                    AddProviderController controller = addProviderLoader.getController();
                    controller.setProviderId(row.getItem().getId());

                    Scene currentScene = providerTableView.getScene();
                    currentScene.setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

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

    }

    @FXML
    private void newProviderOnClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../VIEWS/admin/add-provider.fxml"));
        Parent root = loader.load();

        AddProviderController controller = loader.getController();
        controller.setProviderId(null);

        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(root);
    }
}

