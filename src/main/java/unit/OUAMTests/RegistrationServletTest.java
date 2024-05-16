package unit.OUAMTests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import registration.RegistrationServlet;
import java.util.Random;

public class RegistrationServletTest {
	
    private RegistrationServletUnderTest registrationServlet;
    
    @Mock
    private HttpServletRequest request;
    
    @Mock
    private HttpServletResponse response;
    
    @Mock
    private HttpSession session;
    
    @Mock
    private RequestDispatcher dispatcher;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        registrationServlet = new RegistrationServletUnderTest();
        when(request.getSession()).thenReturn(session); // Inject mock session into request
    }
    
    // Subclass to expose protected doPost method
    private class RegistrationServletUnderTest extends RegistrationServlet {
		public void doPostTest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
            super.doPost(req, resp);
        }
    }
    
    @Test
    public void testDoPostWithValidData() throws Exception {
        // Set up the mock request with valid data
        when(request.getParameter("fname")).thenReturn("John");
        when(request.getParameter("lname")).thenReturn("Doe");
        when(request.getParameter("email")).thenReturn(generateRandomEmail());
        when(request.getParameter("phone")).thenReturn("1234567890");
        when(request.getParameter("password")).thenReturn("Testing1.");
        when(request.getParameter("re_pass")).thenReturn("Testing1.");
        
        // Mock the response writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute the servlet's doPost method
        registrationServlet.doPostTest(request, response);
        
        // Verify the behavior of the servlet
        verify(session).setAttribute(eq("email"), anyString());
        verify(response).sendRedirect("verification.jsp");
    }
    
    @Test
    public void testDoPostWithMissingFirstName() throws Exception {
        // Set up the mock request with missing first name
        when(request.getParameter("fname")).thenReturn("");
        // Set other necessary parameters
        when(request.getParameter("lname")).thenReturn("Doe");
        when(request.getParameter("email")).thenReturn("testing@example.com");
        when(request.getParameter("phone")).thenReturn("1234567890");
        when(request.getParameter("password")).thenReturn("Testing1.");
        when(request.getParameter("re_pass")).thenReturn("Testing1.");
        
        // Mock the response writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute the servlet's doPost method
        registrationServlet.doPostTest(request, response);
        
        // Verify the behavior of the servlet
        verify(response).sendRedirect("registration.jsp?regFailed=true");
        
    }
    
    public static String generateRandomEmail() {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder emailBuilder = new StringBuilder();
        Random random = new Random();
        
        // Generate random email address
        for (int i = 0; i < 10; i++) { // You can adjust the length of the email address as needed
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            emailBuilder.append(randomChar);
        }
        emailBuilder.append("@example.com"); // Append domain name
        
        return emailBuilder.toString();
    }
}
