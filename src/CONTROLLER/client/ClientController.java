package CONTROLLER.client;

import com.jfoenix.controls.*;
import database.adminRepo.NotificationsRepo;
import database.clientRepo.ClientRepo;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import MODELS.*;
import MODELS.Region;
import utils.helper.DataHolder;
import utils.helper.Navigator;
import utils.helper.NavigatorDetail;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientController implements Initializable {


    @FXML
    private StackPane main;
    @FXML
    private StackPane notification, service;
    @FXML
    private HBox home;
    @FXML
    private Pane account, bill, homecalculate, homescheduling, slider;
    @FXML
    private Label tabCloneHome, labelGreeting;
    @FXML
    private JFXButton btnLogout, checkin, checkout;
    @FXML
    private Pane btnNotification;
    @FXML
    private AnchorPane reports;
    @FXML
    private TableView<Invoice> tableBill;
    @FXML
    private TableColumn<Invoice, String> idColumn;
    @FXML
    private TableColumn<Invoice, Date> timeColumn;
    @FXML
    private TableColumn<Invoice, Boolean> status;
    @FXML
    private TableColumn<Invoice, Double> valueColumn;
    @FXML
    private JFXTextField idText, previousValue, currentValue, consumeValue, VAT, total, electricityType, fromDate, toDate;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private TableView<Notification> tableNotification;
    @FXML
    private TableColumn<Notification, String> no_id, detail;
    @FXML
    private TableColumn<Notification, Date> date;
    @FXML
    private JFXComboBox<Region.REGION_ENUM> cbRegion;
    @FXML
    private JFXTextField txtFullName, txtID, txtNumber, txtAddress, txtNationalID, txtDateRegister, txtUsername,
            txtInvoiceCardHolderName, txtInvoiceCardNumber, txtInvoiceAccountNumber;
    @FXML
    private JFXPasswordField pwfCurrentPassword, pwfNewPassword, pwfRetypePassword;
    @FXML
    private RadioButton rBtnMale, rBtnFemale, rBtnOrther;
    @FXML
    private DatePicker dpBirthday;
    @FXML
    private TableView<CreditCard> tblCreditCard;
    @FXML
    private TableColumn<CreditCard, String> tblColumnCardHolderName, tblColumnAccountNumber, tblColumnBank, tblColumnStatus;


    private ClientRepo repo;
    private Customer user = new Customer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String username = DataHolder.getINSTANCE().getUserName();
        try {
            repo = new ClientRepo(username);
            user = repo.getCustomer();
            initComponents();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    //Event handlers for Home
    @FXML
    private void btnHome() {
        setLblWelcome(user.getName());
        checkSum(main);
        home.setVisible(true);
    }

    @FXML
    private void btnCalendar(MouseEvent mouseEvent) {
        checkSum(service);
        homescheduling.setVisible(true);
    }

    @FXML
    private void btnCalculator(MouseEvent mouseEvent) {
        checkSum(service);
        homecalculate.setVisible(true);
    }

    //Event handlers for Account
    @FXML
    private void btnAccount() {
        labelGreeting.setText("Account");
        checkSum(main);
        account.setVisible(true);
    }

    @FXML
    private void btnAddCredit() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./../../views/client/add-credit-card.fxml")));
        Scene scene = new Scene(root, 600, 420);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    //Event handlers for BILL
    @FXML
    private void btnBill() {
        labelGreeting.setText("Invoice");
        checkSum(main);
        bill.setVisible(true);
    }

    //Event handlers for Statistic
    @FXML
    private void btnReport() {
        labelGreeting.setText("Statistic");
        checkSum(main);
        reports.setVisible(true);
    }

    //Event handlers for Notification
    @FXML
    private void btnNotification(MouseEvent mouseEvent) {
        labelGreeting.setText("Notification");
        checkSum(main);
        notification.setVisible(true);
    }

    //Log out
    @FXML
    private void btnLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Are you sure to log out?");

        ButtonType btnTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType btnTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(btnTypeYes, btnTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnTypeYes) {
            try {
                DataHolder.getINSTANCE().setUserName(null);
                Navigator.navigate((Stage) main.getScene().getWindow(), new NavigatorDetail("auth/auth-view.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Others
    private void checkSum(StackPane tab) {
        for (int i = 0; i < tab.getChildren().size(); i++) {
            tab.getChildren().get(i).setVisible(false);
        }
    }

    private void setLblWelcome(String name) {
        String str = "";
        for (String s : name.split(" ")) {
            str = s;
        }
        labelGreeting.setText("Hi, " + str);
    }

    //Initializations

    private void initComponents() {

        setLblWelcome(user.getName());
        initComboBoxRegion();
        initTables();

        tableBill.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 1) {
                if (tableBill.getSelectionModel().getSelectedItems() != null) {
                    Invoice invoice = tableBill.getSelectionModel().getSelectedItem();
                    idText.setText(String.valueOf(invoice.getId()));
                    previousValue.setText(String.valueOf(invoice.getPreviousValue()));
                    currentValue.setText(String.valueOf(invoice.getCurrentValue()));
                    consumeValue.setText(String.valueOf(invoice.getConsumedValue()));
                    VAT.setText(String.valueOf(invoice.getVAT()));
                    total.setText(String.valueOf(invoice.getTotal()));
                    fromDate.setText(String.valueOf(invoice.getFromDate()));
                    toDate.setText(String.valueOf(invoice.getToDate()));
                    electricityType.setText(String.valueOf(invoice.getElectricityType()));
                    checkin.setVisible(!invoice.isPaid());
                }
            }
        });


        slider.setTranslateX(400);
        checkin.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();
            slider.setTranslateX(400);
        });
        checkout.setOnMouseClicked(event -> {
            try {
                repo.updateInvoice(idText.getText());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(400);
            slide.play();
            slider.setTranslateX(0);
        });
    }

    private void initTables() {
        initAccountProfile();
        initAccountChangePassword();
        initAccountPaymentMethod();
        initInvoiceTable();
        initInvoiceSliderPayment();
        initStatisticLineChart();
        initStatisticBarChart();
        initNotificationsTable();
    }

    private void initAccountProfile() {
        txtFullName.setText(user.getName());
        txtID.setText(user.getId());
        txtNumber.setText(user.getPhoneNumber());
        if (user.getGender()) {
            rBtnMale.setSelected(true);
        } else {
            rBtnFemale.setSelected(true);
        }
        dpBirthday.setValue(user.getBirthday());
        txtAddress.setText(user.getAddress());
        txtNationalID.setText(user.getNationalId());
        cbRegion.setValue(user.getRegion());
        txtDateRegister.setText(user.getDateRegister().toString());
    }

    private void initAccountChangePassword() {
        Customer customer = null;
        customer = repo.getCustomer();
        txtUsername.setText(user.getUsername());
    }


    private void initAccountPaymentMethod() {
        tblColumnCardHolderName.setCellValueFactory(new PropertyValueFactory<>("cardholdername"));
        tblColumnAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountnumber"));
        tblColumnBank.setCellValueFactory(new PropertyValueFactory<>("bankname"));
        tblColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblCreditCard.setItems(repo.getAllCreditCards());
    }

    private void initInvoiceTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        status.setCellValueFactory(new PropertyValueFactory<>("paid"));

        tableBill.setItems(repo.getInvoices());
    }

    private void initInvoiceSliderPayment() {
        for (CreditCard card : tblCreditCard.getItems()) {
            if (card.getStatus().equals("DEFAULT")) {
                txtInvoiceCardHolderName.setText(card.getCardholdername());
                txtInvoiceCardNumber.setText(card.getCardnumber());
                txtInvoiceAccountNumber.setText(card.getAccountnumber());
            }
        }
    }

    private void initStatisticLineChart() {
        ObservableList<Invoice> invoices = tableBill.getItems();
        int i = invoices.size();
        XYChart.Series series = new XYChart.Series();
        if (i >= 5) {
            for (int d = 5; d >= 1; d--) {
                series.getData().add(new XYChart.Data(invoices.get(i - d).getFromDate().toString().subSequence(0, 7), invoices.get(i - d).getConsumedValue()));
            }
        } else {
            for (int d = i; d >= 1; d--) {
                series.getData().add(new XYChart.Data(invoices.get(i - d).getFromDate().toString().subSequence(0, 7), invoices.get(i - d).getConsumedValue()));
            }
        }

        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        series.getNode().setStyle("-fx-stroke: #F5F5F5");
    }

    private void initStatisticBarChart() {
        ObservableList<Invoice> invoices = tableBill.getItems();
        int i = invoices.size();
        XYChart.Series series1 = new XYChart.Series();

        if (i >= 5) {
            for (int d = 5; d >= 1; d--) {
                series1.getData().add(new XYChart.Data(invoices.get(i - d).getFromDate().toString().subSequence(0, 7), invoices.get(i - d).getConsumedValue()));
            }
        } else {
            for (int d = i; d >= 1; d--) {
                series1.getData().add(new XYChart.Data(invoices.get(i - d).getFromDate().toString().subSequence(0, 7), invoices.get(i - d).getConsumedValue()));
            }
        }
        barChart.getData().addAll(series1);
        barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
    }

    private void initNotificationsTable() {
        NotificationsRepo notificationsRepo = new NotificationsRepo();
        no_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("datePublished"));
        detail.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableNotification.setItems(notificationsRepo.getNotifications());
    }


    private void initComboBoxRegion() {
        Region.REGION_ENUM[] regions = {Region.REGION_ENUM.NorthSide, Region.REGION_ENUM.MidSide, Region.REGION_ENUM.SouthSide, Region.REGION_ENUM.WestSide};

        Callback<ListView<Region.REGION_ENUM>, ListCell<Region.REGION_ENUM>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Region.REGION_ENUM region, boolean empty) {
                super.updateItem(region, empty);
                setText(empty ? "" : Region.regionToString(region));
            }
        };
        cbRegion.getItems().addAll(regions);
        cbRegion.setCellFactory(factory);
        cbRegion.setButtonCell(factory.call(new JFXListView<>()));
    }
}
