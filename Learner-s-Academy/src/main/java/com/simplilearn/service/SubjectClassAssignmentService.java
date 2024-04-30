package com.simplilearn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.simplilearn.model.Assignment;
import com.simplilearn.model.SchoolClass;
import com.simplilearn.model.Subject;

public class SubjectClassAssignmentService {
    private DataSource dataSource;

    public SubjectClassAssignmentService() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/LearnerAcademyDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void assignSubjectToClass(int subjectId, int classId) {
        String sql = "INSERT INTO subject_class_mapping (subject_id, class_id) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subjectId);
            stmt.setInt(2, classId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to retrieve all classes
    public List<SchoolClass> getAllClasses() {
        List<SchoolClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM classes";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                classes.add(new SchoolClass(rs.getInt("class_id"), rs.getString("class_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    // Method to retrieve all subjects
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subjects";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                subjects.add(new Subject(rs.getInt("subject_id"), rs.getString("subject_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }
    
    // Method to retrieve all assignments of subjects to classes
    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT c.class_name, s.subject_name FROM subject_class_mapping scm "
                   + "INNER JOIN classes c ON scm.class_id = c.class_id "
                   + "INNER JOIN subjects s ON scm.subject_id = s.subject_id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String className = rs.getString("class_name");
                String subjectName = rs.getString("subject_name");
                assignments.add(new Assignment(className, subjectName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
}
