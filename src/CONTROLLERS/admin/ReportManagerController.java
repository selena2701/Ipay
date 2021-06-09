package CONTROLLERS.admin;

import javafx.fxml.Initializable;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportManagerController implements Initializable {
    @FXML
    private MaterialIconView btnPrint;

    @FXML
    private TableView<?> tbStatistic;

    @FXML
    private TableColumn<?, ?> clPeriod;

    @FXML
    private TableColumn<?, ?> clAmountofUser;

    @FXML
    private TableColumn<?, ?> clAmountofPayment;

    @FXML
    private TableColumn<?, ?> clAmountofMoney;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TO DO
    }
}

