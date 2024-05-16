package payment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCardServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardIdStr = request.getParameter("cardId");
        String cardNumber = request.getParameter("cardNumber");
        String cardName = request.getParameter("cardName");
        String expDate = request.getParameter("expDate");
        String cvv = request.getParameter("cvv");

        int cardId = Integer.parseInt(cardIdStr);
        // Assume CardService is already defined to handle DB operations
        CardService.updateCard(cardId, cardNumber, cardName, expDate, cvv);
        
        response.sendRedirect("payment.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardIdStr = request.getParameter("cardId");
        try {
            int cardId = Integer.parseInt(cardIdStr);  // Convert string ID to integer
            Card card = CardService.getCardById(cardId);  // Fetch the card using the service

            if (card != null) {
                request.setAttribute("card", card);  // Set the card in the request attribute
                request.getRequestDispatcher("/updateCard.jsp").forward(request, response);  // Forward to JSP
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Card not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid card ID");
        }
    }
}
