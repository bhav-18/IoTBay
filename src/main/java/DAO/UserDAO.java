package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Model.User;



public class UserDAO{	
	private Statement st;

	

	 public User getCurrentUser(String email) {
		User currentUser = new User();
    	try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
/*            String sql = "SELECT * FROM user_access_logs WHERE user_email = ?";*/
            PreparedStatement prep = connection.prepareStatement("SELECT id, firstName, lastName, email, upassword, phone, userType FROM users WHERE email = ?");
            prep.setString(1, email);
            ResultSet result = prep.executeQuery();  
            
            while(result.next()) {
            	int id = result.getInt("ID");
            	String firstName = result.getString("firstName");
            	String lastName = result.getString("lastName");
            	String user_email = result.getString("email");
            	String phone = result.getString("phone");
            	String password = result.getString("upassword");
            	String userType = result.getString("userType");
            	
            	currentUser = new User(id, firstName, lastName, user_email, phone, password, userType);
            } 
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        
    	return currentUser;
    	
	}
	
	
	public List<User> getAllUsers() {
	    List<User> users = new ArrayList<User>();
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
	        PreparedStatement prep = connection.prepareStatement("SELECT id, firstName, lastName, email, upassword, phone, userType FROM users");
	        ResultSet result = prep.executeQuery();    
	        
	        while (result.next()) {
	        	int id = result.getInt("ID");
	            String firstName = result.getString("firstName");
	            String lastName = result.getString("lastName");
	            String email = result.getString("email");
	            String phone = result.getString("phone");
	            String password = result.getString("upassword");
	            String userType = result.getString("userType");
	            System.out.println("Getting user");
	            User user = new User(id,firstName, lastName, email, phone, password, userType); 
	            users.add(user);  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return users;
	}
	
	public void createUser(User user) throws SQLException {
	    
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
		     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
		     String sql = "INSERT INTO users (firstName, lastName, email, upassword, phone, userType) VALUES (?, ?, ?, ?, ?, ?)";
	         PreparedStatement pstmt = conn.prepareStatement(sql); {
	        pstmt.setString(1, user.getFirstName());
	        pstmt.setString(2, user.getLastName());
	        pstmt.setString(3, user.getEmail());
	        pstmt.setString(4, user.getPassword());
	        pstmt.setString(5, user.getPhoneNumber());
	        pstmt.setString(6, user.getUserType());
	        pstmt.executeUpdate();
	    }
	    }catch  (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void updateUser(User user) throws SQLException {
	    String sql = "UPDATE users SET firstName = ?, lastName = ?, email = ?, upassword = ?, phone = ?, userType = ? WHERE email = ?";
	    try  {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
		     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
		     PreparedStatement pstmt = conn.prepareStatement(sql); {
	        pstmt.setString(1, user.getFirstName());
	        pstmt.setString(2, user.getLastName());
	        pstmt.setString(3, user.getEmail());
	        pstmt.setString(4, user.getPassword());
	        pstmt.setString(5, user.getPhoneNumber());
	        pstmt.setString(6, user.getUserType());	        
	        pstmt.executeUpdate();
	    }
	    }catch  (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void deleteUser(int id) throws SQLException {
	    String sql = "DELETE FROM users WHERE id = ?";
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");	    	
	         PreparedStatement pstmt = conn.prepareStatement(sql); {
	        pstmt.setInt(1, id);
	        pstmt.executeUpdate();
	    }
	}catch  (Exception e) {
        e.printStackTrace();
    }
	}


	public User getUserById(int id) throws SQLException {
	    User user = null;
	    String sql = "SELECT * FROM users WHERE id = ?";
	    
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1."); // Ensure you have a method `connect()` that establishes a database connection.
	         PreparedStatement pstmt = conn.prepareStatement(sql); {
	        
	        pstmt.setInt(1, id);  // Set the ID parameter in the SQL query.
	        
	         }try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                // Assuming you have a constructor in User class that accepts all these parameters.
	                user = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
	                                rs.getString("email"), rs.getString("upassword"), rs.getString("phone"),
	                                rs.getString("userType"));
	            }
	        }
	    }catch  (Exception e) {
	        e.printStackTrace();
	    }
	    return user;  // Returns the user object or null if not found.
	}




  		
 }