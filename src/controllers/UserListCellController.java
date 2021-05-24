package controllers;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.User;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UserListCellController extends JFXListCell<User> {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    public UserListCellController() {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("../views/user-list-cell.fxml"));
            loader.setController(this);
            gridPane = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            nameLabel.setText(user.getName());
            idLabel.setText(user.getId());
            setText(null);
            setGraphic(gridPane);
        }
    }
}
