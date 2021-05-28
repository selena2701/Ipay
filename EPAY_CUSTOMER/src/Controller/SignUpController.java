package Controller;

import javafx.scene.control.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class SignUpController  implements Initializable {
    @FXML
    private Label lblLogin;
    @FXML
    private AnchorPane anchorpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //TODO
    }

    @FXML
    private void openLoginScence(MouseEvent mouseEvent) throws IOException {
        Parent root= FXMLLoader.load((Objects.requireNonNull(getClass().getResource("./View/LoginView.fxml"))));
        Scene accountScene= lblLogin.getScene();
        root.translateYProperty().set(accountScene.getHeight());
        StackPane rootPane=(StackPane)accountScene.getRoot();
        rootPane.getChildren().add(root);
        Timeline timeline= new Timeline();
        KeyValue keyValue=new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame keyFrame= new KeyFrame(Duration.seconds(2),keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timeline.setOnFinished((ActionEvent event2)->{
            rootPane.getChildren().remove(anchorpane);
        });

    }
}
