package registration;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{
	private int orderId;
	private LocalDate date;
	private User user;
    private List<Product> products;

    public Order() {
        products = new ArrayList<>();
    }
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getOrderId() {
		return orderId;
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
    
    
    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}