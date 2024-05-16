package payment;

public class PaymentHistoryUtils {
    public static boolean validateCardNumber(String cardNumber) {
        // Implement validation logic (Luhn algorithm, etc.)
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    public static boolean validateDate(String date) {
        // Simple date validation
        return date != null && date.matches("(0[1-9]|1[0-2])/([0-9]{2})");
    }
}
