package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MyConnection;
import model.User;

public class UserDAO {
	public static boolean isExsists(String email) throws SQLException
	{
		Connection con=MyConnection.getConnection();
		PreparedStatement ps=con.prepareStatement("select email from users");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			String mail=rs.getString(1);
			if(mail.equals(email)) {
				return true;
			}
		}
		return false;
		
	}
	public static int addUser(User user) throws SQLException {
		Connection con=MyConnection.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into users values(default,?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		
		return ps.executeUpdate();
	}

}
