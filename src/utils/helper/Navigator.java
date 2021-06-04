package utils.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigator {

    /*
     * @param destination.destination is relative String
     * This function will create a new stage and close the current stage
     * */
    public static void navigate(Stage currentStage, NavigatorDetail destination) throws IOException {
        Stage stage = new Stage();
        stage.setTitle(destination.getTitle());
        stage.setWidth(destination.getWidth());
        stage.setHeight(destination.getHeight());

        Parent root = FXMLLoader.load(Objects.requireNonNull(Navigator.class.getResource("./../../views/" + destination.getDestination())));
        stage.setScene(new Scene(root));

        currentStage.close();
        stage.show();
    }


}
