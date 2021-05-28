package Model;

import Controller.Database.*;

import java.sql.SQLException;
import java.util.Date;


/**
 * The class User represent a user
 * 
 * @author Diego Marczal
 * @version 2011.06.15
 */
public class Customer {
	
	private String username;
	private String password_login;
	private String id ;
	private String fullname;
	private String phone;
	private String address;
	private String nationalid;
	private String gender;
	private Date birthday;
	private String region;
	private java.util.Date dateregister;
	
	public Customer(String name, String login) {
		this.username = name;
		this.password_login = login;
	}
	public Customer(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getPasswordLogin() {
		return password_login;
	}
	
	public void setPasswordLogin(String login) {
		this.password_login = login;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationalid() {
		return nationalid;
	}

	public void setNationalid(String nationalid) {
		this.nationalid = nationalid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getDateregister() {
		return dateregister;
	}

	public void setDateregister(Date dateregister) {
		this.dateregister = dateregister;
	}

	public String[] toArray(){
		return new String[] {this.id, this.username, this.phone, this.address,
			this.nationalid, this.gender, this.birthday.toString(), this.region, this.dateregister.toString()};
	}

	@Override
	public String toString() {
		return  " id: "    + id +
				" Username: "  + username +
				" Password: " + password_login +
				" Fullname: " + fullname +
				" Phone: " + phone +
				" Address: "+ address +
				" National: " + nationalid +
				" Gender: " + gender +
				" Birthday: "+ birthday +
				" Region: "+ region +
				" DateRegister: "+ dateregister;
	}
	/*  Methods to work with the database **/

	/**
	 * Method to find one user by login
	 * @param username user's login
	 * @return user the user with the login given,
	 * 		   if the login doesn't exists, the method return null
	 */
	public static Customer findByLogin(String username) throws SQLException {
		return userDAO().findByLogin(username);
	}

	public boolean valid() throws SQLException{
		return userDAO().validateLogin(this);
	}
	
	/* 
	 * Method to return the user DAO
	 * @return dao the user dao 
     */
	private static CustomerDao userDAO(){
		DaoFactory dao = DaoFactory.getDatabase();
		return dao.getUserDao();
	}
}
