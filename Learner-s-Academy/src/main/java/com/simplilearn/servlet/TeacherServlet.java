package com.simplilearn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.model.Teacher;
import com.simplilearn.service.TeacherService;

@WebServlet("/teachers")
public class TeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeacherService teacherService;

    public void init() {
        teacherService = new TeacherService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Teacher> teachers = teacherService.getAllTeachers();
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("/WEB-INF/views/teachers.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addTeacher(request, response);
                    break;
                case "edit":
                    editTeacher(request, response);
                    break;
                case "delete":
                    deleteTeacher(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/teachers");
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/teachers");
        }
    }

    private void addTeacher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        Teacher newTeacher = new Teacher(name);
        teacherService.addTeacher(newTeacher);
        response.sendRedirect(request.getContextPath() + "/teachers");
    }

    private void editTeacher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Teacher updatedTeacher = new Teacher(id, name);
        teacherService.updateTeacher(updatedTeacher);
        response.sendRedirect(request.getContextPath() + "/teachers");
    }

    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        teacherService.deleteTeacher(id);
        response.sendRedirect(request.getContextPath() + "/teachers");
    }
}
