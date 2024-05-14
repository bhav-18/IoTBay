package payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CardService {
    private static List<Card> cards = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(0); // For ID generation

    static {
        // Dummy data
        cards.add(new Card(idGenerator.incrementAndGet(), "1234-5678-9012-3456", "12/26", "John Doe", "1234"));
        cards.add(new Card(idGenerator.incrementAndGet(), "9876-5432-1098-7654", "09/24", "Jane Doe", "9876"));
    }

    public static int getNextId() {
        return idGenerator.incrementAndGet();
    }

    public static List<Card> getAllCards() {
        return cards;
    }

    public static Card getCardById(int id) {
        return cards.stream().filter(card -> card.getId() == id).findFirst().orElse(null);
    }

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
            		"jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","Hailey5149!");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Unable to connect to database.", e);
        }
    }

    public static void addCard(Card card) {
        String sql = "INSERT INTO Cards (cardNumber, expirationDate, cardHolderName, cvv) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, card.getCardNumber());
            pstmt.setString(2, card.getExpirationDate());
            pstmt.setString(3, card.getCardHolderName());
            pstmt.setString(4, card.getCvv());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateCard(Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getId() == card.getId()) {
                cards.set(i, card);
                return;
            }
        }
    }

    public static void deleteCard(int id) {
        cards.removeIf(card -> card.getId() == id);
    }
}
