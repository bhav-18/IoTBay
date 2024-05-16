

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ page import = "java.util.List"%>
<%@ page import = "registration.Product" %>
<%@ page import = "registration.User"%>
<%@ page import="java.util.*, registration.*, java.sql.Connection, java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
    
    
 <% 
 	Product product = new Product(); 
 	List<Product> products = product.getAllProducts();
 	
 	String email = (String) session.getAttribute("name");
 	User user = new User();
 	User currentUser = user.getCurrentUser(email);
 	
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
    <title>IoTBay | Products</title>

    <!-- Include the Google Font (Poppins) -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap">

    <!-- Iconscout CDN -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">

    <!-- css.gg CDN -->
    <!-- <link href='https://unpkg.com/css.gg@2.0.0/icons/css/menu.css' rel='stylesheet'> -->
    
    <!--+++++++++++++++++++++++++ BOOTSTRAP +++++++++++++++++++++++++-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <link rel="stylesheet" href="./css/styles.css">
    
    <!-- <link rel="stylesheet" href="./css/products.css"> -->

    <link rel="icon" type="image/x-icon" href="assets/iot-bay.ico"/>
    
       <!-- Include Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    
</head>
<body>

    <!--================= NAVBAR ======================-->
    <nav>
        <div class="container nav__container">
            <h3>IoTBay</h3>
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

    <div class="container" style="margin-top: 100px">
    	<div class="card-header my-3">
    		<h3 style= "color: #000000">All Products</h3>
    	<% if (currentUser == null){ %>

        <% } else if ( currentUser != null && currentUser.getUserType().equals("staff")){ %>
            <a href="createProduct.jsp" class="btn btn-primary" style="margin-top:15px">Create Product</a>
        <% } %>
    	</div>
    	<div class="form-group">
			<label style="margin-top: 0.5rem; margin-left: 0.5rem"><i class="zmdi zmdi-search"></i></label>
			<input type="text" id="searchInput" onkeyup="searchProducts()" placeholder="Search products..." style="margin-top: 1rem; width: 605px">						
		</div>
		<hr> 
    	<div class="row justify-content-between" style="margin-left: -1.5rem">
    	<% 
    		if(!products.isEmpty()){
    			 for(Product p : products){%>
    		    		<div class ="col-md-3 mx-3 my-3 product-container">
    	    			<div class="card" style="width: 18rem;">
	                        <div class="image-container">
	                            <img src="images/<%=p.getProductImage()%>" class="card-img-top" alt="...">
	                        </div>
    			  			<div class="card-body">
    			   				<h5 class="card-title"><%=p.getProductName()%></h5>
    			   				<h5 class="price">Price: <%=p.getPrice()%></h5>
    			   				<h6 class="category"><%=p.getCategory()%></h6>
    			    			<p class="card-text"><%=p.getDescription()%></p>
    			    			<div class="mt-3 d-flex justify-content-between">
    			    			    <% if (currentUser == null){ %>
            								<a href="add-to-cart?productId=<%=p.getProductId()%>" class="btn btn-primary">Add to Cart</a>
    			    			     <% }else if (currentUser != null && currentUser.getUserType().equals("staff")){ %>
            							<a href="adminProduct.jsp?productId=<%=p.getProductId()%>" class="btn btn-primary">Edit Product</a>
       								 <% } else{ %>
       									 <a href="add-to-cart?productId=<%=p.getProductId()%>" class="btn btn-primary">Add to Cart</a>
       								 <% }%>
    			    			</div>
    	  					</div>
    					</div>
    	    		</div>
    			 <%}
    		}
    	%>

    	</div>
    </div>
    
    
    
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src ="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <!-- message shown after sign up -->
	<link rel="stylesheet" href="alert/dist/sweetalert.css"> <!-- message shown after sign up -->
	
<script type="text/javascript">
	var urlParams = new URLSearchParams(window.location.search);
	var createSuccess = urlParams.get('createSuccess');
	var createFailed = urlParams.get('createFailed');
	var deleteSuccess = urlParams.get('deleteSuccess');
	var deleteFailed = urlParams.get('deleteFailed');
	
	if (createSuccess) {
	    swal("Success!","New product added successfully!", "success");
	    history.replaceState({}, document.title, window.location.pathname);
	}	
	if (createFailed) {
		swal("Unsuccessful!","Unable to add product.", "error");
	    history.replaceState({}, document.title, window.location.pathname);
	}
	if (deleteSuccess) {
	    swal("Success!","Product deleted successfully!", "success");
	    history.replaceState({}, document.title, window.location.pathname);
	}	
	
	function searchProducts() {
	    var input, filter, productContainers, productName, productCategory, i;
	    input = document.getElementById('searchInput');
	    filter = input.value.toLowerCase();
	    productContainers = document.getElementsByClassName('product-container');
	    
	    for (i = 0; i < productContainers.length; i++) {
	        productName = productContainers[i].getElementsByClassName('card-title')[0].innerHTML.toLowerCase();
	        productCategory = productContainers[i].getElementsByClassName('category')[0].innerHTML.toLowerCase();
	        
	        if (productName.indexOf(filter) > -1 || productCategory.indexOf(filter) > -1) {
	            productContainers[i].style.display = '';
	        } else {
	            productContainers[i].style.display = 'none';
	        }
	    }
	} 
    
    window.addEventListener('scroll', () => {
    	document.querySelector('nav').classList.toggle('window-scroll', window.scrollY > 50)
    })
</script>

</body>
</html>