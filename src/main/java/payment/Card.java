package payment;

public class Card {
    private int id;
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;
    private String cvv;

    // Constructors, Getters, and Setters
    public Card(int id, String cardNumber, String expirationDate, String cardHolderName, String cvv) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv; // Initialize the cvv field
    }

    public int getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }
    
    public String getCvv() {
        return cvv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
