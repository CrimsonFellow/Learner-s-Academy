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

import com.simplilearn.model.Teacher;

public class TeacherService {

    private DataSource dataSource;

    public TeacherService() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/LearnerAcademyDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new teacher
    public void addTeacher(Teacher teacher) {
        String sql = "INSERT INTO teachers (teacher_name) VALUES (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacher.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve a teacher by ID
    public Teacher getTeacher(int id) {
        String sql = "SELECT * FROM teachers WHERE teacher_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Teacher(rs.getInt("teacher_id"), rs.getString("teacher_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    // Method to update an existing teacher
    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE teachers SET teacher_name = ? WHERE teacher_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacher.getName());
            stmt.setInt(2, teacher.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a teacher by ID
    public void deleteTeacher(int id) {
        String sql = "DELETE FROM teachers WHERE teacher_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all teachers
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
}