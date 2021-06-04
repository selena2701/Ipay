package controllers.admin;

import com.jfoenix.controls.JFXButton;
import database.repos.NotificationsRepo;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Notification;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class NotificationsManager implements Initializable {

    //MODEL
    private final NotificationsRepo notificationsRepo = new NotificationsRepo();

    @FXML
    private TableView<Notification> notificationsTableView;

    @FXML
    private TableColumn<Notification, String> idColumn;

    @FXML
    private TableColumn<Notification, String> descriptionColumn;

    @FXML
    private TableColumn<Notification, Date> dateCreatedColumn;

    @FXML
    private TableColumn<Notification, Date> datePublishedColumn;

    @FXML
    private TableColumn<Notification, String> editedByColumn;

    @FXML
    private JFXButton addNotificationButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        datePublishedColumn.setCellValueFactory(new PropertyValueFactory<>("datePublished"));
        editedByColumn.setCellValueFactory(new PropertyValueFactory<>("editedBy"));

        notificationsTableView.setRowFactory(tableView -> {
            TableRow<Notification> row = new TableRow<>();

            MenuItem editMenuItem = new MenuItem("Modify");
            editMenuItem.setOnAction(event -> {
                onAddNotificationClicked(event, row.getItem().getId());
            });

            MenuItem deleteMenuItem = new MenuItem("Delete");
            deleteMenuItem.setOnAction(event -> {
                try {
                    notificationsRepo.deleteNotification(row.getItem().getId());
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

        notificationsTableView.setItems(notificationsRepo.getNotifications());

        addNotificationButton.setOnMouseClicked(mouseEvent -> {
            onAddNotificationClicked(mouseEvent, null);
        });

    }

    private void onAddNotificationClicked(Event event, String notificationId) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/admin/add-notification.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddNotificationController controller = loader.getController();
        controller.setNotificationId(notificationId);

        Scene currentScene;

        if (notificationId == null) {
            currentScene = ((Node) event.getSource()).getScene();
        } else {
            currentScene = notificationsTableView.getScene();
        }
        currentScene.setRoot(root);
    }
}
