# IoTBay - Web Application Project

## Description:
IoTBay is an online retail management system designed to streamline the process of managing an e-commerce platform. The system provides a user-friendly interface for both customers and administrators, facilitating efficient browsing, purchasing, and management of products and orders.

## Key Features:
- **User Authentication:** Users can register for new accounts, log in securely, and manage their profiles.
- **Product Management:** Administrators can add, update, and remove products, each with details such as name, description, price, and quantity.
- **Order Management:** Customers can browse products, add them to their cart, and place orders. Administrators can view and manage orders, process payments, and update order statuses.
- **Search and Filtering:** Users can search for products using keywords and filter products based on categories, price ranges, and other criteria.
- **Responsive Design:** The web application is designed to provide an optimal viewing experience across various devices, including desktops, tablets, and smartphones.

## Technologies Used:
- **Frontend:** HTML, CSS, JavaScript, Bootstrap
- **Backend:** Java Servlets, JavaServer Pages (JSP), JDBC
- **Database:** MySQL
- **Development Tools:** Eclipse IDE, Apache Tomcat, Git

## Prerequisites:
- Eclipse IDE for Enterprise Java and Web Developers [Download Here](https://www.eclipse.org/downloads/packages/release/2024-03/r/eclipse-ide-enterprise-java-and-web-developers)
- MySQL
- Apache Tomcat v9.0

## Steps:

### Set-Up Apache Tomcat:
1. Download Apache Tomcat v9.0 and Extract: [Download Here](https://tomcat.apache.org/download-90.cgi) (64-bit Windows zip (pgp, sha512))
2. Open Eclipse > Window > Show View > Servers > Create New Server
3. Select Server Type: Tomcat v9.0 Server
4. Add Server Runtime Environment > Browse and select installation directory where Tomcat was extracted.
5. Finish and Start Server to verify

### Set-up MySQL Database:
#### For Windows:
1. Download MySQL: [Download Here](https://dev.mysql.com/downloads/installer/). (mysql-installer-community-8.0.36.0.msi)
    - Select "Full" as Setup Type > Execute Installation > Next.
    - Ensure Port is 3306 > Select Use Strong Password > Create MySQL Root Password > Next > Next > Execute > Finish
    - Next > Finish > Next > Enter Password > Check > Next > Execute > Finish > Next > Finish.
   
#### For Mac:
1. Download MySQL: [Download Here](https://dev.mysql.com/downloads/mysql/).
    - Follow Installation Steps and Enter a password for "root" user.
    - Download MySQL Workbench: [Download Here](https://dev.mysql.com/downloads/workbench/).
    - Open System Prefences > Search "MySQL" > Select Start MySQL Server

2. Open MySQL Workbench > Connect to Local instance > Input the following:
    ```sql
    create database iotbay;
    USE iotbay;
    CREATE TABLE users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        firstName VARCHAR(50) NOT NULL,
        lastName VARCHAR(50) NOT NULL,
        email VARCHAR(100) NOT NULL UNIQUE,
        upassword VARCHAR(100) NOT NULL,
        phone VARCHAR(20),
        userType ENUM('customer', 'staff') NOT NULL,
        verificationCode VARCHAR(6) NOT NULL,
        registrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    ```

### Clone Repo:
1. ```bash
   git clone https://github.com/bhav-18/IoTBay.git
2. Open in Eclipse
3. Navigate to src > main > java > registration > RegistrationServlet.java
4. Update 'LocalHost1.' in the below with your MySQL password in line 86 and line 125
5. ```java :
   connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","LocalHost1.");
6. Repeat above in all Servlet files where there is sql connection.

### Launch Web App:
1. Right-click on project > Run As > Run on Server

## Contributing:
Contributions to the project are welcome! Feel free to submit bug reports, feature requests, or pull requests.
