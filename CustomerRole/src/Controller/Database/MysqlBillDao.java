package Controller.Database;

import Model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MysqlBillDao implements BillDao {


	private static final String
			ALL = "SELECT * FROM e_electricity_bill";

	public List<Bill> all() throws SQLException {
		ArrayList<Bill> bills = new ArrayList<>();

		Connection c = DaoFactory.getDatabase().getJDBCConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		while(rset.next()){
			bills.add(createBill(rset));
		}

		pstmt.close();
		c.close();

		return bills;
	}



	
	/* method to create users **/
	private Bill createBill(ResultSet rset) throws SQLException{
		Bill bill = new Bill(rset.getString("CUS_ID"), rset.getString("ELEC_BILL_ID"));

		bill.setPreviousvalue(rset.getInt("PreviousValue"));
		bill.setCurentvalue(rset.getInt("CurrentValue"));
		bill.setConsumevalue(rset.getInt("ConsumeValue"));
		bill.setVat(rset.getString("VAT"));
		bill.setTotal(rset.getDouble("Total"));
		bill.setElectrictype(rset.getString("Electricity_Type"));
		bill.setFromdate(rset.getString("FromDate").toString());
		bill.setTodate(rset.getString("ToDate").toString());
		bill.setStatusbill(rset.getString("StatusBill"));

		return bill;
	}


}
