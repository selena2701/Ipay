package Controller;

import Model.Bill;
import Model.Customer;
import Model.HolderManager;
import Model.Notification;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnAccount;
    @FXML
    private Button btnBill;
    @FXML
    private Button btnReport;
    @FXML
    private Button btnInform;
    @FXML
    private VBox layout1;
    @FXML
    private StackPane tab;
    @FXML
    private StackPane main;
    @FXML
    private VBox home;
    @FXML
    private Pane account;
    @FXML
    private StackPane tabClone;
    @FXML
    private VBox tabCloneHome;
    @FXML
    private VBox tabCloneTime;
    @FXML
    private VBox tabCloneCalculated;
    @FXML
    private Pane bill;
    @FXML
    private Label lblWelcome;
    @FXML
    private Button btnLogout;
    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtCusID;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtGender;
    @FXML
    private DatePicker txtBirthDay;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtNationalID;
    @FXML
    private TextField txtRegion;
    @FXML
    private TextField txtDateRegister;
    @FXML
    private AnchorPane reports;
    @FXML
    private ListView<String> myListView;
    @FXML
    private ListView<String> homeInform;

    @FXML
    private TableView<Bill> tableBill;
    @FXML
    private TableColumn<Bill, Integer> idColumn;
    @FXML
    private TableColumn<Bill, String> timeColumn;
    @FXML
    private TableColumn<Bill, Double> valueColumn;
    @FXML
    private TableColumn<Bill, String> status;
    @FXML
    private TextField idText;
    @FXML
    private TextField previousValue;
    @FXML
    private TextField currentValue;
    @FXML
    private TextField consumeValue;
    @FXML
    private TextField VAT;
    @FXML
    private TextField total;
    @FXML
    private TextField electricityType;
    @FXML
    private TextField fromDate;
    @FXML
    private TextField toDate;

    private ObservableList<Bill> billList;


    @FXML
    private Pane slider;
    @FXML
    private Button checkin;
    @FXML
    private Button checkout;


    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private StackPane notification;
    @FXML
    private TableView<?> homescheduling;
    @FXML
    private Pane homecalculate;
    @FXML
    private StackPane service;
    @FXML
    private Button btneditprofile;
    @FXML
    private TableView<Notification> tableNotification;
    @FXML
    private TableColumn<Notification, String> no_id;
    @FXML
    private TableColumn<Notification, String> date;
    @FXML
    private TableColumn<Notification, String> detail;

    private ObservableList<Notification> notificationsList;


    public void checkSum(StackPane tab){
        for(int i=0; i<tab.getChildren().size();i++){
            tab.getChildren().get(i).setVisible(false);
        }
    }

    public void setLblWelcome(String name){
        String str = "";
        for(String s: name.split(" ")){
            str = s;
        }
        lblWelcome.setText("Hi, "+ str);
    }

    /* Xử lý sự kiện phần Home */

    public void btnHome(ActionEvent event){
        checkSum(main);
        home.setVisible(true);
        checkSum(tabClone);
        tabCloneHome.setVisible(true);
        checkSum(service);
        homeInform.setVisible(true);
    }

    public void btnCalendar(ActionEvent event){
        checkSum(tabClone);
        tabCloneTime.setVisible(true);
        checkSum(service);
        homescheduling.setVisible(true);
    }

    public void btnCalculator(ActionEvent event){
        checkSum(tabClone);
        tabCloneCalculated.setVisible(true);
        checkSum(service);
        homecalculate.setVisible(true);
    }


    /* Xử lý sự kiện phần Account */

    public void btnAccount(ActionEvent event) throws SQLException {
        lblWelcome.setText("Account");
        checkSum(main);
        account.setVisible(true);
    }

    /* Xử lý sự kiện phần Invoices */

    public void btnBill(ActionEvent event){
        lblWelcome.setText("Invoice");
        checkSum(main);
        bill.setVisible(true);

    }

    /* Xử lý sự kiện phần Statistic*/

    public void btnReport(ActionEvent event){
        lblWelcome.setText("Statistic");
        checkSum(main);
        reports.setVisible(true);
    }

    /* Xử lý sự kiện phần Notification*/

    public void btnInform(ActionEvent event){
        lblWelcome.setText("Notification");
        checkSum(main);
        notification.setVisible(true);
    }

    /* Xử lý sự kiện phần Log out */

    public void btnLogout(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Bạn có chắc muốn thoát");

        ButtonType btnTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType btnTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(btnTypeYes, btnTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()== btnTypeYes){
            logout();
        }
    }
    public void logout() {
        Parent root1 = null;
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(root, 540, 400));
            primaryStage.show();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Khởi tạo announcement */
        String[] inform = {"Vui lòng thanh toán trước ngày chốt chỉ số 28/04/2021", "Đã có lịch cúp điện tháng 5"};

        homeInform.getItems().addAll(inform);

        /* Xuất profile CUS ra màn hình */
        try {
            Customer customer = CustomerController.getInstance().findbylogin(HolderManager.getInstance().getUsername());
            txtFullName.setText(customer.getFullname());
            txtCusID.setText(customer.getId());
            txtPhone.setText(customer.getPhone());
            txtGender.setText(customer.getGender());
            txtBirthDay.setValue(LocalDate.parse(customer.getBirthday().toString()));
            txtAddress.setText(customer.getAddress());
            txtNationalID.setText(customer.getNationalid());
            txtRegion.setText(customer.getRegion());
            txtDateRegister.setText(customer.getDateregister().toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        /* Hiển thị thông tin tổng quát hóa đơn */

        try {
            billList = FXCollections.observableArrayList(BillController.getInstance().allBill());
            idColumn.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("electricbillid"));
            timeColumn.setCellValueFactory(new PropertyValueFactory<Bill, String>("fromdate"));
            valueColumn.setCellValueFactory(new PropertyValueFactory<Bill, Double>("total"));
            status.setCellValueFactory(new PropertyValueFactory<Bill, String>("statusbill"));

            tableBill.setItems(billList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /* Hiển thị thông tin chi tiết hóa đơn khi chọn 1 hóa đơn trong bảng hóa đơn*/

        tableBill.setOnMouseClicked((MouseEvent event) ->{
            if (event.getClickCount() == 1){
                if(tableBill.getSelectionModel().getSelectedItems() != null){
                    Bill selectedBill = tableBill.getSelectionModel().getSelectedItem();
                    idText.setText(String.valueOf(selectedBill.getElectricbillid()));
                    previousValue.setText(String.valueOf(selectedBill.getPreviousvalue()));
                    currentValue.setText(String.valueOf(selectedBill.getCurentvalue()));
                    consumeValue.setText(String.valueOf(selectedBill.getConsumevalue()));
                    VAT.setText(String.valueOf(selectedBill.getVat()));
                    total.setText(String.valueOf(selectedBill.getTotal()));
                    fromDate.setText(String.valueOf(selectedBill.getFromdate()));
                    toDate.setText(String.valueOf(selectedBill.getTodate()));
                    electricityType.setText(String.valueOf(selectedBill.getElectrictype()));
                }
            }
        });

        /* Hiển thị thông tin thanh toán */

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
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(400);
            slide.play();
            slider.setTranslateX(0);
        });

        /* Hiển thị bảng thông báo */

        try {
            notificationsList = FXCollections.observableArrayList(NotificationController.getInstance().allNotification());
            no_id.setCellValueFactory(new PropertyValueFactory<Notification, String>("no_id"));
            date.setCellValueFactory(new PropertyValueFactory<Notification, String>("datecreated"));
            detail.setCellValueFactory(new PropertyValueFactory<Notification, String>("detail"));

            tableNotification.setItems(notificationsList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /* Hiển thị biểu đồ thống kê lượng điện và tiền trong 5 tháng gần nhất */

        initLineChart();
        initBarChart();
    }


    private void initLineChart(){
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("1", 167000));
        series.getData().add(new XYChart.Data("2", 223000));
        series.getData().add(new XYChart.Data("3", 124000));
        series.getData().add(new XYChart.Data("4", 230000));
        series.getData().add(new XYChart.Data("5", 160000));

        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");


    }
    private void initBarChart(){
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("1", 200));
        series1.getData().add(new XYChart.Data("2", 503));
        series1.getData().add(new XYChart.Data("3", 445));
        series1.getData().add(new XYChart.Data("4", 230));
        series1.getData().add(new XYChart.Data("5", 652));

        barChart.getData().addAll(series1);
        barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");


    }

}
