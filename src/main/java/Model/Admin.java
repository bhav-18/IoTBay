package Model;


import java.io.Serializable;
/**
*
* @author William
*/


public class Admin implements Serializable{
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private String userType;
	
	public Admin(String firstName, String lastName, String email, String phoneNumber, String password, String userType) {
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
		return userType;
	}
	
	
}