package com.simplilearn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.model.Student;
import com.simplilearn.service.StudentService;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentService studentService;

    public void init() {
        studentService = new StudentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = studentService.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action != null) {
            switch (action) {
                case "add":
                    addStudent(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                case "edit":
                    editStudent(request, response);
                    break;
                default:
                    // Handle unknown action
                    break;
            }
        }
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        int classId = Integer.parseInt(request.getParameter("classId"));
        Student newStudent = new Student(name, classId);
        studentService.addStudent(newStudent);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        studentService.deleteStudent(studentId);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int classId = Integer.parseInt(request.getParameter("classId"));
        Student updatedStudent = new Student(id, name, classId);        
        studentService.updateStudent(updatedStudent);
        response.sendRedirect(request.getContextPath() + "/students");
    }
}

