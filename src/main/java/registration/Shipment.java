package registration;

import java.io.Serializable;
import java.time.LocalDate;

public class Shipment implements Serializable{
	private String address;
	private LocalDate date;
	private User user;
	private Order order;
	
	public Shipment() {}
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
    public void setOrder(Order order) {
    	this.order = order;
    }

    public Order getOrder() {
        return order;
    }
    
}