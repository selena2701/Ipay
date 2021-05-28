package Controller.Database;

import Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MysqlCustomerDao implements CustomerDao {


	private static final String 
	FIND_BY_LOGIN = "SELECT * FROM e_customer WHERE Username = ?";


	private static final String
	VALIDATE_LOGIN= "SELECT count(1) FROM user_account WHERE Username = ? AND Password_Login= ?";
	


	/**
	 * Method to find a user by login
	 * @param username the username that will be inserted
	 * @return user User find by the login, otherwise null
	 */
	public Customer findByLogin(String username) throws SQLException {
		Connection c = DaoFactory.getDatabase().getJDBCConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_LOGIN);
		pstmt.setString(1, username);

		Customer customer = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()) {
			customer = createUser(rset);
		}

		pstmt.close();
		c.close();

		return customer;
	}
	/**
	 * Method to valid a user by login
	 * @param customer the user that will be enter
	 * @return true , otherwise null
	 */
	public boolean validateLogin(Customer customer) throws SQLException{
		Connection c = DaoFactory.getDatabase().getJDBCConnection();

		PreparedStatement pstmt = c.prepareStatement(VALIDATE_LOGIN);
		pstmt.setString(1, customer.getUsername());
		pstmt.setString(2, customer.getPasswordLogin());

		ResultSet rset = pstmt.executeQuery();
		while(rset.next()){
			if(rset.getInt(1)==1){
				return true;
			}
		}
		return false;
	}
	
	/* method to create users **/
	private Customer createUser(ResultSet rset) throws SQLException{
		Customer customer = new Customer(rset.getString("CUS_ID"));

		customer.setUsername(rset.getString("Username"));
		customer.setFullname(rset.getString("FullnameCUS"));
		customer.setPhone(rset.getString("PhoneCUS"));
		customer.setAddress(rset.getString("AddressCUS"));
		customer.setNationalid(rset.getString("NationalID"));
		customer.setGender(rset.getString("Gender"));
		customer.setBirthday(rset.getDate("Birthday"));
		customer.setRegion(rset.getString("Region"));
		customer.setDateregister(rset.getDate("DateRegister"));
		
		return customer;
	}
}
