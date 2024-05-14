package payment;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SaveCardServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String cardNumber = request.getParameter("cardNumber");
	    String expirationDate = request.getParameter("expirationDate");
	    String cardHolderName = request.getParameter("cardHolderName");
	    String cvv = request.getParameter("cvv");

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
	    if (cardNumber == null || cardNumber.isEmpty()) return "Card Number is required.";
	    if (expirationDate == null || expirationDate.isEmpty()) return "Expiration Date is required.";
	    if (cardHolderName == null || cardHolderName.isEmpty()) return "Card Holder's Name is required.";
	    if (cvv == null || cvv.isEmpty() || cvv.length() < 3 || cvv.length() > 4) return "Valid CVV is required.";
	    return null;
	}
}