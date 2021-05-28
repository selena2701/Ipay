package Controller;

import Controller.Database.DatabaseConnection;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabAdmin;
    @FXML
    private Tab tabUser;
    @FXML
    private Label lbSignUp;
    @FXML
    private Pane slidingPane;
    @FXML
    private  Label lblAdmin;
    @FXML
    private Label lblUser;
    @FXML
    private Label lblStatus;
    @FXML
    private Button AdCancelButton;
    @FXML
    private Button CusCancelButton;
    @FXML
    private TextField AdUsernameTextField;
    @FXML
    private PasswordField AdPassTextField;
    @FXML
    private Label AdLoginMessText;
    @FXML
    private TextField CusUsernameTextField;
    @FXML
    private PasswordField CusPassTextField;
    @FXML
    private Label CusLoginMessText;


    private void handleButtonAction(ActionEvent event){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //TODO
    }
    public static boolean Login(String userName, String password) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM USER_ACCOUNT WHERE Username=?";

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1, userName);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            if(!rst.getString(1).equals(userName)){
                return false;
            }
            String pwd = rst.getString(2);
            if (pwd.equals(password)) {
                return true;
            }
        }
        return false;

    }
    public static boolean AccountType(String userName)throws ClassNotFoundException,SQLException {
        String SQL = "SELECT * FROM USER_ACCOUNT WHERE Username=?";
        int LType = 1;

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1, userName);
        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            String type = rst.getString(3);
            if (type.equals(LType)) {
                return true;
            }
        }
        return false;
    }
    //Login Button
    @FXML
    public void AdloginButtonOnAction(ActionEvent Event) throws ClassNotFoundException, SQLException
    {
        if(AdUsernameTextField.getText().isBlank() == false && AdPassTextField.getText().isBlank()==false)
        {
            String userName=AdUsernameTextField.getText();
            String password=AdPassTextField.getText();
            boolean bool=Login(userName, password);

            try {
                AnchorPane mainMenuAdmin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(("./View/Notification.fxml"))));
                if(bool ){
                    boolean type=LoginController.AccountType(userName);
                    if(type==false){
                        //rootLogin.getChildren().setAll(mainMenuAdmin)
                        System.out.println("ADMIN");
                    }
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Error!");
                    alert.setHeaderText("Login Failed!");
                    alert.setContentText("Username/Password does not match");

                    alert.showAndWait();
                    System.out.println("Wrong login details");
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            AdLoginMessText.setText("Please enter username and password!");
        }
    }
    //Customer Login Tab
    @FXML
    public void CusloginButtonOnAction(ActionEvent Event) throws SQLException, ClassNotFoundException {
        if(CusUsernameTextField.getText().isBlank() == false && CusPassTextField.getText().isBlank()==false)
        {
            String userName=CusUsernameTextField.getText();
            String password=CusPassTextField.getText();
            boolean bool=Login(userName, password);

            try {
                AnchorPane mainMenu = FXMLLoader.load(getClass().getResource((""))); //Chuyền đến Frame Khách hàng
                if(bool ){
                    boolean type=LoginController.AccountType(userName);
                    if(type==false){
                        //rootLogin.getChildren().setAll(mainMenu);
                        System.out.println("CUSTOMER");
                    }
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Error!");
                    alert.setHeaderText("Login Failed!");
                    alert.setContentText("Username/Password does not match");

                    alert.showAndWait();
                    System.out.println("Wrong login details");
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            CusLoginMessText.setText("Please enter username and password!");
        }
    }


    //Cancel Button
    public void AdcancelButtonOnAction(ActionEvent event)
    {
        Stage stage = (Stage) AdCancelButton.getScene().getWindow();
        stage.close();
    }

    public void CuscancelButtonOnAction(ActionEvent event)
    {
        Stage stage = (Stage) CusCancelButton.getScene().getWindow();
        stage.close();
    }

    //Change Tab
    public void openUserTab(MouseEvent mouseEvent) {
        TranslateTransition toRightAnimation= new TranslateTransition(new Duration(500), lblStatus);
        toRightAnimation.setToX(slidingPane.getTranslateX()+(slidingPane.getPrefWidth()-lblStatus.getPrefWidth()));
        toRightAnimation.play();
        toRightAnimation.setOnFinished(event -> {
            lblStatus.setText("USER");
        });
        tabPane.getSelectionModel().select(tabUser);
    }

    public void openAdminTab(MouseEvent mouseEvent) {
        TranslateTransition toLeftTransition = new TranslateTransition(new Duration(500),lblStatus);
        toLeftTransition.setToX(slidingPane.getTranslateX());
        toLeftTransition.play();
        toLeftTransition.setOnFinished((ActionEvent event2)->{
            lblStatus.setText("ADMINSTRATOR");
        });
        tabPane.getSelectionModel().select(tabAdmin);
    }

    //Sign Up Scence
    public void openSignUpScence(MouseEvent mouseEvent) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./View/signupView.fxml")));
        Scene loginScene=lblAdmin.getScene();
        root.translateYProperty().set(loginScene.getHeight());
        rootPane.getChildren().add(root);
        Timeline timeline= new Timeline();
        KeyValue keyValue=new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame keyFrame= new KeyFrame(Duration.seconds(2),keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timeline.setOnFinished((ActionEvent event2)->{
            rootPane.getChildren().remove(anchorPane);
        });

    }
}
