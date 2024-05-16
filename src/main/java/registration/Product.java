package registration;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Product implements Serializable{
	private int product_id;
	private String productName;
	private String productCategory;
	private double productPrice;
	private int productStock;
	private String productDesc;
	private String productImage;

	public Product(){}
	
	public Product(String productName, String productCategory, double productPrice, int productStock, String productDesc, String productImage) {
		this.productName = productName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productDesc = productDesc;
		this.productImage = productImage;
	}
	
	public void setProductId(int product_id) {
		this.product_id = product_id;
	}
	
	public int getProductId() {
		return product_id;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	
	public String getCategory() {
		return productCategory;
	}
	
	public void setPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	public double getPrice() {
		return productPrice;
	}
	
	public void setDescription(String productDesc) {
		this.productDesc = productDesc;
	}
	
	public String getDescription() {
		return productDesc;
	}


	public void setStock(int productStock) {
		this.productStock = productStock;
	}
	
	public int getStock() {
		return productStock;
	}
	
	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
	/* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","Ks180507");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
			PreparedStatement prep = connect.prepareStatement("select * from products");
			ResultSet result = prep.executeQuery();
			
			while(result.next()) {
				Product row = new Product();
				row.setProductId(result.getInt("product_id"));
				row.setProductName(result.getString("productName"));
				row.setPrice(result.getDouble("productPrice"));
				row.setCategory(result.getString("productCategory"));
				row.setStock(result.getInt("productStock"));
				row.setDescription(result.getString("productDesc"));
				row.setProductImage(result.getString("productImage"));
				
				products.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return products;
		
	}
	
	public Product getCurrentProduct(int product_id) {
		Product currentProduct = new Product();
    	try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
/*            String sql = "SELECT * FROM user_access_logs WHERE user_email = ?";*/
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?");
            prep.setInt(1, product_id);
            ResultSet result = prep.executeQuery();  
            
            while(result.next()) {
            	String product_name = result.getString("productName");
            	Double productPrice = result.getDouble("productPrice");
            	String productCategory = result.getString("productCategory");
            	int productStock = result.getInt("productStock");
            	String productDesc = result.getString("productDesc");
            	String productImage = result.getString("productImage");
            	
            	currentProduct = new Product(product_name, productCategory, productPrice,  productStock, productDesc, productImage);
            } 
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        
    	return currentProduct;
    	
	}
	
	
	public List<Cart> getCartProducts(Connection connection, ArrayList<Cart> cartList) {
	    List<Cart> cartItems = new ArrayList<>();

	    try {
	        String getProductQuery = "SELECT * FROM products WHERE product_id = ?";
	        PreparedStatement prep = connection.prepareStatement(getProductQuery);

	        for (Cart cart : cartList) {
	            int productId = cart.getProductId();
	            prep.setInt(1, productId);

	            ResultSet result = prep.executeQuery();

	            while (result.next()) {
	                Cart row = new Cart();
	                row.setProductId(productId);
	                row.setProductName(result.getString("productName"));
	                row.setPrice(result.getDouble("productPrice"));
	                row.setCategory(result.getString("productCategory"));
	                row.setStock(result.getInt("productStock"));
	                row.setDescription(result.getString("productDesc"));
	                row.setProductImage(result.getString("productImage"));

	                // Calculate total price based on quantity
	                double totalPrice = result.getDouble("productPrice") * cart.getQuantity();
	                row.setPrice(totalPrice);
	                row.setQuantity(cart.getQuantity());

	                cartItems.add(row);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }

	    return cartItems;
	}
	
	
	public double getTotalCartPrice(Connection connection, ArrayList<Cart> cartList) {
		
        double sum = 0;
        
        try {
	        String getProductQuery = "SELECT * FROM products WHERE product_id = ?";
	        PreparedStatement prep = connection.prepareStatement(getProductQuery);

	        for (Cart cart : cartList) {
	            int productId = cart.getProductId();
	            prep.setInt(1, productId);

	            ResultSet result = prep.executeQuery();

	            while (result.next()) {
	                sum+= result.getDouble("productPrice") * cart.getQuantity();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }

	    return sum;
	}
	
	public Product getSingleProduct(int product_id) {
		Product currentProduct = null;
    	try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?");
            prep.setInt(1, product_id);
            ResultSet result = prep.executeQuery();  
            
            while(result.next()) {
            	String product_name = result.getString("productName");
            	Double productPrice = result.getDouble("productPrice");
            	String productCategory = result.getString("productCategory");
            	int productStock = result.getInt("productStock");
            	String productDesc = result.getString("productDesc");
            	String productImage = result.getString("productImage");
            	
            	currentProduct = new Product(product_name, productCategory, productPrice,  productStock, productDesc, productImage);
            } 
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        
    	return currentProduct;
    	
	}
	
    
}