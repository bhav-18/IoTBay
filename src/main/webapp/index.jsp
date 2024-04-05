<%-- <%
    if(session.getAttribute("name") == null){
        String continueAsGuest = request.getParameter("continueAsGuest");
        if (continueAsGuest != null && continueAsGuest.equals("true")) {
            // Allow guest access
        	response.sendRedirect("index.jsp");
        } else {
            // Redirect to login page if user is not logged in
            response.sendRedirect("login.jsp");
            return; // Stop further execution of the JSP
        }
    }
%> --%>

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
</head>
<body>
    <!--================= NAVBAR ======================-->
    <nav>
        <div class="container nav__container">
            <h3><a href="index.jsp">IoTBay</a></h3>
            <ul class="nav__menu">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="index.jsp">About</a></li>
                <li><a href="logout">Logout</a></li>
                <li><small><%= session.getAttribute("name") != null ? session.getAttribute("name") : "Guest" %></small></li>
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
                <a href="registration.jsp" class="btn btn-primary">Get Stared</a>
            </div>

            <div class="header__right">
                <div class="header__right-image">
                    <img src="assets/iotbay-image.svg">
                </div>
            </div>
        </div>
    </header>
    <!--================= END HEADER ======================-->