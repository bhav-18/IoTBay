package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.regex.*;
import java.util.Random;


/**
 * Servlet implementation class RegistraionServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String repass = request.getParameter("re_pass");
		String verificationCode = generateVerificationCode();
		
		HttpSession session = request.getSession();
		
		// Password validation regex pattern
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=.])(?=\\S+$).{8,20}$";

		Pattern passwordPattern = Pattern.compile(regex);
		Matcher pwdMatcher = passwordPattern.matcher(password);
		
		
		Connection connect = null;
		RequestDispatcher dispatcher = null;
		
//		server-side validation
		if(fname == null || fname.equals("")) {
			response.sendRedirect("registration.jsp?regFailed=true");
		}
		else if(lname == null || lname.equals("")) {
			response.sendRedirect("registration.jsp?regFailed=true");
		}
		else if(email == null || email.equals("")) {
			response.sendRedirect("registration.jsp?regFailed=true");
		}
		else if (emailExists(email)) {
            response.sendRedirect("registration.jsp?emailExists=true");
        }		
		else if(phone == null || phone.equals("") || phone.length() != 10) {
			response.sendRedirect("registration.jsp?invalidPhone=true");
		}
		else if(password == null || password.equals("")) {
			response.sendRedirect("registration.jsp?regFailed=true");
		}
		else if(pwdMatcher.matches() == false) {
            response.sendRedirect("registration.jsp?invalidPassword=true");
		}
		else if(!password.equals(repass)) {
			response.sendRedirect("registration.jsp?passNoMatch=true");
		}		
		else {	
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
				PreparedStatement prep = connect.prepareStatement("insert into users(firstName,lastName,email,upassword,phone, verificationCode) values(?,?,?,?,?, ?)");
				prep.setString(1,fname);
				prep.setString(2,lname);
				prep.setString(3,email);
				prep.setString(4,password);
				prep.setString(5,phone);
				prep.setString(6, verificationCode);
				
				int rowCount = prep.executeUpdate();
//				dispatcher = request.getRequestDispatcher("registration.jsp");
				if(rowCount > 0) {
//					response.sendRedirect("registration.jsp?regSuccess=true");
					session.setAttribute("email", email);
					response.sendRedirect("verification.jsp");
				} else {
//					request.setAttribute("status", "failed");
					response.sendRedirect("registration.jsp?regFailed=true");
				}
				
//				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
    // Method to check if the email already exists in the database
    private boolean emailExists(String email) {
        Connection connectEmail = null;
        PreparedStatement prepEmail = null;
        ResultSet resultSet = null;
        boolean exists = false;
        
        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectEmail = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
            
            // Prepare and execute query to check if email exists
            String query = "SELECT * FROM users WHERE email = ?";
            prepEmail = connectEmail.prepareStatement(query);
            prepEmail.setString(1, email);
            resultSet = prepEmail.executeQuery();
            
            // If a row is returned, email exists
            if (resultSet.next()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (prepEmail != null) {
                	prepEmail.close();
                }
                if (connectEmail != null) {
                	connectEmail.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return exists;
    }
    
    // Method to generate 6-digit verification code
    private String generateVerificationCode() {
    	int codeLength = 6;
    	String allowed = "0123456789";
    	
    	StringBuilder codeBuilder = new StringBuilder();
    	Random random = new Random();
    	
        // Generate the verification code
        for (int i = 0; i < codeLength; i++) {
            int randomIndex = random.nextInt(allowed.length());
            codeBuilder.append(allowed.charAt(randomIndex));
        }
        
        return codeBuilder.toString();
    }
   
}
