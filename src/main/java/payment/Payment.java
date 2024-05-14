package payment;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Payment {
    private int id;
    private LocalDateTime dateTime;
    private double amount;
    private String details;   
    private String customerName;
    private String status;

    // Constructor (optional)
    public Payment() {
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
    
    public String getDetails() {
        return details;
    }

    public String getCustomerName() {
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
    
    public void setDetails(String details) {
        this.details = details;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

