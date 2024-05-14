<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IoTBay | Thank You</title>

    <!-- Include the Google Font (Poppins) -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap">

    <!-- Iconscout CDN -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">

    <!-- css.gg CDN -->
    <link href='https://unpkg.com/css.gg@2.0.0/icons/css/menu.css' rel='stylesheet'>

    <!-- SWIPER JS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>

    <link rel="stylesheet" href="css/styles.css">

    <link rel="icon" type="image/x-icon" href="assets/iot-bay.ico"/>

    <link rel="stylesheet" href="css/styleFinish.css" />
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
    <div class="thank-you-for">
      <div class="div">
        <div class="undraw-delivery">
          <div class="overlap-group">
            <img class="group" src="images/group.png" />
            <img class="img" src="images/group-1.png" />
            <img class="vector" src="images/vector.svg" />
            <img class="vector-2" src="images/vector-1.svg" />
            <img class="vector-3" src="images/vector-2.svg" />
            <img class="vector-4" src="images/vector-3.svg" />
            <img class="vector-5" src="images/vector-4.svg" />
            <img class="vector-6" src="images/vector-5.svg" />
            <img class="vector-7" src="images/vector-6.svg" />
            <img class="vector-8" src="images/vector-7.svg" />
            <img class="vector-9" src="images/vector-8.svg" />
            <img class="vector-10" src="images/vector-9.svg" />
            <img class="group-2" src="images/group-2.png" />
            <img class="vector-11" src="images/vector-10.svg" />
            <img class="group-3" src="images/group-3.png" />
            <img class="vector-12" src="images/vector-11.svg" />
            <div class="text-wrapper">IoTBay</div>
          </div>
        </div>
        <div class="overlap">
          <img class="undraw-house" src="images/undraw-house-searching-re-stk8-1.svg" />
          <div class="product-card"></div>
        </div>
        <p class="payment-successful">
          <span class="span">Payment Successful!<br /></span>
          <span class="text-wrapper-2">Thank you for shopping with IoTBay!</span>
        </p>
        <div class="group-4">
          <a href="landing.jsp" class="btn btn-primary" style="margin-left: 20px;">Continue Shopping</a>
          <a href="history.jsp" class="btn btn-primary" style="margin-left: 400px;">View Payment History</a>
        </div>
      </div>
    </div>
  </body>
</html>
