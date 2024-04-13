package registration;

import java.io.Serializable;
import java.time.*;

public class AccessLog implements Serializable{
	private int logId;
	private LocalDateTime loginDateTime;
	private LocalDateTime logoutDateTime;
	private User user;
	
	public AccessLog() {}
	
	public void setLogId(int logId) {
		this.logId = logId;
	}
	
	public int getLogId(){
		return logId;
	}
	
	public void setLoginDateTime(LocalDateTime loginDateTime) {
		this.loginDateTime = loginDateTime;
	}
	
	public LocalDateTime loginDateTime() {
		return loginDateTime;
	}
	
	public void setLogoutDateTime(LocalDateTime logoutDateTime) {
		this.logoutDateTime = logoutDateTime;
	}
	
	public LocalDateTime logoutDateTime() {
		return logoutDateTime;
	}
	
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
	
}