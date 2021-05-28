package Model;


import Controller.Database.BillDao;
import Controller.Database.DaoFactory;

import java.sql.SQLException;
import java.util.List;

public class Bill extends Customer {
    private String electricbillid;
    private int previousvalue;
    private int curentvalue;
    private int consumevalue;
    private String vat;
    private double total;
    private String electrictype;
    private String fromdate;
    private String todate;
    private String statusbill;

    public Bill(String id, String electricbillid){
        super(id);
        this.electricbillid = electricbillid;
    }

    public String getElectricbillid() {
        return electricbillid;
    }

    public void setElectricbillid(String electricbillid) {
        this.electricbillid = electricbillid;
    }

    public int getPreviousvalue() {
        return previousvalue;
    }

    public void setPreviousvalue(int previousvalue) {
        this.previousvalue = previousvalue;
    }

    public int getCurentvalue() {
        return curentvalue;
    }

    public void setCurentvalue(int curentvalue) {
        this.curentvalue = curentvalue;
    }

    public int getConsumevalue() {
        return consumevalue;
    }

    public void setConsumevalue(int consumevalue) {
        this.consumevalue = consumevalue;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getElectrictype() {
        return electrictype;
    }

    public void setElectrictype(String electrictype) {
        this.electrictype = electrictype;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getStatusbill() {
        return statusbill;
    }

    public void setStatusbill(String statusbill) {
        this.statusbill = statusbill;
    }
    public static List<Bill> all() throws SQLException {
        return billDAO().all();
    }

    private static BillDao billDAO(){
        DaoFactory dao = DaoFactory.getDatabase();
        return dao.getBillDao();
    }
}
