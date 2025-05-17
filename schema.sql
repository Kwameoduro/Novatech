CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    status VARCHAR(50) CHECK (status IN ('Pending', 'In Progress', 'Completed'))
);

select * from tasks;
