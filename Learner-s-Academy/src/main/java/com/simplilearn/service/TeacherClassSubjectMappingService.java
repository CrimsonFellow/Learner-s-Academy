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

import com.simplilearn.model.SchoolClass;
import com.simplilearn.model.Subject;
import com.simplilearn.model.Teacher;
import com.simplilearn.model.TeacherClassSubjectMapping;

public class TeacherClassSubjectMappingService {
    private DataSource dataSource;

    public TeacherClassSubjectMappingService() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/LearnerAcademyDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void addMapping(TeacherClassSubjectMapping mapping) {
        assignTeacherToClassForSubject(mapping);
    }

    public void assignTeacherToClassForSubject(TeacherClassSubjectMapping mapping) {
        String sql = "INSERT INTO teacher_class_subject_mapping (teacher_id, class_id, subject_id) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mapping.getTeacherId());
            stmt.setInt(2, mapping.getClassId());
            stmt.setInt(3, mapping.getSubjectId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TeacherClassSubjectMapping> getAllMappings() {
        List<TeacherClassSubjectMapping> mappings = new ArrayList<>();
        String sql = "SELECT mapping.id, class.class_name, subject.subject_name, teacher.teacher_name " +
                     "FROM teacher_class_subject_mapping AS mapping " +
                     "JOIN teachers AS teacher ON mapping.teacher_id = teacher.teacher_id " +
                     "JOIN classes AS class ON mapping.class_id = class.class_id " +
                     "JOIN subjects AS subject ON mapping.subject_id = subject.subject_id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TeacherClassSubjectMapping mapping = new TeacherClassSubjectMapping();
                mapping.setId(rs.getInt("id"));
                mapping.setClassName(rs.getString("class_name"));
                mapping.setSubjectName(rs.getString("subject_name"));
                mapping.setTeacherName(rs.getString("teacher_name"));
                mappings.add(mapping);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mappings;
    }



    public void updateMapping(TeacherClassSubjectMapping mapping) {
        String sql = "UPDATE teacher_class_subject_mapping SET teacher_id = ?, class_id = ?, subject_id = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mapping.getTeacherId());
            stmt.setInt(2, mapping.getClassId());
            stmt.setInt(3, mapping.getSubjectId());
            stmt.setInt(4, mapping.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMapping(int id) {
        String sql = "DELETE FROM teacher_class_subject_mapping WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                teachers.add(new Teacher(rs.getInt("teacher_id"), rs.getString("teacher_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

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
}

