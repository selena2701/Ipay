package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EditProfileController {
    @FXML
    private Button btneditprofileback;


    public void btnEditProfileBack(ActionEvent event){
        Stage stage = (Stage) btneditprofileback.getScene().getWindow();
        stage.close();
    }

}
