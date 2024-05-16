package payment;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PaymentHistory {
    private int id;
    private LocalDateTime dateTime;
    private double amount;  
    private String customerName;
    private String status;

    // Constructor (optional)
    public PaymentHistory() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public String getPayment() {
        return customerName;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void setPayment(String customerName) {
        this.customerName = customerName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

