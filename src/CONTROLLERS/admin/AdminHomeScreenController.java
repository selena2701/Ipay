package CONTROLLERS.admin;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.helper.DataHolder;
import utils.helper.Navigator;
import utils.helper.NavigatorDetail;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminHomeScreenController implements Initializable {

    private static final String USERS_MANAGER = "./../../VIEWS/admin/users-management_Screen.fxml";
    private static final String PROVIDERS_MANAGER = "./../../VIEWS/admin/providers-management_Screen.fxml";
    private static final String NOTIFICATIONS_MANAGER = "./../../VIEWS/admin/notifications-management_Screen.fxml";
    private static final String REPORT_MANAGER = "./../../VIEWS/admin/report-management_Screen.fxml";
    @FXML
    private ImageView iconImageView;

    @FXML
    private GridPane mainroot;

    @FXML
    private JFXButton usersButton;

    @FXML
    private JFXButton providersButton;

    @FXML
    private JFXButton notificationsButton;

    @FXML
    private JFXButton reportButton;

    @FXML JFXButton btnLogOut;

    @FXML
    private BorderPane content;

    private final Node usersNode;
    private final Node providersNode;
    private final Node notificationsNode;
    private final Node reportNode;

    private String adminUsername;

    //Display admin home screen
    public AdminHomeScreenController() throws IOException {
        FXMLLoader usersLoader = new FXMLLoader(getClass().getResource(USERS_MANAGER));
        FXMLLoader providersLoader = new FXMLLoader(getClass().getResource(PROVIDERS_MANAGER));
        FXMLLoader notificationsLoader = new FXMLLoader(getClass().getResource(NOTIFICATIONS_MANAGER));
        FXMLLoader reportLoader = new FXMLLoader(getClass().getResource(REPORT_MANAGER));

        usersNode = usersLoader.load();
        providersNode = providersLoader.load();
        notificationsNode = notificationsLoader.load();
        reportNode = reportLoader.load();

        this.adminUsername = DataHolder.getINSTANCE().getUserName();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InitListeners();
        setContent(usersNode);
    }

    private void setContent(Node node) {
        content.setCenter(node);
    }

    //Select a function and then show selected screen
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
        reportButton.setOnMouseClicked(mouseEvent -> {
            setContent(reportNode);
        });


    }

    // button Log Out
    @FXML
    private void btnLogOut() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Are you sure to log out?");

        ButtonType btnTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType btnTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(btnTypeYes, btnTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnTypeYes) {
            try {
                DataHolder.getINSTANCE().setUserName(null);
                Navigator.navigate((Stage) mainroot.getScene().getWindow(), new NavigatorDetail("auth/auth_screen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}