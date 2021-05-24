package controllers;

import com.jfoenix.controls.JFXButton;
import database.repos.AdminRepo;
import database.repos.NotificationsRepo;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import models.Notification;
import models.Provider;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class NotificationsManager implements Initializable {

    //MODEL
    private NotificationsRepo notificationsRepo = new NotificationsRepo();

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

        notificationsTableView.setRowFactory(notificationTableView -> {
            TableRow<Notification> row = new TableRow<>();

            MenuItem editMenuItem = new MenuItem("Modify");
            editMenuItem.setOnAction(event -> {

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

    }
}
