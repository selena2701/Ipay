package controller;


import model.User;

import java.sql.SQLException;

public class UserController {
	
	private static UserController instance = new UserController();
	
	private UserController(){}
	
	public static UserController getInstance() {
		return instance;
	}

	public boolean valid(User user) throws SQLException{
		return user.valid();
	}
	public User findbylogin(String username) throws  SQLException{
		User user = User.findByLogin(username);
		return user;
	}

}
