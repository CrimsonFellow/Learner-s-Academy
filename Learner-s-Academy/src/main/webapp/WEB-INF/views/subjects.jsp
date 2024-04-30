<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Subject Management</title>
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
            <li><a href="teachers">Manage Teachers</a></li>
            <li><a href="classes">Manage Classes</a></li>
            <li><a href="students">Manage Students</a></li>
            <li><a href="assign-subject-class">Assign Subjects to Classes</a></li>
            <li><a href="teacher-class-mapping">Manage Teacher-Class Mapping</a></li>
        </ul>
    </div>
    
    <div class="main">
        <h1>Subject Management</h1>
       
        <table>
            <thead>
                <tr>
                    <th>Subject ID</th>
                    <th>Subject Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="subject" items="${subjects}">
                    <tr>
                        <td>${subject.id}</td>
                        <td>${subject.name}</td>
                        <td>
                            <button onclick="editSubject(${subject.id}, '${subject.name}')">Edit</button>
                            <button onclick="deleteSubject(${subject.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h2>Add New Subject</h2>
        <form action="subjects" method="post">
            <label for="subjectName">Subject Name:</label>
            <input type="text" id="subjectName" name="name" required>
            <input type="hidden" name="action" value="add"> 
            <button type="submit">Add Subject</button>
        </form>
        
        <form id="editSubjectForm" action="subjects" method="post" style="display:none;">
            <div style="margin-top: 10px;"> 
                <label for="editSubjectName">New Subject Name:</label>
                <input type="text" id="editSubjectName" name="name" required>
                <input type="hidden" id="editSubjectId" name="id">
                <input type="hidden" name="action" value="edit">
                <button type="submit">Save Changes</button>
            </div>
        </form>
    </div>
    
    <script>
        function editSubject(subjectId, subjectName) {
            // Populate the form with subject details
            document.getElementById('editSubjectForm').style.display = 'block';
            document.getElementById('editSubjectId').value = subjectId;
            document.getElementById('editSubjectName').value = subjectName;
        }

        function deleteSubject(subjectId) {
            // Send a confirmation dialog before deleting
            if (confirm('Are you sure you want to delete this subject?')) {
                // Create a form element to submit the delete request
                var form = document.createElement('form');
                form.setAttribute('method', 'post');
                form.setAttribute('action', 'subjects');
                form.innerHTML = '<input type="hidden" name="id" value="' + subjectId + '">' +
                                 '<input type="hidden" name="action" value="delete">';
                document.body.appendChild(form);
                form.submit(); // Submit the form to trigger the delete action
            }
        }
    </script>
</body>
</html>
