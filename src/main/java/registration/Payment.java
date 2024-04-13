package registration;

import java.io.Serializable;
import java.time.LocalDate;

public class Payment implements Serializable{
	private int payId;
	private String cardNumber;
	private double amount;
	private LocalDate date;
	private User user;
	
	public Payment() {}
	
	public void setPayID(int payId) {
		this.payId = payId;
	}
	
	public int getPayId() {
		return payId;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double amount() {
		return amount;
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
}