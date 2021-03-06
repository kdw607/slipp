

package net.slipp.bark;


import net.slipp.user.User;

public class UserDAO0518normal {

	public Connection getConnection() throws SQLException {

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "board";
		String pw = "board";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void addUser(User user) throws SQLException {

		String sql = "insert into users values(?, ?, ?, ?)";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		
		pstmt.executeUpdate();

	}

	public User findByUserId(String userId) throws SQLException{

		String sql = "select * from users where userId = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			User user = new User(rs.getString("userId"),
								 rs.getString("password"),
								 rs.getString("name"),
								 rs.getString("email"));
			return user;
		}
		
		return null;

	}

	public void removeUser(String userId) throws SQLException {

		String sql = "delete from users where userId = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1,  userId);
		
		pstmt.executeUpdate();
		
	}

	public void updateUser(User user) throws SQLException {
		
		String sql = "update users set password=?, name=?, email=? where userId=? ";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getEmail());
		pstmt.setString(4, user.getUserId());
		
		pstmt.executeUpdate();
	}	
	
}