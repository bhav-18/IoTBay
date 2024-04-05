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

### Create Dynamic Web Project:
1. Open Eclipse > New > Dynamic Web Project
2. Select Target Runtime as Apache Tomcat v9.0
3. Next > Next > Generate web.xml deployment descriptor
4. Finish

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
        userType ENUM('staff', 'customer') NOT NULL
      );
    ```

### Clone Repo:
1. ```bash
   git clone https://github.com/deep-sohana/iot-bay-project
2. Open in Eclipse
3. Navigate to iotbay-initial > src > main > java > registration > RegistrationServlet.java
4. Update 'XXXX' in the below with your MySQL password
5. ```java :
   connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false","root","XXXXXX.");
6. Repeat above in Login.java

### Launch Web App:
1. Right-click iotbay-initial > Run As > Run on Server

## Contributing:
Contributions to the project are welcome! Feel free to submit bug reports, feature requests, or pull requests.
