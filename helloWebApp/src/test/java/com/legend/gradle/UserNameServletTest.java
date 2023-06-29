package com.legend.gradle;

import com.Legend.gradle.UserNameServlet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserNameServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private RequestDispatcher requestDispatcher;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void doGet() throws IOException {
        //Arrange
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        // mocking the method to return stub
        when(httpServletResponse.getWriter()).thenReturn(printWriter);

        //Act
        new UserNameServlet().doGet(httpServletRequest,httpServletResponse);

        //Act
        printWriter.flush();
        assertEquals("hello",stringWriter.toString());
    }

    @Test
    public void doPost() throws ServletException, IOException {
        //Arrange
        //mock the required methods
        when(httpServletRequest.getParameter("username")).thenReturn("Legend");
        when(httpServletRequest.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);

        //Act
        new UserNameServlet().doPost(httpServletRequest,httpServletResponse);

        //Assert
        verify(httpServletRequest).setAttribute("username","Legend");
        verify(requestDispatcher).forward(httpServletRequest,httpServletResponse);
    }

}
