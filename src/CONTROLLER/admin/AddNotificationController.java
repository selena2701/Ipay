package CONTROLLER.admin;

import database.adminRepo.NotificationsRepo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import controllers.Notification;
import utils.helper.DateConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;


public class AddNotificationController implements Initializable {
    @FXML
    private TextField editFld;
    @FXML
    private DatePicker dcreateFld;
    @FXML
    private DatePicker dsentFld;
    @FXML
    private TextField ncontentFld;
    @FXML
    private Label titleLabel;
    @FXML
    private Label idLabel;

    private final NotificationsRepo notificationsRepo = new NotificationsRepo();

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
        }

        onNavigateBack((Node) event.getSource());

    }
    public void back(MouseEvent mouseEvent) throws IOException {
        onNavigateBack((Node) mouseEvent.getSource());
    }
    private void onNavigateBack(Node node) throws IOException {
        Scene currentScene = node.getScene();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./../../views/admin/admin-home-screen.fxml")));
        currentScene.setRoot(root);
    }

    public void setNotificationId(String id) {
        if (id == null) {
            titleLabel.setText("Add New Notification");
        } else {
            setFields(notificationsRepo.getNotificationById(id));
        }
    }

    /*
     * Set fields for update by passing values to fields
     * */
    private void setFields(Notification notification) {
        titleLabel.setText("Update " + notification.getId());
        idLabel.setText(notification.getId());
        dcreateFld.setValue(new java.sql.Date(notification.getDateCreated().getTime()).toLocalDate());
        dsentFld.setValue(new java.sql.Date(notification.getDatePublished().getTime()).toLocalDate());
        editFld.setText(notification.getEditedBy());
        ncontentFld.setText(notification.getDescription());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

