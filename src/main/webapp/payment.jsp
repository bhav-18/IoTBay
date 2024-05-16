<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="payment.Card"%>
<%@ page import="payment.CardService"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IoTBay | Payment Methods</title>

<!-- Include the Google Font (Poppins) -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap">

<!-- Iconscout CDN -->
<link rel="stylesheet"
	href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">

<!-- css.gg CDN -->
<link href='https://unpkg.com/css.gg@2.0.0/icons/css/menu.css'
	rel='stylesheet'>

<!-- SWIPER JS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />

<link rel="icon" type="image/x-icon" href="assets/iot-bay.ico" />
<link rel="stylesheet" href="css/styleCheckout.css">
<link rel="stylesheet" href="css/stylePayment.css">
</head>
<body>
	<!--================= NAVBAR ======================-->
	<nav>
		<div class="container nav__container">
			<h3>
				<a href="landing.jsp">IoTBay</a>
			</h3>
			<ul class="nav__menu">
				<li><a href="landing.jsp">Home</a></li>
				<li><a href="product.jsp">Products</a></li>
				<li><a href="landing.jsp">About</a></li>
				<li><a href="logout">Logout</a></li>
				<%
				if (session.getAttribute("name") != null) {
				%>
				<li><a href="account.jsp"><%=session.getAttribute("name")%></a></li>
				<%
				} else {
				%>
				<li><small>Guest</small></li>
				<%
				}
				%>
			</ul>
			<button id="open-menu-btn">
				<i class="uil uil-bars"></i>
			</button>
			<button id="close-menu-btn">
				<i class="uil uil-times"></i>
			</button>
		</div>
	</nav>
	<!--================= END NAVBAR ======================-->

	<!--================= HEADER ======================-->
	<header id="header">
		<!-- Heading -->
		<div class="bg-primary">
			<div class="container py-4">
				<!-- Breadcrumb -->
				<div class="d-flex">
					<h6 class="mb-0">
						<a href="landing.jsp" class="text-white-50">Home</a> <span
							class="text-white-50 mx-2"> > </span> <a href="cart.jsp"
							class="text-white-50">Shopping Cart</a> <span
							class="text-white-50 mx-2"> > </span> <a href="checkout.jsp"
							class="text-white-50">Order Info</a> <span
							class="text-white-50 mx-2"> > </span> <a href="payment.jsp"
							class="text-white">Payment</a>
					</h6>
				</div>
				<!-- Breadcrumb -->
			</div>
		</div>
		<!-- Heading -->
	</header>
	<!--================= END HEADER ======================-->
	<div class="container header__container">
		<div class="header__left">
			<h1>Your Payment Methods</h1>
			<button id="editButton" onclick="editSelectedCard();" disabled>Edit</button>
			<p></p>
			<div>
				<%
				try {
					List<Card> cards = CardService.getAllCards(); // Assuming this method fetches all cards
					for (Card card : cards) {
						out.println("<div class='card' onclick='selectCard(" + card.getCardId() + ", " + card.getCardNumber() + ", this)' style='background-image: url(\"images/creditcardimage.png\"); background-size: contain; background-repeat: no-repeat; height: 160px;'>");
						out.println("<p style= 'margin-bottom: 1.5em; color: white;'>" + card.getCardNumber()
						+ "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;" + "</p>");
						out.println("<p> &nbsp; &nbsp; &nbsp;" + card.getCardHolderName() + "&nbsp; &nbsp;"
						+ card.getExpDate() + "</p>");
						out.println("</div>");
					}
				} catch (Exception e) {
					out.println("Error retrieving cards: " + e.getMessage());
				}
				%>
			</div>
			<p></p>
			<form action="ProcessPaymentServlet" method="post"
				onsubmit="return checkCardSelection();">
				<input type="hidden" id="cardNumber" name="cardNumber"> <input
					type="hidden" name="totalAmount" value="${totalAmount}"> <input
					type="hidden" name="status" value="Completed">
				<button type="submit" id="proceedButton"
					class="payment-btn btn-primary" style="margin-right: 50px;"
					disabled>Proceed to Payment</button>
				<a href="addCard.jsp" class="payment-btn btn-primary">Add
					Payment Method</a>
			</form>
		</div>
	</div>
	<script src="js/scriptP.js"></script>
</body>
</html>