package Controller;


import Model.Customer;

import java.sql.SQLException;

public class CustomerController {
	
	private static CustomerController instance = new CustomerController();
	
	private CustomerController(){}
	
	public static CustomerController getInstance() {
		return instance;
	}

	public boolean valid(Customer customer) throws SQLException{
		return customer.valid();
	}
	public Customer findbylogin(String username) throws  SQLException{
		Customer customer = Customer.findByLogin(username);
		return customer;
	}

}
