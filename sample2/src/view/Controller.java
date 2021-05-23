package view;

import controller.NotificationController;
import controller.UserController;
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
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Bill;
import model.HolderManager;
import model.Notification;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private HBox tabAccount;
    @FXML
    private HBox tabBill;
    @FXML
    private HBox tabReport;
    @FXML
    private HBox tabInform;
    @FXML
    private HBox tabHome;
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
    private HBox tabCloneHome;
    @FXML
    private HBox tabCloneTime;
    @FXML
    private HBox tabCloneCalculated;
    @FXML
    private StackPane bill;
    @FXML
    private Button btnHistory;
    @FXML
    private Button btnPayment;
    @FXML
    private Label lblBill;
    @FXML
    private VBox billTransaction;
    @FXML
    private VBox billPayment;
    @FXML
    private Pane billHome;
    @FXML
    private Button btnLogout;
    @FXML
    private Label lblbillFullname;
    @FXML
    private AnchorPane reports;
    @FXML
    private ListView<String> myListView;
    @FXML
    private ListView<String> myListView1;
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
    private TextField idText;
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
    private ObservableList<Bill> billList;
    private ObservableList<Notification> notificationsList;

    public void checkSum(StackPane tab){
        for(int i=0; i<tab.getChildren().size();i++){
            tab.getChildren().get(i).setVisible(false);
        }
    }

    public void btnHome(ActionEvent event){
        checkSum(tab);
        tabHome.setVisible(true);
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

    public void btnAccount(ActionEvent event) throws SQLException {
        HolderManager holder = HolderManager.getInstance();
        lblbillFullname.setText(UserController.getInstance().findbylogin(holder.getUsername()).getFullname());
        myListView1.getItems().clear();
        myListView1.getItems().addAll(UserController.getInstance().findbylogin(holder.getUsername()).toArray());
        checkSum(tab);
        tabAccount.setVisible(true);
        checkSum(main);
        account.setVisible(true);
    }
    public void btnEditProfile(ActionEvent event){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editprofile.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            EditProfileController controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public void btnChangePassword(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("changepassword.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void btnBill(ActionEvent event){
        checkSum(tab);
        tabBill.setVisible(true);
        checkSum(main);
        bill.setVisible(true);
        checkSum(bill);
        billHome.setVisible(true);
        lblBill.setVisible(false);

    }
    public void btnHistory(ActionEvent event) {
        checkSum(bill);
        billTransaction.setVisible(true);
        lblBill.setVisible(true);
        lblBill.setText(">>Transaction");
    }
    public void btnPayment(ActionEvent event){
        checkSum(bill);
        billPayment.setVisible(true);
        lblBill.setVisible(true);
        lblBill.setText(">>Payment");
    }

    public void btnReport(ActionEvent event){
        checkSum(tab);
            tabReport.setVisible(true);
        checkSum(main);
            reports.setVisible(true);
    }

    public void btnInform(ActionEvent event){
        checkSum(tab);
            tabInform.setVisible(true);
            checkSum(main);
            notification.setVisible(true);
    }
    @FXML
    private Label lblWelcome;
    public void setLblWelcome(String name){
        String str = "";
        for(String s: name.split(" ")){
            str = s;
        }
        lblWelcome.setText("Hi, "+ str);
    }



    String[] prop ={"ID", "Username", "Phone", "Address", "National", "Gender", "Birthday", "Region","Date Register"};
    String[] inform = {"Vui lòng thanh toán trước ngày chốt chỉ số 28/04/2021", "Đã có lịch cúp điện tháng 5"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLineChart();
        initBarChart();
        myListView.getItems().addAll(prop);

        homeInform.getItems().addAll(inform);

        billList = FXCollections.observableArrayList(
                new Bill(1, "03/2021", 264567),
                new Bill(2, "04/2021", 406125)
        );
        idColumn.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("id"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Bill, String>("date"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Bill, Double>("value"));

        tableBill.setItems(billList);
        tableBill.setOnMouseClicked((MouseEvent event) ->{
            if (event.getClickCount() == 1){
                if(tableBill.getSelectionModel().getSelectedItems() != null){
                    Bill selectedBill = tableBill.getSelectionModel().getSelectedItem();
                    idText.setText(String.valueOf(selectedBill.getId()));
                }
            }
        });
        try {
            notificationsList = FXCollections.observableArrayList(NotificationController.getInstance().allNotification());
            no_id.setCellValueFactory(new PropertyValueFactory<Notification, String>("no_id"));
            date.setCellValueFactory(new PropertyValueFactory<Notification, String>("datecreated"));
            detail.setCellValueFactory(new PropertyValueFactory<Notification, String>("detail"));

            tableNotification.setItems(notificationsList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
