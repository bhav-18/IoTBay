package unit.DCMTests;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import registration.adminProduct;

public class AdminProductTest {

    private AdminProductServletUnderTest adminProductServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        adminProductServlet = new AdminProductServletUnderTest(); 
    }

    // Subclass to expose protected doPost method
    private class AdminProductServletUnderTest extends adminProduct {
        public void doPostTest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
            super.doPost(req, resp);
        }
    }

    @Test
    public void testDoPostWithUpdateButton() throws Exception {
        // Set up the mock request with update button pressed
        when(request.getParameter("updateProduct")).thenReturn("update");
        when(request.getParameter("productId")).thenReturn("1");
        when(request.getParameter("productName")).thenReturn("Mac");
        when(request.getParameter("price")).thenReturn("1000.0");
        when(request.getParameter("category")).thenReturn("Laptops");
        when(request.getParameter("stock")).thenReturn("50");
        when(request.getParameter("description")).thenReturn("Description 1");
        when(request.getParameter("image")).thenReturn("laptop.svg.");
        
        // Execute the servlet's doPost method
        adminProductServlet.doPostTest(request, response);

    }

    @Test
    public void testDoPostWithDeleteButton() throws Exception {
        // Set up the mock request with delete button pressed
        when(request.getParameter("deleteProduct")).thenReturn("delete");
        when(request.getParameter("productId")).thenReturn("1");

        // Execute the servlet's doPost method
        adminProductServlet.doPostTest(request, response);
    }
}
