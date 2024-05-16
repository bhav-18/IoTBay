package unit.OUAMTests;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import registration.Account;

public class AccountTest {

    private AccountServletUnderTest accountServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        accountServlet = new AccountServletUnderTest();
    }

    // Subclass to expose protected doPost method
    private class AccountServletUnderTest extends Account {
        public void doPostTest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
            super.doPost(req, resp);
        }
    }

    @Test
    public void testDoPostWithUpdateButton() throws Exception {
        // Set up the mock request with update button pressed
        when(request.getParameter("updateAccount")).thenReturn("update");
        when(request.getParameter("email")).thenReturn("testing@example.com");
        when(request.getParameter("fname")).thenReturn("John");
        when(request.getParameter("lname")).thenReturn("Doe");
        when(request.getParameter("phone")).thenReturn("1234567890");
        when(request.getParameter("password")).thenReturn("Testing1.");
        
        // Execute the servlet's doPost method
        accountServlet.doPostTest(request, response);

    }

    @Test
    public void testDoPostWithDeleteButton() throws Exception {
        // Set up the mock request with delete button pressed
        when(request.getParameter("deleteAccount")).thenReturn("delete");
        when(request.getParameter("email")).thenReturn("testing@example.com");

        // Execute the servlet's doPost method
        accountServlet.doPostTest(request, response);

    }
}
