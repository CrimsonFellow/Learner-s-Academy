package com.simplilearn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.model.SchoolClass;
import com.simplilearn.service.ClassService;

@WebServlet("/classes")
public class ClassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClassService classService;

    public void init() {
        classService = new ClassService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SchoolClass> classes = classService.getAllClasses();
        request.setAttribute("classes", classes);
        request.getRequestDispatcher("/WEB-INF/views/classes.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addClass(request, response);
                    break;
                case "update":
                    updateClass(request, response);
                    break;
                case "delete":
                    deleteClass(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/classes");
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/classes");
        }
    }

    private void addClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name"); 
        SchoolClass newClass = new SchoolClass(name);
        classService.addClass(newClass);
        response.sendRedirect(request.getContextPath() + "/classes");
    }

    private void updateClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        SchoolClass updatedClass = new SchoolClass(id, name);
        classService.updateClass(updatedClass);
        response.sendRedirect(request.getContextPath() + "/classes");
    }

    private void deleteClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        classService.deleteClass(id);
        response.sendRedirect(request.getContextPath() + "/classes");
    }
}
