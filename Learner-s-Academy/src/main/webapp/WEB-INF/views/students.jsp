<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Management</title>
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
            <li><a href="teachers">Manage Teachers</a></li>
            <li><a href="assign-subject-class">Assign Subjects to Classes</a></li>
            <li><a href="teacher-class-mapping">Manage Teacher-Class Mapping</a></li>
        </ul>
    </div>
    
    <div class="main">
        <h1>Student Management</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Class ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.classId}</td>
                        <td>
                            <button onclick="editStudent(${student.id}, '${student.name}', ${student.classId})">Edit</button>
                            <button onclick="deleteStudent(${student.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h2>Add New Student</h2>
        <form action="students" method="post">
            <label for="studentName">Student Name:</label>
            <input type="text" id="studentName" name="name" required>
            <label for="classId">Class ID:</label>
            <input type="number" id="classId" name="classId" required>
            <input type="hidden" name="action" value="add"> 
            <button type="submit">Add Student</button>
        </form>
        
        <form id="editStudentForm" action="students" method="post" style="display:none;">
            <div style="margin-top: 10px;"> 
                <label for="editStudentName">New Student Name:</label>
                <input type="text" id="editStudentName" name="name" required>
                <label for="editClassId">New Class ID:</label>
                <input type="number" id="editClassId" name="classId" required>
                <input type="hidden" id="editStudentId" name="id">
                <input type="hidden" name="action" value="edit"> 
                <button type="submit">Save Changes</button>
            </div>
        </form>
    </div>
    
    <script>
        function editStudent(studentId, studentName, classId) {
            // Populate the form with student details
            document.getElementById('editStudentForm').style.display = 'block';
            document.getElementById('editStudentId').value = studentId;
            document.getElementById('editStudentName').value = studentName;
            document.getElementById('editClassId').value = classId;
        }

        function deleteStudent(studentId) {
            // Send a confirmation dialog before deleting
            if (confirm('Are you sure you want to delete this student?')) {
                // Create a form element to submit the delete request
                var form = document.createElement('form');
                form.setAttribute('method', 'post');
                form.setAttribute('action', 'students');
                form.innerHTML = '<input type="hidden" name="id" value="' + studentId + '">' +
                                 '<input type="hidden" name="action" value="delete">';
                document.body.appendChild(form);
                form.submit(); // Submit the form to trigger the delete action
            }
        }
    </script>
</body>
</html>
