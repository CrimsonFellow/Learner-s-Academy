package com.simplilearn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.model.Subject;
import com.simplilearn.service.SubjectService;

/**
 * Servlet implementation class SubjectServlet
 */
@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SubjectService subjectService;

    public void init() {
        subjectService = new SubjectService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Subject> subjects = subjectService.getAllSubjects();
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("/WEB-INF/views/subjects.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "add":
                    addSubject(request, response);
                    break;
                case "edit":
                    editSubject(request, response);
                    break;
                case "delete":
                    deleteSubject(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
        }
    }

    private void addSubject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            Subject newSubject = new Subject(name);
            subjectService.addSubject(newSubject);
            response.sendRedirect(request.getContextPath() + "/subjects");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Subject name parameter is missing or empty");
        }
    }

    private void editSubject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = request.getParameter("id");
        String name = request.getParameter("name");
        if (idString != null && !idString.isEmpty() && name != null && !name.isEmpty()) {
            int id = Integer.parseInt(idString);
            Subject updatedSubject = new Subject(id, name);
            subjectService.updateSubject(updatedSubject);
            response.sendRedirect(request.getContextPath() + "/subjects");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Subject ID or name parameter is missing or empty");
        }
    }

    private void deleteSubject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = request.getParameter("id");
        if (idString != null && !idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            subjectService.deleteSubject(id);
            response.sendRedirect(request.getContextPath() + "/subjects");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Subject ID parameter is missing or empty");
        }
    }
}
