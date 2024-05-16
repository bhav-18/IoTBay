<%@ page import="java.util.*" %>
<%@ page import="registration.User" %>
<%@ page import="java.util.*, registration.*, java.sql.Connection, java.sql.DriverManager"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.sql.SQLException"%>

<%
DecimalFormat dcf = new DecimalFormat("0.00");
request.setAttribute("dcf", dcf);

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
Connection connect = null;
try {
	// Load the MySQL JDBC driver and establish connection
    Class.forName("com.mysql.jdbc.Driver");
    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
	
    if (cart_list != null) {
    	Product productDAO = new Product();
    	cartProduct = productDAO.getCartProducts(connect, cart_list);
    	double total = productDAO.getTotalCartPrice(connect, cart_list);
    	request.setAttribute("total", total);
    	request.setAttribute("cart_list", cart_list);
    }
} catch (ClassNotFoundException | SQLException e) {
    // Handle exceptions
    e.printStackTrace();
}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IoTBay | Cart Page</title>
	
    <!-- Include the Google Font (Poppins) -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap">

    <!-- Iconscout CDN -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">

    <!-- css.gg CDN -->
    <link href='https://unpkg.com/css.gg@2.0.0/icons/css/menu.css' rel='stylesheet'>

    <!-- SWIPER JS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>

    <link rel="stylesheet" href="./css/styles.css">

    <link rel="icon" type="image/x-icon" href="assets/iot-bay.ico"/>
    
    <!-- Include Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    
    <style type="text/css">

	.cart_container {
	  margin-top: 5rem;
	}
	
	.table tbody td{
	vertical-align: middle;
	}
	
	.add_button, .minus_button {
	box-shadow: none;
	font-size: 25px;
	}
	
	
	</style>
</head>
<body>
    <!--================= NAVBAR ======================-->
    <nav>
        <div class="container nav__container">
            <h3><a href="landing.jsp">IoTBay</a></h3>
            <ul class="nav__menu">
                <li><a href="landing.jsp">Home</a></li>
                <li><a href="landing.jsp">About</a></li>
                <li><a href="product.jsp">Products</a></li>
                <li><a href="cart.jsp">Cart<span class="badge badge-danger">${cart_list.size()}</span></a></li>
                <% if (session.getAttribute("name") != null) { %>
                    <li><a href="order.jsp">Orders</a></li>
                <% } %>
                <li><a href="logout">Logout</a></li>      	
	            <% if (session.getAttribute("name") != null) { %>
					<li><a href="account.jsp"><%= session.getAttribute("name") %></a></li>
	            <% } else { %>
	                <li><small>Guest</small></li>
	            <% } %>               	
            </ul>
            <button id="open-menu-btn"><i class="uil uil-bars"></i></button>
            <button id="close-menu-btn"><i class="uil uil-times"></i></button>
        </div>
    </nav>
    <!--================= END NAVBAR ======================-->
    
    <!-- Main content -->
    <div class="cart_container">
       		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		 <div class="d-flex py-3">
            <h3>Total Price: $ ${(total > 0) ? dcf.format(total) : "0.00"}</h3> 
            <a class="mx-3 btn btn-primary" href="checkout">Check Out</a>
        </div>
    </div>
    <!-- Footer content -->
</body>
</html>
