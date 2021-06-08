package CONTROLLERS.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddCreditCardController {
    @FXML
    private Button btnCreditCardBack;
    public void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCreditCardBack.getScene().getWindow();
        stage.close();
    }
}
