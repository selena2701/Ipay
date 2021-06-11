package CONTROLLERS.admin;

import MODELS.Report;
import com.jfoenix.controls.JFXButton;
import database.adminRepo.ReportsRepo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportManagerController implements Initializable {

    private final ReportsRepo reportsRepo = new ReportsRepo();

    @FXML
    private JFXButton btnPrint;

    @FXML
    private TableView<Report> tbStatistic;

    @FXML
    private TableColumn<Report, String> clPeriod;

    @FXML
    private TableColumn<Report, Integer> clAmountofUser;

    @FXML
    private TableColumn<Report, Integer> clAmountofPayment;

    @FXML
    private TableColumn<Report, Double> clAmountofMoney;

    public ReportManagerController() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void PrintOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        clAmountofUser.setCellValueFactory(new PropertyValueFactory<>("totalUsers"));
        clAmountofPayment.setCellValueFactory(new PropertyValueFactory<>("numberTransactions"));
        clAmountofMoney.setCellValueFactory(new PropertyValueFactory<>("totalTransactionValue"));

        tbStatistic.setItems(reportsRepo.getReports());
    }

    public void PrintOnAction(javafx.event.ActionEvent event) {
        //TO DO
    }
}

