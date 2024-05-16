package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {
        
        	
        	HttpSession session = request.getSession();
            
            // Retrieve the cart items from the session
            ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

            if(cartList != null) {
                for(Cart cartItem : cartList) {
                    Order order = new Order();
        	        order.setProductId(cartItem.getProductId());
        	        order.setProductName(cartItem.getProductName());
        	        order.setCategory(cartItem.getCategory());
        	        order.setQuantity(cartItem.getQuantity());
        	        order.setPrice(cartItem.getPrice());
                    
                    OrderDao orderDao = new OrderDao();
                  
                    boolean result = orderDao.insertOrder(order);
                    
                    if(!result) {
                        // If insertion fails, handle the error (you can log it or display a message)
                        break; // Exit the loop if insertion fails for any item
                    }
                }
                
                cartList.clear(); // Clear the shopping cart after all items have been successfully ordered
                response.sendRedirect("order.jsp"); // Redirect to the "orders.jsp" page after successful order placement
            } else {
                response.sendRedirect("cart.jsp"); // Redirect to the "cart.jsp" page if the shopping cart is empty
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}
