package unit.DCMTests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import registration.createProduct;

public class CreateProductTest {

    private CreateProductServletUnderTest createProductServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        createProductServlet = new CreateProductServletUnderTest();
    }

    // Subclass to expose protected doPost method
    private class CreateProductServletUnderTest extends createProduct {
        public void doPostTest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
            super.doPost(req, resp);
        }

        public boolean productExistsTest(String productName) throws Exception {
            return super.productExists(productName);
        }
    }
    
    @Test
    public void testDoPostWithValidData() throws Exception {
        // Set up the mock request with valid data
        when(request.getParameter("productName")).thenReturn(generateRandomName());
        when(request.getParameter("price")).thenReturn("1700.0");
        when(request.getParameter("category")).thenReturn("Laptops");
        when(request.getParameter("stock")).thenReturn("100");
        when(request.getParameter("description")).thenReturn("Random Macbook Pro");
        when(request.getParameter("image")).thenReturn("laptop.svg");

        // Mock the response writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Set up database mock behavior
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Execute the servlet's doPost method
        createProductServlet.doPostTest(request, response);

        // Verify the behavior of the servlet
        verify(response).sendRedirect("product.jsp?createSuccess=true");
    }

    @Test
    public void testDoPostWithMissingValues() throws Exception {
        // Set up the mock request with missing values
        when(request.getParameter("productName")).thenReturn("");
        when(request.getParameter("price")).thenReturn("");
        when(request.getParameter("category")).thenReturn("");
        when(request.getParameter("stock")).thenReturn("");
        when(request.getParameter("description")).thenReturn("");
        when(request.getParameter("image")).thenReturn("");

        // Mock the response writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute the servlet's doPost method
        createProductServlet.doPostTest(request, response);

        // Verify the behavior of the servlet
        verify(response).sendRedirect("createProduct.jsp?missingValues=true");
    }

    @Test
    public void testDoPostWithInvalidValues() throws Exception {
        // Set up the mock request with invalid values
        when(request.getParameter("productName")).thenReturn("Product1");
        when(request.getParameter("price")).thenReturn("abc");
        when(request.getParameter("category")).thenReturn("Category1");
        when(request.getParameter("stock")).thenReturn("xyz");
        when(request.getParameter("description")).thenReturn("Description1");
        when(request.getParameter("image")).thenReturn("image.jpg");

        // Mock the response writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute the servlet's doPost method
        createProductServlet.doPostTest(request, response);

        verify(response).sendRedirect("createProduct.jsp?invalidValues=true");
    }

  
//    Generate random product name
    public static String generateRandomName() {
        String baseName = "Apple Macbook Pro";
        Random random = new Random();
        int randomNumber = random.nextInt(1000); // Generate a random number between 0 and 999
        
        String generatedName = baseName + " " + randomNumber;
        
        return generatedName;
    }
}
