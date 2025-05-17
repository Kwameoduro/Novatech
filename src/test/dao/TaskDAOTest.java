package dao;

import com.taskmanagement.dao.TaskDAO;
import com.taskmanagement.model.Task;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskDAOTest {

    private TaskDAO taskDAO;

    @BeforeEach
    void setUp() {
        taskDAO = new TaskDAO();
    }

    @AfterEach
    void tearDown() {
        // Clean up all tasks after each test for test isolation
        List<Task> tasks = TaskDAO.getAllTasks();
        for (Task t : tasks) {
            taskDAO.deleteTask(t.getId());
        }
    }

    @Test
    void testAddTaskAndGetAllTasks() {
        taskDAO.addTask("Test Task", "Test Description", LocalDate.of(2025, 5, 20), "Pending");

        List<Task> tasks = TaskDAO.getAllTasks();
        assertFalse(tasks.isEmpty());

        Task task = tasks.get(0);
        assertEquals("Test Task", task.getTitle());
        assertEquals("Test Description", task.getDescription());
        assertEquals(LocalDate.of(2025, 5, 20), task.getDueDate());
        assertEquals("Pending", task.getStatus());
    }


    @Test
    void testDeleteTask() {
        taskDAO.addTask("Delete Title", "Delete Desc", LocalDate.now(), "Pending");

        List<Task> tasks = TaskDAO.getAllTasks();
        assertFalse(tasks.isEmpty());

        Task lastAdded = tasks.get(tasks.size() - 1);

        taskDAO.deleteTask(lastAdded.getId());

        Task deleted = taskDAO.getTask(lastAdded.getId());
        assertNull(deleted);
    }
}
