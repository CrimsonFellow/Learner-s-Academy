<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Teacher-Class Mappings</title>
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
            width: 80%;
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
            <li><a href="assign-subject-class">Assign Subjects to Classes</a></li>
        </ul>
    </div>
    
    <div class="main">
        <h1>Manage Teacher-Class Mappings</h1>
        
        <h2>Add New Mapping</h2>
        <form action="teacher-class-mapping" method="post">
            <input type="hidden" name="action" value="add">
            <label for="teacherId">Select a Teacher:</label>
            <select id="teacherId" name="teacherId">
                <c:forEach var="teacher" items="${teachers}">
                    <option value="${teacher.id}">${teacher.name}</option>
                </c:forEach>
            </select>
            <br>

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
            
            <button type="submit">Add Mapping</button>
        </form>
        
        <h2>Existing Mappings</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Teacher</th>
                    <th>Class</th>
                    <th>Subject</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mapping" items="${mappings}">
                    <tr>
                        <td>${mapping.id}</td>
                        <td>${mapping.teacherName}</td>
                        <td>${mapping.className}</td>
                        <td>${mapping.subjectName}</td>
                        <td>
                            <button onclick="deleteMapping(${mapping.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script>
        function deleteMapping(mappingId) {
            // Send a confirmation dialog before deleting
            if (confirm('Are you sure you want to delete this mapping?')) {
                // Create a form element to submit the delete request
                var form = document.createElement('form');
                form.setAttribute('method', 'post');
                form.setAttribute('action', 'teacher-class-mapping');
                form.innerHTML = '<input type="hidden" name="mappingId" value="' + mappingId + '">' +
                                 '<input type="hidden" name="action" value="delete">';
                document.body.appendChild(form);
                form.submit(); // Submit the form to trigger the delete action
            }
        }
    </script>
</body>
</html>
