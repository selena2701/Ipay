package dao;

import model.User;

import java.sql.SQLException;

public interface UserDao {

	User findByLogin(String username) throws SQLException;
	boolean validateLogin(User user) throws SQLException;
}
