package controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.taskmanagement.controller.TaskServlet;
import com.taskmanagement.dao.TaskDAO;
import com.taskmanagement.model.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

class TaskServletTest {

    @InjectMocks
    private TaskServlet servlet;

    @Mock
    private TaskDAO mockTaskDAO;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new TaskServlet();
        servlet.setTaskDAO(mockTaskDAO);  // inject mock DAO
    }

   
    @Test
    void doPost_shouldAddTaskAndRedirect() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("title")).thenReturn("New Task");
        when(request.getParameter("description")).thenReturn("Description");
        when(request.getParameter("dueDate")).thenReturn("2025-05-17");
        when(request.getParameter("status")).thenReturn("Pending");

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(mockTaskDAO).addTask(eq("New Task"), eq("Description"), eq(LocalDate.parse("2025-05-17")), eq("Pending"));
        verify(response).sendRedirect("tasks");
    }
}
