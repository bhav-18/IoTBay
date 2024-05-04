package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verify")
public class Verification extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredVerificationCode = request.getParameter("vcode");
        String email = request.getParameter("email");     
        String storedVerificationCode = getStoredVerificationCode(email);
     
        if (enteredVerificationCode.equals(storedVerificationCode)) {
        	response.sendRedirect("verification.jsp?regSuccess=true");
        } else {
            response.sendRedirect("verification.jsp?verificationFailed=true");
        }
    }
    
    // Method to retrieve the stored verification code from the database
    private String getStoredVerificationCode(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String verificationCode = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","Ds180507.");
            
            String query = "SELECT verificationCode FROM users WHERE email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            
            // If a row is returned, retrieve the verification code
            if (resultSet.next()) {
                verificationCode = resultSet.getString("verificationCode");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return verificationCode;
    }
}