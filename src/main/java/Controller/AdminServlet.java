package Controller;

import java.io.IOException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*import java.util.ArrayList;
import java.util.List;*/
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.User;
import DAO.UserDAO;

/**
 * Servlet implementation
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO user = new UserDAO();
		
		HttpSession session = request.getSession();
		
		List<User> users = user.getAllUsers();
		session.setAttribute("users", users);
		System.out.println(users.size());
		request.getRequestDispatcher("admin.jsp").include(request, response);
		String action = request.getParameter("action");

	}

}