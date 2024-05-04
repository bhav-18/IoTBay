<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>IoTBay | Verify</title>

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
							<img src="images/verification.svg" alt="verification image">
						</figure>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Verification code</h2>
						<h3 style="font-weight: normal;">Enter verification code sent to <%= session.getAttribute("email") %> </h3>
						<form method="post" action="verify" class="register-form"
							id="verify-form">
							<input type="hidden" name="email" id="email" value="<%= session.getAttribute("email") %>">
							<div class="form-group">
									<label for="vcode"><i class="zmdi zmdi-lock"></i></label> <input
									type="text" name="vcode" id="vcode" placeholder="123456" required="required"/>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="verify" id="verify"
									class="form-submit" value="Verify" />
							</div>
						</form>
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
    var regSuccess = urlParams.get('regSuccess');
    var verificationFailed = urlParams.get('verificationFailed');
    
    if (regSuccess) {
		swal("Congratulations!","Account successfully registered!", "success")
        .then((value) => {
        	window.location.href = "index.jsp"; 
        });
    }
		
    if (verificationFailed) {
        swal("Unsuccessful!","Verification was unsuccessful, please try again.", "error");
        history.replaceState({}, document.title, window.location.pathname);
    }
</script> 

</body>
</html>