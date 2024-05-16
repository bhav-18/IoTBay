package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*import java.sql.ResultSet;*/
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/createProduct")
public class createProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {     
    	
    	String product_name = request.getParameter("productName");
    	String productPriceString = request.getParameter("price");
    	String productCategory = request.getParameter("category");
    	String productStockString = request.getParameter("stock");
    	String productDesc = request.getParameter("description");
    	String productImage = request.getParameter("image"); 
    	
//    	int productStock = Integer.parseInt(productStockString);
//    	double productPrice = Double.parseDouble(productPriceString);
    
        
        if (product_name == null || product_name.isEmpty() ||
                productPriceString == null || productPriceString.isEmpty() ||
                productCategory == null || productCategory.isEmpty() ||
                productStockString == null || productStockString.isEmpty() ||
                productDesc == null || productDesc.isEmpty() ||
                productImage == null || productImage.isEmpty()) {
                response.sendRedirect("createProduct.jsp?missingValues=true");
        }
        else if (!productPriceString.matches("[0-9.]+") || !productStockString.matches("[0-9]+")) {
        	response.sendRedirect("createProduct.jsp?invalidValues=true");
        }
        else if(productExists(product_name)) {
        	response.sendRedirect("createProduct.jsp?productExists=true");
        }
        else {  
	        try {
	        	int productStock = Integer.parseInt(productStockString);
	        	double productPrice = Double.parseDouble(productPriceString);
	        	
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
	            PreparedStatement prep = connection.prepareStatement("insert into products(productName, productPrice, productCategory, productStock, productDesc, productImage) values(?,?,?,?,?,?)");
	            
	            prep.setString(1, product_name);
	            prep.setDouble(2, productPrice);
	            prep.setString(3, productCategory);
	            prep.setInt(4, productStock);
	            prep.setString(5, productDesc);
	            prep.setString(6, productImage);
	            
	            int rowCount = prep.executeUpdate();
				if(rowCount > 0) {
					response.sendRedirect("product.jsp?createSuccess=true");
				} else {
					response.sendRedirect("product.jsp?createFailed=true");
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
				e.printStackTrace();
			} 
        }
    }
    
 // Method to check if the product already exists in the database
    public boolean productExists(String productName) {
        Connection connectProduct = null;
        PreparedStatement prepProduct = null;
        ResultSet resultSet = null;
        boolean exists = false;
        
        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectProduct = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
            
            // Prepare and execute query to check if product exists
            String query = "SELECT * FROM products WHERE productName = ?";
            prepProduct = connectProduct.prepareStatement(query);
            prepProduct.setString(1, productName);
            resultSet = prepProduct.executeQuery();
            
            // If a row is returned, product exists
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
                if (prepProduct != null) {
                	prepProduct.close();
                }
                if (connectProduct != null) {
                	connectProduct.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return exists;
    }
}