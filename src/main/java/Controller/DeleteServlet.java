package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.User;
import DAO.UserDAO;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
    	UserDAO userDao = new UserDAO();
    	try {
    	if ("Delete".equalsIgnoreCase(action)) {
            deleteUser(request, userDao);
        }
        response.sendRedirect("admin.jsp"); // Refresh the page to show updated list
    } catch (Exception e) {
        e.printStackTrace(); // Log the full stack trace for debugging
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
    }
    }
private void deleteUser(HttpServletRequest request, UserDAO userDao) throws SQLException {
    int userId = Integer.parseInt(request.getParameter("id"));
    userDao.deleteUser(userId);
}

}
