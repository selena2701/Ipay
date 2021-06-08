package controllers.admin;

import database.adminRepo.InvoicesRepo;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
<<<<<<< HEAD:src/CONTROLLER/admin/InvoicesManagerController.java
import MODELS.Invoice;
=======
import models.Invoice;
>>>>>>> b46c0cc0da32f7bf4e24fa2aa3679d67f7533556:src/MODELS/admin/InvoicesManagerController.java

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class InvoicesManagerController implements Initializable {

    //MODEL

    InvoicesRepo invoicesRepo=new InvoicesRepo();

    @FXML
    private TableView<Invoice> invoicesTableView;

    @FXML
    private TableColumn<Invoice, String> idColumn;

    @FXML
    private TableColumn<Invoice, Integer> previousColumn;

    @FXML
    private TableColumn<Invoice, Integer> currentColumn;

    @FXML
    private TableColumn<Invoice, Integer> consumedColumn;

    @FXML
    private TableColumn<Invoice,Double> totalColumn;

    @FXML
    private TableColumn<Invoice, String> electricityTypeColumn;

    @FXML
    private TableColumn<Invoice, Date> fromDateColumn;

    @FXML
    private TableColumn<Invoice, Date> toDateColumn;

    @FXML
    private TableColumn<Invoice, String> customerIdColumn;

    public InvoicesManagerController() throws SQLException, ClassNotFoundException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        previousColumn.setCellValueFactory(new PropertyValueFactory<>("previousValue"));
        currentColumn.setCellValueFactory(new PropertyValueFactory<>("currentValue"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        electricityTypeColumn.setCellValueFactory(new PropertyValueFactory<>("electricityType"));
        fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        toDateColumn.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        invoicesTableView.setRowFactory(invoiceTableView -> {
            TableRow<Invoice> row=new TableRow<>();

            MenuItem editMenuItem=new MenuItem("Modify");
            editMenuItem.setOnAction(event -> {

            });

            MenuItem deleteMenuItem=new MenuItem("Delete");
            deleteMenuItem.setOnAction(event -> {

            });

            ContextMenu secondaryContextMenu = new ContextMenu();
            secondaryContextMenu.getItems().addAll(editMenuItem,deleteMenuItem);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu)null)
                            .otherwise(secondaryContextMenu));
            return row;
        });

        invoicesTableView.setItems(invoicesRepo.getInvoices());
    }
}
