package model;

import com.taskmanagement.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testConstructorAndGetters() {
        LocalDate dueDate = LocalDate.of(2025, 5, 20);
        Task task = new Task(1, "Title", "Description", dueDate, "Pending");

        assertEquals(1, task.getId());
        assertEquals("Title", task.getTitle());
        assertEquals("Description", task.getDescription());
        assertEquals(dueDate, task.getDueDate());
        assertEquals("Pending", task.getStatus());
    }

    @Test
    void testSettersAndGetters() {
        Task task = new Task();

        task.setId(2);
        task.setTitle("New Title");
        task.setDescription("New Description");
        LocalDate newDueDate = LocalDate.of(2026, 1, 15);
        task.setDueDate(newDueDate);
        task.setStatus("Completed");

        assertEquals(2, task.getId());
        assertEquals("New Title", task.getTitle());
        assertEquals("New Description", task.getDescription());
        assertEquals(newDueDate, task.getDueDate());
        assertEquals("Completed", task.getStatus());
    }
}
