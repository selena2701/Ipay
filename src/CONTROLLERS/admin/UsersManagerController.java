package CONTROLLERS.admin;

import database.adminRepo.UsersRepo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import MODELS.Customer;
import MODELS.Region;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class UsersManagerController implements Initializable {

    //MODEL
    private UsersRepo usersRepo = new UsersRepo();

    @FXML
    private TableView<Customer> userTableView;

    @FXML
    private TableColumn<Customer, String> idColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Customer, String> nationalIdColumn;

    @FXML
    private TableColumn<Customer, Boolean> genderColumn;

    @FXML
    private TableColumn<Customer, Date> birthdayColumn;

    @FXML
    private TableColumn<Customer, Region.REGION_ENUM> regionColumn;

    @FXML
    private TableColumn<Customer, Date> dateRegisterColumn;

    public UsersManagerController() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        nationalIdColumn.setCellValueFactory(new PropertyValueFactory<>("nationalId"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));
        dateRegisterColumn.setCellValueFactory(new PropertyValueFactory<>("dateRegister"));

        regionColumn.setCellFactory(userRegionTableColumn -> new TableCell<>() {
            @Override
            protected void updateItem(Region.REGION_ENUM region, boolean empty) {
                super.updateItem(region, empty);
                if (region == null || empty) {
                    setText(null);
                } else {
                    setText(Region.regionToString(region));
                }
            }
        });

        genderColumn.setCellFactory(userBooleanTableColumn -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean value, boolean empty) {
                super.updateItem(value, empty);
                if (value == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(value ? "Nam" : "Nữ");
                    setStyle("-fx-alignment: center");
                }
            }
        });

        userTableView.setItems(usersRepo.getCustomers());
    }


}
