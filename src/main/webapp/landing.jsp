<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome | Task Management System</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        body.landing-page {
            margin: 0;
            padding: 0;
            font-family: 'Inter', sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(-45deg, #ff6ec4, #7873f5, #4ADEDE, #2dd4bf);
            background-size: 400% 400%;
            animation: gradientMove 15s ease infinite;
        }

        @keyframes gradientMove {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .landing-container {
            background: rgba(255, 255, 255, 0.95);
            padding: 60px;
            border-radius: 20px;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
            text-align: center;
            max-width: 500px;
            width: 90%;
            animation: fadeInUp 1.2s ease;
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .logo {
            width: 160px;
            margin-bottom: 20px;
        }

        h1 {
            font-size: 2.8rem;
            font-weight: 800;
            margin: 0 0 10px;
            color: #222;
        }

        p {
            font-size: 1.1rem;
            color: #555;
            margin-bottom: 30px;
        }

        .btn-primary {
            background: linear-gradient(90deg, #4ADEDE, #7873f5);
            border: none;
            padding: 14px 32px;
            font-size: 1rem;
            color: white;
            border-radius: 50px;
            text-decoration: none;
            font-weight: 600;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            display: inline-block;
        }

        .btn-primary:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body class="landing-page">
    <div class="landing-container">
        <img src="NovaTech!.png" class="logo" alt="Task Management Logo" />
        <h1>NovaTech!</h1>
        <p>Simplify Task Management. Empower Your Workflow.</p>
        <a href="tasks" class="btn-primary">Your Tasks</a>
    </div>
</body>
</html>
