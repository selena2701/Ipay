package CONTROLLERS.client;

import MODELS.Bank;
import MODELS.CreditCard;
import MODELS.Region;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import database.clientRepo.ClientRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddCreditCardController implements Initializable {
    @FXML
    private JFXTextField txtCardHolderName, txtAccountNumber, txtCardNumber;
    @FXML
    private JFXComboBox<Bank.BANK_ENUM> cbbBank;
    @FXML
    private Button btnCreditCardBack, btnSave;
    private ClientRepo repo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBoxBank();
    }
    public void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCreditCardBack.getScene().getWindow();
        stage.close();
    }
    public ClientRepo getRepo(ClientRepo repo){
        return this.repo = repo;
    }
    public void saveButtonOnAction(ActionEvent event){
        String id = UUID.randomUUID().toString().substring(0, 4);
        String name = txtCardHolderName.getText();
        String number = txtCardNumber.getText();
        String account = txtAccountNumber.getText();
        Bank.BANK_ENUM bank = cbbBank.getValue();
        String status = "";
        try {
            repo.addCreditCard(new CreditCard(
                    id,
                    name,
                    number,
                    account,
                    bank,
                    status,
                    repo.getCustomer().getId()
            ));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
    private void initComboBoxBank() {
        Bank.BANK_ENUM[] banks = {Bank.BANK_ENUM.VCB, Bank.BANK_ENUM.ACB, Bank.BANK_ENUM.BIDV, Bank.BANK_ENUM.ARB,};
        Callback<ListView<Bank.BANK_ENUM>, ListCell<Bank.BANK_ENUM>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Bank.BANK_ENUM bank, boolean empty) {
                super.updateItem(bank, empty);
                setText(empty ? "" : Bank.bankToString(bank));
            }
        };
        cbbBank.getItems().addAll(banks);
        cbbBank.setCellFactory(factory);
        cbbBank.setButtonCell(factory.call(new JFXListView<>()));
    }
}
