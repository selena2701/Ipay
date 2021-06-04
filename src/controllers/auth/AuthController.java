package controllers.auth;

import com.jfoenix.controls.*;
import database.authAPI.Authenticate;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;
import models.Admin;
import models.Customer;
import models.Region;
import utils.DateConverter;
import utils.exceptions.UserExistsException;
import utils.validators.NonEmptyValidator;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    ObservableList<String> genderlist = FXCollections.observableArrayList("Nu", "Nam");
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
    @FXML
    private Label RegisterMessTest;

    public AuthController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        setUpComboxBoxGender();
        setUpComboBoxRegion();

        loginUsername.setValidators(new NonEmptyValidator("Username cannot be empty"));
        loginPassword.setValidators(new NonEmptyValidator("Password cannot be empty"));
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


    @FXML
    /*
     * Login for user
     * */
    public void loginButtonOnClick() throws SQLException, ClassNotFoundException {
        if (!loginValidation()) {
            return;
        }

        Boolean isCorrectUser = Authenticate.login(loginUsername.getText(), loginPassword.getText());
        if (isCorrectUser != null) {
            LoginMessText.setText(isCorrectUser ? "You are Admin" : "You are Customer");
            //TODO Navigate to appropriate screen

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

    private boolean loginValidation() {
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
