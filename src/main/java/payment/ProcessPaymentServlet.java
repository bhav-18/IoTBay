package payment;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

public class ProcessPaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardNumber = request.getParameter("cardNumber");
        String totalAmount = request.getParameter("totalAmount");

        // Assume cardNumber is validated as part of card selection
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false", "root", "Hailey5149!");
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO payment_history (total_amount, card_number, status, payment_id) VALUES (?, ?, ?, ?)");
            pstmt.setBigDecimal(1, new BigDecimal(totalAmount));
            pstmt.setString(2, cardNumber);
            pstmt.setString(3, "Completed");
            pstmt.setInt(4, retrievePaymentIdForCard(conn, cardNumber)); // Assuming function to retrieve payment_id

            pstmt.executeUpdate();
            response.sendRedirect("history.jsp"); // Redirect to a history page or confirmation page
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Error: " + e.getMessage(), e);
		}
    }

    private int retrievePaymentIdForCard(Connection conn, String cardNumber) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT payment_id FROM payment_method WHERE card_number = ?")) {
            pstmt.setString(1, cardNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("payment_id");
            } else {
                throw new SQLException("No payment method found for card number: " + cardNumber);
            }
        }
    }
}
