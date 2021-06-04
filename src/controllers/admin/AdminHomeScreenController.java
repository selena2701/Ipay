package controllers.admin;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeScreenController implements Initializable {

    private static final String USERS_MANAGER = "./../../views/admin/users-manager.fxml";
    private static final String PROVIDERS_MANAGER = "./../../views/admin/providers-manager.fxml";
    //private static final String INVOICES_MANAGER = "./../../views/admin/invoices-manager.fxml";
    private static final String NOTIFICATIONS_MANAGER = "./../../views/admin/notifications-manager.fxml";

    @FXML
    private ImageView iconImageView;

    @FXML
    private JFXButton usersButton;

    @FXML
    private JFXButton providersButton;

    @FXML
    private JFXButton notificationsButton;

    @FXML
    private BorderPane content;

    private final Node usersNode;
    private final Node providersNode;
    private final Node notificationsNode;

    public AdminHomeScreenController() throws IOException {
        FXMLLoader usersLoader = new FXMLLoader(getClass().getResource(USERS_MANAGER));
        FXMLLoader providersLoader = new FXMLLoader(getClass().getResource(PROVIDERS_MANAGER));
        FXMLLoader notificationsLoader = new FXMLLoader(getClass().getResource(NOTIFICATIONS_MANAGER));

        usersNode = usersLoader.load();
        providersNode = providersLoader.load();
        notificationsNode = notificationsLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InitListeners();
        setContent(usersNode);
    }

    private void setContent(Node node) {
        content.setCenter(node);
    }

    private void InitListeners() {
        usersButton.setOnMouseClicked(mouseEvent -> {
            setContent(usersNode);
        });

        providersButton.setOnMouseClicked(mouseEvent -> {
            setContent(providersNode);
        });

        notificationsButton.setOnMouseClicked(mouseEvent -> {
            setContent(notificationsNode);
        });

    }
}
