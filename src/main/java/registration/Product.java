package registration;

import java.io.Serializable;

public class Product implements Serializable{
	private int deviceId;
	private String deviceName;
	private String type;
	private double price;
	private int quantity;
	
	public Product(){}
	
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
	public int getDeviceId() {
		return deviceId;
	}
	
	public void setdeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
}