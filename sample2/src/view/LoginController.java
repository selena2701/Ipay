package view;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.HolderManager;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginButtonOnAction(ActionEvent event){
        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();

        } else {
            loginMessageLabel.setText("Vui lòng nhập đầy đủ thông tin đăng nhập");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        try{
            User user = new User(usernameTextField.getText(), enterPasswordField.getText());
            if(UserController.getInstance().valid(user)) {
                HolderManager holder = HolderManager.getInstance();
                holder.setUsername(usernameTextField.getText());
                login(UserController.getInstance().findbylogin(holder.getUsername()).getFullname());
            }else {
                loginMessageLabel.setText("Tài khoản hoặc mật khẩu không đúng");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void login(String name) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Controller controller = loader.getController();
            controller.setLblWelcome(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1280, 800);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) cancelButton.getScene().getWindow();
        stage1.close();
    }
}
