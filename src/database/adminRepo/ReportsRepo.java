package database.adminRepo;

import MODELS.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReportsRepo {
    private final ObservableList<Report> reports= FXCollections.observableArrayList();

    private Connection connection=null;

    public ReportsRepo() throws SQLException, ClassNotFoundException {
        loadReportsFromDB();
    }

    private void loadReportsFromDB() throws SQLException, ClassNotFoundException {
        connection= DBConnection.connect();

        String query="SELECT CAST(MONTH(DatePaid)AS varchar) +'-'+ CAST(YEAR(DatePaid)AS varchar) AS \"PERIOD\", COUNT(ELEC_BILL_ID) AS \"SLGIAODICH\", SUM(Total) AS \"TONGSOTIENGD\"FROM E_ELECTRICITY_BILL EB, E_CUSTOMER CUS WHERE EB.CUS_ID=CUS.CUS_ID AND EB.StatusBill='PAID' GROUP BY CAST(MONTH(DatePaid)AS varchar) +'-'+ CAST(YEAR(DatePaid)AS varchar)";

        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(query);

        reports.clear();
        while(resultSet.next()){
            Report resultReport=Report.fromResultSet(resultSet);
            if(!reports.contains(resultReport)){
                reports.add(resultReport);
            }
        }

        connection.close();
    }

    public ObservableList<Report> getReports() {
        return reports;
    }
}
