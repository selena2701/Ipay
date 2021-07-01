import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.helper.CaculateElectricityBill;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VIEWS/auth/auth_screen.fxml")));
        primaryStage.setTitle("Electricity Payment Admin");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}

