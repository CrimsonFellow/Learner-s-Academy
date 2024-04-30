<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Assign Subjects to Classes</title>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
            text-align: center;
        }
        
        h1 {
            color: #333;
        }
        
        form {
            margin-top: 20px;
        }
        
        label {
            margin-right: 10px;
        }
        
        select {
            margin-bottom: 10px;
        }
        
        button {
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        button:hover {
            background-color: #45a049;
        }
        
        table {
            width: 50%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }
        
        th {
            background-color: #f0f0f0;
        }
        
       
        .sidenav {
            height: 100%;
            width: 200px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #333;
            padding-top: 20px;
        }
        
        .sidenav a {
            padding: 10px 8px 10px 16px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            display: block;
            transition: 0.3s;
        }
        
        .sidenav a:hover {
            background-color: #ddd;
            color: black;
        }
        
        .main {
            margin-left: 220px; 
            padding: 0px 10px;
        }
    </style>
</head>
<body>
  
    <div class="sidenav">
        <ul style="list-style-type:none; padding: 0;">
            <li><a href="dashboard.jsp">Dashboard</a></li>
            <li><a href="subjects">Manage Subjects</a></li>
            <li><a href="teachers">Manage Teachers</a></li>
            <li><a href="classes">Manage Classes</a></li>
            <li><a href="students">Manage Students</a></li>
            <li><a href="teacher-class-mapping">Manage Teacher-Class Mapping</a></li>
        </ul>
    </div>
    
 
    <div class="main">
        <h1>Assign Subjects to Classes</h1>
        
    
        <form action="assign-subject-class" method="post">
          
            <label for="classId">Select a Class:</label>
            <select id="classId" name="classId">
                <c:forEach var="schoolClass" items="${classes}">
                    <option value="${schoolClass.id}">${schoolClass.name}</option>
                </c:forEach>
            </select>
            <br>
                      
            <label for="subjectId">Select a Subject:</label>
            <select id="subjectId" name="subjectId">
                <c:forEach var="subject" items="${subjects}">
                    <option value="${subject.id}">${subject.name}</option>
                </c:forEach>
            </select>
            <br>
            
            <button type="submit">Assign Subject to Class</button>
        </form>
        
        <h2>Assignments</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Class</th>
                    <th>Subject</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="assignment" items="${assignments}">
                    <tr>
                        <td>${assignment.className}</td>
                        <td>${assignment.subjectName}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
