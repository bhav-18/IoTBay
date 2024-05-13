package registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*import java.util.ArrayList;
import java.util.List;*/

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private String userType;
	
	public User(){}
	
	
	public User(String firstName, String lastName, String email, String phoneNumber, String password, String userType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.userType = userType;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getfirstName(){
		return firstName;
	}
	
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getlastName(){
		return lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserType(){
		if (userType == null) {
			return "Guest";
		}
		return userType;
	}
	
	
	
	public User getCurrentUser(String email) {
		User currentUser = new User();
    	try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
/*            String sql = "SELECT * FROM user_access_logs WHERE user_email = ?";*/
            PreparedStatement prep = connection.prepareStatement("SELECT firstName, lastName, email, upassword, phone, userType FROM users WHERE email = ?");
            prep.setString(1, email);
            ResultSet result = prep.executeQuery();  
            
            while(result.next()) {
            	String firstName = result.getString("firstName");
            	String lastName = result.getString("lastName");
            	String user_email = result.getString("email");
            	String phone = result.getString("phone");
            	String password = result.getString("upassword");
            	String userType = result.getString("userType");
            	
            	currentUser = new User(firstName, lastName, user_email, phone, password, userType);
            } 
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        
    	return currentUser;
    	
	}
	
}