package registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("name");
		
		logUserLogout(userEmail);
		
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
	
    // Method to log user logout
    private void logUserLogout(String userEmail) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false", "root", "LocalHost1.");
            PreparedStatement prep = connect.prepareStatement("UPDATE user_access_logs SET logout_datetime = ? WHERE user_email = ? AND logout_datetime IS NULL");
            prep.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            prep.setString(2, userEmail);
            prep.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
