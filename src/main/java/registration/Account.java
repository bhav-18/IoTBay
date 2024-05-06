package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/*import java.sql.ResultSet;*/
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {     
    	String action = request.getParameter("action");
    	String email = request.getParameter("email");
    	
    	if ("update".equals(action)) {
	        String firstName = request.getParameter("fname");
	        String lastName = request.getParameter("lname");
	        String phone = request.getParameter("phone");
	        String password = request.getParameter("password");  
	
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
	            PreparedStatement prep = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, upassword = ?, phone = ? WHERE email = ?");
	            
	            prep.setString(1, firstName);
	            prep.setString(2, lastName);
	            prep.setString(3, password);
	            prep.setString(4, phone);
	            prep.setString(5, email);
	            
	            int rowCount = prep.executeUpdate();
				if(rowCount > 0) {
					response.sendRedirect("account.jsp?updateSuccess=true");
				} else {
					response.sendRedirect("acccount.jsp?updateFailed=true");
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
				e.printStackTrace();
			} 
    	}
    	
    	else if ("delete".equals(action)) {
    		try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");    		
	        	PreparedStatement prep = connection.prepareStatement("DELETE FROM users WHERE email = ?");
	        	prep.setString(1, email);
	        	
	            int rowCount = prep.executeUpdate();
				if(rowCount > 0) {
					response.sendRedirect("account.jsp?deleteSuccess=true");
				} else {
					response.sendRedirect("account.jsp?deleteFailed=true");
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
				e.printStackTrace();
			}	        	
    	}
    }
}