<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="payment.Payment" %>
<%@ page import="payment.PaymentDAO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IoTBay | Payment History</title>

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
    <link rel="stylesheet" type="text/css" href="css/styleHistory.css">
</head>
<body>
	<!--================= NAVBAR ======================-->
    <nav>
        <div class="container nav__container">
            <h3><a href="landing.jsp">IoTBay</a></h3>
            <ul class="nav__menu">
                <li><a href="landing.jsp">Home</a></li>
                <li><a href="landing.jsp">About</a></li>
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
    	<div class="header">
        	<h1>Payment History</h1>
    	</div>

    <div class="search-box">
        <form action="history.jsp" method="GET">
            <label for="orderDate">Order Date:</label>
            <input type="date" id="orderDate" name="orderDate">

            <label for="paymentId">Payment ID:</label>
            <input type="text" id="paymentId" name="paymentId">

            <button type="submit">Search</button>
        </form>
    </div>

    <% 
        String orderDateString = request.getParameter("orderDate");
        String paymentId = request.getParameter("paymentId");
        PaymentDAO paymentDAO = new PaymentDAO();
        List<Payment> payments = new ArrayList<>();

        if (orderDateString != null && !orderDateString.isEmpty()) {
            LocalDate orderDate = LocalDate.parse(orderDateString);
            payments = paymentDAO.searchPayments(orderDate, paymentId);
        }
    %>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Amount</th>
                <th>Payment Method</th>
                <th>Details</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <% for (Payment payment : payments) { %>
            <tr>
                <td><%= payment.getId() %></td>
                <td><%= payment.getDateTime() %></td>
                <td><%= payment.getAmount() %></td>
                <td><%= payment.getPayment() %></td>
                <td><%= payment.getDetails() %></td>
                <td><%= payment.getStatus() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
