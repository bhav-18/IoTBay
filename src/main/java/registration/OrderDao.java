package registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class OrderDao {

	public boolean insertOrder(Order order) throws Exception {
	    try {
	        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false", "root", "LocalHost1.");
	        
	        // Insert into orders table
	        PreparedStatement orderStatement = connect.prepareStatement("INSERT INTO orders (user_id, total_price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
	        orderStatement.setInt(1, order.getUser().getId());
	        orderStatement.setDouble(2, calculateTotalPrice(order.getProducts()));
	        orderStatement.executeUpdate();
	        
	        // Retrieve the generated order_id
	        ResultSet generatedKeys = orderStatement.getGeneratedKeys();
	        int orderId = -1;
	        if (generatedKeys.next()) {
	            orderId = generatedKeys.getInt(1);
	        }
	        
	        // Insert into order_details table
	        if (orderId != -1) {
	            PreparedStatement orderDetailStatement = connect.prepareStatement("INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)");
	            for (Product product : order.getProducts()) {
	                orderDetailStatement.setInt(1, orderId);
	                orderDetailStatement.setInt(2, product.getProductId());
	                orderDetailStatement.setInt(3, 1); // Assuming quantity is always 1 for simplicity
	                orderDetailStatement.setDouble(4, product.getPrice());
	                orderDetailStatement.executeUpdate();
	            }
	        }
	        
	        connect.close();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}

    // Helper method to calculate total price of products in the order
    private double calculateTotalPrice(List<Product> products) {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}