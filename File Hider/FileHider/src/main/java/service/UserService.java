package service;

import java.sql.SQLException;

import dao.UserDAO;
import model.User;

public class UserService {
	public static Integer saveUser(User user) {
		try {
			if(UserDAO.isExsists(user.getEmail())) {
				return 0;
			}
			else {
				return UserDAO.addUser(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
