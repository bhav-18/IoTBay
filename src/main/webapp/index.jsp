<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>IoTBay Login</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
<link rel="icon" type="image/x-icon" href="assets/iot-bay.ico"/>
</head>
<body>

<input type = "hidden" id ="status" value ="<%= request.getAttribute("status")%>"> <!-- get status value of sign in process-->
	
	<div class="main">

		<!-- Sign in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="images/login-image.svg" alt="login image">
						</figure>
						<a href="registration.jsp" class="signup-image-link">Create an
							account</a>
						<a href="landing.jsp" class="signup-image-link">Continue as guest</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Log In</h2>
						<form method="post" action="login" class="register-form"
							id="login-form">
							<div class="form-group">
<!-- 								<label for="username"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="email" id="email"
									placeholder="Email" required="required"/> -->
									<label for="email"><i class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="email" name="email" id="email" placeholder="Email" required="required"/>
							</div>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password"
									placeholder="Password" required="required"/>
							</div>
							<div class="form-group">
<!-- 								<input type="checkbox" name="remember-me" id="remember-me"
									class="agree-term" /> <label for="remember-me"
									class="label-agree-term"><span><span></span></span>Remember
									me</label> -->
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
						</form>
<!-- 						<div class="social-login">
							<span class="social-label">Or login with</span>
							<ul class="socials">
								<li><a href="#"><i
										class="display-flex-center zmdi zmdi-facebook"></i></a></li>
								<li><a href="#"><i
										class="display-flex-center zmdi zmdi-twitter"></i></a></li>
								<li><a href="#"><i
										class="display-flex-center zmdi zmdi-google"></i></a></li>
							</ul>
						</div> -->
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
/* 	var status = document.getElementById("status").value; */
    var urlParams = new URLSearchParams(window.location.search);
    var loginFailed = urlParams.get('loginFailed');
	var deleteSuccess = urlParams.get('deleteSuccess');
	var deleteFailed = urlParams.get('deleteFailed');
    
    if (loginFailed) {
        swal("Unsuccessful!","Incorrect email or password, please try again.", "error");
        history.replaceState({}, document.title, window.location.pathname);
    }	
    
    if(deleteSuccess){
		swal("Success!","Account successfully deleted!", "success")
        .then((value) => {
        	window.location.href = "index.jsp"; 
        });    	
    }
    if(deleteFailed){
    	swal("Unsuccessful!","Unable to delete account.", "error");
        history.replaceState({}, document.title, window.location.pathname);    	
    }  
/* 	if(status == "failed"){
		swal("Unsuccessful!","Incorrect email or password, please try again.", "error");
	}
	else if(status == "emptyEmail"){
		swal("Unsuccessful!","Please enter email.", "error");
	}
	else if(status == "emptyPassword"){
		swal("Unsuccessful!","Please enter password.", "error");
	} */
</script>

</body>
</html>