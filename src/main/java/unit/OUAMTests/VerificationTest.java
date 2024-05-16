package unit.OUAMTests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import registration.Verification;

public class VerificationTest {
    
   private VerificationServletUnderTest verificationServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        verificationServlet = new VerificationServletUnderTest();
    }

    // Subclass to expose protected doPost method
    private class VerificationServletUnderTest extends Verification {
        public void doPostTest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
            super.doPost(req, resp);
        }
    }

    @Test
    public void testDoPostVerificationSuccess() throws Exception {
        // Set up the mock request
        when(request.getParameter("vcode")).thenReturn("823588");
        when(request.getParameter("email")).thenReturn("deep@gmail.com");

        // Execute the servlet's doPost method
        verificationServlet.doPostTest(request, response);

        // Verify the correct redirection for successful verification
        verify(response).sendRedirect("verification.jsp?regSuccess=true");
    }

    @Test
    public void testDoPostVerificationFailure() throws Exception {
        // Set up the mock request with incorrect verification code
        when(request.getParameter("vcode")).thenReturn("654321");
        when(request.getParameter("email")).thenReturn("test@example.com");

        // Execute the servlet's doPost method
        verificationServlet.doPostTest(request, response);

        // Verify the correct redirection for failed verification
        verify(response).sendRedirect("verification.jsp?verificationFailed=true");
    }
}
