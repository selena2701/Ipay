package controllers;

import database.repos.UsersRepo;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import models.Region;
import models.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class UsersManagerController implements Initializable {

    //MODEL
    private UsersRepo usersRepo = new UsersRepo();

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, String> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TableColumn<User, String> addressColumn;

    @FXML
    private TableColumn<User, String> nationalIdColumn;

    @FXML
    private TableColumn<User, Boolean> genderColumn;

    @FXML
    private TableColumn<User, Date> birthdayColumn;

    @FXML
    private TableColumn<User, Region> regionColumn;

    @FXML
    private TableColumn<User, Date> dateRegisterColumn;

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
            protected void updateItem(Region region, boolean empty) {
                super.updateItem(region, empty);
                if (region == null || empty) {
                    setText(null);
                } else {
                    switch (region) {
                        case MidSide:
                            setText("Miền Trung");
                            break;
                        case SouthSide:
                            setText("Miền Nam");
                            break;
                        case NorthSide:
                            setText("Miền Bắc");
                            break;
                        case WestSide:
                            setText("Miền Tây");
                            break;
                    }
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

        userTableView.setItems(usersRepo.getUsers());
    }

    public void addUser() {
        usersRepo.addNewUser();
    }
}
