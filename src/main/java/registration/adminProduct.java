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


@WebServlet("/adminProduct")
public class adminProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {     

    	String productIdString = request.getParameter("productId");
    	int productId = Integer.parseInt(productIdString);
    	
        String deleteButton = request.getParameter("deleteProduct");
        String updateButton = request.getParameter("updateProduct");    	
    	
        if (updateButton != null) {
        	String product_name = request.getParameter("productName");
        	String productPriceString = request.getParameter("price");
        	String productCategory = request.getParameter("category");
        	String productStockString = request.getParameter("stock");
        	String productDesc = request.getParameter("description");
        	String productImage = request.getParameter("image"); 
        
            if (product_name == null || product_name.isEmpty() ||
                    productPriceString == null || productPriceString.isEmpty() ||
                    productCategory == null || productCategory.isEmpty() ||
                    productStockString == null || productStockString.isEmpty() ||
                    productDesc == null || productDesc.isEmpty() ||
                    productImage == null || productImage.isEmpty()) {
            		response.sendRedirect("adminProduct.jsp?productId=" + productId + "&missingValues=true");
            }
            else if (!productPriceString.matches("[0-9.]+") || !productStockString.matches("[0-9]+")) {
            		response.sendRedirect("adminProduct.jsp?productId=" + productId + "&invalidValues=true");
            }
            else {
		        try {	        	
		        	int productStock = Integer.parseInt(productStockString);
		        	double productPrice = Double.parseDouble(productPriceString);
		        	
		        	Class.forName("com.mysql.cj.jdbc.Driver");
		        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
		            PreparedStatement prep = connection.prepareStatement("UPDATE products SET productName = ?, productPrice = ?, productCategory = ?, productStock = ?, productDesc = ?, productImage = ? WHERE product_id = ?");
		            
		            prep.setString(1, product_name);
		            prep.setDouble(2, productPrice);
		            prep.setString(3, productCategory);
		            prep.setInt(4, productStock);
		            prep.setString(5, productDesc);
		            prep.setString(6, productImage);
		            prep.setInt(7, productId);
		            
		            int rowCount = prep.executeUpdate();
					if(rowCount > 0) {
	//					response.sendRedirect("product.jsp?updateSuccess=true");
						response.sendRedirect("adminProduct.jsp?productId=" + productId + "&updateSuccess=true");
					} else {
						response.sendRedirect("adminProduct.jsp?productId=" + productId + "&updateFailed=true");
					}
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (Exception e) {
					e.printStackTrace();
				} 
            }
        }
    	
    	 if (deleteButton != null) {
    		try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");    		
	        	
       	
	        	
	        	PreparedStatement prep = connection.prepareStatement("DELETE FROM products WHERE product_id = ?");
	        	prep.setInt(1, productId);
	        	
	            int rowCount = prep.executeUpdate();
				if(rowCount > 0) {
					response.sendRedirect("product.jsp?deleteSuccess=true");
				} else {
					response.sendRedirect("adminProduct.jsp?productId=" + productId + "&updateFailed=true");
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
				e.printStackTrace();
			}	        	
    	}
    }
}