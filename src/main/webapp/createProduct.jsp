<%@ page import="java.util.List" %>
<%@ page import="registration.Product" %>


<%@ page session="true" %>

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
						<h2 class="form-title">New product</h2>
						<h3 style="font-weight: normal; margin-top: -1rem;">Enter product details </h3>
						<form method="post" action="createProduct" class="register-form"
							id="register-form">
							<div class="form-group" style="margin-top: 1.5rem">
								<label for="fname"><i
									class="zzmdi zmdi-devices"></i></label> <input
									type="text" name="productName" id="productName" placeholder="Product Name" required="required"/>
							</div>
							<div class="form-group">
								<label for="lname"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="price" id="price" placeholder="Price" required="required"/>
							</div>
<!-- 							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="text" name="category" id="category" placeholder="Category" required="required"/>
							</div> -->
							<div class="form-group">
							    <label for="category"><i class="zmdi zmdi-email"></i></label>
							    <select name="category" id="category" class="form-control" required="required" style="margin-left:30px; margin-top:10px; border:none; font-family: Poppins; cursor: pointer; color: #999;" onfocus="this.style.color='#222'" onchange="this.style.color='#222'">
							        <option value="">Select a category</option>
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
									placeholder="Stock" required="required"/>
							</div>							
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="text" name="description" id="description" placeholder="Description" required="required"/>
							</div>
<!-- 							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="text" name="image" id="image" placeholder="Image" required="required"/>
							</div> -->
							<div class="form-group">
							    <label for="image"><i class="zmdi zmdi-email"></i></label>
							    <select name="image" id="image" class="form-control" required="required" style="margin-left:30px; margin-top:10px; border:none; font-family: Poppins; cursor: pointer; color: #999;" onfocus="this.style.color='#222'" onchange="this.style.color='#222'">
							        <option value="">Select an image</option>
							        <option value="phone.svg">phone.svg</option>
							        <option value="laptop.svg">laptop.svg</option>
							        <option value="smartwatch.svg">smartwatch.svg</option>
							        <option value="tablet.svg">tablet.svg</option>
							    </select>
							    <hr>
							</div>
							<div class="form-group form-button" style="display: flex; justify-content: space-between">
								<input type="submit" name="addProduct" id="signup"
									class="form-submit" value="Add product" style="margin-right: 10px" />	
								<input type="submit" name="cancel" id="cancel"
									class="form-submit" value="Cancel product"/>																		
							</div>						
						</form>					
					</div>
					<div class="signup-image" style="width:300px; margin-top:125px">
						<figure>
							<img src="images/account.svg" alt="signup image">
						</figure>							
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
	var missingValues = urlParams.get('missingValues');
	var invalidValues = urlParams.get('invalidValues');
	var productExists = urlParams.get('productExists')
	
	
	if (missingValues) {
		swal("Unsuccessful!","Please enter all details.", "error");
	    history.replaceState({}, document.title, window.location.pathname);
	}
	if(invalidValues){
		swal("Invalid!","Please enter numbers only.", "error");
	    history.replaceState({}, document.title, window.location.pathname);    	
	}  
    if (productExists) {
    	swal("Unsuccessful!","Product already exists.", "error");
	    history.replaceState({}, document.title, window.location.pathname); 
    }
    
    document.getElementById("cancel").addEventListener("click", function() {
        // Redirect to product.jsp
        window.location.href = "product.jsp";
    });   
</script>



</body>
</html>