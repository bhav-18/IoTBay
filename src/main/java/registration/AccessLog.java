package registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccessLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int logId;
	private LocalDateTime loginDateTime;
	private LocalDateTime logoutDateTime;
//	private User user;
	private String email;
	
	public AccessLog(){}
	
	public AccessLog(int logId, String email, LocalDateTime loginDateTime, LocalDateTime logoutDateTime) {
		this.logId = logId;
        this.email = email;
        this.loginDateTime = loginDateTime;
        this.logoutDateTime = logoutDateTime;		
	}
	
	public void setLogId(int logId) {
		this.logId = logId;
	}
	
	public int getLogId(){
		return logId;
	}
	
	public void setLoginDateTime(LocalDateTime loginDateTime) {
		this.loginDateTime = loginDateTime;
	}
	
	public LocalDateTime getLoginDateTime() {
		return loginDateTime;
	}
	
	public void setLogoutDateTime(LocalDateTime logoutDateTime) {
		this.logoutDateTime = logoutDateTime;
	}
	
	public LocalDateTime getLogoutDateTime() {
		return logoutDateTime;
	}
	
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
    public List<AccessLog> getAllAccessLogs(String email) {
    	List<AccessLog> accessLogs = new ArrayList<AccessLog>();
    	
    	try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
/*            String sql = "SELECT * FROM user_access_logs WHERE user_email = ?";*/
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM user_access_logs WHERE user_email = ?");
            prep.setString(1, email);
            ResultSet result = prep.executeQuery();    	
            
            while (result.next()) {
            	int logId = result.getInt("log_id");
                String userEmail = result.getString("user_email");
                String loginDateTimeStr = result.getString("login_datetime");
                String logoutDateTimeStr = result.getString("logout_datetime");
                
                // Parse String values to LocalDateTime
                LocalDateTime loginDateTime = LocalDateTime.parse(loginDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime logoutDateTime = LocalDateTime.parse(logoutDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                
				/*
				 * System.out.println("Logs:" + logId); System.out.println("Logs:" + userEmail);
				 * System.out.println("Logs:" + loginDateTime); System.out.println("Logs:" +
				 * logoutDateTime);
				 */
                
				 AccessLog accessLog = new AccessLog(logId, userEmail, loginDateTime, logoutDateTime); 
				 accessLogs.add(accessLog);  
            }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return accessLogs;
    }
}