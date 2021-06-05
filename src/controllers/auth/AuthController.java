package controllers.auth;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.authAPI.Authenticate;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import models.Customer;
import models.Region;
import utils.exceptions.UserExistsException;
import utils.helper.DataHolder;
import utils.helper.Navigator;
import utils.helper.NavigatorDetail;
import utils.validators.NonEmptyValidator;
import utils.validators.PhoneValidator;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    @FXML
    public StackPane mainPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabLogin;
    @FXML
    private Tab tabRegister;
    @FXML
    private Pane slidingPane;
    @FXML
    private Label lblStatus;
    @FXML
    public JFXTextField loginUsername;
    @FXML
    public JFXPasswordField loginPassword;
    @FXML
    private Label LoginMessText;
    @FXML
    private JFXTextField fldFullname;
    @FXML
    private JFXPasswordField fldPassword;
    @FXML
    private JFXTextField fldUsername;
    @FXML
    private JFXTextField fldCusID;
    @FXML
    private JFXTextField fldPhone;
    @FXML
    private JFXTextField fldAddress;
    @FXML
    private JFXTextField fldNationId;
    @FXML
    private JFXComboBox<Boolean> cbGender;
    @FXML
    private DatePicker dpBirthday;
    @FXML
    private JFXComboBox<Region.REGION_ENUM> cbRegion;

    public AuthController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        initComponents();
        setUpValidators();
    }

    private void setUpValidators() {
        fldUsername.setValidators(new NonEmptyValidator("Username cannot be empty"));
        fldPassword.setValidators(new NonEmptyValidator("Password cannot be empty"));
        fldFullname.setValidators(new NonEmptyValidator("Customer name cannot be empty"));
        fldAddress.setValidators(new NonEmptyValidator("Address cannot be empty"));
        fldPhone.setValidators(new PhoneValidator());
        fldNationId.setValidators(new NonEmptyValidator("National ID cannot be empty"));
        cbGender.setValidators(new NonEmptyValidator("Gender cannot be empty"));
        cbRegion.setValidators(new NonEmptyValidator("Region cannot be empty"));
    }

    private void initComponents() {
        setUpComboxBoxGender();
        setUpComboBoxRegion();
        setUpBirthDayPicker();
    }

    private void setUpComboBoxRegion() {
        Region.REGION_ENUM[] regions = {Region.REGION_ENUM.NorthSide, Region.REGION_ENUM.MidSide, Region.REGION_ENUM.SouthSide, Region.REGION_ENUM.WestSide};

        Callback<ListView<Region.REGION_ENUM>, ListCell<Region.REGION_ENUM>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Region.REGION_ENUM region, boolean empty) {
                super.updateItem(region, empty);
                setText(empty ? "" : Region.regionToString(region));
            }
        };
        cbRegion.getItems().addAll(regions);
        cbRegion.setCellFactory(factory);
        cbRegion.setButtonCell(factory.call(new JFXListView<>()));
    }

    private void setUpComboxBoxGender() {

        Callback<ListView<Boolean>, ListCell<Boolean>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Boolean gender, boolean empty) {
                super.updateItem(gender, empty);
                setText(empty ? "" : gender ? "Nam" : "Nữ");
            }
        };

        cbGender.getItems().addAll(true, false);
        cbGender.setCellFactory(factory);
        cbGender.setButtonCell(factory.call(new JFXListView<>()));
    }

    private void setUpBirthDayPicker() {
        dpBirthday.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(localDate.isBefore(LocalDate.now()));
            }
        });

        dpBirthday.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                return localDate != null ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalDate.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
            }
        });
    }

    private boolean signUpValidate() {
        return (fldFullname.validate() && fldUsername.validate() && fldPassword.validate() && fldPhone.validate() && fldAddress.validate() && fldNationId.validate() && cbGender.validate() && cbRegion.validate());
    }


    /*
     * Login for user
     * */
    @FXML
    public void loginButtonOnClick() throws SQLException, ClassNotFoundException {
        if (!loginValidate()) {
            return;
        }

        Boolean isCorrectUser = Authenticate.login(loginUsername.getText(), loginPassword.getText());
        if (isCorrectUser != null) {
            LoginMessText.setText(isCorrectUser ? "You are Admin" : "You are Customer");
            //TODO Navigate to appropriate screen

            try {
                NavigatorDetail detail = new NavigatorDetail(isCorrectUser ? "admin/admin-home-screen.fxml" : "client/client.fxml");
                detail.setTitle(isCorrectUser ? "Admin" : "Customer");
                DataHolder.getINSTANCE().setUserName(loginUsername.getText());
                Navigator.navigate((Stage) mainPane.getScene().getWindow(), detail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error!");
            alert.setHeaderText("Login Failed!");
            alert.setContentText("The username or password is incorrect ");
            alert.showAndWait();
            loginPassword.clear();
        }
    }

    //Register
    public void signUpButtonOnClick() throws SQLException, ClassNotFoundException {

        if (signUpValidate()) {
            Customer customer = new Customer(
                    fldFullname.getText(),
                    fldUsername.getText(),
                    fldPhone.getText(),
                    fldAddress.getText(),
                    fldNationId.getText(),
                    cbGender.getSelectionModel().getSelectedItem(),
                    dpBirthday.getValue(),
                    cbRegion.getSelectionModel().getSelectedItem(),
                    Date.from(Instant.now())
            );
            try {
                Authenticate.signUp(customer, fldPassword.getText());
                System.out.println("Sign Up Successfully");
            } catch (UserExistsException e) {

                e.printStackTrace();
            }
        }
    }

    private boolean loginValidate() {
        return loginUsername.validate() && loginPassword.validate();
    }


    //Change Tab
    public void openRegisterTab(MouseEvent mouseEvent) {
        TranslateTransition toRightAnimation = new TranslateTransition(new Duration(500), lblStatus);
        toRightAnimation.setToX(slidingPane.getTranslateX() + (slidingPane.getPrefWidth() - lblStatus.getPrefWidth()) - 40);
        toRightAnimation.play();
        toRightAnimation.setOnFinished(event -> {
            lblStatus.setText("REGISTER");
        });
        tabPane.getSelectionModel().select(tabRegister);
    }

    public void openLoginTab(MouseEvent mouseEvent) {
        TranslateTransition toLeftTransition = new TranslateTransition(new Duration(500), lblStatus);
        toLeftTransition.setToX(slidingPane.getTranslateX());
        toLeftTransition.play();
        toLeftTransition.setOnFinished((ActionEvent event2) -> {
            lblStatus.setText("LOGIN");
        });
        tabPane.getSelectionModel().select(tabLogin);
    }

}
