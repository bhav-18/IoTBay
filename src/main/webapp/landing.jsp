<%@ page import="registration.User" %>
<%@ page import="java.util.*, registration.*, java.sql.Connection, java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>


<%
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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IoTBay | All Things Internet</title>

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

    <!--================= HEADER ======================-->
    <header>
        <div class="container header__container">
            <div class="header__left">
                <h1>Welcome to IoTBay: Your Premier Destination for IoT Devices</h1>
                <p>
                    At IoTBay, we're dedicated to bringing the future to your finger tips. As a small yet dynamic company nestled in the heart of Sydney, Australia, we're passionate about revolutionizing the way you interact with the Internet of Things (IoT). Our mission is simple: to provide you with a seamless online platform where you can explore, discover, and purchase cutting-edge IoT devices. 
                </p>
                <a href="landing.jsp" class="btn btn-primary">Get Started</a>
            </div>

            <div class="header__right">
                <div class="header__right-image">
                    <img src="assets/iotbay-image.svg">
                </div>
            </div>
        </div>
    </header>
    <!--================= END HEADER ======================-->