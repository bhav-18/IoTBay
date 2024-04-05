<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>IoTBay Sign Up</title>

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

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
					
						<form method="post" action="register" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="fname"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="fname" id="fname" placeholder="First Name" required="required"/>
							</div>
							<div class="form-group">
								<label for="lname"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="lname" id="lname" placeholder="Last Name" required="required"/>
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" required="required"/>
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-phone"></i></label>
								<input type="text" name="phone" id="phone"
									placeholder="Phone no" required="required"/>
							</div>							
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password" placeholder="Password" required="required"/>
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="password" name="re_pass" id="re_pass"
									placeholder="Repeat your password" required="required"/>
							</div>
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <!-- <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label> -->
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.svg" alt="signup image">
						</figure>
						<a href="login.jsp" class="signup-image-link">I am already
							member</a>
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
	var status = document.getElementById("status").value;
	if(status == "success"){
		swal("Congratulations!","Account successfully registered!", "success");
	}
	if(status == "emptyFname"){
		swal("Unsuccessful!","Please enter a first name.", "error");
	}
	if(status == "emptyLname"){
		swal("Unsuccessful!","Please enter a last name.", "error");
	}
	if(status == "emptyEmail"){
		swal("Unsuccessful!","Please enter an email.", "error");
	}
	if(status == "emailExists"){
		swal("Unsuccessful!","Email already exists.", "error");
	}	
	if(status == "invalidPhone"){
		swal("Unsuccessful!","Please enter a valid phone number.", "error");
	}
	if(status == "emptyPassword"){
		swal("Unsuccessful!","Please enter a password.", "error");
	}
	if(status == "passNoMatch"){
		swal("Unsuccessful!","Passwords do not match.", "error");
	}
	if(status == "invalidPassword"){
		swal("Unsuccessful!","Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace.", "error");
	}
</script>



</body>
</html>