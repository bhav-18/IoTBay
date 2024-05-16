package payment;

public class Card {
    private int cardId;
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String expDate = expMonth + "/" + expYear;
    private String cardHolderName;
    private String cvv;

    // Existing constructor
    public Card(int cardId, String cardNumber, String expirationDate, String cardHolderName, String cvv) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.expDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }

    // Getters and setters remain the same
    public int getCardId() {
        return cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
