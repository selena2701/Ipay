package Controller;

import Model.E_Notification;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Model.DatabaseConnection;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import Model.E_Notification;




public class NotificationController implements Initializable {

    @FXML
    private TableView<E_Notification> notiTable;
    @FXML
    private TableColumn<E_Notification,String> idCol;
    @FXML
    private TableColumn<E_Notification, String>  DCreateCol;
    @FXML
    private TableColumn<E_Notification, String> DSentCol;
    @FXML
    private TableColumn<E_Notification, String> EditByCol;
    @FXML
    private TableColumn<E_Notification, String> DetailCol;
    @FXML
    private TableColumn<E_Notification, String> editCol;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    E_Notification notice = null ;

    ObservableList<E_Notification>  NoticList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }
    @FXML
    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/addnotification.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void refreshTable() {
        try {
            NoticList.clear();

            query = "Select * from E_NOTIFICATION";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                NoticList.add(new E_Notification(
                        resultSet.getString("ID"),
                        resultSet.getDate("DateCreated"),
                        resultSet.getDate("DateSent"),
                        resultSet.getString("EditedBy"),
                        resultSet.getString("Detail")));
                notiTable.setItems(NoticList);

            }


        } catch (SQLException ex) {
            Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void print(MouseEvent event) {
    }

    private void loadDate() {

        connection = DatabaseConnection.getConnection();
        refreshTable();

        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        DCreateCol.setCellValueFactory(new PropertyValueFactory<>("DateCreated"));
        DSentCol.setCellValueFactory(new PropertyValueFactory<>("DateSent"));
        EditByCol.setCellValueFactory(new PropertyValueFactory<>("EditedBy"));
        DetailCol.setCellValueFactory(new PropertyValueFactory<>("Detail"));

        //add cell of button edit
        Callback<TableColumn<E_Notification, String>, TableCell<E_Notification, String>> cellFoctory = (TableColumn<E_Notification, String> param) -> {
            // make cell containing buttons
            final TableCell<E_Notification, String> cell = new TableCell<E_Notification, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                notice = notiTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM E_NOTIFICATION WHERE id  ="+notice.getID();
                                connection = DatabaseConnection.getConnection();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            notice = notiTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/View/addnotification.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddNotificationController addNotificationController = loader.getController();
                            addNotificationController.setUpdate(true);
                            addNotificationController.setTextField(notice.getID(), notice.getDateCreated().toLocalDate(),
                                    notice.getDateSent().toLocalDate(),notice.getEditBy(), notice.getDetail());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();




                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editCol.setCellFactory(cellFoctory);
        notiTable.setItems(NoticList);


    }
}

