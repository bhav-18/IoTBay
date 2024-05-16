package unit.OUAMTests;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import registration.Logout;

public class LogoutTest {
    
    private LogoutServletUnderTest logoutServlet;
    
    @Mock
    private HttpServletRequest request;
    
    @Mock
    private HttpServletResponse response;
    
    @Mock
    private HttpSession session;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        logoutServlet = new LogoutServletUnderTest();
    }
    
    // Subclass to expose protected doGet method
    private class LogoutServletUnderTest extends Logout {
        public void doGetTest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
            super.doGet(req, resp);
        }
    }
    
    @Test
    public void testDoGet() throws Exception {
        // Set up the mock session
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("name")).thenReturn("test@example.com");

        // Execute the servlet's doGet method
        logoutServlet.doGetTest(request, response);
        
        // Verify the behavior of the servlet
        verify(session).invalidate();
        verify(response).sendRedirect("index.jsp");
    }
}
