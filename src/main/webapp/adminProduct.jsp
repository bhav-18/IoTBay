<%@ page import="java.util.List" %>
<%@ page import="registration.Product" %>


<%@ page session="true" %>

<%  
	String productIdString = request.getParameter("productId"); // Retrieve product ID from URL parameter
	int productId = Integer.parseInt(productIdString); 
    Product product = new Product(); 
    Product currentProduct = product.getCurrentProduct(productId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>IoTBay | Product </title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
<link rel="icon" type="image/x-icon" href="assets/iot-bay.ico"/>
</head>
<body>

	<input type = "hidden" id ="status" value ="<%= request.getAttribute("status")%>"> <!-- get status value of sign up process-->

	<div class="main">
		<section class="signup">
			<div class="container" style="width:900px">
 				<div class="signup-content" style="width:905px">
					<div class="signup-form">						
						<h2 class="form-title"><%=currentProduct.getProductName()%></h2>
						<h3 style="font-weight: normal; margin-top: -1rem;">Update product details </h3>
						<form method="post" action="adminProduct" class="register-form"
							id="register-form">
							<input type="hidden" name="action" value="update">
							<input type ="hidden" name="productId" id="productId" value=<%= productId%>>
							<div class="form-group" style="margin-top: 1.5rem">
								<label for="fname"><i
									class="zzmdi zmdi-devices"></i></label> <input
									type="text" name="productName" id="productName" placeholder="Product Name" required="required" value="<%= currentProduct.getProductName()%>"/>
							</div>
							<div class="form-group">
								<label for="lname"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="price" id="price" placeholder="Price $" required="required" value="<%= currentProduct.getPrice()%>"/>
							</div>
<%-- 							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="text" name="category" id="category" placeholder="Category" required="required" value="<%= currentProduct.getCategory()%>"/>
							</div> --%>
							<div class="form-group">
							    <label for="category"><i class="zmdi zmdi-email"></i></label>
							    <select name="category" id="category" class="form-control" required="required" style="margin-left:30px; margin-top:10px; border:none; font-family: Poppins; cursor: pointer; color: #222;">
							        <option value="<%= currentProduct.getCategory()%>"><%= currentProduct.getCategory()%></option>
							        <option value="Mobile Phones">Mobile Phones</option>
							        <option value="Laptops">Laptops</option>
							        <option value="Smartwatches">Smartwatches</option>
							        <option value="Tablets">Tablets</option>
							    </select>
							    <hr>
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-phone"></i></label>
								<input type="text" name="stock" id="stock"
									placeholder="Stock" required="required" value="<%= currentProduct.getStock()%>"/>
							</div>							
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="text" name="description" id="description" placeholder="Description" required="required" value="<%= currentProduct.getDescription()%>"/>
							</div>
<%-- 							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="text" name="image" id="image" placeholder="Image" required="required" value="<%= currentProduct.getProductImage()%>"/>
							</div> --%>
							<div class="form-group">
							    <label for="image"><i class="zmdi zmdi-email"></i></label>
							    <select name="image" id="image" class="form-control" required="required" style="margin-left:30px; margin-top:10px; border:none; font-family: Poppins; cursor: pointer; color: #222;">
							        <option value="<%= currentProduct.getProductImage()%>"><%= currentProduct.getProductImage()%></option>
							        <option value="phone.svg">phone.svg</option>
							        <option value="laptop.svg">laptop.svg</option>
							        <option value="smartwatch.svg">smartwatch.svg</option>
							        <option value="tablet.svg">tablet.svg</option>
							    </select>
							    <hr>
							</div>
							<div class="form-group form-button" style="display: flex; justify-content: space-between">
								<input type="submit" name="updateProduct" id="signup"
									class="form-submit" value="Update details" style="margin-right: 10px" />	
								<input type="submit" name="deleteProduct" id="deleteProduct"
									class="form-submit" value="Delete product"/>														
							</div>						
						</form>					
					</div>
					<div class="signup-image" style="width:300px; margin-top:125px">
						<figure>
							<img src="images/account.svg" alt="signup image">
						</figure>		
						<a href="product.jsp" class="form-submit" style="margin-top: -3.5rem; text-decoration: none;">Return to products</a>				
					</div>			    					
				</div> 	
			</div>
		</section>
	</div>
	
	
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src ="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <!-- message shown after sign up -->
	<link rel="stylesheet" href="alert/dist/sweetalert.css"> <!-- message shown after sign up -->
	
<script type="text/javascript">
	var urlParams = new URLSearchParams(window.location.search);
	var updateSuccess = urlParams.get('updateSuccess');
	var updateFailed = urlParams.get('updateFailed');
	var deleteFailed = urlParams.get('deleteFailed');
	var productExists = urlParams.get('productExists');
	const productId = urlParams.get('productId');
	

    if (updateSuccess) {
        swal("Success!","Product details have been updated!", "success");
        const newUrl = window.location.pathname + "?productId=" + productId;
        history.replaceState({}, document.title, newUrl);
    }	
    if (updateFailed) {
    	swal("Unsuccessful!","Unable to update details.", "error");
        const newUrl = window.location.pathname + "?productId=" + productId;
        history.replaceState({}, document.title, newUrl);
    }
    if(deleteFailed){
    	swal("Unsuccessful!","Unable to delete product.", "error");
        const newUrl = window.location.pathname + "?productId=" + productId;
        history.replaceState({}, document.title, newUrl);  	
    }       
</script>



</body>
</html>