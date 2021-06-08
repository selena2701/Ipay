package CONTROLLERS.admin;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import MODELS.Customer;
import java.io.IOException;
import java.io.UncheckedIOException;

public class UserListCellController extends JFXListCell<Customer> {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    public UserListCellController() {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("../VIEWS/admin/user-list-cell.fxml"));
            loader.setController(this);
            gridPane = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }

    @Override
    protected void updateItem(Customer customer, boolean empty) {
        super.updateItem(customer, empty);

        if (empty || customer == null) {
            setText(null);
            setGraphic(null);
        } else {
            nameLabel.setText(customer.getName());
            idLabel.setText(customer.getId());
            setText(null);
            setGraphic(gridPane);
        }
    }
}
