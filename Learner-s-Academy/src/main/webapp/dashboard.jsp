<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
            text-align: center;
        }
        
        h2 {
            color: #333;
        }
        
        ul {
            list-style-type: none;
            padding: 0;
            margin: 20px 0;
        }
        
        li {
            display: inline-block;
            margin-right: 20px;
        }
        
        li a {
            text-decoration: none;
            color: #333;
            padding: 10px 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            transition: background-color 0.3s;
        }
        
        li a:hover {
            background-color: #f0f0f0;
        }
        
        form {
            display: inline-block;
        }
        
        input[type="submit"] {
            padding: 10px 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Welcome to the Dashboard</h2>
    
    <ul>
        <li><a href="subjects">Manage Subjects</a></li>
        <li><a href="teachers">Manage Teachers</a></li>
        <li><a href="classes">Manage Classes</a></li>
        <li><a href="students">Manage Students</a></li>
        <li><a href="assign-subject-class">Assign Subjects to Classes</a></li>
        <li><a href="teacher-class-mapping">Manage Teacher-Class Mapping</a></li>
    </ul>
    
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
