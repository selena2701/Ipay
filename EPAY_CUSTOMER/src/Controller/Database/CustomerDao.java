package Controller.Database;

import Model.Customer;

import java.sql.SQLException;

public interface CustomerDao {

	Customer findByLogin(String username) throws SQLException;
	boolean validateLogin(Customer customer) throws SQLException;
}
