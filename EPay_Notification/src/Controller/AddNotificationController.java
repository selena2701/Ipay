package Controller;

import Model.DatabaseConnection;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Model.E_Notification;
public class AddNotificationController implements Initializable{
    @FXML
    private TextField editFid;
    @FXML
    private DatePicker dcreateFid;
    @FXML
    private DatePicker dsentFid;
    @FXML
    private TextField ncontentFid;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    E_Notification notice = null;
    private boolean update;
    String Id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(MouseEvent event) {

        connection = DatabaseConnection.getConnection();
        String editby = editFid.getText();
        String datecreate = String.valueOf(dcreateFid.getValue());
        String datesent = String.valueOf(dsentFid.getValue());
        String ncontent = ncontentFid.getText();

        if (editby.isEmpty() || datecreate.isEmpty() || datesent.isEmpty() || ncontent.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        dcreateFid.setValue(null);;
        dsentFid.setValue(null);
        editFid.setText(null);
        ncontentFid.setText(null);

    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO E_NOTIFICATION( DateCreated, DateSent, EditedBy, Detail) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE E_NOTIFICATION SET "
                    + "DateCreated=?,"
                    + "DateSent=?,"
                    + "EditedBy=?,"
                    + "Detail= ? WHERE id = '"+Id+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, String.valueOf(dsentFid.getValue()));;
            preparedStatement.setString(2, String.valueOf(dcreateFid.getValue()));
            preparedStatement.setString(3, editFid.getText());
            preparedStatement.setString(4, ncontentFid.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddNotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(String id, LocalDate dateCreated, LocalDate dateSent, String editBy, String detail) {

        Id = id;
        dcreateFid.setValue(dateCreated);
        dsentFid.setValue(dateSent);
        editFid.setText(editBy);
        ncontentFid.setText(detail);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
}
