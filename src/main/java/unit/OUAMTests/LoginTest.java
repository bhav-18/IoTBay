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

import registration.Login;

public class LoginTest {
	
    private LoginServletUnderTest loginServlet;
    
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
        loginServlet = new LoginServletUnderTest();
    }
    
    // Subclass to expose protected doPost method
    private class LoginServletUnderTest extends Login {
		public void doPostTest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
            super.doPost(req, resp);
        }
    }
    @Test
    public void testDoPostSuccess() throws Exception {
        // Set up the mock request
        when(request.getParameter("email")).thenReturn("deep@gmail.com");
        when(request.getParameter("password")).thenReturn("Testing1.");
        when(request.getSession()).thenReturn(session);

        // Mock the response writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute the servlet's doPost method
        loginServlet.doPostTest(request, response);
        
        // Verify the correct redirection
        verify(response).sendRedirect("landing.jsp");
        verify(session).setAttribute(eq("name"), anyString());
    }
    
    @Test
    public void testDoPostFailure() throws Exception {
        // Set up the mock request for failure
        when(request.getParameter("email")).thenReturn("wrong@example.com");
        when(request.getParameter("password")).thenReturn("wrongpassword");
        when(request.getSession()).thenReturn(session);

        // Mock the response writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute the servlet's doPost method
        loginServlet.doPostTest(request, response);
        
        // Verify the correct redirection
        verify(response).sendRedirect("index.jsp?loginFailed=true");
    }

    @Test
    public void testDoPostMissingEmail() throws Exception {
        // Set up the mock request for missing email
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getSession()).thenReturn(session);

        // Mock the response writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute the servlet's doPost method
        loginServlet.doPostTest(request, response);
        
        // Verify the correct redirection
        verify(response).sendRedirect("index.jsp?loginFailed=true");
    }

    @Test
    public void testDoPostMissingPassword() throws Exception {
        // Set up the mock request for missing password
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("");
        when(request.getSession()).thenReturn(session);

        // Mock the response writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute the servlet's doPost method
        loginServlet.doPostTest(request, response);
        
        // Verify the correct redirection
        verify(response).sendRedirect("index.jsp?loginFailed=true");
    }
}
