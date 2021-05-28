package Controller;


import Model.Bill;

import java.sql.SQLException;
import java.util.List;

public class BillController {

	private static BillController instance = new BillController();

	private BillController(){}
	
	public static BillController getInstance() {
		return instance;
	}

	public List<Bill> allBill() throws SQLException {
		return Bill.all();
	}

}
