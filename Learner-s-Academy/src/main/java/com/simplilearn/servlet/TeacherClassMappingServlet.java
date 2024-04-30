package com.simplilearn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.model.SchoolClass;
import com.simplilearn.model.Subject;
import com.simplilearn.model.Teacher;
import com.simplilearn.model.TeacherClassSubjectMapping;
import com.simplilearn.service.TeacherClassSubjectMappingService;

@WebServlet("/teacher-class-mapping")
public class TeacherClassMappingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeacherClassSubjectMappingService mappingService;

    public void init() {
        mappingService = new TeacherClassSubjectMappingService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 List<TeacherClassSubjectMapping> mappings = mappingService.getAllMappings();
         
         request.setAttribute("mappings", mappings);

         List<Teacher> teachers = mappingService.getAllTeachers();
         List<SchoolClass> classes = mappingService.getAllClasses();
         List<Subject> subjects = mappingService.getAllSubjects();

         request.setAttribute("teachers", teachers);
         request.setAttribute("classes", classes);
         request.setAttribute("subjects", subjects);
        
        // Forward request to the JSP
        request.getRequestDispatcher("/WEB-INF/views/teacher_class_mapping.jsp").forward(request, response);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "add":
                    addMapping(request, response);
                    break;
                case "delete":
                    deleteMapping(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
        }
    }

    private void addMapping(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from request
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int classId = Integer.parseInt(request.getParameter("classId"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        
        // Create a new mapping object
        TeacherClassSubjectMapping mapping = new TeacherClassSubjectMapping();
        mapping.setTeacherId(teacherId);
        mapping.setClassId(classId);
        mapping.setSubjectId(subjectId);
        
        // Add the new mapping using the service
        mappingService.addMapping(mapping);
        
        // Redirect back to the mappings page
        response.sendRedirect(request.getContextPath() + "/teacher-class-mapping");
    }
    
    private void deleteMapping(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the mapping ID from request
        int mappingId = Integer.parseInt(request.getParameter("mappingId"));
        
        // Delete the mapping using the service
        mappingService.deleteMapping(mappingId);
        
        // Redirect back to the mappings page
        response.sendRedirect(request.getContextPath() + "/teacher-class-mapping");
    }

}
