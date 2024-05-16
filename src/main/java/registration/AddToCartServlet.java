package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServletSerlvet
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
        	ArrayList<Cart> cartList = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("productId"));
            Cart cm = new Cart();
            cm.setProductId(id);
            cm.setQuantity(1);

            HttpSession session = request.getSession();
            
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            
            if (cart_list == null) {
                
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("product.jsp");
                
            } else {
            	
            	cartList = cart_list;
            	            	
                boolean exist = false;
                
                for (Cart c : cart_list) {
                    if (c.getProductId() == id) {
                        exist = true;
                        response.sendRedirect("cart.jsp");
                        break;
                    }
                }
                if (!exist) {
                    cart_list.add(cm);
                    response.sendRedirect("product.jsp");
                }
            }
            
        }
    }
}

