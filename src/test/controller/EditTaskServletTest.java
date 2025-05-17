package controller;

import com.taskmanagement.controller.EditTaskServlet;
import com.taskmanagement.dao.TaskDAO;
import com.taskmanagement.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EditTaskServletTest {

    private EditTaskServlet servlet;

    @Mock
    private TaskDAO mockTaskDAO;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new EditTaskServlet();

        // Inject mock DAO
        java.lang.reflect.Field field = EditTaskServlet.class.getDeclaredField("taskDAO");
        field.setAccessible(true);
        field.set(servlet, mockTaskDAO);
    }

    @Test
    void testDoGet_fetchesTaskAndForwardsToJSP() throws Exception {
        // Arrange
        Task task = new Task(1, "Test", "desc", LocalDate.now(), "Pending");
        when(request.getParameter("id")).thenReturn("1");
        when(mockTaskDAO.getTask(1)).thenReturn(task);
        when(request.getRequestDispatcher("edittask.jsp")).thenReturn(dispatcher);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(mockTaskDAO).getTask(1);
        verify(request).setAttribute("task", task);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_updatesTaskAndRedirects() throws Exception {
        // Arrange
        when(request.getParameter("id")).thenReturn("2");
        when(request.getParameter("title")).thenReturn("Updated Title");
        when(request.getParameter("description")).thenReturn("Updated Description");
        when(request.getParameter("dueDate")).thenReturn("2025-06-01");
        when(request.getParameter("status")).thenReturn("Completed");

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(mockTaskDAO).updateTask(
                2,
                "Updated Title",
                "Updated Description",
                LocalDate.parse("2025-06-01"),
                "Completed"
        );
        verify(response).sendRedirect("tasks");
    }

    @Test
    void testDoGet_invalidId_throwsException() {
        when(request.getParameter("id")).thenReturn("abc");

        assertThrows(NumberFormatException.class, () -> {
            servlet.doGet(request, response);
        });
    }

    @Test
    void testDoPost_invalidDueDate_throwsException() {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("title")).thenReturn("Sample");
        when(request.getParameter("description")).thenReturn("Desc");
        when(request.getParameter("dueDate")).thenReturn("invalid-date");
        when(request.getParameter("status")).thenReturn("Pending");

        assertThrows(Exception.class, () -> {
            servlet.doPost(request, response);
        });
    }
}
