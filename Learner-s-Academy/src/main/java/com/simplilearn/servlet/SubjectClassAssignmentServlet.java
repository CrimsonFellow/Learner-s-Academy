package com.simplilearn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.model.Assignment;
import com.simplilearn.model.SchoolClass;
import com.simplilearn.model.Subject;
import com.simplilearn.service.SubjectClassAssignmentService;

@WebServlet("/assign-subject-class")
public class SubjectClassAssignmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SubjectClassAssignmentService assignmentService;

    public void init() {
        assignmentService = new SubjectClassAssignmentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Subject> subjects = assignmentService.getAllSubjects();
        List<SchoolClass> classes = assignmentService.getAllClasses();
        List<Assignment> assignments = assignmentService.getAllAssignments();
        
        request.setAttribute("subjects", subjects);
        request.setAttribute("classes", classes);
        request.setAttribute("assignments", assignments);
        
        request.getRequestDispatcher("/WEB-INF/views/assign_subject_class.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int classId = Integer.parseInt(request.getParameter("classId"));
        
        assignmentService.assignSubjectToClass(subjectId, classId);
        
        response.sendRedirect(request.getContextPath() + "/assign-subject-class");
    }
}
