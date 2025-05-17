package controller;

import com.taskmanagement.controller.DeleteTaskServlet;
import com.taskmanagement.dao.TaskDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteTaskServletTest {

    private DeleteTaskServlet servlet;

    @Mock
    private TaskDAO mockTaskDAO;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new DeleteTaskServlet();

        // Inject mock DAO into private field
        java.lang.reflect.Field field = DeleteTaskServlet.class.getDeclaredField("taskDAO");
        ((java.lang.reflect.Field) field).setAccessible(true);
        ((java.lang.reflect.Field) field).set(servlet, mockTaskDAO);
    }

    @Test
    void testDoGet_validId_deletesTaskAndRedirects() throws Exception {
        // Arrange
        when(request.getParameter("id")).thenReturn("42");

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(mockTaskDAO).deleteTask(42);
        verify(response).sendRedirect("tasks");
    }

    @Test
    void testDoGet_missingId_throwsNumberFormatException() throws IOException {
        // Arrange
        when(request.getParameter("id")).thenReturn(null);

        // Assert
        assertThrows(NumberFormatException.class, () -> {
            servlet.doGet(request, response);
        });

        verify(mockTaskDAO, never()).deleteTask(anyInt());
        verify(response, never()).sendRedirect(anyString());
    }

    @Test
    void testDoGet_invalidIdFormat_throwsNumberFormatException() throws IOException {
        // Arrange
        when(request.getParameter("id")).thenReturn("abc");

        // Assert
        assertThrows(NumberFormatException.class, () -> {
            servlet.doGet(request, response);
        });

        verify(mockTaskDAO, never()).deleteTask(anyInt());
        verify(response, never()).sendRedirect(anyString());
    }

    @Test
    void testDoGet_daoThrowsRuntimeException_propagatesException() throws IOException {
        // Arrange
        when(request.getParameter("id")).thenReturn("7");
        doThrow(new RuntimeException("DB error")).when(mockTaskDAO).deleteTask(7);

        // Assert
        assertThrows(RuntimeException.class, () -> {
            servlet.doGet(request, response);
        });

        verify(mockTaskDAO).deleteTask(7);
        verify(response, never()).sendRedirect(anyString());
    }
}
