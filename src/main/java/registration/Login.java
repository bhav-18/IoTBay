package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
//		server-side validation
		if(email == null || email.equals("")) {
			response.sendRedirect("index.jsp?loginFailed=true");
		}
		else if (password == null || password.equals("")) {
			response.sendRedirect("index.jsp?loginFailed=true");
		}
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
			PreparedStatement prep = connect.prepareStatement("select * from users where email = ? and upassword = ?");
			prep.setString(1, email);
			prep.setString(2, password);
			
			ResultSet result = prep.executeQuery();
			if(result.next()) {
				logUserAccess(email);
				session.setAttribute("name", result.getString("email"));
				response.sendRedirect("landing.jsp");
			}else {
				response.sendRedirect("index.jsp?loginFailed=true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    // Method to log user access
    private void logUserAccess(String email) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false", "root", "LocalHost1.");
            PreparedStatement prep = connect.prepareStatement("INSERT INTO user_access_logs (user_email, login_datetime) VALUES (?, ?)");
            prep.setString(1, email);
            prep.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            prep.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	

}
