<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.taskmanagement.model.Task" %>
<%
    Task task = (Task) request.getAttribute("task");
%>
<html>
<head>
    <title>Edit Task</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background: #f0f4f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .edit-card {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 500px;
        }

        .edit-card h1 {
            margin-bottom: 20px;
            font-size: 24px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 500;
        }

        input[type="text"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 14px;
        }

        button {
            background-color: #4ADEDE;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #2dd4bf;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #333;
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="edit-card">
    <h1>Edit Task</h1>
    <form method="post" action="edittask" accept-charset="UTF-8">
        <input type="hidden" name="id" value="<%= task.getId() %>" />

        <div class="form-group">
            <label>Title:</label>
            <input type="text" name="title" value="<%= task.getTitle() %>" required />
        </div>

        <div class="form-group">
            <label>Description:</label>
            <input type="text" name="description" value="<%= task.getDescription() %>" required />
        </div>

        <div class="form-group">
            <label>Due Date:</label>
            <input type="date" name="dueDate" value="<%= task.getDueDate() %>" required />
        </div>

        <div class="form-group">
            <label>Status:</label>
            <select name="status">
                <option value="Pending" <%= "Pending".equals(task.getStatus()) ? "selected" : "" %>>Pending</option>
                <option value="In Progress" <%= "In Progress".equals(task.getStatus()) ? "selected" : "" %>>In Progress</option>
                <option value="Completed" <%= "Completed".equals(task.getStatus()) ? "selected" : "" %>>Completed</option>
            </select>
        </div>

        <button type="submit">Update</button>
    </form>

    <a href="tasks" class="back-link">⬅ To your Task List</a>
</div>

</body>
</html>
