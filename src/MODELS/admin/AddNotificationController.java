package controllers.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import MODELS.Admin;
import database.adminRepo.AdminRepo;
import database.adminRepo.NotificationsRepo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
<<<<<<< HEAD:src/CONTROLLER/admin/AddNotificationController.java
import MODELS.Notification;
import javafx.util.Callback;
=======
import models.Notification;
>>>>>>> b46c0cc0da32f7bf4e24fa2aa3679d67f7533556:src/MODELS/admin/AddNotificationController.java
import utils.helper.DateConverter;
import utils.validators.NonEmptyValidator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;


public class AddNotificationController implements Initializable {

    @FXML
    private JFXComboBox<Admin> cbbEditBy;
    @FXML
    private DatePicker dcreateFld;
    @FXML
    private DatePicker dsentFld;
    @FXML
    private JFXTextField ncontentFld;
    @FXML
    private Label titleLabel;
    @FXML
    private Label idLabel;

    private final NotificationsRepo notificationsRepo = new NotificationsRepo();
    private final AdminRepo adminRepo = new AdminRepo();

<<<<<<< HEAD:src/CONTROLLER/admin/AddNotificationController.java
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Callback<ListView<Admin>, ListCell<Admin>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Admin admin, boolean empty) {
                super.updateItem(admin, empty);
                setText(empty ? "" : admin.getName());
            }
        };

        for (Admin admin : adminRepo.getAdmins()) {
            cbbEditBy.getItems().add(admin);
        }
        cbbEditBy.setCellFactory(factory);
        cbbEditBy.setButtonCell(factory.call(new JFXListView<>()));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        setValidation();
    }
    // Save and Back Button
    @FXML
    private void save(MouseEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (validate()) {
            if (idLabel.getText().isEmpty()) {
                notificationsRepo.addNotification(new Notification(UUID.randomUUID().toString().substring(0, 10),
                        ncontentFld.getText(),
                        Date.from(dcreateFld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(dsentFld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        cbbEditBy.getSelectionModel().getSelectedItem().getId()));
            } else {
                notificationsRepo.updateNotification(
                        new Notification(
                                idLabel.getText(),
                                ncontentFld.getText(),
                                DateConverter.fromLocalDate(dcreateFld.getValue()),
                                DateConverter.fromLocalDate(dsentFld.getValue()),
                                cbbEditBy.getSelectionModel().getSelectedItem().getId()));
            }

            onNavigateBack((Node) event.getSource());
=======
    // Save and Back Button
    @FXML
    private void save(MouseEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (idLabel.getText().isEmpty()) {
            notificationsRepo.addNotification(new Notification(UUID.randomUUID().toString().substring(0, 10),
                    ncontentFld.getText(),
                    Date.from(dcreateFld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(dsentFld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    editFld.getText()));

            //TODO ix notification to be passed to Repository at admin edit field

        } else {
            notificationsRepo.updateNotification(
                    new Notification(
                            idLabel.getText(),
                            ncontentFld.getText(),
                            DateConverter.fromLocalDate(dcreateFld.getValue()),
                            DateConverter.fromLocalDate(dsentFld.getValue()),
                            editFld.getText()));

            //TODO ix notification to be passed to Repository at admin edit field
>>>>>>> b46c0cc0da32f7bf4e24fa2aa3679d67f7533556:src/MODELS/admin/AddNotificationController.java
        }
    }
    public void back(MouseEvent mouseEvent) throws IOException {
        onNavigateBack((Node) mouseEvent.getSource());
    }
    private void onNavigateBack(Node node) throws IOException {
        Scene currentScene = node.getScene();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./../../VIEWS/admin/admin-home-screen.fxml")));
        currentScene.setRoot(root);
    }

    public void setNotificationId(String id) {
        if (id == null) {
            titleLabel.setText("Add New Notification");
        } else {
            setFields(notificationsRepo.getNotificationById(id));
        }
    }
    private boolean validate() {
        return ncontentFld.validate()&& cbbEditBy.validate() ;
    }

    private void setValidation() {
        ncontentFld.setValidators(new NonEmptyValidator("Detail cannot be empty"));
        cbbEditBy.setValidators(new NonEmptyValidator("You must specify the admin promoted"));
    }

    // ? //
    private int getAdminIndexInComboBox(String id) {
        for (int i = 0; i < cbbEditBy.getItems().size(); i++) {
            if (cbbEditBy.getItems().get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Set fields for update by passing values to fields
     * */
    private void setFields(Notification notification) {
        titleLabel.setText("Update " + notification.getId());
        idLabel.setText(notification.getId());
        dcreateFld.setValue(new java.sql.Date(notification.getDateCreated().getTime()).toLocalDate());
        dsentFld.setValue(new java.sql.Date(notification.getDatePublished().getTime()).toLocalDate());
        cbbEditBy.getSelectionModel().select(getAdminIndexInComboBox(notification.getEditedBy()));
        ncontentFld.setText(notification.getDescription());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

