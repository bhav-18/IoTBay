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

import java.util.regex.*;

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
		
		// Password validation regex pattern
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=.])(?=\\S+$).{8,20}$";

		Pattern passwordPattern = Pattern.compile(regex);
		Matcher pwdMatcher = passwordPattern.matcher(password);
		
		
		Connection connect = null;
		RequestDispatcher dispatcher = null;
		
//		server-side validation
		if(fname == null || fname.equals("")) {
			request.setAttribute("status", "emptyFname");
			dispatcher = request.getRequestDispatcher("registration.jsp");	
			dispatcher.forward(request,response);
		}
		else if(lname == null || lname.equals("")) {
			request.setAttribute("status", "emptyLname");
			dispatcher = request.getRequestDispatcher("registration.jsp");	
			dispatcher.forward(request,response);
		}
		else if(email == null || email.equals("")) {
			request.setAttribute("status", "emptyEmail");
			dispatcher = request.getRequestDispatcher("registration.jsp");	
			dispatcher.forward(request,response);
		}
		else if (emailExists(email)) {
            request.setAttribute("status", "emailExists");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }		
		else if(phone == null || phone.equals("") || phone.length() != 10) {
			request.setAttribute("status", "invalidPhone");
			dispatcher = request.getRequestDispatcher("registration.jsp");	
			dispatcher.forward(request,response);
		}
		else if(password == null || password.equals("")) {
			request.setAttribute("status", "emptyPassword");
			dispatcher = request.getRequestDispatcher("registration.jsp");	
			dispatcher.forward(request,response);
		}
		else if(pwdMatcher.matches() == false) {
            request.setAttribute("status", "invalidPassword");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);			
		}
		else if(!password.equals(repass)) {
			request.setAttribute("status", "passNoMatch");
			dispatcher = request.getRequestDispatcher("registration.jsp");	
			dispatcher.forward(request,response);
		}		
		else {	
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","Ds180507.");
				PreparedStatement prep = connect.prepareStatement("insert into users(firstName,lastName,email,upassword,phone) values(?,?,?,?,?)");
				prep.setString(1,fname);
				prep.setString(2,lname);
				prep.setString(3,email);
				prep.setString(4,password);
				prep.setString(5,phone);
				
				int rowCount = prep.executeUpdate();
				dispatcher = request.getRequestDispatcher("registration.jsp");
				if(rowCount > 0) {
					request.setAttribute("status", "success");
				} else {
					request.setAttribute("status", "failed");
				}
				
				dispatcher.forward(request, response);
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
            connectEmail = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","Ds180507.");
            
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


}
