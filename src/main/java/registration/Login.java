package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			request.setAttribute("status", "emptyEmail");
			dispatcher = request.getRequestDispatcher("login.jsp");	
			dispatcher.forward(request,response);
		}
		else if (password == null || password.equals("")) {
			request.setAttribute("status", "emptyPassword");
			dispatcher = request.getRequestDispatcher("login.jsp");		
			dispatcher.forward(request,response);
		}
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","Ds180507.");
			PreparedStatement prep = connect.prepareStatement("select * from users where email = ? and upassword = ?");
			prep.setString(1, email);
			prep.setString(2, password);
			
			ResultSet result = prep.executeQuery();
			if(result.next()) {
				session.setAttribute("name", result.getString("email"));
				dispatcher = request.getRequestDispatcher("index.jsp");
			}else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
