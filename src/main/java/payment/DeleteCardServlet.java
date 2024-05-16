package payment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCardServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardId = request.getParameter("cardId");
        CardService.deleteCard(Integer.parseInt(cardId));
        response.sendRedirect("payment.jsp"); // Redirect back to the payment page
    }
    
    public static void deleteCard(int cardId) throws ServletException, IOException  {
        String sql = "DELETE FROM Cards WHERE cardId = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","Hailey5149!");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace(); // More detailed error
			throw new ServletException("SQL Error: " + e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Error: " + e.getMessage(), e);
		}
    }

}