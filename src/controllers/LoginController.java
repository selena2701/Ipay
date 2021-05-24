package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javax.xml.validation.Validator;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements Initializable {

    @FXML
    private GridPane backgroundPane;

    @FXML
    private JFXButton signInButton;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private void onSignIn(MouseEvent event) {
        if(isEmailValid()&&isPasswordValid()){

        }
        return;
    }

    @FXML
    private void onSignUp(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RequiredFieldValidator emailRequiredValidator=new RequiredFieldValidator("Email cannot be empty");
        emailRequiredValidator.setIcon(new MaterialIconView(MaterialIcon.WARNING));

        RequiredFieldValidator passwordRequiredValidator=new RequiredFieldValidator("Password cannot be empty");
        passwordRequiredValidator.setIcon(new MaterialIconView(MaterialIcon.WARNING));

        RegexValidator emailValidator = new RegexValidator("Invalid Email");
        emailValidator.setRegexPattern("^(.+)@(.+)$");
        emailValidator.setIcon(new MaterialIconView(MaterialIcon.WARNING));

        emailTextField.setValidators(emailRequiredValidator,emailValidator);
        passwordTextField.setValidators(passwordRequiredValidator);
    }

    private boolean isEmailValid() {
        return emailTextField.validate();
    }

    private boolean isPasswordValid() {
        return passwordTextField.validate();
    }


}
