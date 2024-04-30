<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Management</title>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
            text-align: center;
        }
        
        h1 {
            color: #333;
        }
        
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }
        
        th {
            background-color: #f0f0f0;
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
        
        form {
            display: inline-block;
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
            <li><a href="classes">Manage Classes</a></li>
            <li><a href="students">Manage Students</a></li>
            <li><a href="assign-subject-class">Assign Subjects to Classes</a></li>
            <li><a href="teacher-class-mapping">Manage Teacher-Class Mapping</a></li>
        </ul>
    </div>
    
    <div class="main">
        <h1>Teacher Management</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Teacher ID</th>
                    <th>Teacher Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="teacher" items="${teachers}">
                    <tr>
                        <td>${teacher.id}</td>
                        <td>${teacher.name}</td>
                        <td>
                            <button onclick="editTeacher(${teacher.id}, '${teacher.name}')">Edit</button>
                            <button onclick="deleteTeacher(${teacher.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h2>Add New Teacher</h2>
        <form action="teachers" method="post">
            <label for="teacherName">Teacher Name:</label>
            <input type="text" id="teacherName" name="name" required>
            <input type="hidden" name="action" value="add"> 
            <button type="submit">Add Teacher</button>
        </form>
        
        <form id="editTeacherForm" action="teachers" method="post" style="display:none;">
            <div style="margin-top: 10px;"> 
                <label for="editTeacherName">New Teacher Name:</label>
                <input type="text" id="editTeacherName" name="name" required>
                <input type="hidden" id="editTeacherId" name="id">
                <input type="hidden" name="action" value="edit">
                <button type="submit">Save Changes</button>
            </div>
        </form>
    </div>
    
    <script>
        function editTeacher(teacherId, teacherName) {
            // Populate the form with teacher details
            document.getElementById('editTeacherForm').style.display = 'block';
            document.getElementById('editTeacherId').value = teacherId;
            document.getElementById('editTeacherName').value = teacherName;
        }

        function deleteTeacher(teacherId) {
            // Send a confirmation dialog before deleting
            if (confirm('Are you sure you want to delete this teacher?')) {
                // Create a form element to submit the delete request
                var form = document.createElement('form');
                form.setAttribute('method', 'post');
                form.setAttribute('action', 'teachers');
                form.innerHTML = '<input type="hidden" name="id" value="' + teacherId + '">' +
                                 '<input type="hidden" name="action" value="delete">';
                document.body.appendChild(form);
                form.submit(); // Submit the form to trigger the delete action
            }
        }
    </script>
</body>
</html>
