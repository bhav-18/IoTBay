package payment;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveCardServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cardNumber = request.getParameter("cardNumber");
		String cardHolderName = request.getParameter("cardHolderName");
		String expirationDate = request.getParameter("expMonth" + "/" + "expYear");
		String cvv = request.getParameter("cvv");

		System.out.println("Received card info: " + cardNumber); // Debugging output

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","Hailey5149!");
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO payment_method (card_number, card_holder_name, expiration_date, cvv) VALUES (?, ?, ?, ?)");
			pstmt.setString(1, cardNumber);
			pstmt.setString(2, cardHolderName);
			pstmt.setString(3, expirationDate);
			pstmt.setString(4, cvv);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("Card saved successfully.");
				response.sendRedirect("payment.jsp"); // Redirect
			} else {
				System.out.println("Failed to save card.");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // More detailed error
			throw new ServletException("SQL Error: " + e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Error: " + e.getMessage(), e);
		}

		String errorMessage = validateCard(cardNumber, expirationDate, cardHolderName, cvv);
		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/addCard.jsp").forward(request, response);
		} else {
			Card newCard = new Card(CardService.getNextId(), cardNumber, expirationDate, cardHolderName, cvv);
			CardService.addCard(newCard);
			response.sendRedirect("payment.jsp");
		}
	}

	private String validateCard(String cardNumber, String expirationDate, String cardHolderName, String cvv) {
		if (cardNumber == null || cardNumber.isEmpty())
			return "Card Number is required.";
		if (expirationDate == null || expirationDate.isEmpty())
			return "Expiration Date is required.";
		if (cardHolderName == null || cardHolderName.isEmpty())
			return "Card Holder's Name is required.";
		if (cvv == null || cvv.isEmpty() || cvv.length() < 3 || cvv.length() > 4)
			return "Valid CVV is required.";
		return null;
	}
}