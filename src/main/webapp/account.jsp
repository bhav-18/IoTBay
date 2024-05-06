<%@ page import="java.util.List" %>
<%@ page import="registration.AccessLog" %>
<%@ page import="registration.User" %>

<%@ page session="true" %>

<%  
	String email = (String) session.getAttribute("name");
	AccessLog accessLog = new AccessLog();
	List<AccessLog> accessLogs = accessLog.getAllAccessLogs(email);
	
	User user = new User();
	User currentUser = user.getCurrentUser(email);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>IoTBay | My account</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
<link rel="icon" type="image/x-icon" href="assets/iot-bay.ico"/>
</head>
<body>

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
	    </div>
	</nav>

	<input type = "hidden" id ="status" value ="<%= request.getAttribute("status")%>"> <!-- get status value of sign up process-->

	<div class="main">
		<section class="signup">
			<div class="container" style="width:900px">
 				<div class="signup-content" style="width:905px">
					<div class="signup-form">						
						<h2 class="form-title">My account</h2>
						<h3 style="font-weight: normal; margin-top: -1rem;">Update your account details </h3>
						<form method="post" action="account" class="register-form"
							id="register-form">
							<input type="hidden" name="userEmail" id="userEmail" value="<%= session.getAttribute("name") %>">
							<input type="hidden" name="action" value="update">
							<div class="form-group" style="margin-top: 1.5rem">
								<label for="fname"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="fname" id="fname" placeholder="First Name" required="required" value="<%= currentUser.getfirstName() %>"/>
							</div>
							<div class="form-group">
								<label for="lname"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="lname" id="lname" placeholder="Last Name" required="required" value="<%= currentUser.getlastName() %>"/>
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" required="required" value="<%= currentUser.getEmail() %>" readonly/>
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-phone"></i></label>
								<input type="text" name="phone" id="phone"
									placeholder="Phone no" required="required" value="<%= currentUser.getPhoneNumber() %>"/>
							</div>							
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password" placeholder="Password" required="required" value="<%= currentUser.getPassword() %>"/>
							</div>
							<div class="form-group form-button" style="display: flex; justify-content: space-between">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Update details" style="margin-right: 10px" />	
								<input type="button" name="deleteAccount" id="deleteAccount"
									class="form-submit" value="Delete account" onclick="deleteAccount()" />															
							</div>						
						</form>
						<form id="deleteForm" method="post" action="account">
						    <input type="hidden" name="action" value="delete"> <!-- Include an action parameter to identify the action -->
						    <input type="hidden" name="email" value="<%= currentUser.getEmail() %>">
						</form>						
					</div>
					<div class="signup-image" style="width:300px; margin-top:125px">
						<figure>
							<img src="images/account.svg" alt="signup image">
						</figure>						
					</div>			    					
				</div> 	
				<div class="signup-content" style="margin-top: -4rem;">
					<div class="signup-form" style="width: 75rem">
						<h2>My logs</h2> 
						<div class="form-group">
							<label style="margin-top: 0.5rem; margin-left: 0.5rem"><i class="zmdi zmdi-search"></i></label>
							<input type="text" id="searchInput" onkeyup="searchLogs()" placeholder="Search logs..." style="margin-top: 1rem; width: 605px">						
						</div>
				        <table border="1" style="margin-top: 2rem">
				            <thead>
				                <tr>
				                    <th style="white-space: nowrap; padding: 20px;">Log ID</th>
				                    <th style="padding: 20px">Email</th>
				                    <th style="padding: 20px">Login Time</th>
				                    <th style="padding: 20px">Logout Time</th>
				                </tr>
				            </thead>
				            <tbody id="logTableBody">
				                <% 
				                    if (accessLogs != null) {
				                        for (AccessLog log : accessLogs) {
				                %>
				                <tr>
				                    <td style="white-space: nowrap; padding: 20px;"><%= log.getLogId() %></td>
				                    <td style="white-space: nowrap; padding: 20px;"><%= log.getEmail() %></td>
				                    <td style="white-space: nowrap; padding: 20px;"><%= log.getLoginDateTime() %></td>
				                    <td style="white-space: nowrap; padding: 20px;"><%= (log.getLogoutDateTime() != null) ? log.getLogoutDateTime() : "N/A" %></td>
				                </tr>
				                <% 
				                        }
				                    }
				                %>
				            </tbody>
				        </table>				        	
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
	var deleteSuccess = urlParams.get('deleteSuccess');
	var deleteFailed = urlParams.get('deleteFailed');

    if (updateSuccess) {
        swal("Success!","Your details have been updated!", "success");
        history.replaceState({}, document.title, window.location.pathname);
    }	
    if (updateFailed) {
    	swal("Unsuccessful!","Unable to update details.", "error");
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
    
    function deleteAccount() {
    	document.getElementById("deleteForm").submit();
    }   
    
/*     function searchLogs() {
        var input, filter, table, tbody, tr, td, i, txtValue;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        table = document.querySelector("table");
        tbody = document.getElementById("logTableBody");
        tr = tbody.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[3]; // Change index to the column you want to search (0-indexed)
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    } */
    function searchLogs() {
        var input, filter, table, tbody, tr, td, i, j, txtValue;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        table = document.querySelector("table");
        tbody = document.getElementById("logTableBody");
        tr = tbody.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            var found = false;
            for (j = 0; j < tr[i].cells.length; j++) {
                td = tr[i].cells[j];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }    
</script>



</body>
</html>