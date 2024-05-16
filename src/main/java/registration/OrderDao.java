package registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
	
	public boolean insertOrder(Order order) {
	    try {
	        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false", "root", "LocalHost1.");
	        
	        PreparedStatement orderStatement = connect.prepareStatement("INSERT INTO orders (order_id, user_id, product_id, order_date, quantity) VALUES (?, ?, ?, NOW(), ?)");
	        orderStatement.setInt(1, order.getOrderId());
	        orderStatement.setInt(2, order.getUserId());
	        orderStatement.setInt(3, order.getProductId());
	        orderStatement.setInt(4, order.getQuantity());

	        int rowsAffected = orderStatement.executeUpdate();
	        
	        return rowsAffected > 0; // If rowsAffected > 0, insertion was successful
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // If any exception occurs, return false
	    }
	}


    
    public List<Order> userOrders(Connection connection, ArrayList<Cart> cartList) {
        List<Order> orders = new ArrayList<>();
        try {
        	String getProductQuery = "SELECT * FROM orders WHERE user_id=? order by orders.order_id desc";
	        PreparedStatement prep = connection.prepareStatement(getProductQuery);
            
            for (Cart cart : cartList) {
	            int productId = cart.getProductId();
	            prep.setInt(1, productId);

	            ResultSet result = prep.executeQuery();

            // Iterate through the result set and construct Order objects
	            while (result.next()) {
	                Order order = new Order();
	                order.setOrderId(result.getInt("order_id"));
	                order.setProductId(result.getInt("product_id"));
	                order.setDate(result.getString("order_date"));
	                Product product = new Product();
	                order.setProductName(product.getProductName());
	                order.setCategory(product.getCategory());
	                order.setPrice(product.getPrice()*result.getInt("quantity"));
	                order.setQuantity(result.getInt("quantity"));
	                order.setDate(result.getString("order_date"));
	                orders.add(order);
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public void cancelOrder(int orderId) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false", "root", "LocalHost1.");
            
            // Delete the order from the database
            PreparedStatement statement = connect.prepareStatement("DELETE FROM orders WHERE order_id = ?");
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
