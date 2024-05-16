package registration;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registration.*;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            
            if (cartList != null) {
                OrderDao orderDao = new OrderDao();
                for (Cart cart : cartList) {
                    Order order = new Order();
                    
                    // Add product to order
                    Product product = new Product();
                    product.setProductId(cart.getProductId()); // Assuming cart.getId() corresponds to product_id
                    product.setStock(cart.getQuantity()); // Assuming cart.getQuantity() returns the quantity of the product in the cart
                    order.addProduct(product);
                    
                    
                    User user = new User();
                    // Retrieve user email from session
                    String userEmail = (String) request.getSession().getAttribute("email");
                    
                    // Set email for the order
                    user.setEmail(userEmail);
                    
                    // Set order date
                    order.setDate(LocalDate.now());
                    
                    // Insert order into database
                    try {
                        boolean result = orderDao.insertOrder(order);
                        if (!result)
                            break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                cartList.clear();
                response.sendRedirect("order.jsp");
            } else {
                response.sendRedirect("cart.jsp");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}