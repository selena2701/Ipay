package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChangePasswordController {
    @FXML
    private Button btnchangepasswordback;
    public void btnChangePasswordBack(ActionEvent event){
        Stage stage = (Stage) btnchangepasswordback.getScene().getWindow();
        stage.close();
    }
}
